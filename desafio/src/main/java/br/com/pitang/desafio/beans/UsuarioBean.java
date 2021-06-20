package br.com.pitang.desafio.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.pitang.desafio.dao.UsuarioDAO;
import br.com.pitang.desafio.entities.Telefone;
import br.com.pitang.desafio.entities.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean extends DAOBean<Usuario, UsuarioDAO>{

	
	private UsuarioDAO usuarioDAO;
	
	@Override
	public Usuario criarNovaEntidade() {
		return new Usuario();
	}

	@Override
	public UsuarioDAO getDao() {
		
		if(usuarioDAO == null) {
			usuarioDAO = new UsuarioDAO();
		}
		return usuarioDAO;
	}
	
	
	public void addTelefone(Telefone telefone) {
		getEntidade().getTelefones().add(new Telefone(telefone));
	}

	public void removeTelefone(Telefone telefone) {
		if (getEntidade().getTelefones() != null && !getEntidade().getTelefones().isEmpty()) {
			getEntidade().getTelefones().remove(telefone);
		}
	}

}
