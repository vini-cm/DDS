package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TelaCadastroDeProdutos extends JPanel {

    private static final long serialVersionUID = 1L;

    // ===== Componentes =====
    private JButton btn_adicionar;
    private JButton btnVoltar;
    private JTextField textID;
    private JTextField textnome;
    private JTextField textDesc;
    private JTextField textQtd;
    private JTextField textpreco;
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;

    // ===== Construtor =====
    public TelaCadastroDeProdutos() {
        setPreferredSize(new Dimension(700, 500));
        setBackground(Color.WHITE);
        setLayout(null);

        // ===== Título =====
        JLabel lblTitulo = new JLabel("CADASTRAR PRODUTO");
        lblTitulo.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
        lblTitulo.setBounds(42, 11, 350, 52);
        add(lblTitulo);

        // ===== Labels =====
        JLabel lblId = new JLabel("ID");
        lblId.setBounds(56, 77, 86, 14);
        add(lblId);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(56, 102, 86, 14);
        add(lblNome);

        JLabel lblDesc = new JLabel("Descrição");
        lblDesc.setBounds(56, 127, 86, 14);
        add(lblDesc);

        JLabel lblQtd = new JLabel("Quantidade");
        lblQtd.setBounds(56, 152, 86, 16);
        add(lblQtd);

        JLabel lblPreco = new JLabel("Preço");
        lblPreco.setBounds(56, 179, 86, 14);
        add(lblPreco);

        // ===== Campos de texto =====
        textID = new JTextField();
        textID.setBounds(181, 74, 86, 20);
        add(textID);

        textnome = new JTextField();
        textnome.setBounds(181, 99, 180, 20);
        add(textnome);

        textDesc = new JTextField();
        textDesc.setBounds(181, 124, 180, 20);
        add(textDesc);

        textQtd = new JTextField();
        textQtd.setBounds(181, 150, 86, 20);
        add(textQtd);

        textpreco = new JTextField();
        textpreco.setBounds(181, 176, 86, 20);
        add(textpreco);

        // ===== Botão Adicionar =====
        btn_adicionar = new JButton("Adicionar Produto");
        btn_adicionar.setBounds(107, 234, 160, 30);
        btn_adicionar.setBackground(new Color(200, 255, 200));
        add(btn_adicionar);

        // ===== Botão Voltar =====
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(10, 11, 80, 23);
        btnVoltar.setBackground(new Color(255, 200, 200));
        add(btnVoltar);

        // ===== Tabela de produtos (Estoque) =====
        modeloTabela = new DefaultTableModel(
            new Object[]{"ID", "Nome", "Descrição", "Preço", "Quantidade"}, 0
        );

        tabelaProdutos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        scrollPane.setBounds(400, 50, 280, 350);
        add(scrollPane);

        JLabel lblEstoque = new JLabel("Estoque Atual");
        lblEstoque.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblEstoque.setBounds(470, 20, 200, 20);
        add(lblEstoque);

        // Debug opcional
        System.out.println("[VIEW ctor] TelaCadastroDeProdutos criada. this=" + System.identityHashCode(this));
    }

    // ===== Métodos para adicionar listeners =====
    public void adicionar(ActionListener actionListener) {
        this.btn_adicionar.addActionListener(actionListener);
    }

    public void voltar(ActionListener actionListener) {
        this.btnVoltar.addActionListener(actionListener);
    }

    // ===== Ouvinte de componente =====
    public void adicionarOuvinte(ComponentListener listener) {
        this.addComponentListener(listener);
    }

    // ===== Getters =====
    public int getId() {
        try {
            return Integer.parseInt(textID.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public String getNome() {
        return textnome.getText();
    }

    public String getDescricao() {
        return textDesc.getText();
    }

    public double getPreco() {
        try {
            return Double.parseDouble(textpreco.getText());
        } catch (Exception e) {
            return 0.0;
        }
    }

    public int getQuantidade() {
        try {
            return Integer.parseInt(textQtd.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    // ===== Setters =====
    public void setTextID(String id) { this.textID.setText(id); }
    public void setTextNome(String nome) { this.textnome.setText(nome); }
    public void setTextDesc(String desc) { this.textDesc.setText(desc); }
    public void setTextQtd(String qtd) { this.textQtd.setText(qtd); }
    public void setTextPreco(String preco) { this.textpreco.setText(preco); }

    // ===== Acesso à tabela =====
    public DefaultTableModel getModeloTabela() {
        return this.modeloTabela;
    }

    public JTable getTabelaProdutos() {
        return this.tabelaProdutos;
    }
}
