package br.com.aplicacao.demo.dto.produto;

import br.com.aplicacao.demo.enums.categorias.Categoria;
import org.springframework.data.domain.Page;

import java.util.List;

public record DadosProdutoVitrineConjuntoAleatorioDTO(List<DadosProdutoVitrineDTO> produtos, Categoria categoria, String subCategoria) {
}
