package br.com.pitang.desafio.dao;

import java.util.List;

import br.com.pitang.desafio.entities.Login;
import br.com.pitang.desafio.entities.Usuario;
import br.com.pitang.desafio.exception.SystemError;
import br.com.pitang.desafio.interfaces.CrudDAO;

public class LoginDAO implements CrudDAO<Login>  {
	
	public boolean loginControl(String username, String password)  {
		
		salvar(new Login("admin", "admin"));
		
		DAOJPA<Login> dao = new DAOJPA();
		try {
			Object l = dao.getEm().createQuery(
					"SELECT c FROM Login c WHERE c.username LIKE :username and c.password LIKE :password" )
					.setParameter("username", username)
					.setParameter("password", password)
					.getSingleResult();
			if(l != null) {
				return true;
			}

		} catch (Exception e) {
			return false;
		}
		return false;
		
	}

	@Override
	public void salvar(Login login)  {
		DAOJPA<Login> dao = new DAOJPA();

		Object l = dao.getEm().createQuery(
				"SELECT c FROM Login c WHERE c.username LIKE :username and c.password LIKE :password" )
				.setParameter("username", "admin")
				.setParameter("password", "admin")
				.getSingleResult();
		if (l == null) {
			dao.incluirT(login);
		}
	}

	@Override
	public void deletar(Login entidade) throws SystemError {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Login> buscar() throws SystemError {
		// TODO Auto-generated method stub
		return null;
	}

	
}
