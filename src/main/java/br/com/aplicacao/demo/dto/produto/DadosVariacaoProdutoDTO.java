package br.com.aplicacao.demo.dto.produto;

import br.com.aplicacao.demo.entidades.ImagemVariacaoProduto;

import java.util.List;

public record DadosVariacaoProdutoDTO(String titulo, double preco, int estoque, List<ImagemVariacaoProdutoDTO> imagens) {
}