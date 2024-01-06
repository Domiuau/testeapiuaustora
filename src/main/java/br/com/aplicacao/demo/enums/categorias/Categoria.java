package br.com.aplicacao.demo.enums.categorias;

import br.com.aplicacao.demo.enums.categorias.subcategorias.SubCategoria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Categoria {




    CASA("casa", new ArrayList<>(
            List.of(
            SubCategoria.CASA1,
            SubCategoria.CASA2,
            SubCategoria.CASA3))),

    CARRO("carro", new ArrayList<>(
            List.of(
            SubCategoria.CARRO1,
         SubCategoria.CARRO2,
         SubCategoria.CARRO3))),

    COMPUTADOR("computador", new ArrayList<>(
            List.of(
                    SubCategoria.COMPUTADOR1,
                    SubCategoria.COMPUTADOR2,
                    SubCategoria.COMPUTADOR3)));





    public static ArrayList<Categoria> CATEGORIAS = new ArrayList<>(Arrays.asList(CASA, CARRO, COMPUTADOR));
    private String categoria;
    private ArrayList<SubCategoria> subCategorias;

    private SubCategoria subCategoria;

    Categoria(String categoria, ArrayList<SubCategoria> subCategorias) {

        this.categoria = categoria;
        this.subCategorias = subCategorias;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ArrayList<SubCategoria> getSubCategorias() {
        return subCategorias;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategorias.get(subCategorias.indexOf(subCategoria));
    }

    public SubCategoria  getSubCategoria() {
        return subCategoria;
    }
}
