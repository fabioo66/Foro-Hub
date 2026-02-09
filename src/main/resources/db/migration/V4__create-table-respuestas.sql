CREATE TABLE respuestas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    activo tinyint NOT NULL,
    mensaje TEXT NOT NULL,
    id_topico BIGINT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    id_usuario BIGINT NOT NULL,
    solucion BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    CONSTRAINT fk_respuestas_topico_id FOREIGN KEY (id_topico) REFERENCES topicos(id),
    CONSTRAINT fk_respuestas_usuario_id FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);
