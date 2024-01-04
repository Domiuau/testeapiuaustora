package br.com.aplicacao.demo.dto.produto;

import br.com.aplicacao.demo.entidades.Produto;
import br.com.aplicacao.demo.entidades.Usuario;
import br.com.aplicacao.demo.entidades.VariacaoProduto;
import br.com.aplicacao.demo.enums.EstadoDoProduto;
import br.com.aplicacao.demo.enums.TipoDeGarantia;
import br.com.aplicacao.demo.enums.categorias.Categoria;
import br.com.aplicacao.demo.enums.categorias.subcategorias.SubCategoria;

import java.util.ArrayList;
import java.util.List;

public record DadosProdutoDTO (
                               String descricao,
                               Categoria categoria,
                               SubCategoria subCategoria,
                               String dataAnuncio,
                               String fimGarantia,
                               Usuario idUsuario,
                               List<DadosVariacaoProdutoDTO> variacoesDoProduto){


    public DadosProdutoDTO(Produto p) {
        this (p.getDescricao(), p.getCategoria(), p.getSubCategoria(), p.getDataAnuncio(), p.getFimGarantia(), p.getIdUsuario(), null);
    }
}
