package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TelaDeCadastro extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textNome;
    private JTextField textCpf;
    private JButton btnCadastrar;
    private DefaultTableModel modeloTabela;
    private DefaultTableModel modeloCarrinho;
    private JButton btnVoltar;
    public TelaDeCadastro() {
        setLayout(null);

        JLabel lblTituloCadastro = new JLabel("CADASTRO");
        lblTituloCadastro.setBounds(155, 5, 139, 35);
        lblTituloCadastro.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
        add(lblTituloCadastro);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNome.setBounds(108, 66, 78, 35);
        add(lblNome);

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCpf.setBounds(108, 136, 78, 35);
        add(lblCpf);

        textNome = new JTextField();
        textNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textNome.setBounds(231, 70, 113, 31);
        add(textNome);
        textNome.setColumns(10);

        textCpf = new JTextField();
        textCpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textCpf.setBounds(231, 140, 113, 31);
        add(textCpf);
        textCpf.setColumns(10);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnCadastrar.setBounds(172, 239, 106, 35);
        add(btnCadastrar);
        
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(10, 11, 80, 23);
        btnVoltar.setBackground(new Color(255, 200, 200));
        add(btnVoltar);
    }
    
    public void voltar(ActionListener actionListener) {
        System.out.println("[VIEW] voltar() chamado. view=" + System.identityHashCode(this)
            + " listener=" + actionListener.getClass().getName());
        this.btnVoltar.addActionListener(actionListener);
    }
    
    // Método para adicionar ação ao botão
    public void cadastrar(ActionListener actionListener) {
        this.btnCadastrar.addActionListener(actionListener);
    }

    // Getters que pegam o valor ATUAL dos campos
    public String getNome() {
        return textNome.getText();
    }

    public String getCpf() {
        return textCpf.getText();
    }

    public void adicionarController(ComponentListener listener) {
        this.addComponentListener(listener);
    }
    
    
}
