package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.ValidacionException;
import com.alura.foro_hub.domain.topico.Respuesta;
import com.alura.foro_hub.domain.topico.RespuestaRepository;
import com.alura.foro_hub.domain.topico.TopicoRepository;
import com.alura.foro_hub.domain.topico.dto.DatosActualizacionRespuesta;
import com.alura.foro_hub.domain.topico.dto.DatosDetalleRespuesta;
import com.alura.foro_hub.domain.topico.dto.DatosListaRespuesta;
import com.alura.foro_hub.domain.topico.dto.DatosRegistroRespuesta;
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
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional // transacciones dentro de DB
    @PostMapping // atiende llamadas post
    //public void registrar(@RequestBody @Valid DatosRegistroRespuesta datos){
    // ResponseEntity: respondiendo con código HTTP
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroRespuesta datos, UriComponentsBuilder uriBuilder){
        if (repository.existsByMensajeAndTopico_Id(datos.mensaje(),datos.idTopico())) {
            throw new ValidacionException("Respuesta duplicada");
        }

        var topico = topicoRepository.findById(datos.idTopico())
                .orElseThrow(() -> new ValidacionException("Topico no existe"));

        var usuario = usuarioRepository.findById(datos.idUsuario())
                .orElseThrow(() -> new ValidacionException("Usuario no existe"));

        //System.out.println(datos);
        //repository.save(new Respuesta(datos));
        var respuesta = new Respuesta(datos, topico, usuario);
        repository.save(respuesta);
        var uri = uriBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleRespuesta(respuesta));
    }

    @GetMapping
    //public Page<DatosListaRespuesta> listar(@PageableDefault(page = 0, size = 10, sort = { "fechaDeCreacion" }) Pageable paginacion) {
    public ResponseEntity<Page<DatosListaRespuesta>> listar(@PageableDefault(size=10,sort={ "fechaDeCreacion" }) Pageable paginacion) {
        //return repository.findAllByStatus(paginacion).map(DatosListaRespuesta::new);
        //var page = repository.findAllByStatus(paginacion).map(DatosListaRespuesta::new);
        var page = repository.findAll(paginacion).map(DatosListaRespuesta::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping("/{id}")
    //public void actualizar(@RequestBody @Valid DatosActualizacionRespuesta datos) {
    //public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionRespuesta datos) {
    public ResponseEntity<DatosDetalleRespuesta> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionRespuesta datos) {
        //var optionalRespuesta = repository.findById(datos.id());
        var optionalRespuesta = repository.findById(id);
        //if (!repository.isPresent(datos.id())) {
        if (!optionalRespuesta.isPresent()) {
            throw new ValidacionException("Respuesta no existe");
        }

        //var respuesta = repository.getReferenceById(datos.id());



        var respuesta = optionalRespuesta.get();

        // si cambian los datos validar duplicado:
        if (
                !respuesta.getMensaje().equals(datos.mensaje())) {

            if (repository.existsByMensaje(datos.mensaje())) {
                throw new ValidacionException("Respuesta duplicada");
            }
        }
        respuesta.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

    @Transactional
    @DeleteMapping("/{id}")
    //public void eliminar(@PathVariable Long id){
    public ResponseEntity eliminar(@PathVariable Long id){
        var optionalRespuesta = repository.findById(id);
        //if (!repository.isPresent(id)) {
        if(!optionalRespuesta.isPresent()) {
            throw new ValidacionException("Respuesta no existe");
        }
        //repository.delete(optionalRespuesta.get());
        repository.deleteById(id);
        //var respuesta = repository.getReferenceById(id);
        //respuesta.solucionar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    //public void detallar(@PathVariable  Long id){
    public ResponseEntity detallar(@PathVariable  Long id){
        //repository.getReferenceById(id);
        //var respuesta = repository.getReferenceById(id);
        var respuesta = repository.findById(id)
                .orElseThrow(() -> new ValidacionException("Respuesta no existe"));
        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

}
