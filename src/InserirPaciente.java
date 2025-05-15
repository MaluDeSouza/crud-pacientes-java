import java.sql.*;
import java.util.Scanner;

public class InserirPaciente {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:banco_pacientes.db";

        try (Connection conn = DriverManager.getConnection(url);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Digite o nome do paciente: ");
            String nome = scanner.nextLine();

            System.out.print("Digite a idade: ");
            int idade = scanner.nextInt();

            System.out.print("Digite o peso: ");
            double peso = scanner.nextDouble();
            scanner.nextLine(); // limpar a quebra de linha

            System.out.print("Digite os sintomas: ");
            String sintomas = scanner.nextLine();

            String sql = "INSERT INTO pacientes(nome, idade, peso, sintomas) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nome);
                pstmt.setInt(2, idade);
                pstmt.setDouble(3, peso);
                pstmt.setString(4, sintomas);
                pstmt.executeUpdate();
                System.out.println("Paciente inserido com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir paciente: " + e.getMessage());
        }
    }
}

