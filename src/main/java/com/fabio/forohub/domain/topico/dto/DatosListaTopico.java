package com.fabio.forohub.domain.topico.dto;

import com.fabio.forohub.domain.curso.dto.DatosDetalleCurso;
import com.fabio.forohub.domain.topico.Estado;
import com.fabio.forohub.domain.topico.Topico;
import com.fabio.forohub.domain.usuario.dto.DatosRespuestaUsuario;

import java.time.LocalDateTime;

public record DatosListaTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado estado,
        DatosRespuestaUsuario autor,
        DatosDetalleCurso curso
) {

    public DatosListaTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado(),
                new DatosRespuestaUsuario(topico.getAutor()),
                new DatosDetalleCurso(topico.getCurso())
        );
    }
}
