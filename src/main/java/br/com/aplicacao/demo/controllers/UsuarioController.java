package br.com.aplicacao.demo.controllers;

import br.com.aplicacao.demo.dto.DadosAtualizarUsuarioDTO;
import br.com.aplicacao.demo.dto.DadosUsuarioDTO;
import br.com.aplicacao.demo.dto.LoginResponseDTO;
import br.com.aplicacao.demo.entidades.Usuario;
import br.com.aplicacao.demo.repository.UsuarioRepository;
import br.com.aplicacao.demo.security.config.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity atualizarUsuario (@RequestBody DadosAtualizarUsuarioDTO dadosUsuarioDTO) {




        Usuario usuario = usuarioRepository.findById(dadosUsuarioDTO.id()).get();
        System.out.println("4");
        usuario.atualizarDados(dadosUsuarioDTO);

        var usernamePassword = new UsernamePasswordAuthenticationToken(dadosUsuarioDTO.nomeDeUsuario(), dadosUsuarioDTO.senha());
        System.out.println("usernamePassword " + usernamePassword.getCredentials().toString());
        var authentication = authenticationManager.authenticate(usernamePassword);
        System.out.println("authentication");
        var token = tokenService.generateToken((Usuario) authentication.getPrincipal());






        DadosUsuarioDTO dadosUsuario = new DadosUsuarioDTO(usuario.getId(), usuario.getApelido(), usuario.getUsername(),
                usuario.getPassword(), usuario.getEmail(), usuario.getTelefone());




        return ResponseEntity.ok(new LoginResponseDTO(token, dadosUsuario));
    }

    @Transactional
    @DeleteMapping("/desativar")
    public ResponseEntity desativarUsuario (@RequestHeader(name = "Authorization") String token) {

        var login = tokenService.validateToken(token.replace("Bearer ",""));
        Usuario usuario = (Usuario) usuarioRepository.findByUsername(login);
        usuario.desativar();

        return ResponseEntity.ok("A conta " + usuario.getUsername() + " foi desativada");



    }

}
