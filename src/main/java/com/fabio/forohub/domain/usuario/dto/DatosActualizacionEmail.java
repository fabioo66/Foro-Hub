package com.fabio.forohub.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosActualizacionEmail(
        @NotBlank @Email String email
) {
}
