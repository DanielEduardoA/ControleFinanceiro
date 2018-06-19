package br.edu.univas.financas.controller;

import java.sql.SQLException;

import br.edu.univas.financas.view.DespesaListDialog;
import br.edu.univas.financas.view.ListenerTopMenus;
import br.edu.univas.financas.view.MainView;
import br.edu.univas.financas.view.ReceitaListDialog;

public class MainController {

	private MainView mainView;
	
	public MainController() {
		mainView = new MainView();
	}
	
	public void initApplication() {
		mainView.setListener(new ListenerTopMenus() {
			
			@Override
			public void listarReceita() throws SQLException {
				showListReceitaPanel();
			}
			
			@Override
			public void listarDespesa() throws SQLException {
				showListDespesaPanel();
			}
		});
		mainView.setVisible(true);
	}
	
	private void showListDespesaPanel() throws SQLException {
		DespesaListDialog despesaListDialog = new DespesaListDialog();
		despesaListDialog.display();
	}
	private void showListReceitaPanel() throws SQLException {
		ReceitaListDialog receitaListDialog = new ReceitaListDialog();
		receitaListDialog.display();
	}
}
