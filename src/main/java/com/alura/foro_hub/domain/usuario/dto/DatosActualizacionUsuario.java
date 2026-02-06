package com.alura.foro_hub.domain.usuario.dto;

import com.alura.foro_hub.domain.usuario.PerfilUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionUsuario(
        @NotNull Long id,
        @NotBlank String nombre,
        @NotNull PerfilUsuario perfil
) {
}
