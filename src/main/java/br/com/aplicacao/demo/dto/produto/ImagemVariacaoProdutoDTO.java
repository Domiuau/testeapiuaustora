package br.com.aplicacao.demo.dto.produto;

import org.springframework.web.multipart.MultipartFile;

public record ImagemVariacaoProdutoDTO (MultipartFile multipartFile) {
}
