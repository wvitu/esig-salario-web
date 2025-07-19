package com.vitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/esig_salario";
        String user = "postgres";
        String password = "729016";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Conexão estabelecida com sucesso!");
            } else {
                System.out.println("Falha na conexão.");
            }
        } catch (SQLException e) {
            System.out.println("Erro de conexão:");
            e.printStackTrace();
        }
    }
}
