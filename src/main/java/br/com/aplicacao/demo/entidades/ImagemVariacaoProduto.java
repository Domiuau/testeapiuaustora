package br.com.aplicacao.demo.entidades;

import br.com.aplicacao.demo.repository.ImagemVariacaoProdutoRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@Entity(name = "imagem-variacao-produto")
@Table(name = "imagens_da_variacao_do_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImagemVariacaoProduto {

    @ManyToOne @JoinColumn(name = "id_variacao_do_produto")
    private VariacaoProduto idVariacaoDoProduto;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "imagem", columnDefinition = "bytea")
    private byte[] imagem;



    public ImagemVariacaoProduto(byte[] imagem, VariacaoProduto variacaoProduto) {
        this.imagem = imagem;
        this.idVariacaoDoProduto = variacaoProduto;
    }


    @Override
    public String toString() {
        return "ImagemVariacaoProduto{" +
                ", id=" + id +
                ", imagem=" + Arrays.toString(imagem) +
                '}';
    }

    public VariacaoProduto getVariacaoProduto() {
        return idVariacaoDoProduto;
    }

    public void setVariacaoProduto(VariacaoProduto variacaoProduto) {
        this.idVariacaoDoProduto = variacaoProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
