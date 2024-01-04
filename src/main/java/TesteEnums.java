import br.com.aplicacao.demo.enums.categorias.Categoria;
import br.com.aplicacao.demo.enums.categorias.subcategorias.SubCategoria;

public class TesteEnums {

    public static void main(String[] args) {

        Categoria a = Categoria.valueOf("CASA");
        a.setSubCategoria(SubCategoria.valueOf("CASA1"));
        System.out.println(a.getSubCategoria());

    }
}
