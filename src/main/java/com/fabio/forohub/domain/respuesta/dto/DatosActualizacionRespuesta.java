package com.fabio.forohub.domain.respuesta.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizacionRespuesta(
            @NotBlank String mensaje
) {
}
