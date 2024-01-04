CREATE TABLE imagens_da_variacao (
    id_variacao_do_produto VARCHAR(50) PRIMARY KEY,
    imagem BYTEA,
    CONSTRAINT fk_variacao_do_produto FOREIGN KEY (id_variacao_do_produto) REFERENCES variacao_do_produto (id)
);