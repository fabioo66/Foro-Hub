-- Eliminamos la columna curso antigua (era VARCHAR)
ALTER TABLE topicos DROP COLUMN curso;

-- Agregamos la nueva columna curso_id como foreign key
ALTER TABLE topicos ADD COLUMN curso_id BIGINT;

-- Agregamos la constraint de foreign key
ALTER TABLE topicos ADD CONSTRAINT fk_topicos_curso FOREIGN KEY (curso_id) REFERENCES cursos(id);

