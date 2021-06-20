package br.com.pitang.desafio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pitang.desafio.entities.Usuario;
import br.com.pitang.desafio.exception.SystemError;
import br.com.pitang.desafio.utility.ConnectionFactory;

public class UsuarioDAO_ implements CrudDAO<Usuario>{
	@Override
	public void salvar(Usuario usuario) throws SystemError {
		try {
			Connection con = ConnectionFactory.getConexao();
			PreparedStatement ps;
			if (usuario.getId() == null) {
				ps = con.prepareStatement("INSERT INTO USUARIO (nome, email, senha) VALUES (?, ?, ?)");
			} else {
				ps = con.prepareStatement("update USUARIO set nome=?, email=?, senha=? where id=?");
				ps.setLong(4, usuario.getId());
			}
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.execute();
			ConnectionFactory.closeConnection();
		} catch (SQLException e) {
			throw new SystemError("Erro ao Salvar", e);
		}
	}
	@Override
	public List<Usuario> buscar() throws SystemError {
		try {
			Connection con = ConnectionFactory.getConexao();
			PreparedStatement ps = con.prepareStatement("select * from usuario");
			ResultSet rs = ps.executeQuery();
			List<Usuario> usuarios = new ArrayList<>();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuarios.add(usuario);
			}

			return usuarios;

		} catch (SQLException e) {
			throw new SystemError("Erro ao Listar", e);
		}
	}
	@Override
	public void deletar(Usuario usuario) throws SystemError {

		try {
			Connection con = ConnectionFactory.getConexao();
			PreparedStatement ps = con.prepareStatement("delete from usuario where id = ?");
			ps.setLong(1, usuario.getId());
			ps.execute();
		} catch (SQLException e) {
			throw new SystemError("Erro ao Excluir", e);
		}
	}

}
