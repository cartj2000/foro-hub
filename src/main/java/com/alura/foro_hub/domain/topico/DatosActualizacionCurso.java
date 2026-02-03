package com.alura.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionCurso(
        @NotNull Long id,
        @NotBlank String nombre
) {
}
