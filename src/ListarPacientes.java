import java.sql.*;

public class ListarPacientes {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:banco_pacientes.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM pacientes";

            ResultSet resultado = stmt.executeQuery(sql);

            System.out.println("Pacientes cadastrados:\n");

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                int idade = resultado.getInt("idade");
                double peso = resultado.getDouble("peso");
                String sintomas = resultado.getString("sintomas");
                String observacoes = resultado.getString("observacoes");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Idade: " + idade);
                System.out.println("Peso: " + peso);
                System.out.println("Sintomas: " + sintomas);
                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        }
    }
}

