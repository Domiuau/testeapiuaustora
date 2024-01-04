DROP TABLE imagens_da_variacao;

CREATE TABLE imagens_da_variacao (
    id serial PRIMARY KEY,
    id_variacao_do_produto VARCHAR(50),
    imagem BYTEA,
    FOREIGN KEY (id_variacao_do_produto) REFERENCES variacao_do_produto (id)
);