ALTER TABLE produtos
DROP COLUMN preco_desconto;

ALTER TABLE variacao_do_produto
ADD COLUMN preco_desconto DOUBLE PRECISION;
