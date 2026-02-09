package com.fabio.forohub.domain.respuesta;

import com.fabio.forohub.domain.respuesta.dto.DatosRegistroRespuesta;
import com.fabio.forohub.domain.topico.Topico;
import com.fabio.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "Respuesta")
@Table(name = "respuestas")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean activo;
    @Setter
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "id_topico")
    private Topico topico;
    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario autor;
    @Setter
    private boolean solucion;

    public Respuesta(DatosRegistroRespuesta datos, Topico topico, Usuario autor) {
        this.activo = true;
        this.mensaje = datos.mensaje();
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.autor = autor;
        this.solucion = false;
    }

    public void marcarComoSolucion() {
        this.solucion = true;
    }

    public void deshabilitar() {
        this.activo = false;
    }
}
