package com.fabio.forohub.service;

import com.fabio.forohub.infra.exception.ValidacionException;
import com.fabio.forohub.domain.usuario.dto.DatosLoginUsuario;
import com.fabio.forohub.domain.usuario.dto.DatosRegistroUsuario;
import com.fabio.forohub.domain.usuario.Usuario;
import com.fabio.forohub.domain.usuario.UsuarioRepository;
import com.fabio.forohub.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AutenticacionService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public String autenticar(DatosLoginUsuario datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.password());
        var autenticacion = authenticationManager.authenticate(authenticationToken);

        var usuario = (Usuario) autenticacion.getPrincipal();
        return tokenService.generarToken(usuario);
    }

    @Transactional
    public Usuario registrarUsuario(DatosRegistroUsuario datos) {
        validarEmailUnico(datos.email());

        var passwordEncriptado = passwordEncoder.encode(datos.password());
        var usuario = new Usuario(datos, passwordEncriptado);

        return repository.save(usuario);
    }

    private void validarEmailUnico(String email) {
        var usuarioExistente = repository.findByEmail(email);

        if (usuarioExistente != null) {
            throw new ValidacionException("El email ya está registrado en el sistema.");
        }
    }
}
