create table topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    activo tinyint NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    estado VARCHAR(20) NOT NULL,
    curso VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);
