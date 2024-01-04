package br.com.aplicacao.demo.dto.usuario;

public record TodosOsDadosUsuarioDTO(String id, String apelido, String nomeDeUsuario, String senha, String email, String telefone) {

    public TodosOsDadosUsuarioDTO(String id, String apelido, String nomeDeUsuario, String senha, String email, String telefone) {
        this.id = id;
        this.apelido = apelido;
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
    }



}
