package br.com.aplicacao.demo.erros.postgre;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrosPg {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tratarErro400(DataIntegrityViolationException ex) {
        var erros = ex.getMessage().split("=");
        System.out.println(ex.getMessage());
        System.out.println(ex.getLocalizedMessage());
        System.out.println(ex.getCause().getLocalizedMessage());
        String[] vetorGetCampo = erros[0].split(" ");

        return ResponseEntity.badRequest().body(new DadosErroValidacao(retirarParenteses(vetorGetCampo[vetorGetCampo.length-1]),
                retirarParenteses(erros[1].split(" ")[0]) + " já está em uso."));

        //  return ResponseEntity.badRequest().body(new DadosErroValidacao(erros[3].split("\\.")[1],erros[1] + " já está em uso."));
    }

    private String retirarParenteses (String s) {
        return s.replace("(","").replace(")","");
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
