package com.fabio.forohub.domain.curso;

import com.fabio.forohub.domain.curso.dto.DatosActualizacionCurso;
import com.fabio.forohub.domain.curso.dto.DatosRegistroCurso;
import com.fabio.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Curso")
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean activo;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Topico> topicos;

    public Curso(DatosRegistroCurso datos) {
        this.activo = true;
        this.nombre = datos.nombre();
        this.categoria = Categoria.valueOf(datos.categoria().toUpperCase());
        this.topicos = new ArrayList<>();
    }

    public void actualizarInformaciones(@Valid DatosActualizacionCurso datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }

        if (datos.categoria() != null) {
            this.categoria = Categoria.valueOf(datos.categoria());
        }
    }

    public void deshabilitar() {
        this.activo = false;
    }
}
