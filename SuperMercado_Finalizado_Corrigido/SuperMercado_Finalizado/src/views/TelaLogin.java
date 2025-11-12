package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;

public class TelaLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JButton btnCadastrar, btnLogin;
	
	

	/**
	 * Create the panel.
	 */
	public TelaLogin() {
		setPreferredSize(new Dimension(450, 350));
		setLayout(null);
		
		JLabel nome = new JLabel("Nome :");
		nome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nome.setBounds(80, 51, 71, 17);
		add(nome);
		
		JLabel cpf = new JLabel("CPF :");
		cpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cpf.setBounds(80, 103, 71, 22);
		add(cpf);
		
		txtNome = new JTextField();
		txtNome.setForeground(new Color(192, 192, 192));
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNome.setBounds(222, 45, 141, 28);
		add(txtNome);
		txtNome.setColumns(10);
		
		txtCpf = new JTextField();
		txtCpf.setForeground(new Color(192, 192, 192));
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCpf.setBounds(222, 100, 141, 28);
		add(txtCpf);
		txtCpf.setColumns(10);
		
		btnLogin = new JButton("Entrar");
		
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setBounds(162, 161, 141, 28);
		add(btnLogin);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastrar.setBounds(162, 261, 141, 28);
		add(btnCadastrar);
		
		JLabel lblNCdt = new JLabel("Não tem Cadastro :");
		lblNCdt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNCdt.setBounds(80, 221, 223, 17);
		add(lblNCdt);
		


			
	

	}
	
	public void entrar(ActionListener actionListener) {
		this.btnLogin.addActionListener(actionListener);
	}
	public void cadastrar(ActionListener actionListener) {
		this.btnCadastrar.addActionListener(actionListener);
	}
	public String getNome() {
		return this.txtNome.getText();
		
	}
	public String getCpf(){
		return this.txtCpf.getText();
	}
}
