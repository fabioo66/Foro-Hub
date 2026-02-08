package com.fabio.forohub.service;

import com.fabio.forohub.infra.exception.ValidacionException;
import com.fabio.forohub.domain.usuario.dto.DatosLoginUsuario;
import com.fabio.forohub.domain.usuario.dto.DatosRegistroUsuario;
import com.fabio.forohub.domain.usuario.Usuario;
import com.fabio.forohub.domain.usuario.UsuarioRepository;
import com.fabio.forohub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutenticacionService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String autenticar(DatosLoginUsuario datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.password());
        var autenticacion = authenticationManager.authenticate(authenticationToken);

        var usuario = (Usuario) autenticacion.getPrincipal();
        return tokenService.generarToken(usuario);
    }

    @Transactional
    public Usuario registrarUsuario(DatosRegistroUsuario datos) {
        validarEmailUnico(datos.email());

        var usuario = new Usuario(
                null,
                datos.nombre(),
                datos.email(),
                passwordEncoder.encode(datos.password()),
                null
        );

        return repository.save(usuario);
    }

    private void validarEmailUnico(String email) {
        var usuarioExistente = repository.findByEmail(email);

        if (usuarioExistente != null) {
            throw new ValidacionException("El email ya está registrado en el sistema.");
        }
    }
}
