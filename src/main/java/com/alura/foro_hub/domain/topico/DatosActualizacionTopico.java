package com.alura.foro_hub.domain.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(
        @NotNull Long id,
        @NotBlank String titulo,
        @NotBlank String mensaje
) {
}
