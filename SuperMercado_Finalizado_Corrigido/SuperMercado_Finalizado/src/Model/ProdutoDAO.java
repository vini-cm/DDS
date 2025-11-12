package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO {
	public void adicionarProduto(Produto produto) {
		String sql = "INSERT INTO produtos(id, nome, descricao, preco, quantidade) VALUES (?, ?, ?, ?, ?)";

		Connection conexao = null;
		PreparedStatement pstm = null;
		try {
			conexao = BancoConnection.conectar();
			pstm = conexao.prepareStatement(sql);
			pstm.setInt(1, produto.getId());
			pstm.setString(2, produto.getNome());
			pstm.setString(3, produto.getDescricao());
			pstm.setDouble(4, produto.getPreco());
			pstm.setInt(5, produto.getQuantidade());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BancoConnection.desconectar(conexao);
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	public void atualizarProduto(Produto produto) {
		String sql = "update produtos set nome = ?, descricao = ?, preco = ?, quantidade =? where id = ?";
		Connection conexao = null;
		PreparedStatement pstm = null;
		try {
			conexao = BancoConnection.conectar();
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			pstm.setDouble(3, produto.getPreco());
			pstm.setInt(4, produto.getQuantidade());
			pstm.setInt(5, produto.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BancoConnection.desconectar(conexao);
		}
	}
	
	public void deletarProduto(String id) {
		String sql = "delete usuarios where id = ?";
		Connection conexao = null;
		PreparedStatement pstm = null;
		 try {
	    	 conexao = BancoConnection.conectar();
	    	 pstm = conexao.prepareStatement(sql);
	    	 pstm.setString(1, id);
	    	 pstm.execute();
	     } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BancoConnection.desconectar(conexao);
		}
	}
	
	public List<Produto> listarProdutos(){
		List<Produto> lista = new ArrayList();
		String sql = "select * from produtos";
		Connection conexao = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conexao = BancoConnection.conectar();
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setQuantidade(rs.getInt("quantidade"));
				lista.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			BancoConnection.desconectar(conexao);
		}
		return lista;
	}
	
	public Produto pesquisarProduto(int id) {
		String sql = "select * from produtos where id = ?";
		Connection conexao = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conexao = BancoConnection.conectar();
			pstm = conexao.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setQuantidade(rs.getInt("quantidade"));
				return produto;
			}
		} catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
	}
	
}