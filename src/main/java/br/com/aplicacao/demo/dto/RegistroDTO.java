package br.com.aplicacao.demo.dto;

import br.com.aplicacao.demo.enums.TipoDeUsuario;

public record RegistroDTO (String apelido,String nomeDeUsuario, String senha, String email, String telefone, TipoDeUsuario tipo){
}
