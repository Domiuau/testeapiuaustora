package br.com.aplicacao.demo.entidades;

import br.com.aplicacao.demo.dto.produto.AnunciarProdutoDTO;
import br.com.aplicacao.demo.enums.TipoDeGarantia;
import br.com.aplicacao.demo.enums.categorias.Categoria;
import br.com.aplicacao.demo.enums.EstadoDoProduto;
import br.com.aplicacao.demo.enums.categorias.subcategorias.SubCategoria;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity(name = "produto")
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private EstadoDoProduto estado;

    private String descricao;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Enumerated(EnumType.STRING)
    private SubCategoria subCategoria;

    private String dataAnuncio;
    private boolean ativo;
    private String fabricante;

    @Enumerated(EnumType.STRING)
    private TipoDeGarantia tipoGarantia;

    private int diasDeGarantia;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<VariacaoProduto> variacoesDoProduto = new ArrayList<>();

    public Produto(EstadoDoProduto estado, String descricao, Categoria categoria, int duracaoGarantia, String fabricante, TipoDeGarantia tipoGarantia, Usuario idUsuario, List<VariacaoProduto> variacoes) {
        this.estado = estado;
        this.descricao = descricao;
        this.categoria = categoria;
        this.subCategoria = categoria.getSubCategoria();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataAnuncio = currentDate.format(formatter);
        this.ativo = true;
        this.fabricante = fabricante;
        this.tipoGarantia = tipoGarantia;
        this.diasDeGarantia = duracaoGarantia;
        this.idUsuario = idUsuario;
        variacoes.forEach(variacao -> variacao.setProduto(this));
        this.variacoesDoProduto = variacoes;
    }

    public Produto(AnunciarProdutoDTO anunciarProdutoDTO, Usuario usuario) {

        this.estado = anunciarProdutoDTO.estado();
        this.descricao = anunciarProdutoDTO.descricao();
        this.categoria = anunciarProdutoDTO.categoria();
        this.subCategoria = anunciarProdutoDTO.subcategoria();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataAnuncio = currentDate.format(formatter);
        this.ativo = true;
        this.fabricante = anunciarProdutoDTO.fabricante();
        this.tipoGarantia = anunciarProdutoDTO.tipoGarantia();
        this.diasDeGarantia = anunciarProdutoDTO.duracaoGarantia();
        this.idUsuario = usuario;
        anunciarProdutoDTO.variacoes().forEach(variacao -> this.variacoesDoProduto.add(new VariacaoProduto(variacao, this)));




    }

    @Override
    public String toString() {
        return "Produto{" +
                "id='" + id + '\'' +
                ", estado=" + estado +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", subCategoria=" + subCategoria +
                ", dataAnuncio='" + dataAnuncio + '\'' +
                ", ativo=" + ativo +
                ", fabricante='" + fabricante + '\'' +
                ", tipoGarantia=" + tipoGarantia +
                ", fimGarantia='" + diasDeGarantia + '\'' +
                ", variacoesDoProduto=" + variacoesDoProduto +
                '}';
    }
    public void desativar() {

        this.ativo = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EstadoDoProduto getEstado() {
        return estado;
    }

    public void setEstado(EstadoDoProduto estado) {
        this.estado = estado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    public String getDataAnuncio() {
        return dataAnuncio;
    }

    public void setDataAnuncio(String dataAnuncio) {
        this.dataAnuncio = dataAnuncio;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public int getDiasDeGarantia() {
        return diasDeGarantia;
    }

    public void setDiasDeGarantia(int diasDeGarantia) {
        this.diasDeGarantia = diasDeGarantia;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public TipoDeGarantia getTipoGarantia() {
        return tipoGarantia;
    }

    public void setTipoGarantia(TipoDeGarantia tipoGarantia) {
        this.tipoGarantia = tipoGarantia;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<VariacaoProduto> getVariacoesDoProduto() {
        return variacoesDoProduto;
    }

    public void setVariacoesDoProduto(List<VariacaoProduto> variacoesDoProduto) {
        this.variacoesDoProduto = variacoesDoProduto;
    }
}
