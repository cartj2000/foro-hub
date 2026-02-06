package com.alura.foro_hub.domain.usuario.dto;

import com.alura.foro_hub.domain.usuario.PerfilUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotBlank String login,
        @NotBlank String contrasena,
        @NotBlank String nombre,
        @NotNull PerfilUsuario perfil
) {
}