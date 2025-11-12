package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Model.Produto;
import Model.ProdutoDAO;
import views.TelaCadastroDeProdutos;

public class TelaCadastroDeProdutosController {

    private final TelaCadastroDeProdutos view;
    private final ProdutoDAO model;
    private final Navegador navegador;

    public TelaCadastroDeProdutosController(TelaCadastroDeProdutos view, ProdutoDAO model, Navegador navegador) {
        this.view = view;
        this.model = model;
        this.navegador = navegador;

        // ===== Listener do botão Adicionar Produto =====
        this.view.adicionar(e -> {
            System.out.println("[CONTROLLER] Adicionar produto acionado");

            int id = this.view.getId();
            String nome = this.view.getNome();
            String descricao = this.view.getDescricao();
            double preco = this.view.getPreco();
            int quantidade = this.view.getQuantidade();

            // Verificações básicas
            if (nome == null || nome.isBlank() ||
                descricao == null || descricao.isBlank() ||
                id == 0 || preco == 0 || quantidade == 0) {

                JOptionPane.showMessageDialog(null,
                        "Preencha todos os campos corretamente.",
                        "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Verifica se o produto já existe
            Produto existente = this.model.pesquisarProduto(id);
            if (existente != null) {
                JOptionPane.showMessageDialog(null,
                        "Produto com este ID já existe!",
                        "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Cria e adiciona o novo produto
            Produto produto = new Produto(id, nome, descricao, preco, quantidade);
            this.model.adicionarProduto(produto);

            // Confirma o cadastro
            if (this.model.pesquisarProduto(id) != null) {
                JOptionPane.showMessageDialog(null,
                        "✅ CADASTRO REALIZADO COM SUCESSO!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // Atualiza tabela de estoque
                atualizarTabela();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(null,
                        "❌ Erro ao cadastrar produto.",
                        "Problema", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ===== Listener do botão Voltar =====
        this.view.voltar(e -> {
            System.out.println("[CONTROLLER] Botão voltar clicado → indo para Tela de Login");
            this.navegador.navegarPara("TELA LOGIN");
        });

        // ===== Atualiza tabela automaticamente ao abrir a tela =====
        this.view.adicionarOuvinte(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                atualizarTabela();
            }
        });
    }

    /**
     * Atualiza a tabela de produtos (estoque) exibida na tela.
     */
    private void atualizarTabela() {
        DefaultTableModel modeloTabela = view.getModeloTabela();
        modeloTabela.setRowCount(0);

        List<Produto> produtos = model.listarProdutos();
        for (Produto p : produtos) {
            modeloTabela.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getDescricao(),
                    p.getPreco(),
                    p.getQuantidade()
            });
        }

        System.out.println("[CONTROLLER] Tabela de produtos atualizada (" + produtos.size() + " itens).");
    }

    /**
     * Limpa os campos após o cadastro.
     */
    private void limparCampos() {
        view.setTextID("");
        view.setTextNome("");
        view.setTextDesc("");
        view.setTextQtd("");
        view.setTextPreco("");
    }
}
