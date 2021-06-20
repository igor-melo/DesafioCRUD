package br.com.pitang.desafio.interfaces;

import java.util.List;

import br.com.pitang.desafio.exception.SystemError;

public interface CrudDAO<E> {
	
	public void salvar(E entidade) throws SystemError;
	public void deletar(E entidade) throws SystemError;
	public List<E> buscar() throws SystemError;
	
}
