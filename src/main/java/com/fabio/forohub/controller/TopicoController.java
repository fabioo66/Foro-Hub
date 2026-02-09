package com.fabio.forohub.controller;

import com.fabio.forohub.domain.topico.dto.DatosActualizacionTopico;
import com.fabio.forohub.domain.topico.dto.DatosDetalleTopico;
import com.fabio.forohub.domain.topico.dto.DatosListaTopico;
import com.fabio.forohub.domain.topico.dto.DatosRegistroTopico;
import com.fabio.forohub.domain.usuario.Usuario;
import com.fabio.forohub.service.TopicoService;
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

@RequestMapping("/topicos")
@RestController
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService topicoService;


    @PostMapping
    public ResponseEntity<DatosDetalleTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datos,
            @AuthenticationPrincipal Usuario usuarioAutenticado,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var topico = topicoService.crearTopico(datos, usuarioAutenticado);

        var uri = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopicos(
            @PageableDefault(size = 10, sort = {"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable paginacion
    ) {
        var page = topicoService.listarTopicos(paginacion);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> detallarTopico(@PathVariable Long id) {
        var detalle = topicoService.obtenerDetalle(id);
        return ResponseEntity.ok(detalle);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizacionTopico datos,
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        var detalle = topicoService.actualizarTopico(id, datos, usuarioAutenticado);
        return ResponseEntity.ok(detalle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        topicoService.eliminarTopico(id, usuarioAutenticado);
        return ResponseEntity.noContent().build();
    }
}
