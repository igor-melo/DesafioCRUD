package br.com.pitang.desafio.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.pitang.desafio.dao.TelefoneDAO;
import br.com.pitang.desafio.entities.Telefone;

@ManagedBean
@SessionScoped
public class TelefoneBean extends DAOBean<Telefone, TelefoneDAO> {

	private TelefoneDAO telefoneDAO;

	@Override
	public Telefone criarNovaEntidade() {
		return new Telefone();
	}

	@Override
	public TelefoneDAO getDao() {

		if (telefoneDAO == null) {
			telefoneDAO = new TelefoneDAO();
		}
		return telefoneDAO;
	}

}
