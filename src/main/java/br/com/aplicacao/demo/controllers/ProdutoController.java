package br.com.aplicacao.demo.controllers;

import br.com.aplicacao.demo.dto.produto.*;
import br.com.aplicacao.demo.entidades.ImagemVariacaoProduto;
import br.com.aplicacao.demo.entidades.Produto;
import br.com.aplicacao.demo.entidades.Usuario;
import br.com.aplicacao.demo.entidades.VariacaoProduto;
import br.com.aplicacao.demo.enums.categorias.Categoria;
import br.com.aplicacao.demo.repository.ImagemVariacaoProdutoRepository;
import br.com.aplicacao.demo.repository.ProdutoRepository;
import br.com.aplicacao.demo.repository.UsuarioRepository;
import br.com.aplicacao.demo.repository.VariacaoProdutoRepository;
import br.com.aplicacao.demo.security.config.TokenService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.*;

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

        var login = tokenService.validateToken(token.replace("Bearer ", ""));
        Usuario usuario = (Usuario) usuarioRepository.findByUsername(login);

        Gson gson = new Gson();
        AnunciarProdutoDTO anunciarProdutoDTO = gson.fromJson(request.getParameter("json"), AnunciarProdutoDTO.class);

        Produto produto = new Produto(anunciarProdutoDTO, usuario);
        produto.getParcelas().forEach(parcelas -> System.out.println(parcelas.getId()));

        for (int i = 1; i <= produto.getVariacoesDoProduto().size(); i++) {

            for (MultipartFile imagem :
                    request.getFiles("file" + i)) {

                VariacaoProduto variacaoProduto = produto.getVariacoesDoProduto().get(i - 1);

                variacaoProduto.getImagens().add(new ImagemVariacaoProduto(imagem.getBytes(), variacaoProduto));

//

            }

        }

        produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado ID: " + produto.getId());

    }

//    @GetMapping("/{id}")
//    public ResponseEntity

    @GetMapping("/de/{idUsuario}/{pagina}")
    public ResponseEntity getProdutoDe(@PageableDefault(size = 2) Pageable pageable,
                                       @PathVariable String idUsuario,
                                       @PathVariable int pagina) {

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()) {

            pageable = PageRequest.of(pagina, pageable.getPageSize(), pageable.getSort());
            Page<Produto> page = produtoRepository.findAllByIdUsuarioAndAtivoTrue(usuario.get(), pageable);
            Page<DadosProdutoVitrineDTO> pageDTO = page.map(DadosProdutoVitrineDTO::new);

            return ResponseEntity.ok().body(pageDTO);


        } else {
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }

    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> getCategorias() {
        return ResponseEntity.ok(Categoria.getCategorias());
    }

    @GetMapping("/subcategorias/{categoria}")
    public List<? extends Enum<?>> getSubCategorias(@PathVariable String categoria) {
        System.out.println("jhgdfghghj");
        return Categoria.valueOf(categoria).getSubCategorias();
    }

    @GetMapping("/categoria/{categoria}/{pagina}")
    public ResponseEntity<Page<DadosProdutoVitrineDTO>> getProdutosPelaCategoria(@PathVariable String categoria,
                                                                                 @PageableDefault(size = 3) Pageable pageable,
                                                                                 @PathVariable int pagina) {

        return ResponseEntity.ok(produtoRepository.findAllByCategoriaAndAtivoTrue(
                PageRequest.of(pagina, pageable.getPageSize(), pageable.getSort()), Categoria.valueOf(categoria)).map(DadosProdutoVitrineDTO::new));
    }

    @GetMapping("/subcategoria/{subCategoria}/{pagina}")
    public ResponseEntity<Page<DadosProdutoVitrineDTO>> getProdutosPelaSubCategoria(@PathVariable String subCategoria,
                                                                                    @PageableDefault(size = 3) Pageable pageable,
                                                                                    @PathVariable int pagina) {

        return ResponseEntity.ok(produtoRepository.findAllBySubCategoriaAndAtivoTrue(
                PageRequest.of(pagina, pageable.getPageSize(), pageable.getSort()), subCategoria).map(DadosProdutoVitrineDTO::new));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getProduto(@PathVariable String id) {

        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {

            DadosProdutoDTO dadosProdutoDTO = new DadosProdutoDTO(produto.get());

            System.out.println(dadosProdutoDTO);
            return ResponseEntity.ok(dadosProdutoDTO);


        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

    }

    @GetMapping("/{pagina}")
    public ResponseEntity<Page<DadosProdutoVitrineDTO>> getGeral(@PageableDefault(size = 16) Pageable pageable,
                                                                 @PathVariable int pagina) {

        pageable = PageRequest.of(pagina, pageable.getPageSize(), pageable.getSort());

        var paginaDeProdutos = produtoRepository.findAllByAtivoTrue(pageable);
        var paginaVitrineDTO = paginaDeProdutos.map(DadosProdutoVitrineDTO::new);

        for (Produto p :
                paginaDeProdutos) {
            System.out.println(p);
        }

        return ResponseEntity.ok(paginaVitrineDTO);

    }

    @GetMapping("/teste")
    public ResponseEntity aaa() {
//        List<Produto> lista = produtoRepository.findAll();
//        //System.out.println(lista.get(0).getImagens().get(0));
//        System.out.println(lista.size());
//        System.out.println(lista);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<String> desativar(@RequestHeader(name = "Authorization") String token,
                                            @PathVariable String id) {

        var login = tokenService.validateToken(token.replace("Bearer ", ""));
        Usuario usuario = (Usuario) usuarioRepository.findByUsername(login);

        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {

            if (usuario.getAnuncios().contains(produto.get())) {
                produto.get().desativar();
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id + " foi deletado");
            } else {
                return ResponseEntity.badRequest().body("O produto não pertence a este usuário");
            }

        } else {
            return ResponseEntity.badRequest().body(id + " não encontrado");
        }


    }

    @GetMapping("/conjunto/categoria/aleatoria")
    public ResponseEntity<DadosProdutoVitrineConjuntoAleatorioDTO> getConjuntoAleatorio(@PageableDefault Pageable pageable) {

        Random random = new Random();
        Categoria categoria = Categoria.getCategorias().get(random.nextInt(Categoria.getCategorias().size()));
        String[] campos = {"descricao", "subCategoria", "fabricante", "diasDeGarantia", "tipoGarantia", "estado"};
        int tamanhoPagina = (random.nextInt(3) + 2) * 2;
        int totalPaginas = (produtoRepository.countByCategoria(categoria) / tamanhoPagina);


        pageable = PageRequest.of(random.nextInt(Math.max(totalPaginas, 0)),
                tamanhoPagina, Sort.by(Sort.Order.by(campos[random.nextInt(campos.length)]).with(Sort.Direction.ASC)));


        return ResponseEntity.ok(new DadosProdutoVitrineConjuntoAleatorioDTO(
                produtoRepository.findAllByCategoriaAndAtivoTrue(pageable, categoria).map(DadosProdutoVitrineDTO::new).toList(),
                categoria,
                categoria.getSubCategoria()

        ));


    }

    @Transactional
    @PostMapping("/anunciar/lista")
    public ResponseEntity anunciarListaProdutos(@RequestBody ListaProdutosDTO listaProdutosDTO, @RequestHeader(name = "Authorization") String token) throws IOException {

        var login = tokenService.validateToken(token.replace("Bearer ", ""));
        Usuario usuario = (Usuario) usuarioRepository.findByUsername(login);

        for (AnunciarProdutoDTO p:
                listaProdutosDTO.anunciarProdutoDTOS()) {
            Produto produto = new Produto(p, usuario);
            System.out.println(produto);
            produtoRepository.save(produto);

        }





        return ResponseEntity.ok("a");

    }


}
