package com.alura.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
        @NotBlank String login,
        @NotBlank String contrasena,
        @NotBlank String nombre
) {
}