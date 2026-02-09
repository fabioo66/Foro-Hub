package com.fabio.forohub.domain.curso.dto;

import com.fabio.forohub.domain.curso.Categoria;
import com.fabio.forohub.domain.curso.Curso;

public record DatosDetalleCurso(
        Long id,
        String nombre,
        Categoria categoria
) {
    public DatosDetalleCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
