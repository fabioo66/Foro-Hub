package com.fabio.forohub.domain.topico;

public record DatosActualizacionTopico(
        Long id,
        String titulo,
        String mensaje,
        Estado estado
) {
}
