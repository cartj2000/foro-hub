package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.ValidacionException;
import com.alura.foro_hub.domain.topico.*;
import com.alura.foro_hub.domain.topico.dto.DatosActualizacionCurso;
import com.alura.foro_hub.domain.topico.dto.DatosDetalleCurso;
import com.alura.foro_hub.domain.topico.dto.DatosListaCurso;
import com.alura.foro_hub.domain.topico.dto.DatosRegistroCurso;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @Transactional // transacciones dentro de DB
    @PostMapping // atiende llamadas post
    //public void registrar(@RequestBody @Valid DatosRegistroCurso datos){
    // ResponseEntity: respondiendo con código HTTP
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriBuilder){

        //System.out.println(datos);
        //repository.save(new Curso(datos));
        var curso = new Curso(datos);
        repository.save(curso);
        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleCurso(curso));
    }

    @GetMapping
    //public Page<DatosListaCurso> listar(@PageableDefault(page = 0, size = 10, sort = { "nombre" }) Pageable paginacion) {
    public ResponseEntity<Page<DatosListaCurso>> listar(@PageableDefault(size=10,sort={ "nombre" }) Pageable paginacion) {
        //return repository.findAll(paginacion).map(DatosListaCurso::new);
        //
        var page = repository.findAll(paginacion).map(DatosListaCurso::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    //public void actualizar(@RequestBody @Valid DatosActualizacionCurso datos) {
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionCurso datos) {
        var optionalCurso = repository.findById(datos.id());
        //if (!repository.isPresent(datos.id())) {
        if (!optionalCurso.isPresent()) {
            throw new ValidacionException("Curso no existe");
        }
        //var curso = repository.getReferenceById(datos.id());
        var curso = optionalCurso.get();
        curso.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetalleCurso(curso));
    }

    @Transactional
    @DeleteMapping("/{id}")
    //public void eliminar(@PathVariable Long id){
    public ResponseEntity eliminar(@PathVariable Long id){
        var optionalCurso = repository.findById(id);
        //if (!repository.isPresent(id)) {
        if(!optionalCurso.isPresent()) {
            throw new ValidacionException("Curso no existe");
        }
        //repository.deleteById(id);
        repository.delete(optionalCurso.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    //public void detallar(@PathVariable  Long id){
    public ResponseEntity detallar(@PathVariable  Long id){
        //repository.findById(id);
        //var curso = repository.getReferenceById(id);
        var curso = repository.findById(id)
                .orElseThrow(() -> new ValidacionException("Curso no existe"));
        return ResponseEntity.ok(new DatosDetalleCurso(curso));
    }

}
