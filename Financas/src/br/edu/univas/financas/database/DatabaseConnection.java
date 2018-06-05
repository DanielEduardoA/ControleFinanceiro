package br.edu.univas.financas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static DatabaseConnection instancia;
	
	public static DatabaseConnection obterInstancia() {
		if (instancia == null) {
			instancia = new DatabaseConnection();
		}
		return instancia;
	}
	
	public Connection getConexao(String username, String password, String url) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			try {
				conn = DriverManager.getConnection(url,username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}