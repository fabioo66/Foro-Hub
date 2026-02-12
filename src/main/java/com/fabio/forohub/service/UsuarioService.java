package com.fabio.forohub.service;

import com.fabio.forohub.domain.respuesta.RespuestaRepository;
import com.fabio.forohub.domain.topico.Topico;
import com.fabio.forohub.domain.usuario.Usuario;
import com.fabio.forohub.domain.usuario.UsuarioRepository;
import com.fabio.forohub.domain.usuario.dto.DatosActualizacionEmail;
import com.fabio.forohub.domain.usuario.dto.DatosRespuestaUsuario;
import com.fabio.forohub.infra.exception.ValidacionException;
import com.fabio.forohub.infra.security.PermissionValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PermissionValidator permissionValidator;
    private final RespuestaRepository respuestaRepository;

    public Page<DatosRespuestaUsuario> listarUsuarios(Pageable paginacion) {
        return usuarioRepository.findAll(paginacion)
                .map(DatosRespuestaUsuario::new);
    }

    @Transactional
    public Usuario actualizarEmail(@Valid DatosActualizacionEmail datos, Long usuarioId) {
        var existeEmail = usuarioRepository.existsByEmail(datos.email());

        if (existeEmail) {
            throw new ValidacionException("El email ya está en uso");
        }

        Usuario usuario = buscarUsuarioPorId(usuarioId);
        usuario.setEmail(datos.email());
        return usuario;
    }

    @Transactional
    public void eliminarCuenta(Long usuarioId) {
        Usuario usuario = buscarUsuarioPorId(usuarioId);

        usuario.getTopicos().stream()
                .filter(Topico::isActivo)
                .forEach(Topico::deshabilitar);

        respuestaRepository.desactivarPorAutor(usuario.getId());

        usuario.deshabilitar();
    }

    @Transactional
    public void desactivarUsuario(Long usuarioId, Usuario admin) {
        permissionValidator.validarEsAdmin(admin);

        if (admin.getId().equals(usuarioId)) {
            throw new ValidacionException("No puedes desactivar tu propia cuenta usando este endpoint. Usa DELETE /usuarios");
        }

        Usuario usuario = buscarUsuarioPorId(usuarioId);

        if (!usuario.isEnabled()) {
            throw new ValidacionException("El usuario ya está desactivado");
        }

        usuario.getTopicos().stream()
                .filter(Topico::isActivo)
                .forEach(Topico::deshabilitar);

        respuestaRepository.desactivarPorAutor(usuario.getId());

        usuario.deshabilitar();
    }

    private Usuario buscarUsuarioPorId(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ValidacionException("Usuario no encontrado"));
    }
}
