package br.com.aplicacao.demo.repository;

import br.com.aplicacao.demo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {


    UserDetails findByUsername(String username);





}
