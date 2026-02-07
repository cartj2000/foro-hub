package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.usuario.Usuario;
import com.alura.foro_hub.domain.usuario.UsuarioRepository;
import com.alura.foro_hub.domain.usuario.dto.DatosActualizacionUsuario;
import com.alura.foro_hub.domain.usuario.dto.DatosDetalleUsuario;
import com.alura.foro_hub.domain.usuario.dto.DatosListaUsuario;
import com.alura.foro_hub.domain.usuario.dto.DatosRegistroUsuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional // transacciones dentro de DB
    @PostMapping // atiende llamadas post
    //public void registrar(@RequestBody @Valid DatosRegistroUsuario datos){
    // ResponseEntity: respondiendo con código HTTP
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriBuilder){

        //System.out.println(datos);
        //repository.save(new Usuario(datos));
        var usuario = new Usuario(datos);
        //febrero 6 2026
        usuario.setContrasena(passwordEncoder.encode(datos.contrasena()));
        repository.save(usuario);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }

    @GetMapping
    //public Page<DatosListaUsuario> listar(@PageableDefault(page = 0, size = 10, sort = { "nombre" }) Pageable paginacion) {
    public ResponseEntity<Page<DatosListaUsuario>> listar(@PageableDefault(size=10,sort={ "nombre" }) Pageable paginacion) {
        //return repository.findAll(paginacion).map(DatosListaUsuario::new);
        var page = repository.findAll(paginacion).map(DatosListaUsuario::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    //public void actualizar(@RequestBody @Valid DatosActualizacionUsuario datos) {
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionUsuario datos) {
        var usuario = repository.getReferenceById(datos.id());
        usuario.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetalleUsuario(usuario));
    }

    @Transactional
    @DeleteMapping("/{id}")
    //public void eliminar(@PathVariable Long id){
    public ResponseEntity eliminar(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    //public void detallar(@PathVariable  Long id){
    public ResponseEntity detallar(@PathVariable  Long id){
        //repository.findById(id);
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleUsuario(usuario));
    }

}
