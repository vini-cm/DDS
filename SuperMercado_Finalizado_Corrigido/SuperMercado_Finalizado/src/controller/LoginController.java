package controller;

import javax.swing.JOptionPane;

import Model.Usuario;
import Model.UsuarioDao;
import views.TelaDeCompra;
import views.TelaLogin;

public class LoginController {
    private final TelaLogin view;
    private final UsuarioDao model;
    private final Navegador navegador;
    private final TelaDeCompra telaCompra;

    public LoginController(TelaLogin view, Navegador navegador, UsuarioDao model, TelaDeCompra telaCompra) {
        this.view = view;
        this.navegador = navegador;
        this.model = model;
        this.telaCompra = telaCompra;

        this.view.entrar(e -> {
            String nome = this.view.getNome();
            String cpf = this.view.getCpf();

            Usuario usuario = this.model.pesquisarUsuario(cpf);
            if (usuario == null) {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Se for administrador
            if (usuario.isFuncao()) {
                navegador.navegarPara("Tela de Cadastro de Produtos");
            } else { // usuário normal
                // Passa usuário logado para a tela de compra
                telaCompra.setUsuarioLogado(usuario);
                navegador.navegarPara("TELA DE COMPRA");
            }
        });

        this.view.cadastrar(e -> navegador.navegarPara("CADASTRO"));
    }
}
