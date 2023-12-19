package br.com.aplicacao.demo.erros;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity tratarErro400(SQLIntegrityConstraintViolationException ex) {
        var erros = ex.getMessage().split("'");


        return ResponseEntity.badRequest().body(new DadosErroValidacao(erros[3].split("\\.")[1],erros[1] + " já está em uso."));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity tratarErro400(ConstraintViolationException ex) {
        var erros = ex.getMessage();
        return ResponseEntity.badRequest().body(new DadosErroValidacao("Email","O email deve ser válido"));
    }




    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }




}
