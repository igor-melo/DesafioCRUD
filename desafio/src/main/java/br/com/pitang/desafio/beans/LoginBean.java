package br.com.pitang.desafio.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.pitang.desafio.dao.LoginDAO;
import br.com.pitang.desafio.dao.TelefoneDAO;
import br.com.pitang.desafio.entities.Login;
import br.com.pitang.desafio.entities.Telefone;

@ManagedBean
@SessionScoped
public class LoginBean extends DAOBean<Login, LoginDAO> {

	private final LoginDAO query = new LoginDAO();

	public String loginControl(Login login) {
		String user = login.getUsername(); 
		String pass = login.getPassword();
		
		if (query.loginControl(user, pass)) {
			return "index.xhtml?faces-redirect=true";
		}
		RequestContext.getCurrentInstance().update("growl");
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", "Username ou Password"));
		return "";
		
	}
	
	@Override
	public Login criarNovaEntidade() {
		return new Login();
	}

	@Override
	public LoginDAO getDao() {
		return null;
	}

}
