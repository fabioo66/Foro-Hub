package com.fabio.forohub.controller;

import com.fabio.forohub.ValidacionException;
import com.fabio.forohub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/topicos")
@RestController
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private CrearTopico topico;

    @Transactional
    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        var detalleTopico = topico.crearTopico(datos);
        repository.save(detalleTopico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(detalleTopico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(detalleTopico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopicos(@PageableDefault(size = 10, sort={"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable paginacion) {
        var page = repository.findAllByActivoTrue(paginacion).
                map(DatosListaTopico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosListaTopico(topico));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizarTopico(@PathVariable Long id,
                                           @RequestBody @Valid DatosActualizacionTopico datos) {
        var topicoOptional = repository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = topicoOptional.get();

        // Validar que no exista OTRO tópico con el mismo título y mensaje
        var existeOtroTopico = repository.existsByTituloAndMensajeAndIdNotAndActivoTrue(datos.titulo(), datos.mensaje(), id);

        if (existeOtroTopico) {
            throw new ValidacionException("Ya existe un tópico con el mismo título y mensaje.");
        }

        topico.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        topico.deshabilitar();
        return ResponseEntity.noContent().build();
    }
}
