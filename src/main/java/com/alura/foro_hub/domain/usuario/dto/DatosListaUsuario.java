package com.alura.foro_hub.domain.usuario.dto;


import com.alura.foro_hub.domain.usuario.Usuario;

public record DatosListaUsuario(
    Long id,
    String login,
    String nombre,
    String perfil
    ) {
    public DatosListaUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getNombre(),
                usuario.getPerfil()
        );
    }
}