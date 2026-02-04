package com.alura.foro_hub.domain.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Curso(DatosRegistroCurso datos) {
        this.nombre = datos.nombre();
    }
    public void actualizarInformaciones(@Valid DatosActualizacionCurso datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
    }
}