package br.com.aplicacao.demo.repository;

import br.com.aplicacao.demo.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.domain.Pageable;


import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, String> {


    UserDetails findByUsername(String username);

    Page<Usuario> findAll (Pageable pageable);





}
