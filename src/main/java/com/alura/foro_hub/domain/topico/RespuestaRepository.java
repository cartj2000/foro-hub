package com.alura.foro_hub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
//    Page<Respuesta> findAll(Pageable paginacion);
    boolean existsByMensajeAndTopico_Id(String mensaje,Long idTopico);
    boolean existsByMensaje(String mensaje);
    Page<Respuesta> findAllByStatus(StatusRespuesta status, Pageable paginacion);

    @Query("""
            select count(r) > 0
            from Respuesta r where r.status = 'PROCESADA' and r.id = :idRespuesta
            """)
    boolean isRespuestaProcesada(Long idRespuesta);

    @Query("""
           select case when count(r) > 0 then true else false end
           from Respuesta r
           where r.id = :id
           """)
    boolean isPresent(Long id);

}
