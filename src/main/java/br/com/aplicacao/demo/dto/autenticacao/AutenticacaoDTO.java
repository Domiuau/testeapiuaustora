package br.com.aplicacao.demo.dto.autenticacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutenticacaoDTO (@NotBlank String nomeDeUsuario, @NotBlank String senha) {

}