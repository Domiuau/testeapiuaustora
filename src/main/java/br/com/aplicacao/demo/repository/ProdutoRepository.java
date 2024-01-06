package br.com.aplicacao.demo.repository;

import br.com.aplicacao.demo.entidades.Produto;
import br.com.aplicacao.demo.entidades.Usuario;
import br.com.aplicacao.demo.enums.categorias.Categoria;
import br.com.aplicacao.demo.enums.categorias.subcategorias.SubCategoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
   // int countByIdUsuario(Usuario usuario);

    Page<Produto> findAllByIdUsuarioAndAtivoTrue (Usuario usuario, Pageable pageable);
    Page<Produto> findAllByAtivoTrue (Pageable pageable);

    Page<Produto> findAllByCategoriaAndAtivoTrue(Pageable pageable, Categoria categoria);
    Page<Produto> findAllBySubCategoriaAndAtivoTrue(Pageable pageable, SubCategoria subCategoria);


}
