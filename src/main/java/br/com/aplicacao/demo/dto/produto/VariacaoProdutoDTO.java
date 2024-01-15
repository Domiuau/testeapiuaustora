package br.com.aplicacao.demo.dto.produto;

import java.util.List;

public record VariacaoProdutoDTO(String titulo, double preco, double precoDesconto, int estoque, List<ImagemVariacaoProdutoDTO> imagens) {
}
