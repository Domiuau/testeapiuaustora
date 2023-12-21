package br.com.aplicacao.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutenticacaoDTO (@NotBlank String nomeDeUsuario, @NotBlank String senha) {

}