package com.fabio.forohub.domain.topico.dto;

import com.fabio.forohub.domain.topico.Estado;

public record DatosActualizacionTopico(
        Long id,
        String titulo,
        String mensaje,
        Estado estado
) {
}
