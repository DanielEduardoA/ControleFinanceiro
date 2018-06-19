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

import br.edu.univas.financas.controller.ReceitaController;
import br.edu.univas.financas.model.Receita;

public class ReceitaFormDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private JTextField descricaoTextField;
	private JFormattedTextField valorTextField;
	private JFormattedTextField dataTextField;
	private JTextField tipoReceitaTextField;
	private JTextField clienteTextField;
	private JTextField formaPagamentoTextField;
	
	private MascaraFormatter mascaraFormatter;
    private NumberFormatter numberFormatter;
    private GridBagConstraints gbc;
    private JFrame frame;
	private SimpleDateFormat dataFormatter;
	private Receita receita;

    public ReceitaFormDialog() {
        super();
        receita = new Receita();
		dataFormatter = new SimpleDateFormat("dd/MM/yyyy");
		dataFormatter.setLenient(false);
        this.setTitle("Receita");
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
		
		JLabel tipoReceitaLabel = new JLabel();
		tipoReceitaLabel.setText("Tipo Receita:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 15, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_END;
		this.add(tipoReceitaLabel, gbc);
		
		tipoReceitaTextField = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(15, 0, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(tipoReceitaTextField, gbc);
		
		JLabel dataRecebimentoLabel = new JLabel();
		dataRecebimentoLabel.setText("Data Recebimento:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 15, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_END;
		this.add(dataRecebimentoLabel, gbc);
		
		dataTextField = new JFormattedTextField(mascaraFormatter.mascara("##/##/####"));
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 1.0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 0, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(dataTextField, gbc);
		
		JLabel clienteLabel = new JLabel();
		clienteLabel.setText("Cliente:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 15, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_END;
		this.add(clienteLabel, gbc);
		
		clienteTextField = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.weightx = 1.0;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(15, 0, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(clienteTextField, gbc);
		
		JLabel fornaPagamentoLabel = new JLabel();
		fornaPagamentoLabel.setText("Forma Pagamento:");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 15, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_END;
		this.add(fornaPagamentoLabel, gbc);
		
		formaPagamentoTextField = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.weightx = 1.0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 0, 10, 15);
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(formaPagamentoTextField, gbc);
		
		JButton addButton = new JButton();
		addButton.setText("Salvar");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addReceita();
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
    }
    
	private void addReceita() {
		if(valorTextField.getText().isEmpty()
				|| dataTextField.getText().isEmpty() 
				||tipoReceitaTextField.getText().isEmpty()
				|| clienteTextField.getText().isEmpty()
				||formaPagamentoTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Campos obrigatórios não foram preenchidos!",
						"Informação", JOptionPane.WARNING_MESSAGE);
			} else  {
				try {
					String dataRecebimentoString = dataTextField.getText();
					Date dataRecebimento = dataFormatter.parse(dataRecebimentoString);
					receita.setDescricao(descricaoTextField.getText());
					receita.setCliente(clienteTextField.getText());
					receita.setDataRecebimento(dataRecebimento);
					receita.setFormaPagamento(formaPagamentoTextField.getText());
					receita.setTipoReceita(tipoReceitaTextField.getText());
					String valorString = valorTextField.getText().replace(",", ".");;
					receita.setValor(Double.parseDouble(valorString));
					try {
						ReceitaController receitaController = new ReceitaController();
						if (id == null) {
							receitaController.addReceita(receita);
						} else {
							receitaController.alterarReceita(receita);
						}
						
						JOptionPane.showMessageDialog(frame, "Receita salva com sucesso!",
								"Sucesso", JOptionPane.INFORMATION_MESSAGE);
						cleanFields();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(frame, "Erro ao salvar receita!",
								"Erro", JOptionPane.ERROR_MESSAGE);
					};
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
                ReceitaController receitaController = new ReceitaController();
                receita = receitaController.listarReceitaPorId(id);
                descricaoTextField.setText(receita.getDescricao());
                clienteTextField.setText(receita.getCliente());
                formaPagamentoTextField.setText(receita.getFormaPagamento());
                tipoReceitaTextField.setText(receita.getTipoReceita());
                valorTextField.setValue(receita.getValor());
                String dataRecebimento = dataFormatter.format(receita.getDataRecebimento());
                dataTextField.setText(dataRecebimento);
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
		valorTextField.setValue(null);
		dataTextField.setText(null);
		descricaoTextField.setText(null);
		clienteTextField.setText(null);
		formaPagamentoTextField.setText(null);
		tipoReceitaTextField.setText(null);
	}	
}
