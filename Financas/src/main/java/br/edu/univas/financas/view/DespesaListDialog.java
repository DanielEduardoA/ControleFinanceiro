package br.edu.univas.financas.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import br.edu.univas.financas.controller.DespesaController;
import br.edu.univas.financas.model.Despesa;

public class DespesaListDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DespesaController despesaController;
	private List<Despesa> depesas;
    private JPanel panelPesquisar;
    private JPanel panelBotoes;
    private JLabel descricaoLabel;
    private JTextField descricaoTextField;
    private JButton buttonPesquisar;
    private JButton buttonNovo;
    private JButton buttonAlterar;
    private JButton buttonExcluir;
    private JTable tabela;
    private JFrame frame;
    private GridBagConstraints gbc;
    private DefaultTableModel dtm;

    public DespesaListDialog() {
    	despesaController = new DespesaController();
        this.setTitle("Cadastro de Despesa");
        this.setModal(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
		addFiltro();
		addBotoes();
        atualizarTabela("");
    }
    
    private void addFiltro() {
    	 descricaoLabel = new JLabel("Descrição Despesa: ");
         descricaoTextField = new JTextField(30);
         
         buttonPesquisar = new JButton("Pesquisar");
         buttonPesquisar.addActionListener(new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				  atualizarTabela(descricaoTextField.getText());
 			}
 		});
         panelPesquisar = new JPanel();
         panelPesquisar.add(descricaoLabel);
         panelPesquisar.add(descricaoTextField);
         panelPesquisar.add(buttonPesquisar);
        gbc.gridx = 0;
 		gbc.gridy = 1;
 		gbc.weightx = 1.0;
 		gbc.insets = new Insets(15, 15, 15, 15);
 		gbc.anchor = GridBagConstraints.NORTH;
 		this.add(panelPesquisar, gbc);
    }
    
    private void addBotoes() {
    	buttonNovo = new JButton("Novo");
        buttonNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 dispose();
				 DespesaFormDialog dialog = new DespesaFormDialog();
		         dialog.display();
			}
		});
        
        buttonAlterar = new JButton("Alterar");
        buttonAlterar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 int row = tabela.getSelectedRow();
		            if (row == -1) {
		            	JOptionPane.showMessageDialog(frame,  "Selecionar uma despesa", "Erro", JOptionPane.ERROR_MESSAGE);
		            } else {
		            	Long id = (Long) dtm.getValueAt(row, 0);
		            	dispose();
		                DespesaFormDialog dialog = new DespesaFormDialog();
		                dialog.setId(id);
		                dialog.display();
		            }
			}
		});
        
        buttonExcluir = new JButton("Excluir");
        buttonExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tabela.getSelectedRow();
	            if (row == -1) {
	                JOptionPane.showMessageDialog(frame,  "Selecionar uma despesa", "Erro", JOptionPane.ERROR_MESSAGE);
	            } else {
	            	Long id = (Long) dtm.getValueAt(row, 0);
	                try {
						despesaController.apagar(id);
						atualizarTabela("");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(frame,  "Erro ao apagar uma despesa", "Erro", JOptionPane.ERROR_MESSAGE);
					}
	            }
			}
		});
       
        panelBotoes = new JPanel();
        panelBotoes.add(buttonNovo);
        panelBotoes.add(buttonAlterar);
        panelBotoes.add(buttonExcluir);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(panelBotoes, gbc);
    }
    
    private void atualizarTabela(String valorPesquisa) {
  		criarTabela(valorPesquisa);
  		tabela.removeColumn(tabela.getColumn(tabela.getModel().getColumnName(0)));
		
		JScrollPane tableScroll = new JScrollPane(tabela);
		tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.anchor = GridBagConstraints.SOUTH;
		this.add(tableScroll, gbc);
		this.revalidate();
	}

    public void display() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void criarTabela(String valorPesquisa) {
        try {
        	if (valorPesquisa.isEmpty()) {
        		depesas = despesaController.listarTodasDespesas();
			} else {
				depesas = despesaController.listarPorDescricao(valorPesquisa);
			}
        	Vector<String> columnNames = new Vector<>();
    		columnNames.add("Id");
    		columnNames.add("Descrição");
    		columnNames.add("Valor");
    		
    		tabela = new JTable(null, columnNames);
        	dtm = (DefaultTableModel) tabela.getModel();
    		for (Despesa despesa : depesas) {
    			dtm.addRow(new Object[] {
    					despesa.getId(),
    					despesa.getDescricao(),
    					despesa.getValor()
    			});
    		} 
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(frame, "Erro ao listar depesas", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
