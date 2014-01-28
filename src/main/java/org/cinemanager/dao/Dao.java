package org.cinemanager.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
	
	protected EntityManager createContext() {
		if(em != null) throw new RuntimeException("cannot start new transaction if another is still active");
		em = getEntityManager();
		et = em.getTransaction();
		et.begin();
		return em;
	}
	
	protected void closeContext() {
		et.commit();
		em.close();
		em = null;
	}
	
	private static EntityManager getEntityManager() {
		if(entityManagerFactory == null) {
			initialize();
		}
		return entityManagerFactory.createEntityManager();
	}
	
	public static void initialize() {
		entityManagerFactory = Persistence.createEntityManagerFactory("openjpa");
		entityManagerFactory.createEntityManager();		//to force jpa to crete itself
	}
	
	public static void close() {
		shutDownDatabase();
		if(entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	private static void shutDownDatabase() {
		Dao dao = new Dao();
		dao.createContext();
		Query query = getEntityManager().createNativeQuery("shutdown");
		query.executeUpdate();
		dao.closeContext();
	}
}
