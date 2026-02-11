package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.ValidacionException;
import com.alura.foro_hub.domain.topico.*;
import com.alura.foro_hub.domain.topico.dto.DatosActualizacionTopico;
import com.alura.foro_hub.domain.topico.dto.DatosDetalleTopico;
import com.alura.foro_hub.domain.topico.dto.DatosListaTopico;
import com.alura.foro_hub.domain.topico.dto.DatosRegistroTopico;
import com.alura.foro_hub.domain.usuario.UsuarioRepository;
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
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional // transacciones dentro de DB
    @PostMapping // atiende llamadas post
    //public void registrar(@RequestBody @Valid DatosRegistroTopico datos){
    // ResponseEntity: respondiendo con código HTTP
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriBuilder){
        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidacionException("Tópico duplicado");
        }

        var autor = usuarioRepository.findById(datos.idUsuario())
                .orElseThrow(() -> new ValidacionException("Usuario no existe"));

        var curso = cursoRepository.findById(datos.idCurso())
                .orElseThrow(() -> new ValidacionException("Curso no existe"));

        //System.out.println(datos);
        //repository.save(new Topico(datos));
        var topico = new Topico(datos, autor, curso);
        repository.save(topico);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    //public Page<DatosListaTopico> listar(@PageableDefault(page = 0, size = 10, sort = { "fechaCreacion" }) Pageable paginacion) {
    public ResponseEntity<Page<DatosListaTopico>> listar(@PageableDefault(size=10,sort={ "fechaCreacion" }) Pageable paginacion) {
        //return repository.findAllByStatus(paginacion).map(DatosListaTopico::new);
        //var page = repository.findAllByStatus(paginacion).map(DatosListaTopico::new);
        var page = repository.findAll(paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping("/{id}")
    //public void actualizar(@RequestBody @Valid DatosActualizacionTopico datos) {
    //public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionTopico datos) {
    public ResponseEntity<DatosDetalleTopico> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos) {
        //var optionalTopico = repository.findById(datos.id());
        var optionalTopico = repository.findById(id);
        //if (!repository.isPresent(datos.id())) {
        if (!optionalTopico.isPresent()) {
            throw new ValidacionException("Tópico no existe");
        }

        //var topico = repository.getReferenceById(datos.id());
        //var topico = repository.findById(id)
        //        .orElseThrow(() -> new ValidacionException("Topico no existe"));

        var topico = optionalTopico.get();

        // si cambian los datos validar duplicado:
        if (!topico.getTitulo().equals(datos.titulo()) ||
                !topico.getMensaje().equals(datos.mensaje())) {

            if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
                throw new ValidacionException("Tópico duplicado");
            }
        }
        topico.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    //public void eliminar(@PathVariable Long id){
    public ResponseEntity eliminar(@PathVariable Long id){
        var optionalTopico = repository.findById(id);
        //if (!repository.isPresent(id)) {
        if(!optionalTopico.isPresent()) {
            throw new ValidacionException("Tópico no existe");
        }
        //repository.delete(optionalTopico.get());
        repository.deleteById(id);
        //var topico = repository.getReferenceById(id);
        //topico.solucionar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    //public void detallar(@PathVariable  Long id){
    public ResponseEntity detallar(@PathVariable  Long id){
        //repository.getReferenceById(id);
        //var topico = repository.getReferenceById(id);
        var topico = repository.findById(id)
                .orElseThrow(() -> new ValidacionException("Topico no existe"));
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

}
