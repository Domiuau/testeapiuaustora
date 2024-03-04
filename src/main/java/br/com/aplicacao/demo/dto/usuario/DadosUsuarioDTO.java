package br.com.aplicacao.demo.dto.usuario;

import br.com.aplicacao.demo.entidades.Usuario;

public record DadosUsuarioDTO(String apelido, String nomeDeUsuario, String email, String telefone) {



    public DadosUsuarioDTO(String apelido, String nomeDeUsuario, String email, String telefone) {
        this.apelido = apelido;
        this.nomeDeUsuario = nomeDeUsuario;
        this.email = email;
        this.telefone = telefone;
    }



}
