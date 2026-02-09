package com.fabio.forohub.service;

import com.fabio.forohub.domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Spring Security automáticamente verifica isEnabled() del UserDetails retornado
        // Si isEnabled() retorna false, lanza DisabledException
        var userDetails = repository.findByEmail(email);

        if (userDetails == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
        }

        return userDetails;
    }
}
