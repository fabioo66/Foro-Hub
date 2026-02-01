package com.fabio.forohub.domain.topico;

import com.fabio.forohub.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrearTopico {

    @Autowired
    private TopicoRepository repository;

    public Topico crearTopico(DatosRegistroTopico datos) {
        var existeDuplicado = repository.existsByTituloAndMensajeAndActivoTrue(datos.titulo(), datos.mensaje());

        if (existeDuplicado) {
            throw new ValidacionException("Ya existe un tópico con el mismo título y mensaje.");
        }

        return new Topico(datos);
    }
}
