package org.cinemanager.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.cinemanager.entity.IEntity;

public class Dao<T extends IEntity> {

	private static EntityManagerFactory entityManagerFactory;
	protected EntityManager em;
	protected EntityTransaction et;
	
	public void persist(T entity) {
		createContext();
		if(entity.getId() == null) {
			em.persist(entity);
		} else {
			em.merge(entity);
		}
		closeContext();
	}
	
	public void persist(Collection<T> entities) {
		createContext();
		for(T entity : entities) {
			if(entity.getId() == null) {
				em.persist(entity);
			} else {
				em.merge(entity);
			}
		}
		closeContext();
	}
	
	public void remove(Long id, Class<T> clazz) {
		createContext();
		T entity = em.getReference(clazz, id);
		em.remove(entity);
		closeContext();
	}
	
	protected void createContext() {
		em = getEntityManager();
		et = em.getTransaction();
		et.begin();
	}
	
	protected void closeContext() {
		et.commit();
		em.close();
	}
	
	static EntityManager getEntityManager() {
		if(entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("openjpa");
		}
		return entityManagerFactory.createEntityManager();
	}
}
