package com.fabio.forohub.infra.security;

public class RoleConstants {

    public static final String USER = "USER";
    public static final String MODERATOR = "MODERATOR";
    public static final String ADMIN = "ADMIN";

    // Expresiones SpEL para @PreAuthorize
    // hasRole() agrega automáticamente el prefijo ROLE_, por eso usamos los nombres sin prefijo
    public static final String HAS_ROLE_USER = "hasRole('USER')";
    public static final String HAS_ROLE_MODERATOR = "hasRole('MODERATOR')";
    public static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";

    public static final String HAS_ROLE_USER_OR_MODERATOR = "hasAnyRole('USER', 'MODERATOR', 'ADMIN')";
    public static final String HAS_ROLE_MODERATOR_OR_ADMIN = "hasAnyRole('MODERATOR', 'ADMIN')";
    public static final String HAS_ROLE_ADMIN_ONLY = "hasRole('ADMIN')";

    private RoleConstants() {
    }
}

