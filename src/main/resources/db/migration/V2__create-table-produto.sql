CREATE TABLE produtos (
    id VARCHAR(50) PRIMARY KEY,
    estado VARCHAR(50) NOT NULL,
    descricao VARCHAR(400),
    categoria VARCHAR(50),
    subcategoria VARCHAR(50),
    data_anuncio VARCHAR(50),
    ativo BOOLEAN,
    fabricante VARCHAR(50),
    tipo_garantia VARCHAR(50),
    fim_garantia VARCHAR(50),
    id_usuario VARCHAR(50),

    FOREIGN KEY (id_usuario) REFERENCES usuarios (id)
);