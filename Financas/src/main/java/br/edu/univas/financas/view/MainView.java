package br.edu.univas.financas.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel centerPanel;
	private ListenerTopMenus listener;
	
	public MainView() {
		this.setTitle("Controle Financeiro");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		addComponents();
	}
	
	public void addComponents() {
		this.getContentPane().setLayout(new BorderLayout());
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.LIGHT_GRAY);
		createMenus(northPanel);
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		this.getContentPane().add(northPanel, BorderLayout.NORTH);
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
	}
	
	private void createMenus(JPanel panel) {
		JMenuBar menuBar = new JMenuBar();
		
		JMenuItem menuReceita = new JMenuItem("Receita");
		menuBar.add(menuReceita);
		menuReceita.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					listener.listarReceita();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(centerPanel, "Erro ao listar as receitas",
							"Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JMenuItem menuDespesa = new JMenuItem("Despesa");
		menuBar.add(menuDespesa);
		menuDespesa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					listener.listarDespesa();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(centerPanel, "Erro ao listar as despesas",
							"Erro", JOptionPane.ERROR_MESSAGE);
					
				}
			}
		});
		panel.add(menuBar);
	}

	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public ListenerTopMenus getListener() {
		return listener;
	}

	public void setListener(ListenerTopMenus listener) {
		this.listener = listener;
	}
}






