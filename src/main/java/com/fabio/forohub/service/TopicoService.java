package com.fabio.forohub.service;

import com.fabio.forohub.infra.exception.ValidacionException;
import com.fabio.forohub.domain.topico.*;
import com.fabio.forohub.domain.topico.dto.DatosActualizacionTopico;
import com.fabio.forohub.domain.topico.dto.DatosDetalleTopico;
import com.fabio.forohub.domain.topico.dto.DatosListaTopico;
import com.fabio.forohub.domain.topico.dto.DatosRegistroTopico;
import com.fabio.forohub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    @Transactional
    public Topico crearTopico(DatosRegistroTopico datos, Usuario autor) {
        validarTopicoUnico(datos.titulo(), datos.mensaje(), null);
        var topico = new Topico(datos, autor);
        return repository.save(topico);
    }

    public Page<DatosListaTopico> listarTopicos(Pageable paginacion) {
        return repository.findAllByActivoTrue(paginacion)
                .map(DatosListaTopico::new);
    }

    public DatosDetalleTopico obtenerDetalle(Long id) {
        var topico = repository.findById(id)
                .orElseThrow(() -> new ValidacionException("Tópico no encontrado"));
        return new DatosDetalleTopico(topico);
    }

    @Transactional
    public DatosDetalleTopico actualizarTopico(Long id, DatosActualizacionTopico datos, Usuario usuario) {
        var topico = validarPropietario(id, usuario);
        validarTopicoUnico(datos.titulo(), datos.mensaje(), id);
        topico.actualizarInformaciones(datos);
        return new DatosDetalleTopico(topico);
    }

    @Transactional
    public void eliminarTopico(Long id, Usuario usuario) {
        var topico = validarPropietario(id, usuario);
        topico.deshabilitar();
    }

    private Topico validarPropietario(Long topicoId, Usuario usuario) {
        var topico = repository.findById(topicoId)
                .orElseThrow(() -> new ValidacionException("Tópico no encontrado"));

        if (!topico.getAutor().getId().equals(usuario.getId())) {
            throw new ValidacionException("No tenes permiso para realizar esta operación.");
        }

        return topico;
    }

    private void validarTopicoUnico(String titulo, String mensaje, Long idExcluir) {
        boolean existeDuplicado;

        if (idExcluir == null) {
            // Para creación (sin ID a excluir)
            existeDuplicado = repository.existsByTituloAndMensajeAndActivoTrue(titulo, mensaje);
        } else {
            // Para actualización (excluyendo el mismo tópico)
            existeDuplicado = repository.existsByTituloAndMensajeAndIdNotAndActivoTrue(titulo, mensaje, idExcluir);
        }

        if (existeDuplicado) {
            throw new ValidacionException("Ya existe un tópico con el mismo título y mensaje.");
        }
    }
}
