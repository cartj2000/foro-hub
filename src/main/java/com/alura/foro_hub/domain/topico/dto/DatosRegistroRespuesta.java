package com.alura.foro_hub.domain.topico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        @NotBlank String mensaje,
        @NotNull Long idTopico,
        @NotNull Long idUsuario
) {
}