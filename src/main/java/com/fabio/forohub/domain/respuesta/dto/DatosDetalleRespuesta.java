package com.fabio.forohub.domain.respuesta.dto;

import com.fabio.forohub.domain.respuesta.Respuesta;
import com.fabio.forohub.domain.usuario.dto.DatosRespuestaUsuario;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long idRespuesta,
        String mensaje,
        Long idTopico,
        LocalDateTime fechaCreacion,
        DatosRespuestaUsuario autor,
        boolean solucion
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getId(),
                respuesta.getFechaCreacion(),
                new DatosRespuestaUsuario(respuesta.getAutor()),
                respuesta.isSolucion()
        );
    }
}
