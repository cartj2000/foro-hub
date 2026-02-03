package com.alura.foro_hub.domain.topico;


import com.alura.foro_hub.domain.usuario.Usuario;

public record DatosListaUsuario(
    Long id,
    String login,
    String contrasena

    ) {
    public DatosListaUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getContrasena()
        );
    }
}