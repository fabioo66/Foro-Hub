package com.fabio.forohub.service;

import com.fabio.forohub.domain.curso.Categoria;
import com.fabio.forohub.domain.curso.Curso;
import com.fabio.forohub.domain.curso.CursoRepository;
import com.fabio.forohub.domain.curso.dto.DatosActualizacionCurso;
import com.fabio.forohub.domain.curso.dto.DatosDetalleCurso;
import com.fabio.forohub.domain.curso.dto.DatosRegistroCurso;
import com.fabio.forohub.domain.usuario.Usuario;
import com.fabio.forohub.infra.exception.ValidacionException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    // El autor lo voy a usar cuando agregue la funcionalidad de que solo los administradores pueden crear cursos
    @Transactional
    public Curso crearCurso(@Valid DatosRegistroCurso datos, Usuario usuarioAutenticado) {
        /*
        validarAdministrador(curso, usuarioAutenticado);
         */

        validarCursoNoExiste(datos.nombre());

        var curso = new Curso(datos);
        return cursoRepository.save(curso);
    }

    public Page<DatosDetalleCurso> listarCursos(String categoria, Pageable paginacion) {
        if (categoria != null) {
            try {
                var categoriaEnum = Categoria.valueOf(categoria.toUpperCase());
                return cursoRepository
                        .findByCategoriaAndActivoTrue(categoriaEnum, paginacion)
                        .map(DatosDetalleCurso::new);
            } catch (IllegalArgumentException e) {
                throw new ValidacionException("Categoría no válida: " + categoria);
            }
        }

        return cursoRepository
                .findByActivoTrue(paginacion)
                .map(DatosDetalleCurso::new);
    }

    @Transactional
    public DatosDetalleCurso actualizarCurso(Long id, @Valid DatosActualizacionCurso datos, Usuario usuarioAutenticado) {
        var curso = cursoValido(id);

        /*
        validarAdministrador(curso, usuarioAutenticado);
         */

        if (!curso.getNombre().equals(datos.nombre())) {
            validarCursoNoExiste(datos.nombre());
        }

        curso.actualizarInformaciones(datos);
        return new DatosDetalleCurso(curso);
    }

    private Curso cursoValido(Long id) {
        var curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Curso no encontrado"));
        return curso;
    }

    private void validarCursoNoExiste(String nombre) {
        cursoRepository.findByNombreAndActivoTrue(nombre)
                .ifPresent(c -> {
                    throw new ValidacionException("Ya existe un curso activo con ese nombre");
                });
    }

    public void eliminarCurso(Long id, Usuario usuarioAutenticado) {
        var curso = cursoValido(id);

        /*
        validarAdministrador(curso, usuarioAutenticado);
         */

        curso.deshabilitar();
        cursoRepository.save(curso);
    }
}
