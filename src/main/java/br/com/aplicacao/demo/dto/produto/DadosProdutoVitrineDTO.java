package br.com.aplicacao.demo.dto.produto;


import br.com.aplicacao.demo.entidades.Produto;
import br.com.aplicacao.demo.enums.EstadoDoProduto;
import br.com.aplicacao.demo.enums.TipoDeGarantia;

import java.util.Base64;

public record DadosProdutoVitrineDTO (String id,
                                      EstadoDoProduto estado,
                                      String fabricante,
                                      TipoDeGarantia tipoGarantia,
                                      String imagemPrincipal,
                                      double preco,
                                      double precoDesconto,
                                      DadosParcelasDTO parcela,
                                      boolean freteGratis,
                                      boolean entregaFull,
                                      String titulo) {



    public DadosProdutoVitrineDTO(Produto produto) {
        this(produto.getId(), produto.getEstado(), produto.getFabricante(), produto.getTipoGarantia(),
                produto.getVariacoesDoProduto().get(0).getImagens().isEmpty() ? null : Base64.getEncoder().encodeToString(produto.getVariacoesDoProduto().get(0).getImagens().get(0).getImagem()),
                produto.getVariacoesDoProduto().get(0).getPreco(),
                produto.getVariacoesDoProduto().get(0).getPreco_desconto(),
                produto.getMelhorParcela(),
                produto.isFreteGratis(),
                produto.isEntregaFull(),
                produto.getVariacoesDoProduto().get(0).getTitulo());
    }
}

