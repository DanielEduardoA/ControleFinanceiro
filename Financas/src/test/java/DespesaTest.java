import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.edu.univas.financas.dao.DespesaDAO;
import br.edu.univas.financas.model.Despesa;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unused")
public class DespesaTest {
	@Mock
	private DespesaDAO despesaDAO;
	
	private void verifyConnectionUsageOnSuccess(Connection connectionMock) throws SQLException {
		Mockito.verify(connectionMock, Mockito.times(1)).commit();
	}

	@Test
	public void criarDespesa() throws SQLException {
		List<Despesa> despesas;
		Despesa despesa = new Despesa();
		GIVEN:{
			when(despesaDAO.listarTodos()).thenReturn(Arrays.asList(despesa));
		}
		WHEN: {
			despesaDAO.criar(despesa);
			despesas = despesaDAO.listarTodos();
		}
		THEN: {
			assertThat(despesas.size(), equalTo(1));
		}
	}
	
	@Test
	public void alterarDespesa() throws SQLException {
		List<Despesa> despesas;
		Despesa despesa = new Despesa();
		GIVEN:{
			when(despesaDAO.listarTodos()).thenReturn(Arrays.asList(despesa));
		}
		WHEN: {
			despesaDAO.alterar(despesa);
			despesas = despesaDAO.listarTodos();
		}
		THEN: {
			assertThat(despesas.size(), equalTo(1));
		}	
	}
	
	@Test
	public void apagarDespesa() throws SQLException {
		List<Despesa> despesas;
		Despesa despesa = new Despesa();
		GIVEN:{
			when(despesaDAO.listarTodos()).thenReturn(new ArrayList<>());
		}
		WHEN: {
			despesaDAO.apagar(1l);
			despesas = despesaDAO.listarTodos();
		}
		THEN: {
			assertThat(despesas.size(), equalTo(0));
		}	
	}
}
