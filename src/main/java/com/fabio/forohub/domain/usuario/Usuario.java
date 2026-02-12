package com.fabio.forohub.domain.usuario;

import com.fabio.forohub.domain.respuesta.Respuesta;
import com.fabio.forohub.domain.topico.Topico;
import com.fabio.forohub.domain.usuario.dto.DatosActualizacionEmail;
import com.fabio.forohub.domain.usuario.dto.DatosRegistroUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean activo;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private String nombre;
    @Setter
    private String email;
    private String password;
    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Topico> topicos;
    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Respuesta> respuestas;

    public Usuario(DatosRegistroUsuario datos, String passwordEncriptado) {
        this.activo = true;
        this.rol = Rol.USER;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.password = passwordEncriptado;
        this.topicos = new ArrayList<>();
        this.respuestas = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Spring Security espera que los roles tengan el prefijo "ROLE_" cuando se usa hasRole()
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.rol.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.activo;
    }

    public void deshabilitar() {
        this.activo = false;
    }
}
