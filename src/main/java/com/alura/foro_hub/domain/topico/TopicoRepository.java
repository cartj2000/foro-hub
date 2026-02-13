package com.alura.foro_hub.domain.topico;

import com.alura.foro_hub.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
//    Page<Topico> findAll(Pageable paginacion);
Page<Topico> findById(Long IdTopico, Pageable paginacion);
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    Page<Topico> findAllByStatus(StatusTopico status, Pageable paginacion);

    @Query("""
            select count(t) > 0
            from Topico t where t.status = 'ACEPTADO' and t.id = :idTopico
            """)
    boolean isTopicoAceptado(Long idTopico);

    @Query("""
           select case when count(t) > 0 then true else false end
           from Topico t
           where t.id = :id
           """)
    boolean isPresent(Long id);

}
