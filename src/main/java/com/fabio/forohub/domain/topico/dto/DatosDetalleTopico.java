package com.fabio.forohub.domain.topico.dto;

import com.fabio.forohub.domain.respuesta.dto.DatosDetalleRespuesta;
import com.fabio.forohub.domain.topico.Estado;
import com.fabio.forohub.domain.topico.Topico;
import com.fabio.forohub.domain.usuario.dto.DatosRespuestaUsuario;

import java.time.LocalDateTime;
import java.util.List;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado estado,
        DatosRespuestaUsuario autor,
        String curso,
        List<DatosDetalleRespuesta> respuestas
) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado(),
                new DatosRespuestaUsuario(topico.getAutor()),
                topico.getCurso(),
                topico.getRespuestas().stream()
                        .filter(respuesta -> respuesta.isActivo())
                        .map(DatosDetalleRespuesta::new)
                        .toList()
        );
    }
}
