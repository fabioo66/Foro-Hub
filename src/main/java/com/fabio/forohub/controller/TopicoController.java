package com.fabio.forohub.controller;

import com.fabio.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/topicos")
@RestController
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

}
