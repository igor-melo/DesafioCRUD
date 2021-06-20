package br.com.pitang.desafio.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.pitang.desafio.dao.UsuarioDAOJPA;
import br.com.pitang.desafio.entities.Telefone;
import br.com.pitang.desafio.entities.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean extends DAOBean<Usuario, UsuarioDAOJPA>{

	
	private UsuarioDAOJPA usuarioDAO;
	
	@Override
	public Usuario criarNovaEntidade() {
		return new Usuario();
	}

	@Override
	public UsuarioDAOJPA getDao() {
		
		if(usuarioDAO == null) {
			usuarioDAO = new UsuarioDAOJPA();
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
