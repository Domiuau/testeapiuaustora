package br.com.aplicacao.demo.controllers;

import br.com.aplicacao.demo.dto.*;
import br.com.aplicacao.demo.entidades.Usuario;
import br.com.aplicacao.demo.repository.UsuarioRepository;
import br.com.aplicacao.demo.security.config.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Transactional
    @GetMapping("/aa")
    public ResponseEntity teste() {
        System.out.println("fdsfdsdch");
        List<Usuario> a = usuarioRepository.findAll();
        System.out.println(a);
        return ResponseEntity.ok(a);
    }

    @GetMapping("/carro")
    public ResponseEntity carro () {
        return ResponseEntity.ok("carroamarelo");
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO) {

        System.out.println("Login " + autenticacaoDTO);

        var usernamePassword = new UsernamePasswordAuthenticationToken(autenticacaoDTO.nomeDeUsuario(), autenticacaoDTO.senha());

        System.out.println("usernamePassword " + usernamePassword.getCredentials().toString());

        var authentication = authenticationManager.authenticate(usernamePassword);

        if (authentication != null) {

            System.out.println("authentication");
            var token = tokenService.generateToken((Usuario) authentication.getPrincipal());


            System.out.println(token + " fdsfsdfds");

            Usuario usuario = (Usuario) usuarioRepository.findByUsername(autenticacaoDTO.nomeDeUsuario());

            DadosUsuarioDTO dadosUsuarioDTO = new DadosUsuarioDTO(usuario.getId(), usuario.getApelido(), usuario.getUsername(),
                    usuario.getPassword(), usuario.getEmail(), usuario.getTelefone());










            return ResponseEntity.ok(new LoginResponseDTO(token, dadosUsuarioDTO));

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário " + autenticacaoDTO.nomeDeUsuario() + " não encontrado ou senha incorreta.");
        }



    }




}
