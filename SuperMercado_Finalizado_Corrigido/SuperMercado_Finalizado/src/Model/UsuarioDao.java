package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, cpf, funcao) VALUES (?, ?, ?)";
        try (Connection conexao = BancoConnection.conectar();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {

            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getCpf());
            pstm.setBoolean(3, usuario.isFuncao());
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conexao = BancoConnection.conectar();
             PreparedStatement pstm = conexao.prepareStatement(sql);
             ResultSet rset = pstm.executeQuery()) {

            while (rset.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rset.getString("nome"));
                usuario.setCpf(rset.getString("cpf"));
                usuario.setFuncao(rset.getBoolean("funcao"));
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario buscarAdm(String nome, String cpf) {
        String sql = "SELECT * FROM usuarios WHERE nome = ? AND cpf = ?";
        Usuario usuario = null;

        try (Connection conexao = BancoConnection.conectar();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {

            pstm.setString(1, nome);
            pstm.setString(2, cpf);

            try (ResultSet rset = pstm.executeQuery()) {
                if (rset.next()) {
                    usuario = new Usuario();
                    usuario.setNome(rset.getString("nome"));
                    usuario.setCpf(rset.getString("cpf"));
                    usuario.setFuncao(rset.getBoolean("funcao"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public Usuario pesquisarUsuario(String cpf) {
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";
        try (Connection conexao = BancoConnection.conectar();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {

            pstm.setString(1, cpf);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setNome(rs.getString("nome"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setFuncao(rs.getBoolean("funcao"));
                    return usuario;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
