package com.alura.foro_hub.domain.topico;

import com.alura.foro_hub.domain.usuario.Usuario;

public record DatosDetalleUsuario(
        Long id,
        String login,
        String nombre
) {
    public DatosDetalleUsuario(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getNombre()
        );
    }

}
