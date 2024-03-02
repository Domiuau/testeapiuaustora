package br.com.aplicacao.demo.enums.categorias;

import br.com.aplicacao.demo.enums.categorias.subcategorias.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Categoria {
    CASA(TecnologiaSubCategoria.class),
    COMPUTADOR(TecnologiaSubCategoria.class),
    CARRO(TecnologiaSubCategoria.class),
    Tecnologia(TecnologiaSubCategoria.class),
    Esportes(EsportesSubCategoria.class),
    Casa(CasaSubCategoria .class),
    Beleza(BelezaSubCategoria.class),
    Livros(LivrosSubCategoria.class),
    Alimentos(AlimentosSubCategoria.class),
    Automotivo(AutomotivoSubCategoria.class),
    Brinquedos(BrinquedosSubCategoria.class),
    Joias(JoiasSubCategoria.class),
    Saude(SaudeSubCategoria.class),
    Ferramentas(FerramentasSubCategoria.class),
    Viagem(ViagemSubCategoria.class),
    Musica(MusicaSubCategoria.class),
    Animais(AnimaisSubCategoria.class),
    Arte(ArteSubCategoria.class),
    Fitness(FitnessSubCategoria.class),
    Decoracao(DecoracaoSubCategoria.class),
    Jogos(JogosSubCategoria.class),
    Vestuario(VestuarioSubCategoria.class),
    Eletronicos(EletronicosSubCategoria.class);


    private String subCategoria;

    Categoria(String subCategoria) {

        this.subCategoria = subCategoria;

    }

    private Class<? extends Enum<?>> subCategoriaEnum;

    Categoria(Class<? extends Enum<?>> subCategoriaEnum) {
        this.subCategoriaEnum = subCategoriaEnum;
    }

    public Class<? extends Enum<?>> getSubCategoriaEnum() {
        return subCategoriaEnum;
    }

    public List<? extends Enum<?>> getSubCategorias() {

               return Arrays.stream(this.getSubCategoriaEnum().getEnumConstants()).toList();

    }

    public static List<Categoria> getCategorias() {
        return Arrays.asList(values());
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }


    public String getSubCategoria() {
        return subCategoria;
    }
}
