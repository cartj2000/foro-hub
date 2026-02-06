package com.alura.foro_hub.domain.topico.dto;


import com.alura.foro_hub.domain.topico.Curso;

public record DatosListaCurso(
    Long id,
    String nombre

    ) {
    public DatosListaCurso(Curso curso) {
        this(
                curso.getId(),
                curso.getNombre()
        );
    }
}