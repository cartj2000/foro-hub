package com.alura.foro_hub.domain.topico;

public record DatosDetalleCurso(
        Long id,
        String nombre
) {
    public DatosDetalleCurso(Curso curso){
        this(
                curso.getId(),
                curso.getNombre()
        );
    }

}
