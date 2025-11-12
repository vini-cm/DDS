package main;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import Model.ProdutoDAO;
import Model.Usuario;
import Model.UsuarioDao;
import controller.LoginController;
import controller.Navegador;
import controller.TelaCadastroDeProdutosController;
import controller.TelaDeCadastroController;
import controller.TelaDeCompraController;
import views.JanelaPrincipal;
import views.TelaCadastroDeProdutos;
import views.TelaDeCadastro;
import views.TelaDeCompra;
import views.TelaLogin;

public class Main {
	 

    public static void main(String[] args) {
        // ===== Instâncias principais =====
    	
        JanelaPrincipal janela = new JanelaPrincipal();
        Navegador navegador = new Navegador(janela);
        UsuarioDao usuarioDao = new UsuarioDao();
        ProdutoDAO produtoDao = new ProdutoDAO();

        // ===== Telas =====
        TelaLogin telaLogin = new TelaLogin();
        TelaDeCadastro telaCadastro = new TelaDeCadastro();
        TelaDeCompra telaCompra = new TelaDeCompra(); // sem usuário no início
        TelaCadastroDeProdutos telaProdutos = new TelaCadastroDeProdutos();

        // ===== Controllers =====
        LoginController loginController = new LoginController(telaLogin, navegador, usuarioDao, telaCompra);
        TelaDeCadastroController cadastroController = new TelaDeCadastroController(telaCadastro, usuarioDao, navegador);
        telaCadastro.adicionarController(cadastroController);
        TelaCadastroDeProdutosController produtosController = new TelaCadastroDeProdutosController(telaProdutos, produtoDao, navegador);
       

        // ===== Registra telas =====
        navegador.adicionarPainel("TELA LOGIN", telaLogin);
        navegador.adicionarPainel("TELA DE COMPRA", telaCompra);
        navegador.adicionarPainel("CADASTRO", telaCadastro);
        navegador.adicionarPainel("Tela de Cadastro de Produtos", telaProdutos);

        // ===== Abre o sistema =====
        navegador.navegarPara("TELA LOGIN");
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
}
