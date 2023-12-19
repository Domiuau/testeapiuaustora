package br.com.aplicacao.demo.dto;

import br.com.aplicacao.demo.entidades.Usuario;

public record DadosUsuarioDTO(String id, String apelido, String nomeDeUsuario, String senha, String email, String telefone) {

    public DadosUsuarioDTO(String id, String apelido, String nomeDeUsuario, String senha, String email, String telefone) {
        this.id = id;
        this.apelido = apelido;
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
    }



}
