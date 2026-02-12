package com.fabio.forohub.infra.security;

import com.fabio.forohub.domain.usuario.Rol;
import com.fabio.forohub.domain.usuario.Usuario;
import com.fabio.forohub.infra.exception.ValidacionException;
import org.springframework.stereotype.Component;

@Component
public class PermissionValidator {

    public boolean puedeModificarRecurso(Usuario usuario, Long propietarioId) {
        return esPropietario(usuario, propietarioId) ||
               tienePrivilegiosElevados(usuario);
    }

    public boolean esPropietario(Usuario usuario, Long propietarioId) {
        return usuario.getId().equals(propietarioId);
    }


    public boolean tienePrivilegiosElevados(Usuario usuario) {
        return usuario.getRol() == Rol.MODERATOR ||
               usuario.getRol() == Rol.ADMIN;
    }

    public boolean esAdmin(Usuario usuario) {
        return usuario.getRol() == Rol.ADMIN;
    }

    public void validarPermisoModificacion(
            Usuario usuario,
            Long propietarioId,
            String mensajeError
    ) {
        if (!puedeModificarRecurso(usuario, propietarioId)) {
            throw new ValidacionException(mensajeError);
        }
    }

    public void validarEsAdmin(Usuario usuario) {
        if (!esAdmin(usuario)) {
            throw new ValidacionException("Solo los administradores pueden realizar esta operación.");
        }
    }
}

