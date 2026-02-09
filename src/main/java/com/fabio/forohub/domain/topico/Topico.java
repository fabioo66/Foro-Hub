package com.fabio.forohub.domain.topico;

import com.fabio.forohub.domain.respuesta.Respuesta;
import com.fabio.forohub.domain.topico.dto.DatosActualizacionTopico;
import com.fabio.forohub.domain.topico.dto.DatosRegistroTopico;
import com.fabio.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean activo;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne
    private Usuario autor;
    private String curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;

    public Topico(DatosRegistroTopico datos, Usuario autor) {
        this.activo = true;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = Estado.ABIERTO;
        this.autor = autor;
        this.curso = datos.curso();
        this.respuestas = new ArrayList<>();
    }

    public void actualizarInformaciones(DatosActualizacionTopico datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.estado() != null) {
            this.estado = datos.estado();
        }
    }

    public void deshabilitar() {
        this.activo = false;

        this.respuestas.stream()
                .filter(Respuesta::isActivo)
                .forEach(Respuesta::deshabilitar);
    }

    public void agregarRespuesta(Respuesta respuesta) {
        this.respuestas.add(respuesta);
    }
}
