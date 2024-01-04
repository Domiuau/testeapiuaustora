package br.com.aplicacao.demo.repository;

import br.com.aplicacao.demo.entidades.VariacaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariacaoProdutoRepository extends JpaRepository<VariacaoProduto, String> {
}
