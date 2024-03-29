package br.com.aplicacao.demo.entidades;

import br.com.aplicacao.demo.dto.produto.AnunciarProdutoDTO;
import br.com.aplicacao.demo.dto.produto.DadosParcelasDTO;
import br.com.aplicacao.demo.enums.TipoDeGarantia;
import br.com.aplicacao.demo.enums.categorias.Categoria;
import br.com.aplicacao.demo.enums.EstadoDoProduto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    @NotNull
    private Categoria categoria;

    private String subCategoria;

    private String dataAnuncio;
    private boolean ativo;
    private String fabricante;

    @Enumerated(EnumType.STRING)
    private TipoDeGarantia tipoGarantia;

    private boolean freteGratis;
    private boolean entregaFull;
    private int diasDeGarantia;


    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<VariacaoProduto> variacoesDoProduto = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Parcelas> parcelas = new ArrayList<>();

    public Produto(AnunciarProdutoDTO anunciarProdutoDTO, Usuario usuario) {

        this.estado = anunciarProdutoDTO.estado();
        this.descricao = anunciarProdutoDTO.descricao();
        this.categoria = anunciarProdutoDTO.categoria();
        this.subCategoria = anunciarProdutoDTO.subcategoria();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataAnuncio = currentDate.format(formatter);
        this.ativo = true;
        this.entregaFull = anunciarProdutoDTO.entregaFull();
        this.freteGratis = anunciarProdutoDTO.freteGratis();
        this.fabricante = anunciarProdutoDTO.fabricante();
        this.tipoGarantia = anunciarProdutoDTO.tipoGarantia();
        this.diasDeGarantia = anunciarProdutoDTO.duracaoGarantia();
        this.idUsuario = usuario;
        anunciarProdutoDTO.variacoes().forEach(variacao -> this.variacoesDoProduto.add(new VariacaoProduto(variacao, this)));
        anunciarProdutoDTO.parcelas().forEach(parcelas -> this.parcelas.add(new Parcelas(parcelas, this)));




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

    public DadosParcelasDTO getMelhorParcela() {

        if (this.parcelas.isEmpty())
            return null;

        for (int i = this.parcelas.size() - 1; i >= 0 ; i--) {

            Parcelas parcela = this.parcelas.get(i);

            if (parcela.getJuros() == 0) {
                return new DadosParcelasDTO(parcela.getVezes(), parcela.getJuros());
            }
        }


        Parcelas parcela = this.parcelas.get(parcelas.size() - 1);

        return new DadosParcelasDTO(parcela.getVezes(), parcela.getJuros());
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

    public List<Parcelas> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcelas> parcelas) {
        this.parcelas = parcelas;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
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

    public boolean isFreteGratis() {
        return freteGratis;
    }

    public void setFreteGratis(boolean freteGratis) {
        this.freteGratis = freteGratis;
    }

    public boolean isEntregaFull() {
        return entregaFull;
    }

    public void setEntregaFull(boolean entregaFull) {
        this.entregaFull = entregaFull;
    }

    public void setVariacoesDoProduto(List<VariacaoProduto> variacoesDoProduto) {
        this.variacoesDoProduto = variacoesDoProduto;
    }
}
