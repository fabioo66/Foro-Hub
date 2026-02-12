package com.fabio.forohub.controller;

import com.fabio.forohub.domain.curso.Categoria;
import com.fabio.forohub.domain.curso.dto.DatosActualizacionCurso;
import com.fabio.forohub.domain.curso.dto.DatosDetalleCurso;
import com.fabio.forohub.domain.curso.dto.DatosRegistroCurso;
import com.fabio.forohub.domain.usuario.Usuario;
import com.fabio.forohub.service.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

import static com.fabio.forohub.infra.security.RoleConstants.*;

@RequestMapping("/cursos")
@RestController
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    @PreAuthorize(HAS_ROLE_ADMIN_ONLY)
    public ResponseEntity<DatosDetalleCurso> registrarCurso(
            @RequestBody @Valid DatosRegistroCurso datos,
            @AuthenticationPrincipal Usuario usuarioAutenticado,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var curso = this.cursoService.crearCurso(datos, usuarioAutenticado);

        var uri = uriComponentsBuilder.path("/cursos/{id}")
                .buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleCurso(curso));
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleCurso>> listarCursos(
            @RequestParam(required = false) String categoria,
            @PageableDefault(size = 10, sort = {"nombre"}, direction = Sort.Direction.ASC) Pageable paginacion
    ) {
        var page = cursoService.listarCursos(categoria, paginacion);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<String>> listarCategorias() {
        var categorias = Arrays.stream(Categoria.values())
                .map(Enum::name)
                .toList();
        return ResponseEntity.ok(categorias);
    }

    @PutMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN_ONLY)
    public ResponseEntity<DatosDetalleCurso> actualizarCurso(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizacionCurso datos,
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        var curso = this.cursoService.actualizarCurso(id, datos, usuarioAutenticado);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN_ONLY)
    public ResponseEntity<Void> eliminarCurso(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuarioAutenticado
    ) {
        this.cursoService.eliminarCurso(id, usuarioAutenticado);
        return ResponseEntity.noContent().build();
    }
}
