package com.fabio.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensajeAndActivoTrue(String titulo, String mensaje);

    boolean existsByTituloAndMensajeAndIdNotAndActivoTrue(String titulo, String mensaje, Long id);

    Page<Topico> findAllByActivoTrue(Pageable paginacion);
}
