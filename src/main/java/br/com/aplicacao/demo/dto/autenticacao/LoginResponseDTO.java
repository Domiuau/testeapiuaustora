package br.com.aplicacao.demo.dto.autenticacao;

import br.com.aplicacao.demo.dto.usuario.TodosOsDadosUsuarioDTO;

public record LoginResponseDTO (String token, TodosOsDadosUsuarioDTO dadosUsuarioDTO){
}
