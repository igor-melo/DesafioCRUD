package br.com.pitang.desafio.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.pitang.desafio.entities.Telefone;
import br.com.pitang.desafio.entities.Usuario;
import br.com.pitang.desafio.utility.ConnectionFactory;

public class DAOJPA<E> {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;

	static {
		try {
			if (emf == null) {
				emf = Persistence.createEntityManagerFactory("desafio");
			}
		} catch (Exception e) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public DAOJPA() {
		this(null);
	}

	public DAOJPA(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}

	public DAOJPA<E> abrirT() {
		em.getTransaction().begin();
		return this;
	}

	public DAOJPA<E> fecharT() {
		em.getTransaction().commit();
		return this;
	}

	public DAOJPA<E> removeT(E entidade) {
		E temp = em.merge(entidade);
		em.remove(temp);
		return this;
	}
	
	public DAOJPA<E> incluirT(E entidade) {
		em.persist(entidade);
		return this;
	}

	public DAOJPA<E> mergeT(E entidade) {
		em.merge(entidade);
		return this;
	}

	public List<E> listarTudo() {
		if (classe == null) {
			throw new UnsupportedOperationException("Classe nula");
		}
		String jpql = "select e from " + classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		return query.getResultList();
	}

	public void fechar() {
		em.close();
	}

}
