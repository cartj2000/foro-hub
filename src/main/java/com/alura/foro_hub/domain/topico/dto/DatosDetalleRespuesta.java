package com.alura.foro_hub.domain.topico.dto;

import com.alura.foro_hub.domain.topico.StatusRespuesta;
import com.alura.foro_hub.domain.topico.Respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        String topico,
        LocalDateTime fechaCreacion,
        StatusRespuesta status,
        String usuario
) {
    public DatosDetalleRespuesta(Respuesta respuesta){
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getStatus(),
                respuesta.getUsuario().getNombre()
                );
    }

}
