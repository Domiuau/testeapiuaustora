package br.com.aplicacao.demo.dto.produto;

import br.com.aplicacao.demo.dto.usuario.DadosUsuarioDTO;
import br.com.aplicacao.demo.entidades.Produto;
import br.com.aplicacao.demo.entidades.Usuario;
import br.com.aplicacao.demo.entidades.VariacaoProduto;
import br.com.aplicacao.demo.enums.EstadoDoProduto;
import br.com.aplicacao.demo.enums.TipoDeGarantia;
import br.com.aplicacao.demo.enums.categorias.Categoria;
import br.com.aplicacao.demo.enums.categorias.subcategorias.SubCategoria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public record DadosProdutoDTO (
                               String descricao,
                               Categoria categoria,
                               SubCategoria subCategoria,
                               String dataAnuncio,
                               int fimGarantia,
                               DadosUsuarioDTO idUsuario,
                               List<VariacaoProdutoDTO> variacoesDoProduto){



    public DadosProdutoDTO(Produto p) {
        this (p.getDescricao(), p.getCategoria(), p.getSubCategoria(), p.getDataAnuncio(), p.getDiasDeGarantia(), new DadosUsuarioDTO(p.getIdUsuario()), VariacaoProduto.toVariacaoProdutoDTO(p.getVariacoesDoProduto()));
    }


}
