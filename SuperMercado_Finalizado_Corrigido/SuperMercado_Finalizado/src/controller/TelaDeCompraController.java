package controller;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Produto;
import Model.ProdutoDAO;
import Model.Usuario;
import views.TelaDeCompra;

public class TelaDeCompraController {

    private TelaDeCompra view;
    private DefaultTableModel modeloTabela;
    private DefaultTableModel modeloCarrinho;
    private JLabel lblTotal;
    private double total = 0.0;
    private Usuario usuarioLogado;
    private ProdutoDAO model;
    private Navegador navegador;
    

    public TelaDeCompraController(TelaDeCompra view, DefaultTableModel modeloTabela,
                                  DefaultTableModel modeloCarrinho, JLabel lblTotal, Usuario usuarioLogado) {
        this.view = view;
        this.modeloTabela = modeloTabela;
        this.modeloCarrinho = modeloCarrinho;
        this.lblTotal = lblTotal;
        this.usuarioLogado = usuarioLogado;
        this.model = new ProdutoDAO(); // ✅ instância única
        
        this.view.voltar(e -> {
            System.out.println("[CONTROLLER] Botão voltar clicado → indo para Tela de Login");
            this.navegador.navegarPara("TELA LOGIN");
        });
    }
    
   
    

    /** Atualiza a tabela de produtos **/
    public void atualizarLista() {
        modeloTabela.setRowCount(0);
        List<Produto> produtos = model.listarProdutos();

        for (Produto p : produtos) {
            modeloTabela.addRow(new Object[]{
                    p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getQuantidade()
            });
        }

        System.out.println("[TelaDeCompraController] Tabela atualizada: " + produtos.size() + " produtos.");
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    /** Adiciona produto selecionado ao carrinho **/
    public void adicionarAoCarrinho(JTable tabelaProdutos) {
        int linha = tabelaProdutos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um produto para adicionar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabela.getValueAt(linha, 0);
        String nome = (String) modeloTabela.getValueAt(linha, 1);
        double preco = (double) modeloTabela.getValueAt(linha, 3);
        int qtdEstoque = (int) modeloTabela.getValueAt(linha, 4);

        String qtdStr = JOptionPane.showInputDialog(null, "Quantidade desejada:", "1");
        if (qtdStr == null) return;

        int qtd = Integer.parseInt(qtdStr);
        if (qtd <= 0 || qtd > qtdEstoque) {
            JOptionPane.showMessageDialog(null, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double subtotal = preco * qtd;
        modeloCarrinho.addRow(new Object[]{id, nome, preco, qtd, subtotal});
        total += subtotal;
        atualizarTotal();
    }

    /** Remove item do carrinho **/
    public void removerDoCarrinho(JTable tabelaCarrinho) {
        int linha = tabelaCarrinho.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um item para remover!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double subtotal = (double) modeloCarrinho.getValueAt(linha, 4);
        total -= subtotal;
        modeloCarrinho.removeRow(linha);
        atualizarTotal();
    }

    /** Atualiza o texto do total **/
    private void atualizarTotal() {
        lblTotal.setText(String.format("Total: R$ %.2f", total));
    }

    /** Emite a nota fiscal e atualiza o estoque **/
    public void emitirNotaFiscal() {
        if (modeloCarrinho.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "O carrinho está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder nota = new StringBuilder();
        nota.append("===== NOTA FISCAL =====\n");
        nota.append("Cliente: ").append(usuarioLogado.getNome()).append("\n");
        nota.append("CPF: ").append(usuarioLogado.getCpf()).append("\n\n");
        nota.append("Produtos:\n");

        for (int i = 0; i < modeloCarrinho.getRowCount(); i++) {
            int id = (int) modeloCarrinho.getValueAt(i, 0);
            String nomeProd = (String) modeloCarrinho.getValueAt(i, 1);
            double preco = (double) modeloCarrinho.getValueAt(i, 2);
            int qtd = (int) modeloCarrinho.getValueAt(i, 3);
            double subtotal = (double) modeloCarrinho.getValueAt(i, 4);

            nota.append(String.format("- %s (x%d): R$ %.2f\n", nomeProd, qtd, subtotal));

            // Atualiza estoque no banco
            Produto p = model.pesquisarProduto(id);
            if (p != null) {
                p.setQuantidade(p.getQuantidade() - qtd);
                model.atualizarProduto(p);
            }
        }

        nota.append("\nTotal pago: R$ ").append(String.format("%.2f", total)).append("\n");
        nota.append("========================");

        JOptionPane.showMessageDialog(null, nota.toString(), "Nota Fiscal", JOptionPane.INFORMATION_MESSAGE);

        // Limpa carrinho e atualiza lista
        modeloCarrinho.setRowCount(0);
        total = 0;
        atualizarTotal();
        atualizarLista();
    }
    
}
