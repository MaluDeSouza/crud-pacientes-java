package repository;

import model.Paciente;

import java.sql.*;
import java.util.ArrayList;

public class PacienteRepository {
    private static final String url = "jdbc:sqlite:banco_pacientes.db";

    public void createPaciente (Paciente paciente){
        String sql = "INSERT INTO pacientes (nome, idade, diagnostico, comorbidades, observacoes) VALUES (?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, paciente.getNome());
            pstmt.setInt(2, paciente.getIdade());
            pstmt.setString(3, paciente.getDiagnostico());
            pstmt.setString(4, paciente.getComorbidades());
            pstmt.setString(5, paciente.getObservacoes());

            pstmt.executeUpdate();
            System.out.println("Paciente cadastrado com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastra paciente: " + e.getMessage());
        }
    }

    public void readPaciente(){
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
           // ArrayList<Paciente> pacientes = new ArrayList<>();
            String sql = "SELECT * FROM pacientes";

            ResultSet resultado = stmt.executeQuery(sql);

            System.out.println("Pacientes cadastrados:\n");

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                int idade = resultado.getInt("idade");
                String diagnostico = resultado.getString("diagnostico");
                String comorbidades = resultado.getString("comorbidades");
                String observacoes = resultado.getString("observacoes");
                Paciente paciente = new Paciente(id, nome, idade, diagnostico, comorbidades, observacoes);
                //pacientes.add(paciente);

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Idade: " + idade);
                System.out.println("Diagnostico: " + diagnostico);
                System.out.println("Comorbidades: " + comorbidades);
                System.out.println("Observacoes: " +  observacoes);
                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        }
    }

    public void updatePaciente(Paciente paciente){
        String sql = "UPDATE pacientes SET " +
                "nome = ?, " +
                "idade = ?, " +
                "diagnostico = ?, " +
                "comorbidades = ?, " +
                "observacoes = ? " +
                "WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)){


            pstmt.setString(1, paciente.getNome());
            pstmt.setInt(2,paciente.getIdade());
            pstmt.setString(3,paciente.getDiagnostico());
            pstmt.setString(4,paciente.getComorbidades());
            pstmt.setString(5,paciente.getObservacoes());
            pstmt.setInt(6, paciente.getId());

            pstmt.executeUpdate();
            System.out.println("Paciente atualizado com sucesso");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar os dados: " + e.getMessage());
        }
    }

    public void deletePaciente(int id){
        String sql = "DELETE FROM pacientes WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql)){
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("Paciente excluido  com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar: " +e.getMessage());
        }
    }

    public  Paciente  findById(int id){
        String sql = "SELECT * FROM pacientes WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet  rs = pstmt.executeQuery();

            if(rs.next()){
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String diagnostico = rs.getString("diagnostico");
                String comorbidades = rs.getString("comorbidades");
                String observacoes = rs.getString("observacoes");

                return new Paciente(id, nome, idade, diagnostico, comorbidades, observacoes);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        }
        return null;
    }
}
