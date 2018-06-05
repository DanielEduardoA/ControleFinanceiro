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
import br.edu.univas.financas.model.Despesa;

public class DespesaDAO {
	private Connection conexao;
	
	public DespesaDAO() {
		conexao = DatabaseConnection.obterInstancia().getConexao(ConfiguracaoDatabase.usuario, ConfiguracaoDatabase.senha, ConfiguracaoDatabase.url);
	}
	
	public void criar(Despesa despesa) throws SQLException {
		String sql = "INSERT INTO despesa (valor, descricao, data_pagamento, tipo_despesa, fornecedor, centro_custos, numero_nota_fiscal) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setDouble(1, despesa.getValor());
			statement.setString(2, despesa.getDescricao());
			java.sql.Date sqlDate = new java.sql.Date(despesa.getDataPagamento().getTime());
			statement.setDate(3, sqlDate);
			statement.setString(4, despesa.getTipoDespesa());
			statement.setString(5, despesa.getFornecedor());
			statement.setInt(6, despesa.getCentroCustos());
			statement.setString(7, despesa.getNumeroNotaFiscal());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void alterar(Despesa despesa) throws SQLException {
		String sql = "UPDATE despesa SET valor = ?, descricao = ?, data_pagamento = ?, tipo_despesa = ?, fornecedor = ?, centro_custos=?,  numero_nota_fiscal = ?  WHERE id = ?";
		try (PreparedStatement statement = conexao.prepareStatement(sql)){
			statement.setDouble(1, despesa.getValor());
			statement.setString(2, despesa.getDescricao());
			java.sql.Date sqlDate = new java.sql.Date(despesa.getDataPagamento().getTime());
			statement.setDate(3, sqlDate);
			statement.setString(4, despesa.getTipoDespesa());
			statement.setString(5, despesa.getFornecedor());
			statement.setInt(6, despesa.getCentroCustos());
			statement.setString(7, despesa.getNumeroNotaFiscal());
			statement.setLong(8, despesa.getId());
			statement.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void apagar(Long id) throws SQLException {
		String sql = "DELETE FROM despesa WHERE id = ?";
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setLong(1, id);
			statement.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public List<Despesa> listarTodos() throws SQLException {
		String sql = "SELECT * FROM despesa ";
		List<Despesa> despesaList = new ArrayList<Despesa>();
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			Despesa despesa = new Despesa();
			if (resultSet != null) {
				while (resultSet.next()) {
					despesa = new Despesa();
					despesa.setId(resultSet.getLong("id"));
					despesa.setCentroCustos(resultSet.getInt("centro_custos"));
					despesa.setDataPagamento(resultSet.getDate("data_pagamento"));
					despesa.setDescricao(resultSet.getString("descricao"));
					despesa.setFornecedor(resultSet.getString("fornecedor"));
					despesa.setNumeroNotaFiscal(resultSet.getString("numero_nota_fiscal"));
					despesa.setTipoDespesa(resultSet.getString("tipo_despesa"));
					despesa.setValor(resultSet.getDouble("valor"));
					despesaList.add(despesa);
				}
			}
		} catch (SQLException e) {
			throw e;
		}
		return despesaList;
	}
	
	public List<Despesa> listarPorDescricao(String descricao) throws SQLException {
		String sql = "SELECT * FROM despesa where descricao LIKE '%" + descricao + "%'";
		List<Despesa> despesaList = new ArrayList<Despesa>();
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			Despesa despesa = new Despesa();
			if (resultSet != null) {
				while (resultSet.next()) {
					despesa = new Despesa();
					despesa.setId(resultSet.getLong("id"));
					despesa.setCentroCustos(resultSet.getInt("centro_custos"));
					despesa.setDataPagamento(resultSet.getDate("data_pagamento"));
					despesa.setDescricao(resultSet.getString("descricao"));
					despesa.setFornecedor(resultSet.getString("fornecedor"));
					despesa.setNumeroNotaFiscal(resultSet.getString("numero_nota_fiscal"));
					despesa.setTipoDespesa(resultSet.getString("tipo_despesa"));
					despesa.setValor(resultSet.getDouble("valor"));
					despesaList.add(despesa);
				}
			}
		} catch (SQLException e) {
			throw e;
		}
		return despesaList;
	}
	
	public Despesa listarPorId(Long id) throws SQLException {
		String sql = "SELECT * FROM despesa where id = " + id;
		Despesa despesa = new Despesa();
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					despesa.setId(resultSet.getLong("id"));
					despesa.setCentroCustos(resultSet.getInt("centro_custos"));
					despesa.setDataPagamento(resultSet.getDate("data_pagamento"));
					despesa.setDescricao(resultSet.getString("descricao"));
					despesa.setFornecedor(resultSet.getString("fornecedor"));
					despesa.setNumeroNotaFiscal(resultSet.getString("numero_nota_fiscal"));
					despesa.setTipoDespesa(resultSet.getString("tipo_despesa"));
					despesa.setValor(resultSet.getDouble("valor"));
				}
			}
		} catch (SQLException e) {
			throw e;
		}
		return despesa;
	}

}
