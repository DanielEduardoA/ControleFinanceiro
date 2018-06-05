package br.edu.univas.financas.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.univas.financas.dao.ReceitaDAO;
import br.edu.univas.financas.model.Receita;


public class ReceitaController {
	private ReceitaDAO receitaDAO;

	public ReceitaController() {
		receitaDAO = new ReceitaDAO();
	}
	
	public Receita listarReceitaPorId(Long id) throws SQLException {
		Receita receita = receitaDAO.listarPorId(id);
		return receita;
	}
	
	public List<Receita> listarTodasReceitas() throws SQLException {
		List<Receita> receitas = receitaDAO.listarTodos();
		return receitas;
	}
	
	public List<Receita> listarPorDescricao(String descricao) throws SQLException {
		List<Receita> receitas = receitaDAO.listarPorDescricao(descricao);
		return receitas;
	}
	
	public void apagar(Long id) throws SQLException {
		receitaDAO.apagar(id);
	}
	
	public void addReceita(Receita receita) throws SQLException {
		receitaDAO.criar(receita);
	}
	
	public void alterarReceita(Receita receita) throws SQLException {
		receitaDAO.alterar(receita);
	}
}
