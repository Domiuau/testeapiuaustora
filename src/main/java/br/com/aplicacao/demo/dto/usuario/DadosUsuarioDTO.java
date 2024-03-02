package br.com.aplicacao.demo.dto.usuario;

import br.com.aplicacao.demo.entidades.Usuario;

public record DadosUsuarioDTO(String apelido, String nomeDeUsuario, String email, String telefone) {

    public DadosUsuarioDTO(Usuario usuario) {
        this (usuario.getApelido(), usuario.getUsername(), usuario.getEmail(), usuario.getTelefone());
    }


}
