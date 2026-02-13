package com.alura.foro_hub.domain.topico.dto;

import com.alura.foro_hub.domain.topico.StatusTopico;
import com.alura.foro_hub.domain.topico.Topico;

import java.time.LocalDateTime;

public record DatosListaTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaDeCreacion,
    StatusTopico status,
    String autor,
    String curso

    ) {
    public DatosListaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()

        );
    }
}