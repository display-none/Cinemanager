package org.cinemanager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.cinemanager.entity.Employee;

public class EmployeeDao extends Dao<Employee> {
	
	public List<Employee> getAllEmployees() {
		EntityManager em = createContext();
		TypedQuery<Employee> query = em.createQuery("select e from Employee e", Employee.class);
		List<Employee> result = query.getResultList();
		closeContext();
		return result;
	}
}
