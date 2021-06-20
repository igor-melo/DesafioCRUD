DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario(id INT PRIMARY KEY auto_increment, nome VARCHAR(255),  email VARCHAR(255),  senha VARCHAR(255));
INSERT INTO usuario (nome, email, senha) VALUES('igor', 'igor@email.com', '123456');
INSERT INTO usuario (nome, email, senha) VALUES( 'melo', 'melo@email.com', '123456');
SELECT * FROM usuario ORDER BY ID;
