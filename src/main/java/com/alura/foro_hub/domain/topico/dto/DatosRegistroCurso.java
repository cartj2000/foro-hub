package com.alura.foro_hub.domain.topico.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(
        @NotBlank String nombre
) {
}