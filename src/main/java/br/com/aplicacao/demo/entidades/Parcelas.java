package br.com.aplicacao.demo.entidades;


import br.com.aplicacao.demo.dto.produto.DadosParcelasDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "parcela")
@Table(name = "parcelas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parcelas {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    private int vezes;
    private double juros;

    public Parcelas(DadosParcelasDTO parcelas, Produto produto) {
        this.juros = parcelas.juros();
        this.vezes = parcelas.vezes();
        this.produto = produto;
    }

    public static List<DadosParcelasDTO> toParcelasDTO (List<Parcelas> parcelas) {

        ArrayList<DadosParcelasDTO> parcelasDTO = new ArrayList<>();

        for (Parcelas p:
                parcelas) {
            parcelasDTO.add(new DadosParcelasDTO(p.vezes, p.juros));
        }

        return parcelasDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getVezes() {
        return vezes;
    }

    public void setVezes(int vezes) {
        this.vezes = vezes;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }
}

