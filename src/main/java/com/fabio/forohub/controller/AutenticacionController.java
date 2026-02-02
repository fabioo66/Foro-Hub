package com.fabio.forohub.controller;

import com.fabio.forohub.domain.usuario.DatosRegistroUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosRegistroUsuario datos) {
        var token = new UsernamePasswordAuthenticationToken(datos.username(), datos.password());
        var autenticacion = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }

}
