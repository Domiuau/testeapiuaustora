package br.com.aplicacao.demo.controllers;

import br.com.aplicacao.demo.dto.autenticacao.AutenticacaoDTO;
import br.com.aplicacao.demo.dto.autenticacao.LoginResponseDTO;
import br.com.aplicacao.demo.dto.usuario.DadosUsuarioDTO;
import br.com.aplicacao.demo.dto.usuario.TodosOsDadosUsuarioDTO;
import br.com.aplicacao.demo.entidades.Usuario;
import br.com.aplicacao.demo.enums.categorias.Categoria;
import br.com.aplicacao.demo.enums.categorias.subcategorias.SubCategoria;
import br.com.aplicacao.demo.repository.ProdutoRepository;
import br.com.aplicacao.demo.repository.UsuarioRepository;
import br.com.aplicacao.demo.security.config.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Transactional
    @GetMapping("/aa")
    public ResponseEntity<Page<DadosUsuarioDTO>> teste(@PageableDefault(size = 2, sort = {"apelido"}) Pageable pageable) {
        System.out.println("fdsfdsdch");

        Pageable nextPageable = pageable.next();

        Page<Usuario> nextPage = usuarioRepository.findAll(nextPageable);

        Page<DadosUsuarioDTO> nextPageDTO = nextPage.map(DadosUsuarioDTO::new);

        return ResponseEntity.ok(nextPageDTO);
    }

    @Transactional
    @GetMapping("/carro")
    public ResponseEntity carro() {

        System.out.println("fgdhdhfhhdfhdhdhfdhhdfhdfhf");

        Optional<Usuario> usuario = usuarioRepository.findById("0e1be383-941b-4d40-9d47-d0187115a811");

        Categoria categoria = Categoria.CASA;
        categoria.setSubCategoria(SubCategoria.CASA2);
//
//        Produto produto = new Produto(EstadoDoProduto.NOVO,
//                "gdfgdfsgfds",
//                categoria,
//                40,
//                "razer",
//                TipoDeGarantia.UAUSTORA,
//                usuario.get(),
//                new ArrayList<>(Arrays.asList(
//                        new VariacaoProduto("titulo111",123,19, new ArrayList<>(Arrays.asList(new ImagemVariacaoProduto(new byte[]{15,50,61,71,81,91,21,51,71,41,15,50,61,71,81,91,21,51,71,41}), new ImagemVariacaoProduto(new byte[]{60,70,81,91,21,14,16,51,18,71,41,15,50,61,71,41,15,50,61,71})))),
//                        new VariacaoProduto("titulo211",223,29, new ArrayList<>(Arrays.asList(new ImagemVariacaoProduto(new byte[]{15,50,61,71,81,91,21,51,71,41,41,15,50,61,71,41,15,50,61,71}), new ImagemVariacaoProduto(new byte[]{60,70,41,15,50,61,71,41,15,50,61,71,81,91,21,14,16,51,18,71}))))
//
//
//
//
//        )));

        System.out.println("produto criado");

        //  produtoRepository.save(produto);


        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO) {


        try {

            var usernamePassword = new UsernamePasswordAuthenticationToken(autenticacaoDTO.nomeDeUsuario(), autenticacaoDTO.senha());
            var authentication = authenticationManager.authenticate(usernamePassword);
            System.out.println("fghfdgdfghfghd");
            Usuario usuario = (Usuario) authentication.getPrincipal();


            var token = tokenService.generateToken(usuario);
            TodosOsDadosUsuarioDTO dadosUsuarioDTO = new TodosOsDadosUsuarioDTO(usuario.getId(), usuario.getApelido(), usuario.getUsername(),
                    usuario.getPassword(), usuario.getEmail(), usuario.getTelefone());

            return ResponseEntity.ok(new LoginResponseDTO(token, dadosUsuarioDTO));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário " + autenticacaoDTO.nomeDeUsuario() + " não encontrado ou senha incorreta.");

        }


    }


}
