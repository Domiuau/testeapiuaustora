package br.com.aplicacao.demo.entidades;

import br.com.aplicacao.demo.dto.produto.VariacaoProdutoDTO;
import br.com.aplicacao.demo.repository.VariacaoProdutoRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "variacao_do_produto")
@Table(name = "variacao_do_produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class VariacaoProduto {

    public VariacaoProduto(String titulo, double preco, int estoque, List<ImagemVariacaoProduto> imagens) {
        this.titulo = titulo;
        this.preco = preco;
        this.estoque = estoque;
        imagens.forEach(imagemVariacaoProduto -> imagemVariacaoProduto.setVariacaoProduto(this));
        this.imagens = imagens;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String titulo;

    private double preco;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private int estoque;

    @OneToMany(mappedBy = "idVariacaoDoProduto", cascade = CascadeType.ALL)
    private List<ImagemVariacaoProduto> imagens = new ArrayList<>();

    public VariacaoProduto(VariacaoProdutoDTO variacao, Produto produto) {

        this.titulo = variacao.titulo();
        this.preco = variacao.preco();
        this.produto = produto;
        this.estoque = variacao.quantidade();
    }

    @Override
    public String toString() {
        return "VariacaoProduto{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", preco=" + preco +
                ", estoque=" + estoque +
                ", imagens=" + imagens +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<ImagemVariacaoProduto> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemVariacaoProduto> imagens) {
        this.imagens = imagens;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}
