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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")


public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;


    @GetMapping
    public ResponseEntity a () {
        return ResponseEntity.ok("aaa");
    }

    @Transactional
    @PutMapping("/atualizar")
    public ResponseEntity atualizarUsuario (@RequestBody DadosAtualizarUsuarioDTO dadosUsuarioDTO,
                                            @RequestHeader(name = "Authorization") String token) {

        var login = tokenService.validateToken(token.replace("Bearer ",""));
        Usuario usuario = (Usuario) usuarioRepository.findByUsername(login);
        usuario.atualizarDados(dadosUsuarioDTO);

        var usernamePassword = new UsernamePasswordAuthenticationToken(dadosUsuarioDTO.nomeDeUsuario(), dadosUsuarioDTO.senha());

        System.out.println("usernamePassword " + usernamePassword.getCredentials().toString());

        var authentication = authenticationManager.authenticate(usernamePassword);

        if (authentication != null) {

            System.out.println("authentication");
            var tokenNovo = tokenService.generateToken((Usuario) authentication.getPrincipal());


            System.out.println(token + " fdsfsdfds");

            DadosUsuarioDTO dadosAtualizadosUsuarioDTO = new DadosUsuarioDTO(usuario.getId(), usuario.getApelido(), usuario.getUsername(),
                    usuario.getPassword(), usuario.getEmail(), usuario.getTelefone());










            return ResponseEntity.ok(new LoginResponseDTO(tokenNovo, dadosAtualizadosUsuarioDTO));

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário " + dadosUsuarioDTO.nomeDeUsuario() + " não encontrado ou senha incorreta.");
        }







//
//        Usuario usuario = usuarioRepository.findById(dadosUsuarioDTO.id()).get();
//        System.out.println("4");
//        usuario.atualizarDados(dadosUsuarioDTO);
//
//        var usernamePassword = new UsernamePasswordAuthenticationToken(dadosUsuarioDTO.nomeDeUsuario(), dadosUsuarioDTO.senha());
//        System.out.println("usernamePassword " + usernamePassword.getCredentials().toString());
//        var authentication = authenticationManager.authenticate(usernamePassword);
//        System.out.println("authentication");
//        var token = tokenService.generateToken((Usuario) authentication.getPrincipal());
//
//
//
//
//
//
//        DadosUsuarioDTO dadosUsuario = new DadosUsuarioDTO(usuario.getId(), usuario.getApelido(), usuario.getUsername(),
//                usuario.getPassword(), usuario.getEmail(), usuario.getTelefone());




      //  return ResponseEntity.ok(new LoginResponseDTO(token, dadosUsuario));
    }

    @Transactional
    @DeleteMapping("/desativar")
    public ResponseEntity desativarUsuario (@RequestHeader(name = "Authorization") String token) {

        var login = tokenService.validateToken(token.replace("Bearer ",""));
        Usuario usuario = (Usuario) usuarioRepository.findByUsername(login);
        usuario.desativar();

        return ResponseEntity.ok("A conta " + usuario.getUsername() + " foi desativada");



    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegistroDTO registroDTO) {

        System.out.println("Registro");

        String senhaEncriptada = new BCryptPasswordEncoder().encode(registroDTO.senha());



        Usuario usuario = new Usuario(registroDTO, senhaEncriptada);
        usuarioRepository.save(usuario);


        return ResponseEntity.status(HttpStatus.CREATED).body(registroDTO);


    }

}
