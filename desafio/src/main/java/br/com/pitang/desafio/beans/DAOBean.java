package br.com.pitang.desafio.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.pitang.desafio.dao.CrudDAO;
import br.com.pitang.desafio.exception.SystemError;

public abstract class DAOBean<E, D extends CrudDAO> {

	private String estadoTela = "buscar";
	
	private E entidade;
	private List<E> entidades;
	
	
	public abstract E criarNovaEntidade();
	public abstract D getDao();
	
	
	public void novo() {
		entidade = criarNovaEntidade();
		mudarParaInserir();
	}
	
	public void salvar() {
		try {
			getDao().salvar(entidade);
			entidade = criarNovaEntidade();
			mudarParaBuscar();
			adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
		} catch (SystemError e) {
			adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void editar(E entidade) {
		this.entidade = entidade;
		mudarParaEditar();
	}
	public void deletar(E entidade) {
		try {
			getDao().deletar(entidade);
			entidades.remove(entidade);
			adicionarMensagem("Deletado com sucesso!", FacesMessage.SEVERITY_INFO);
		} catch (SystemError e) {
			adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		
	}
	public void buscar() {
		if(!isBuscar()) {
			mudarParaBuscar();
			return;
		}
		
		try {
			entidades = getDao().buscar();
			if (entidades == null || entidades.size() < 1) {
				adicionarMensagem("Nao temos nada cadastrado!", FacesMessage.SEVERITY_INFO);
			}
			
		} catch (SystemError e) {
			adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		
	}
	public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
		FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	
	
	public E getEntidade() {
		return entidade;
	}
	public void setEntidade(E entidade) {
		this.entidade = entidade;
	}
	public List<E> getEntidades() {
		return entidades;
	}
	public void adicionarEntidades(List<E> entidades) {
		this.entidades = entidades;
	}

	public void setEntidades(E entidade) {
		this.entidades.add(entidade);
	}
	
	public boolean isInserir() {
		return "inserir".equals(estadoTela);
	}
	
	public boolean isEditar() {
		return "editar".equals(estadoTela);
	}

	public boolean isBuscar() {
		return "buscar".equals(estadoTela);
	}
	
	public void mudarParaInserir() {
		estadoTela = "inserir";
	}

	public void mudarParaEditar() {
		estadoTela = "editar";
	}
	
	public void mudarParaBuscar() {
		estadoTela = "buscar";
	}
	
	
	
}
