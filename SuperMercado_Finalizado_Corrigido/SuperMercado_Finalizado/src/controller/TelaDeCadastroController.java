package controller;

import java.awt.event.ComponentAdapter;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Model.Usuario;
import Model.UsuarioDao;
import views.TelaDeCadastro;

public class TelaDeCadastroController extends ComponentAdapter {
    private final TelaDeCadastro view;
    private final UsuarioDao model;
    private final Navegador navegador;
    private DefaultTableModel modeloTabela;
    private DefaultTableModel modeloCarrinho;

    public TelaDeCadastroController(TelaDeCadastro view, UsuarioDao model, Navegador navegador) {
        this.view = view;
        this.model = model;
        this.navegador = navegador;

        // Apenas para garantir que a tela conecta com o botão
        System.out.println("[CONTROLLER] TelaDeCadastroController carregado.");

        // Listener do botão
        this.view.cadastrar(e -> {
            String nome = this.view.getNome();
            String cpf = this.view.getCpf();
            boolean funcao = false;

            // Verificação correta (com &&)
            if (nome != null && !nome.isBlank() && cpf != null && !cpf.isBlank()) {

                Usuario existente = this.model.pesquisarUsuario(cpf);
                if (existente != null) {
                    JOptionPane.showMessageDialog(null, "CPF já em uso!", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Usuario user = new Usuario(nome, cpf, funcao);
                model.adicionarUsuario(user);

                if (this.model.pesquisarUsuario(cpf) != null) {
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    navegador.navegarPara("TELA LOGIN");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            
        });
        this.view.voltar(e -> {
        	System.out.println("[CONTROLLER] actionPerformed rodando");
        	this.navegador.navegarPara("TELA LOGIN");
        });
 
    }
}
