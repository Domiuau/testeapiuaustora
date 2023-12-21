package br.com.aplicacao.demo.erros;

import br.com.aplicacao.demo.erros.postgre.TratadorDeErrosPg;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;

@RestControllerAdvice
public class TratadorDeErros {



    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity tratarErro403(JWTDecodeException ex) {
        var erros = ex.getMessage().split("=");


        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Código inválido ou expirado.");

        //  return ResponseEntity.badRequest().body(new DadosErroValidacao(erros[3].split("\\.")[1],erros[1] + " já está em uso."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro403(MethodArgumentNotValidException ex) {
        var erros = "msg: " + ex.getMessage();
        System.out.println(erros);

        System.out.println(ex.getLocalizedMessage());
        System.out.println(ex.getDetailMessageCode());


//
//        ArrayList<String> camposEmBranco = new ArrayList<>(Arrays.asList(
//                ex.getDetailMessageArguments().toString()
//        ));


        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("CAMPOS EM BRANCO, DPS FAÇO ALGO RETORNANDO OS CAMPOS QUE ESTÃO EM BRANCO");

        //  return ResponseEntity.badRequest().body(new DadosErroValidacao(erros[3].split("\\.")[1],erros[1] + " já está em uso."));
    }



}
