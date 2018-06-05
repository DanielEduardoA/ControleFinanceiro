package br.edu.univas.financas.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.univas.financas.dao.DespesaDAO;
import br.edu.univas.financas.model.Despesa;

public class DespesaController {

	private DespesaDAO despesaDAO;
	
	public DespesaController() {
		despesaDAO = new DespesaDAO();
	}
	
	public void addDespesa(Despesa despesa) throws SQLException {
		despesaDAO.criar(despesa);
	}
	
	public void alterarDespesa(Despesa despesa) throws SQLException {
		despesaDAO.alterar(despesa);
	}
	
	public Despesa listarDespesaPorId(Long id) throws SQLException {
		Despesa despesa = despesaDAO.listarPorId(id);
		return despesa;
	}
	
	public List<Despesa> listarTodasDespesas() throws SQLException {
		List<Despesa> despesas = despesaDAO.listarTodos();
		return despesas;
	}
	
	public List<Despesa> listarPorDescricao(String descricao) throws SQLException {
		List<Despesa> despesas = despesaDAO.listarPorDescricao(descricao);
		return despesas;
	}
	
	public void apagar(Long id) throws SQLException {
		despesaDAO.apagar(id);
	}
}
