package com.fabio.forohub.service;

import com.fabio.forohub.domain.curso.Curso;
import com.fabio.forohub.domain.curso.CursoRepository;
import com.fabio.forohub.infra.exception.ValidacionException;
import com.fabio.forohub.domain.topico.*;
import com.fabio.forohub.domain.topico.dto.DatosActualizacionTopico;
import com.fabio.forohub.domain.topico.dto.DatosDetalleTopico;
import com.fabio.forohub.domain.topico.dto.DatosListaTopico;
import com.fabio.forohub.domain.topico.dto.DatosRegistroTopico;
import com.fabio.forohub.domain.usuario.Usuario;
import com.fabio.forohub.infra.security.PermissionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final PermissionValidator permissionValidator;

    @Transactional
    public Topico crearTopico(DatosRegistroTopico datos, Usuario autor) {
        var curso = obtenerCursoActivo(datos.idCurso());

        validarTopicoUnico(datos.titulo(), datos.mensaje(), null);
        var topico = new Topico(datos, autor, curso);
        return topicoRepository.save(topico);
    }

    public Page<DatosListaTopico> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAllByActivoTrue(paginacion)
                .map(DatosListaTopico::new);
    }

    public DatosDetalleTopico obtenerDetalle(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Tópico no encontrado"));
        return new DatosDetalleTopico(topico);
    }

    @Transactional
    public DatosDetalleTopico actualizarTopico(Long id, DatosActualizacionTopico datos, Usuario usuario) {
        var topico = validarPropietario(id, usuario);

        // Validar cambio de estado a CERRADO - solo MODERATOR y ADMIN
        if (datos.estado() != null && datos.estado() == Estado.CERRADO) {
            if (!permissionValidator.tienePrivilegiosElevados(usuario)) {
                throw new ValidacionException("Solo moderadores y administradores pueden cerrar tópicos.");
            }
        }

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
        var topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new ValidacionException("Tópico no encontrado"));

        permissionValidator.validarPermisoModificacion(
                usuario,
                topico.getAutor().getId(),
                "No tenes permiso para realizar esta operación."
        );

        return topico;
    }

    private void validarTopicoUnico(String titulo, String mensaje, Long idExcluir) {
        boolean existeDuplicado;

        if (idExcluir == null) {
            // Para creación (sin ID a excluir)
            existeDuplicado = topicoRepository.existsByTituloAndMensajeAndActivoTrue(titulo, mensaje);
        } else {
            // Para actualización (excluyendo el mismo tópico)
            existeDuplicado = topicoRepository.existsByTituloAndMensajeAndIdNotAndActivoTrue(titulo, mensaje, idExcluir);
        }

        if (existeDuplicado) {
            throw new ValidacionException("Ya existe un tópico con el mismo título y mensaje.");
        }
    }

    private Curso obtenerCurso(Long idCurso) {
        return cursoRepository.findById(idCurso)
                .orElseThrow(() -> new ValidacionException("Curso no encontrado"));
    }

    private Curso obtenerCursoActivo(Long idCurso) {
        var curso = obtenerCurso(idCurso);

        if (!curso.isActivo()) {
            throw new ValidacionException("No se puede crear un tópico para un curso inactivo");
        }
        return curso;
    }
}
