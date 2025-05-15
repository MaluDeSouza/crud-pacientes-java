package repository;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TabelaUtil {

    private static final String url = "jdbc:sqlite:banco_pacientes.db";

    public static void criarTabelaPacientes() {
        String sql = " CREATE TABLE IF NOT EXISTS pacientes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "idade INTEGER NOT NULL," +
                "diagnostico TEXT," +
                "comorbidades TEXT," +
                 "observacoes TEXT"+
                ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'pacientes' criada com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }


    }
    public static void excluirTabelaPacientes() {
        String sql = "DROP TABLE IF EXISTS pacientes;";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabela 'pacientes' excluída com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir tabela: " + e.getMessage());
        }
    }
}
