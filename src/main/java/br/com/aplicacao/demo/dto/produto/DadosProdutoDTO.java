package br.com.aplicacao.demo.dto.produto;

import br.com.aplicacao.demo.dto.usuario.DadosUsuarioDTO;
import br.com.aplicacao.demo.entidades.Parcelas;
import br.com.aplicacao.demo.entidades.Produto;
import br.com.aplicacao.demo.entidades.VariacaoProduto;
import br.com.aplicacao.demo.enums.categorias.Categoria;

import java.util.List;

public record DadosProdutoDTO (
                               String descricao,
                               Categoria categoria,
                               String subCategoria,
                               String dataAnuncio,
                               int fimGarantia,
                               DadosUsuarioDTO idUsuario,
                               List<DadosParcelasDTO> parcelas,
                               List<VariacaoProdutoDTO> variacoesDoProduto)
                                    {



    public DadosProdutoDTO(Produto p) {
        this (p.getDescricao(), p.getCategoria(), p.getSubCategoria(), p.getDataAnuncio(), p.getDiasDeGarantia(),
                new DadosUsuarioDTO(p.getIdUsuario().getApelido(), p.getIdUsuario().getUsername(), p.getIdUsuario().getEmail(), p.getIdUsuario().getTelefone()),
                Parcelas.toParcelasDTO(p.getParcelas()),
                        VariacaoProduto.toVariacaoProdutoDTO(p.getVariacoesDoProduto())
                );
    }


}
