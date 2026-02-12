package com.fabio.forohub.controller;

import com.fabio.forohub.domain.usuario.Usuario;
import com.fabio.forohub.domain.usuario.dto.DatosActualizacionEmail;
import com.fabio.forohub.domain.usuario.dto.DatosRespuestaUsuario;
import com.fabio.forohub.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.fabio.forohub.infra.security.RoleConstants.*;

@RequestMapping("/usuarios")
@RestController
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/me")
    @PreAuthorize(HAS_ROLE_USER_OR_MODERATOR)
    public ResponseEntity<DatosRespuestaUsuario> obtenerPerfil(
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuarioAutenticado));
    }

    @GetMapping
    @PreAuthorize(HAS_ROLE_ADMIN_ONLY)
    public ResponseEntity<Page<DatosRespuestaUsuario>> listarUsuarios(
            @PageableDefault(size = 10) Pageable paginacion
    ) {
        var page = usuarioService.listarUsuarios(paginacion);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @PreAuthorize(HAS_ROLE_USER_OR_MODERATOR)
    public ResponseEntity<DatosRespuestaUsuario> actualizarEmail(
            @RequestBody @Valid DatosActualizacionEmail datos,
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        var usuarioActualizado = usuarioService.actualizarEmail(datos, usuarioAutenticado.getId());
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuarioActualizado));
    }

    @DeleteMapping
    @PreAuthorize(HAS_ROLE_USER_OR_MODERATOR)
    public ResponseEntity<Void> eliminarCuenta(
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        usuarioService.eliminarCuenta(usuarioAutenticado.getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN_ONLY)
    public ResponseEntity<Void> desactivarUsuario(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        usuarioService.desactivarUsuario(id, usuarioAutenticado);
        return ResponseEntity.noContent().build();
    }
}
