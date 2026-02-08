ALTER TABLE topicos ADD COLUMN autor_id BIGINT;

ALTER TABLE topicos ADD CONSTRAINT fk_topicos_autor
FOREIGN KEY (autor_id) REFERENCES usuarios(id);
