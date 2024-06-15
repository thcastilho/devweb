-- possibilitar deleção de gêneros em cascata
ALTER TABLE posts_generos
DROP FOREIGN KEY FKr10k9p0c74mg95cf2svjnb2cf;

ALTER TABLE posts_generos
ADD CONSTRAINT FKr10k9p0c74mg95cf2svjnb2cf FOREIGN KEY (genero_id) REFERENCES generos(id) ON DELETE CASCADE;