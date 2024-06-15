ALTER TABLE comentarios_dislikes
DROP FOREIGN KEY FKsvx70vycbqkvjlpnu2lqxdovc;

ALTER TABLE comentarios_dislikes
ADD CONSTRAINT FKsvx70vycbqkvjlpnu2lqxdovc
FOREIGN KEY (comentario_id) REFERENCES comentarios(id)
ON DELETE CASCADE;

ALTER TABLE comentarios_likes
DROP FOREIGN KEY FK537y7s6gt9jke4ut49k1v5jfw;

ALTER TABLE comentarios_likes
ADD CONSTRAINT FK537y7s6gt9jke4ut49k1v5jfw
FOREIGN KEY (comentario_id) REFERENCES comentarios(id)
ON DELETE CASCADE;

ALTER TABLE posts_generos
DROP FOREIGN KEY FKr10k9p0c74mg95cf2svjnb2cf;

ALTER TABLE posts_generos
ADD CONSTRAINT FKr10k9p0c74mg95cf2svjnb2cf FOREIGN KEY (genero_id) REFERENCES generos(id) ON DELETE CASCADE;