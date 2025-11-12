package controller;

import javax.swing.JPanel;

import views.JanelaPrincipal;

public class Navegador {
	private JanelaPrincipal janela;
	
	
	public Navegador(JanelaPrincipal janela) {
		this.janela = janela;
		
	}
	
	public void adicionarPainel(String nome, JPanel tela) {
		this.janela.adicionarTela(nome, tela);
	}
	
	public void navegarPara(String nome) {
		this.janela.mostrarTela(nome);
	}
	
	
	public void sair() {
		this.janela.dispose();
	}
	
}

