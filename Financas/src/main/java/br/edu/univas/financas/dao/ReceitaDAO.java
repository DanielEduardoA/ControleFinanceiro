package br.edu.univas.financas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.univas.financas.database.ConfiguracaoDatabase;
import br.edu.univas.financas.database.DatabaseConnection;
import br.edu.univas.financas.model.Receita;

public class ReceitaDAO {
	
private Connection conexao;
	
	public ReceitaDAO() {
		conexao = DatabaseConnection.obterInstancia().getConexao(ConfiguracaoDatabase.usuario, ConfiguracaoDatabase.senha, ConfiguracaoDatabase.url);
	}
	
	public void criar(Receita receita) throws SQLException {
		String sql = "INSERT INTO receita (valor, descricao, data_recebimento, tipo_receita, cliente, forma_pagamento) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setDouble(1, receita.getValor());
			statement.setString(2, receita.getDescricao());
			java.sql.Date sqlDate = new java.sql.Date(receita.getDataRecebimento().getTime());
			statement.setDate(3, sqlDate);
			statement.setString(4, receita.getTipoReceita());
			statement.setString(5, receita.getCliente());
			statement.setString(6, receita.getFormaPagamento());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void alterar(Receita receita) throws SQLException {
		String sql = "UPDATE receita SET valor = ?, descricao = ?, data_recebimento = ?, tipo_receita = ?, cliente = ?, forma_pagamento=?  WHERE id = ?";
		try (PreparedStatement statement = conexao.prepareStatement(sql)){
			statement.setDouble(1, receita.getValor());
			statement.setString(2, receita.getDescricao());
			java.sql.Date sqlDate = new java.sql.Date(receita.getDataRecebimento().getTime());
			statement.setDate(3, sqlDate);
			statement.setString(4, receita.getTipoReceita());
			statement.setString(5, receita.getCliente());
			statement.setString(6, receita.getFormaPagamento());
			statement.setLong(7, receita.getId());
			statement.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void apagar(Long id) throws SQLException {
		String sql = "DELETE FROM receita WHERE id = ?";
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setLong(1, id);
			statement.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public List<Receita> listarTodos() throws SQLException {
		String sql = "SELECT * FROM receita ";
		List<Receita> receitaList = new ArrayList<Receita>();
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			Receita receita = new Receita();
			if (resultSet != null) {
				while (resultSet.next()) {
					receita = new Receita();
					receita.setId(resultSet.getLong("id"));
					receita.setDataRecebimento(resultSet.getDate("data_recebimento"));
					receita.setDescricao(resultSet.getString("descricao"));
					receita.setCliente(resultSet.getString("cliente"));
					receita.setFormaPagamento(resultSet.getString("forma_pagamento"));
					receita.setTipoReceita(resultSet.getString("tipo_receita"));
					receita.setValor(resultSet.getDouble("valor"));
					receitaList.add(receita);
				}
			}
		} catch (SQLException e) {
			throw e;
		}
		return receitaList;
	}
	
	public List<Receita> listarPorDescricao(String descricao) throws SQLException {
		String sql = "SELECT * FROM receita where descricao LIKE '%" + descricao + "%'";
		List<Receita> receitaList = new ArrayList<Receita>();
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			Receita receita = new Receita();
			if (resultSet != null) {
				while (resultSet.next()) {
					receita = new Receita();
					receita.setId(resultSet.getLong("id"));
					receita.setDataRecebimento(resultSet.getDate("data_recebimento"));
					receita.setDescricao(resultSet.getString("descricao"));
					receita.setCliente(resultSet.getString("cliente"));
					receita.setFormaPagamento(resultSet.getString("forma_pagamento"));
					receita.setTipoReceita(resultSet.getString("tipo_receita"));
					receita.setValor(resultSet.getDouble("valor"));
					receitaList.add(receita);
				}
			}
		} catch (SQLException e) {
			throw e;
		}
		return receitaList;
	}
	
	public Receita listarPorId(Long id) throws SQLException {
		String sql = "SELECT * FROM receita where id = " + id;
		Receita receita = new Receita();
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					receita.setId(resultSet.getLong("id"));
					receita.setDataRecebimento(resultSet.getDate("data_recebimento"));
					receita.setDescricao(resultSet.getString("descricao"));
					receita.setCliente(resultSet.getString("cliente"));
					receita.setFormaPagamento(resultSet.getString("forma_pagamento"));
					receita.setTipoReceita(resultSet.getString("tipo_receita"));
					receita.setValor(resultSet.getDouble("valor"));
				}
			}
		} catch (SQLException e) {
			throw e;
		}
		return receita;
	}

}
