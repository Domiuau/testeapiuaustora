package br.com.aplicacao.demo.dto.produto;

import br.com.aplicacao.demo.enums.EstadoDoProduto;
import br.com.aplicacao.demo.enums.TipoDeGarantia;
import br.com.aplicacao.demo.enums.categorias.Categoria;
import br.com.aplicacao.demo.enums.categorias.subcategorias.SubCategoria;

import java.util.List;

public record AnunciarProdutoDTO (
        EstadoDoProduto estado,
        String descricao,
        Categoria categoria,
        SubCategoria subcategoria,
        boolean ativo,
        String fabricante,
        TipoDeGarantia tipoGarantia,
        int duracaoGarantia,
        boolean freteGratis,
        boolean entregaFull,
        List<VariacaoProdutoDTO> variacoes,
        List<DadosParcelasDTO> parcelas

) {


//    public AnunciarProdutoDTO(Produto produto) {
//        this(produto.getEstado(),
//                produto.getDescricao(),
//                produto.getCategoria(),
//                produto.getSubCategoria(),
//                produto.isAtivo(),
//                produto.getFabricante(),
//                produto.getTipoGarantia()
//                );
//
//    }


}
