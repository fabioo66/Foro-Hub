package com.fabio.forohub.controller;

import com.fabio.forohub.domain.usuario.dto.DatosLoginUsuario;
import com.fabio.forohub.domain.usuario.dto.DatosRegistroUsuario;
import com.fabio.forohub.domain.usuario.dto.DatosRespuestaUsuario;
import com.fabio.forohub.infra.security.DatosTokenJWT;
import com.fabio.forohub.service.AutenticacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {

    @Autowired
    private AutenticacionService autenticacionService;

    @PostMapping("/login")
    public ResponseEntity<DatosTokenJWT> iniciarSesion(@RequestBody @Valid DatosLoginUsuario datos) {
        var tokenJWT = autenticacionService.autenticar(datos);
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }

    @PostMapping("/register")
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos,
                                                                   UriComponentsBuilder uriComponentsBuilder) {
        var usuario = autenticacionService.registrarUsuario(datos);

        var uri = uriComponentsBuilder.path("/auth/usuarios/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosRespuestaUsuario(usuario));
    }

}
