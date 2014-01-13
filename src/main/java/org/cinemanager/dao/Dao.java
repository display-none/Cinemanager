package org.cinemanager.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Dao {

	private static EntityManagerFactory entityManagerFactory;
	protected EntityManager em;
	protected EntityTransaction et;
	
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
