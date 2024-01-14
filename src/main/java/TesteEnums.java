import br.com.aplicacao.demo.enums.categorias.Categoria;

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




//        for (Enum<?> subCategoria:
//        Categoria.Tecnologia.getSubCategoriaEnum().getEnumConstants()) {
//            System.out.println(subCategoria);
//        }

    }
}
