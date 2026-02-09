package com.fabio.forohub.controller;

import com.fabio.forohub.domain.usuario.Usuario;
import com.fabio.forohub.domain.usuario.dto.DatosActualizacionEmail;
import com.fabio.forohub.domain.usuario.dto.DatosRespuestaUsuario;
import com.fabio.forohub.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuarios")
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PutMapping
    public ResponseEntity<DatosRespuestaUsuario> actualizarEmail(
            @RequestBody @Valid DatosActualizacionEmail datos,
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        var usuarioActualizado = usuarioService.actualizarEmail(datos, usuarioAutenticado.getId());
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuarioActualizado));
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarCuenta(
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        usuarioService.eliminarCuenta(usuarioAutenticado.getId());
        return ResponseEntity.noContent().build();
    }
}
