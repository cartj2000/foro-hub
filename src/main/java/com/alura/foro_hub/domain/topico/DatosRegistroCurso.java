package com.alura.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(
        @NotBlank String nombre
) {
}