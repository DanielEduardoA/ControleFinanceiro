package br.edu.univas.financas.view;

import java.sql.SQLException;

public interface ListenerTopMenus {

	public void listarDespesa() throws SQLException;
	public void listarReceita() throws SQLException;
	
}
