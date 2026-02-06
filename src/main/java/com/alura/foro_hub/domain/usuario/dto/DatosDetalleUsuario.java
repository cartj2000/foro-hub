package com.alura.foro_hub.domain.usuario.dto;

import com.alura.foro_hub.domain.usuario.PerfilUsuario;
import com.alura.foro_hub.domain.usuario.Usuario;

public record DatosDetalleUsuario(
        Long id,
        String login,
        String nombre,
        PerfilUsuario perfil
) {
    public DatosDetalleUsuario(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getNombre(),
                usuario.getPerfil()
        );
    }

}
