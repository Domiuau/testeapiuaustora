package br.com.aplicacao.demo.controllers;

import br.com.aplicacao.demo.dto.produto.AnunciarProdutoDTO;
import br.com.aplicacao.demo.dto.produto.DadosProdutoDTO;
import br.com.aplicacao.demo.dto.produto.DadosProdutoVitrineDTO;
import br.com.aplicacao.demo.dto.produto.VariacaoProdutoDTO;
import br.com.aplicacao.demo.entidades.ImagemVariacaoProduto;
import br.com.aplicacao.demo.entidades.Produto;
import br.com.aplicacao.demo.entidades.Usuario;
import br.com.aplicacao.demo.entidades.VariacaoProduto;
import br.com.aplicacao.demo.enums.EstadoDoProduto;
import br.com.aplicacao.demo.enums.TipoDeGarantia;
import br.com.aplicacao.demo.enums.categorias.Categoria;
import br.com.aplicacao.demo.enums.categorias.subcategorias.SubCategoria;
import br.com.aplicacao.demo.repository.ImagemVariacaoProdutoRepository;
import br.com.aplicacao.demo.repository.ProdutoRepository;
import br.com.aplicacao.demo.repository.UsuarioRepository;
import br.com.aplicacao.demo.repository.VariacaoProdutoRepository;
import br.com.aplicacao.demo.security.config.TokenService;
import ch.qos.logback.core.subst.Token;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ImagemVariacaoProdutoRepository imagemVariacaoProdutoRepository;
    @Autowired
    VariacaoProdutoRepository variacaoProdutoRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/anunciar")
    public ResponseEntity anunciarProduto(MultipartHttpServletRequest request, @RequestHeader(name = "Authorization") String token) throws IOException {

        System.out.println("fghdfdghdfghdfg");

        var login = tokenService.validateToken(token.replace("Bearer ", ""));
        Usuario usuario = (Usuario) usuarioRepository.findByUsername(login);

        Gson gson = new Gson();
        AnunciarProdutoDTO anunciarProdutoDTO = gson.fromJson(request.getParameter("json"), AnunciarProdutoDTO.class);

        Produto produto = new Produto(anunciarProdutoDTO, usuario);

        for (int i = 1; i <= produto.getVariacoesDoProduto().size(); i++) {

            for (MultipartFile imagem :
                    request.getFiles("file" + i)) {

                VariacaoProduto variacaoProduto = produto.getVariacoesDoProduto().get(i-1);

                variacaoProduto.getImagens().add(new ImagemVariacaoProduto(imagem.getBytes(), variacaoProduto));

//

            }

        }

        System.out.println("produto:");
        System.out.println(produto.getDescricao());
        for (VariacaoProduto variacao:
             produto.getVariacoesDoProduto()) {
            System.out.println(variacao.getTitulo());

            for (ImagemVariacaoProduto imagem:
                    variacao.getImagens()) {
                System.out.println(imagem.getImagem());
            }
        }


        produtoRepository.save(produto);

        return ResponseEntity.ok().build();

    }

//    @GetMapping("/{id}")
//    public ResponseEntity

    @GetMapping("/de/{idUsuario}")
    public ResponseEntity<Page<Produto>> getProdutoDe (@PageableDefault (size = 10) Pageable pageable, @PathVariable String idUsuario) {

        var page = produtoRepository.findAllByIdUsuario(usuarioRepository.findById(idUsuario).get(), pageable);
        //Page<DadosProdutoVitrineDTO> paginasDTO = page.map(DadosProdutoVitrineDTO::new);


        System.out.println(page.toString());

        for (Produto pr :
                page) {
          //  System.out.println(pr.getVariacoesDoProduto().get(0).getImagens().get(0));
        }

//        for (DadosProdutoVitrineDTO a:
//                paginasDTO) {
//            System.out.println(a);
//            aa.add(new DadosProdutoDTO())
//        }
//        System.out.println(paginasDTO.toString());
        return ResponseEntity.ok(null);

    }

    @GetMapping("/id/{id}")
    public ResponseEntity getProduto (@PathVariable String id) {

        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get().getFabricante());

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

    }

    @GetMapping("/{pagina}")
    public ResponseEntity<Page<DadosProdutoVitrineDTO>> getGeral (@PageableDefault(size = 3) Pageable pageable,
    @PathVariable int pagina) {

        pageable = PageRequest.of(pagina, pageable.getPageSize(), pageable.getSort());

        var paginaDeProdutos = produtoRepository.findAll(pageable);
        var paginaVitrineDTO = paginaDeProdutos.map(DadosProdutoVitrineDTO::new);

        for (Produto p:
                paginaDeProdutos) {
            System.out.println(p);
        }

        return ResponseEntity.ok(paginaVitrineDTO);

    }

    @GetMapping("/teste")
    public ResponseEntity aaa () {
//        List<Produto> lista = produtoRepository.findAll();
//        //System.out.println(lista.get(0).getImagens().get(0));
//        System.out.println(lista.size());
//        System.out.println(lista);
        return ResponseEntity.ok().build();
    }




}
