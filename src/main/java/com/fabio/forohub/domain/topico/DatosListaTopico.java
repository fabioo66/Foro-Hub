package com.fabio.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosListaTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado estado,
        String autor,
        String curso
) {

    public DatosListaTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
}
