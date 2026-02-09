package com.fabio.forohub.service;

import com.fabio.forohub.domain.respuesta.Respuesta;
import com.fabio.forohub.domain.respuesta.RespuestaRepository;
import com.fabio.forohub.domain.respuesta.dto.DatosActualizacionRespuesta;
import com.fabio.forohub.domain.respuesta.dto.DatosDetalleRespuesta;
import com.fabio.forohub.domain.respuesta.dto.DatosRegistroRespuesta;
import com.fabio.forohub.domain.topico.Topico;
import com.fabio.forohub.domain.topico.TopicoRepository;
import com.fabio.forohub.domain.usuario.Usuario;
import com.fabio.forohub.domain.usuario.UsuarioRepository;
import com.fabio.forohub.infra.exception.ValidacionException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Respuesta crearRespuesta(DatosRegistroRespuesta datos, Usuario autor) {
        var topico = obtenerTopicoActivo(datos.idTopico());

        var respuesta = new Respuesta(datos, topico, autor);
        topico.agregarRespuesta(respuesta);

        return respuestaRepository.save(respuesta);
    }

    public Page<DatosDetalleRespuesta> listarRespuestas(
            Long idTopico,
            Long idUsuario,
            Pageable paginacion
    ) {
        if (idTopico == null && idUsuario == null) {
            throw new ValidacionException("Debe proporcionar al menos un filtro: idTopico o idUsuario");
        }

        if (idTopico != null && idUsuario != null) {
            var topico = obtenerTopico(idTopico);
            validarUsuarioExiste(idUsuario);

            return respuestaRepository
                    .findByTopicoAndAutorIdAndActivoTrue(topico, idUsuario, paginacion)
                    .map(DatosDetalleRespuesta::new);
        }

        if (idTopico != null) {
            var topico = obtenerTopico(idTopico);

            return respuestaRepository
                    .findByTopicoAndActivoTrue(topico, paginacion)
                    .map(DatosDetalleRespuesta::new);
        }

        validarUsuarioExiste(idUsuario);

        return respuestaRepository
                .findByAutorIdAndActivoTrue(idUsuario, paginacion)
                .map(DatosDetalleRespuesta::new);
    }

    public DatosDetalleRespuesta obtenerRespuesta(Long id) {
        var respuesta = obtenerRespuestaPorId(id);
        return new DatosDetalleRespuesta(respuesta);
    }

    @Transactional
    public Respuesta actualizarRespuesta(
            Long id,
            DatosActualizacionRespuesta datos,
            Usuario usuarioAutenticado
    ) {
        var respuesta = obtenerRespuestaActiva(id);

        if (!respuesta.getAutor().getId().equals(usuarioAutenticado.getId())) {
            throw new ValidacionException("No tienes permiso para actualizar esta respuesta");
        }

        respuesta.setMensaje(datos.mensaje());
        return respuesta;
    }

    @Transactional
    public Respuesta marcarComoSolucion(Long idRespuesta, Usuario usuarioAutenticado) {
        var respuesta = obtenerRespuestaActiva(idRespuesta);

        if (!respuesta.getTopico().getAutor().getId().equals(usuarioAutenticado.getId())) {
            throw new ValidacionException("Solo el autor del tópico puede marcar respuestas como solución");
        }

        respuesta.marcarComoSolucion();
        return respuesta;
    }

    @Transactional
    public void eliminarRespuesta(Long id, Usuario usuarioAutenticado) {
        var respuesta = obtenerRespuestaActiva(id);

        if (!respuesta.getAutor().getId().equals(usuarioAutenticado.getId())) {
            throw new ValidacionException("No tienes permiso para eliminar esta respuesta");
        }

        respuesta.deshabilitar();
    }

    private Respuesta obtenerRespuestaPorId(Long id) {
        return respuestaRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Respuesta no encontrada"));
    }

    private Respuesta obtenerRespuestaActiva(Long id) {
        var respuesta = obtenerRespuestaPorId(id);

        if (!respuesta.isActivo()) {
            throw new ValidacionException("No se puede operar sobre una respuesta inactiva");
        }
        return respuesta;
    }

    private Topico obtenerTopico(Long idTopico) {
        return topicoRepository.findById(idTopico)
                .orElseThrow(() -> new ValidacionException("Tópico no encontrado"));
    }

    private Topico obtenerTopicoActivo(Long idTopico) {
        var topico = obtenerTopico(idTopico);

        if (!topico.isActivo()) {
            throw new ValidacionException("No se puede responder a un tópico inactivo");
        }
        return topico;
    }

    private void validarUsuarioExiste(Long idUsuario) {
        usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ValidacionException("Usuario no encontrado"));
    }
}
