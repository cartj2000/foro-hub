package com.alura.foro_hub.domain.topico.dto;

import com.alura.foro_hub.domain.topico.StatusRespuesta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionRespuesta(
        @NotNull Long id,
        @NotBlank String mensaje,
        @NotNull StatusRespuesta status
) {
}
