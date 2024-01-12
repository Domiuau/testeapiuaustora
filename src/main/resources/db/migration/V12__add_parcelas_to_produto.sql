CREATE TABLE parcelas (

    id INT PRIMARY KEY,
    id_produto VARCHAR(50),
    vezes INT NOT NULL,
    juros DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produtos (id)



)
