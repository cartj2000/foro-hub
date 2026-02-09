package com.alura.foro_hub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Curso, Long> {
//    Page<Curso> findAll(Pageable paginacion);
    Page<Curso> findById(Long IdCurso, Pageable paginacion);
}
