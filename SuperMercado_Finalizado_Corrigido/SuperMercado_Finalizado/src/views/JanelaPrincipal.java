package views;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cardLayout;
	private TelaCadastroDeProdutos produto;
	private TelaDeCadastro cdt;
	private TelaDeCompra compra;
	private TelaLogin log;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
		public JanelaPrincipal() {
		setPreferredSize(new Dimension(450, 350));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 347);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(450, 350));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		cardLayout = new CardLayout();
		setContentPane(contentPane);
		contentPane.setLayout(cardLayout);
		setTitle("Janela Pricipal");
	}
	public void adicionarTela(String nome, JPanel tela) {
		setTitle(nome);
		this.contentPane.add(nome,tela);
	}
	
	public void mostrarTela(String nome) {
		this.cardLayout.show(this.contentPane, nome);
		this.pack();
	}

}
