CREATE TABLE usuarios (
    id VARCHAR(50) PRIMARY KEY,
    apelido VARCHAR(50) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(150) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    telefone VARCHAR(50) NOT NULL UNIQUE,
    tipo_de_usuario VARCHAR(50) NOT NULL,
    enabled BOOLEAN DEFAULT true
);