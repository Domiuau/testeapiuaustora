package br.com.aplicacao.demo.repository;

import br.com.aplicacao.demo.entidades.Produto;
import br.com.aplicacao.demo.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
   // int countByIdUsuario(Usuario usuario);

    Page<Produto> findAllByIdUsuario (Usuario usuario, Pageable pageable);
    Page<Produto> findAll (Pageable pageable);
}
