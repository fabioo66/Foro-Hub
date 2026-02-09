package com.fabio.forohub.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNombreAndActivoTrue(String nombre);
    Page<Curso> findByActivoTrue(Pageable paginacion);
    Page<Curso> findByCategoriaAndActivoTrue(Categoria categoria, Pageable paginacion);
}
