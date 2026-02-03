package com.alura.foro_hub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
//    Page<Topico> findAll(Pageable paginacion);
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    Page<Topico> findAllByStatus(StatusTopico status, Pageable paginacion);

    @Query("""
            select t.status
            from Topico t
            where
            t.status = 'ABIERTO' and
            t.id = :idTopico
            """)
    boolean IsTopicoAbierto(Long idTopico);
}
