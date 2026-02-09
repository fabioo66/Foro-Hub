package com.fabio.forohub.service;

import com.fabio.forohub.domain.topico.Topico;
import com.fabio.forohub.domain.usuario.Usuario;
import com.fabio.forohub.domain.usuario.UsuarioRepository;
import com.fabio.forohub.domain.usuario.dto.DatosActualizacionEmail;
import com.fabio.forohub.infra.exception.ValidacionException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

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

        usuario.deshabilitar();
    }

    private Usuario buscarUsuarioPorId(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ValidacionException("Usuario no encontrado"));
    }
}
