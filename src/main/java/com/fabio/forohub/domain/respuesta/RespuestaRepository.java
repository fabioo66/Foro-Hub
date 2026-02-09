package com.fabio.forohub.domain.respuesta;

import com.fabio.forohub.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    Page<Respuesta> findByTopicoAndActivoTrue(Topico topico, Pageable paginacion);
    Page<Respuesta> findByAutorIdAndActivoTrue(Long autorId, Pageable paginacion);
    Page<Respuesta> findByTopicoAndAutorIdAndActivoTrue(Topico topico, Long autorId, Pageable paginacion);
}
