package br.com.pitang.desafio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.pitang.desafio.entities.Usuario;
import br.com.pitang.desafio.exception.SystemError;
import br.com.pitang.desafio.utility.ConnectionFactory;

public class UsuarioDAOJPA implements CrudDAO<Usuario> {

	@Override
	public void salvar(Usuario usuario) {
		DAOJPA<Usuario> dao = new DAOJPA();
		DAOJPA<Usuario> builder = dao.abrirT();
		
		usuario.getTelefones().forEach(telefone -> telefone.setUsuario(usuario));
		builder = usuario.getId() != null ? dao.mergeT(usuario) : dao.incluirT(usuario);
		
		builder.fecharT().fechar();
	}

	public List<Usuario> buscar() {
		DAOJPA<Usuario> dao = new DAOJPA(Usuario.class);
		List<Usuario> usuarios = dao.abrirT().listarTudo();
		dao.fechar();
		return usuarios;
	}

	
	public void deletar(Usuario usuario) {
		DAOJPA<Usuario> dao = new DAOJPA(Usuario.class);
		dao.abrirT().removeT(usuario).fecharT().fechar();
	}
	

}
