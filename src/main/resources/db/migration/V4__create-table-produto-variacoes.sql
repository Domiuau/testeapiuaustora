CREATE TABLE variacao_do_produto (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(50) NOT NULL,
    preco VARCHAR(50) NOT NULL,
    id_produto VARCHAR(50) NOT NULL,
    estoque INT NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produtos (id)
);