import br.com.aplicacao.demo.enums.categorias.Categoria;

import java.util.Random;

public class TesteEnums {

    public static void main(String[] args) {

//        Categoria a = Categoria.valueOf("CASA");
//        a.setSubCategoria(SubCategoria.valueOf("CASA1"));
//        System.out.println(a.getSubCategoria());
//        System.out.println(Categoria.CATEGORIAS);

        Categoria categoria = Categoria.Animais;
        categoria.setSubCategoria("Smartphone");

        System.out.println(categoria + " " + categoria.getSubCategorias());
       // System.out.println(Categoria.getCategorias());
        Random random = new Random();
        System.out.println(Categoria.getCategorias().get(random.nextInt(Categoria.getCategorias().size())));




//        for (Enum<?> subCategoria:
//        Categoria.Tecnologia.getSubCategoriaEnum().getEnumConstants()) {
//            System.out.println(subCategoria);
//        }

    }
}
