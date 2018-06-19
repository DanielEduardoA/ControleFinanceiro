package br.edu.univas.financas.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import br.edu.univas.financas.controller.DespesaController;
import br.edu.univas.financas.model.Despesa;

public class DespesaFormDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private JTextField descricaoTextField;
	private JFormattedTextField valorTextField;
	private JFormattedTextField dataTextField;
	private JTextField tipoDespesaTextField;
	private JTextField fornecedorTextField;
	private JTextField centroCustosTextField;
	private JTextField numeroNotaTextField;
	
	private MascaraFormatter mascaraFormatter;
    private NumberFormatter numberFormatter;
    private GridBagConstraints gbc;
    private JFrame frame;
    private SimpleDateFormat dataFormatter;
    private Despesa despesa;

    public DespesaFormDialog() {
    	super();
		dataFormatter = new SimpleDateFormat("dd/MM/yyyy");
		dataFormatter.setLenient(false);
      
        this.setTitle("Despesa");
        this.setPreferredSize(new Dimension(900, 650));
        this.setModal(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        
        mascaraFormatter = new MascaraFormatter();
		getNumberFormatter();
		
		JLabel descricaoLabel = new JLabel();
		descricaoLabel.setText("Descrição:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.insets = new Insets(15, 15, 10, 15);
		this.add(descricaoLabel, gbc);
		
		descricaoTextField = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(15, 0, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_END;
		this.add(descricaoTextField, gbc);
		
		JLabel valorLabel = new JLabel();
		valorLabel.setText("Valor:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 15, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_END;
		this.add(valorLabel, gbc);
		
		valorTextField = new JFormattedTextField (numberFormatter) ;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(15, 0, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(valorTextField, gbc);
		
		JLabel tipoDespesaLabel = new JLabel();
		tipoDespesaLabel.setText("Tipo Despesa:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 15, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_END;
		this.add(tipoDespesaLabel, gbc);
		
		tipoDespesaTextField = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(15, 0, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(tipoDespesaTextField, gbc);
		
		JLabel dataPagamentoLabel = new JLabel();
		dataPagamentoLabel.setText("Data Pagamento:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 15, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_END;
		this.add(dataPagamentoLabel, gbc);
		
		dataTextField = new JFormattedTextField(mascaraFormatter.mascara("##/##/####"));
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 1.0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 0, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(dataTextField, gbc);
		
		JLabel fornecedorLabel = new JLabel();
		fornecedorLabel.setText("Fornecedor:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 15, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_END;
		this.add(fornecedorLabel, gbc);
		
		fornecedorTextField = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.weightx = 1.0;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(15, 0, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(fornecedorTextField, gbc);
		
		JLabel centroCustosLabel = new JLabel();
		centroCustosLabel.setText("Centro Custos:");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 15, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_END;
		this.add(centroCustosLabel, gbc);
		
		centroCustosTextField = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.weightx = 1.0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 0, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(centroCustosTextField, gbc);
		
		JLabel numeroNotaLabel = new JLabel();
		numeroNotaLabel.setText("Número Nota Fiscal:");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 15, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_END;
		this.add(numeroNotaLabel, gbc);
		
		numeroNotaTextField = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.weightx = 1.0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 0, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(numeroNotaTextField, gbc);
		
		JButton addButton = new JButton();
		addButton.setText("Salvar");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addDespesa();
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.weightx = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(15, 0, 10, 15);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(addButton, gbc);
		
		despesa = new Despesa();
    }
    
    private void addDespesa() {
    	if(valorTextField.getText().isEmpty()
    			|| dataTextField.getText().isEmpty() 
    			|| tipoDespesaTextField.getText().isEmpty()
    			|| fornecedorTextField.getText().isEmpty()
    			||centroCustosTextField.getText().isEmpty()
    			|| numeroNotaTextField.getText().isEmpty()) {
    			JOptionPane.showMessageDialog(frame, "Campos obrigatórios não foram preenchidos!",
    					"Informação", JOptionPane.WARNING_MESSAGE);
    		} else {
    			DespesaController despesaController = new DespesaController();
    			Date dataPagamento;
    			try {
    				String dataPagamentoString = dataTextField.getText();
    				dataPagamento = dataFormatter.parse(dataPagamentoString);
    				try {
    					despesa.setCentroCustos(Integer.parseInt(centroCustosTextField.getText()));
    					despesa.setDataPagamento(dataPagamento);
        				despesa.setDescricao(descricaoTextField.getText());
        				despesa.setFornecedor(fornecedorTextField.getText());
        				despesa.setNumeroNotaFiscal(numeroNotaTextField.getText());
        				despesa.setTipoDespesa(tipoDespesaTextField.getText());
        				String valorString = valorTextField.getText().replace(",", ".");
        				despesa.setValor(Double.parseDouble(valorString));
        				try {
            				if (id == null) {
            					despesaController.addDespesa(despesa);
        					} else {
        						despesaController.alterarDespesa(despesa);
        					}
            				JOptionPane.showMessageDialog(frame, "Despesa salvar com sucesso!",
            						"Sucesso", JOptionPane.INFORMATION_MESSAGE);
            				cleanFields();
            			} catch (SQLException e) {
            				JOptionPane.showMessageDialog(frame, "Erro ao salvar despesa!",
            						"Erro", JOptionPane.ERROR_MESSAGE);
            			}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, "Centro Custos Inválido!",
	    						"Erro", JOptionPane.ERROR_MESSAGE);
					}
    			} catch (ParseException e) {
    				JOptionPane.showMessageDialog(frame, "Data Inválida!",
    						"Erro", JOptionPane.ERROR_MESSAGE);
    			}
    		}
    }

    public void display() {
    	this.pack();
        this.setLocationRelativeTo(null);
        if (id != null) {
            try {
                DespesaController despesaController = new DespesaController();
                despesa = despesaController.listarDespesaPorId(id);
                descricaoTextField.setText(despesa.getDescricao());
                centroCustosTextField.setText(despesa.getCentroCustos().toString());
                numeroNotaTextField.setText(despesa.getNumeroNotaFiscal());
                tipoDespesaTextField.setText(despesa.getTipoDespesa());
                valorTextField.setValue(despesa.getValor());
                String dataPagamento = dataFormatter.format(despesa.getDataPagamento());
                dataTextField.setText(dataPagamento);
                fornecedorTextField.setText(despesa.getFornecedor());
            } catch (Exception e) {
                id = null;
            }
        }
        this.setVisible(true);
    }

    public static boolean verificaData(String texto) {
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        dataFormatada.setLenient(false);
        try {
            dataFormatada.parse(texto);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
    
    public NumberFormatter getNumberFormatter() {
		DecimalFormat decimalFormat = new DecimalFormat("#,###,###.00") ;
		numberFormatter = new NumberFormatter(decimalFormat) ;
		numberFormatter.setFormat(decimalFormat) ;
		numberFormatter.setAllowsInvalid(false) ; 
		return numberFormatter;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	private void cleanFields() {
		dataTextField.setText(null);
		centroCustosTextField.setText(null);
		descricaoTextField.setText(null);
		fornecedorTextField.setText(null);
		numeroNotaTextField.setText(null);
		tipoDespesaTextField.setText(null);
		valorTextField.setValue(null);
	}
	
}
