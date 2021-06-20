package br.com.pitang.desafio.dao;

import java.util.ArrayList;
import java.util.List;


import br.com.pitang.desafio.entities.Telefone;
import br.com.pitang.desafio.interfaces.CrudDAO;

public class TelefoneDAO implements CrudDAO<Telefone>{

    private List<Telefone> telefones = new ArrayList<>();
    
    public void salvar(Telefone telefone) {
    	telefones.add(telefone);
    }

    @Override
    public void deletar(Telefone telefone) {
    	this.telefones.remove(telefone);
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

	public List<Telefone> buscar() {
		return telefones;
	}

	
}