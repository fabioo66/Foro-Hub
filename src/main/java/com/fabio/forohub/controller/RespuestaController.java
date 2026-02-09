package com.fabio.forohub.controller;

import com.fabio.forohub.domain.respuesta.dto.DatosActualizacionRespuesta;
import com.fabio.forohub.domain.respuesta.dto.DatosDetalleRespuesta;
import com.fabio.forohub.domain.respuesta.dto.DatosRegistroRespuesta;
import com.fabio.forohub.domain.usuario.Usuario;
import com.fabio.forohub.service.RespuestaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/respuestas")
@RestController
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class RespuestaController {

    private final RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<DatosDetalleRespuesta> registrarRespuesta(
            @RequestBody @Valid DatosRegistroRespuesta datos,
            @AuthenticationPrincipal Usuario usuarioAutenticado,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var respuesta = respuestaService.crearRespuesta(datos, usuarioAutenticado);

        var uri = uriComponentsBuilder.path("/respuestas/{id}")
                .buildAndExpand(respuesta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleRespuesta(respuesta));
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleRespuesta>> listarRespuestas(
            @RequestParam(required = false) Long idTopico,
            @RequestParam(required = false) Long idUsuario,
            @PageableDefault(size = 10, sort = {"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable paginacion
    ) {
        var page = respuestaService.listarRespuestas(idTopico, idUsuario, paginacion);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleRespuesta> obtenerRespuesta(@PathVariable Long id) {
        var respuesta = respuestaService.obtenerRespuesta(id);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosDetalleRespuesta> actualizarRespuesta(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizacionRespuesta datos,
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        var respuestaActualizada = respuestaService.actualizarRespuesta(id, datos, usuarioAutenticado);
        return ResponseEntity.ok(new DatosDetalleRespuesta(respuestaActualizada));
    }

    @PatchMapping("/{id}/solucion")
    public ResponseEntity<DatosDetalleRespuesta> marcarComoSolucion(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        var respuesta = respuestaService.marcarComoSolucion(id, usuarioAutenticado);
        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRespuesta(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        respuestaService.eliminarRespuesta(id, usuarioAutenticado);
        return ResponseEntity.noContent().build();
    }
}
