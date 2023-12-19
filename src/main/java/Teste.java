import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Teste {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Carregar o driver JDBC para o PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Configuração da conexão com o banco de dados
            String url = "jdbc:postgresql://dpg-cm0g8ta1hbls73dab3i0-a.oregon-postgres.render.com/db_uaustora";
            String user = "db_uaustora_user";
            String password = "kiGeqfYvB1iZnoO0YJ5KtD7AwqOj6QCR";

            // Estabelecer a conexão
            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Conexão estabelecida com o banco de dados PostgreSQL!");
                // Você pode executar consultas ou operações no banco de dados a partir daqui
            } else {
                System.out.println("Falha ao conectar ao banco de dados!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver PostgreSQL JDBC não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Conexão falhou! Verifique suas credenciais.");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}