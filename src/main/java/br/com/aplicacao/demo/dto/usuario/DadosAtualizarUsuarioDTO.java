package br.com.aplicacao.demo.dto.usuario;

import br.com.aplicacao.demo.entidades.Usuario;

public record DadosAtualizarUsuarioDTO(String apelido, String nomeDeUsuario, String senha, String email, String telefone){
    public DadosAtualizarUsuarioDTO(Usuario usuario) {
        this (usuario.getApelido(), usuario.getTipoDeUsuario().name(), usuario.getPassword(), usuario.getEmail(), usuario.getTelefone());
    }
}
