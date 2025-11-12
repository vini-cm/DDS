package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Produto;
import Model.ProdutoDAO;
import Model.Usuario;
import controller.TelaDeCompraController;

public class TelaDeCompra extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;
    private JTable tabelaCarrinho;
    private DefaultTableModel modeloCarrinho;
    private JLabel lblTotal;
    private JLabel lblUsuario;
    private Usuario usuarioLogado;
    private TelaDeCompraController controller;
    private JButton btnVoltar;

    public TelaDeCompra() {
        this(null);
    }

    public TelaDeCompra(Usuario usuarioLogado) {
        setLayout(null);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(511, 343));

        JLabel lblTitulo = new JLabel("TELA DE COMPRA");
        lblTitulo.setFont(new Font("Segoe UI Black", Font.PLAIN, 23));
        lblTitulo.setBounds(150, 0, 300, 30);
        add(lblTitulo);

        lblUsuario = new JLabel();
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblUsuario.setBounds(10, 25, 500, 20);
        add(lblUsuario);

        if (usuarioLogado != null) {
            setUsuarioLogado(usuarioLogado);
        }

        // ====== TABELA DE PRODUTOS ======
        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição", "Preço", "Estoque"}, 0);
        tabelaProdutos = new JTable(modeloTabela);
        JScrollPane scrollProdutos = new JScrollPane(tabelaProdutos);
        scrollProdutos.setBounds(10, 51, 488, 99);
        add(scrollProdutos);

        JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
        btnAdicionar.setBounds(150, 161, 200, 30);
        add(btnAdicionar);

        // ====== TABELA DE CARRINHO ======
        modeloCarrinho = new DefaultTableModel(new Object[]{"ID", "Nome", "Preço", "Qtd", "Subtotal"}, 0);
        tabelaCarrinho = new JTable(modeloCarrinho);
        JScrollPane scrollCarrinho = new JScrollPane(tabelaCarrinho);
        scrollCarrinho.setBounds(10, 192, 488, 99);
        add(scrollCarrinho);

        JButton btnRemover = new JButton("Remover do Carrinho");
        btnRemover.setBounds(10, 302, 159, 30);
        add(btnRemover);
        
        
     // ===== Botão Voltar =====
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(10, 11, 80, 23);
        btnVoltar.setBackground(new Color(255, 200, 200));
        add(btnVoltar);
        
        lblTotal = new JLabel("Total: R$ 0.00");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotal.setBounds(348, 302, 150, 30);
        add(lblTotal);

        JButton btnNota = new JButton("Emitir Nota Fiscal");
        btnNota.setBounds(179, 302, 159, 30);
        add(btnNota);

        // === Inicializa o controller ===
        controller = new TelaDeCompraController(this, modeloTabela, modeloCarrinho, lblTotal, usuarioLogado);
        controller.atualizarLista();

        // === Eventos ===
        btnAdicionar.addActionListener(e -> controller.adicionarAoCarrinho(tabelaProdutos));
        btnRemover.addActionListener(e -> controller.removerDoCarrinho(tabelaCarrinho));
        btnNota.addActionListener(e -> controller.emitirNotaFiscal());

        // === Atualiza automaticamente quando a tela for mostrada ===
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                controller.atualizarLista();
            }
        });
    }
    public void voltar(ActionListener actionListener) {
        this.btnVoltar.addActionListener(actionListener);
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
        lblUsuario.setText("Usuário: " + usuario.getNome() + " | CPF: " + usuario.getCpf());
        controller.setUsuario(usuario);
    }

    // Método auxiliar se quiser forçar atualização manual
    public void atualizarLista() {
        controller.atualizarLista();
    }
}
