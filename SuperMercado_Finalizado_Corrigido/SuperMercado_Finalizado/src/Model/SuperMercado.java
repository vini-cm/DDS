package Model;

import java.util.List;

public class SuperMercado {
    private ProdutoDAO produtoDao;
    private Carrinho carrinho;

    public SuperMercado() {
        produtoDao = new ProdutoDAO();
        carrinho = new Carrinho();
    }

    public List<Produto> listarProdutos() {
        return produtoDao.listarProdutos();
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void atualizarEstoque(Produto p, int quantidadeComprada) {
        int novaQuantidade = p.getQuantidade() - quantidadeComprada;
        if (novaQuantidade < 0) novaQuantidade = 0;
        p.setQuantidade(novaQuantidade);
        produtoDao.atualizarProduto(p);
    }
}
