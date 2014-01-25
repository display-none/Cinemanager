package org.cinemanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

import org.cinemanager.common.EmployeePosition;
import org.cinemanager.dao.EmployeeDao;
import org.cinemanager.entity.Employee;
import org.cinemanager.gui.AddEmployeeView;

public class EmployeeController {
	private static EmployeeController instance;
	private EmployeeDao dao = new EmployeeDao();
	public void createAndPersistEmployee(AddEmployeeView addEmployeeView) {
		Employee employee = createEmployee(addEmployeeView);
		dao.persist(employee);
	}
	
	public Employee createEmployee(AddEmployeeView addEmployeeView) {
		Employee employee = new Employee();
		employee.setFirstName(addEmployeeView.getFirstName());
		employee.setLastName(addEmployeeView.getLastName());
		employee.setPosition(addEmployeeView.getPosition());
		return employee;
	}
	public List<Employee> getAllEmployee() { 
		List<Employee> abc = new ArrayList<Employee>(); 
		 
		Employee e1 = new Employee(); 
		Employee e2 = new Employee(); 
		Employee e3 = new Employee(); 
		 
		e1.setFirstName("Piotr "); 
		e1.setLastName("Mackowiak"); 
		e1.setPosition(EmployeePosition.JUNIOR); 
		 
		e2.setFirstName("Jacek "); 
		e2.setLastName("Hola"); 
		e2.setPosition(EmployeePosition.SENIOR); 
		 
		e3.setFirstName("Rektor "); 
		e3.setLastName("Pwr"); 
		e3.setPosition(EmployeePosition.MANAGER); 
		 
		abc.add(e1); 
		abc.add(e2); 
		abc.add(e3); 
		return abc;
	}
	public static EmployeeController getInstance() {
		if(instance == null) {
			instance = new EmployeeController();
		}
		return instance;
	}
}
