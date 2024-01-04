package br.com.aplicacao.demo.dto.usuario;

import br.com.aplicacao.demo.enums.TipoDeUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroDTO (@NotBlank String apelido,
                           @NotBlank String nomeDeUsuario,
                           @NotBlank String senha, String email,
                           @NotBlank String telefone,
                           TipoDeUsuario tipo){
}
