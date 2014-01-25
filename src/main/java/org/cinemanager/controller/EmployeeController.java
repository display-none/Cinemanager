package org.cinemanager.controller;

import org.cinemanager.dao.EmployeeDao;
import org.cinemanager.entity.Employee;
import org.cinemanager.gui.AddEmployeeView;

public class EmployeeController {
	private static EmployeeController instance;
	
	private EmployeeDao dao = new EmployeeDao();
	
	public void createAndPersistEmployee(AddEmployeeView addEmployeeView) {
		Employee movie = createEmployee(addEmployeeView);
		dao.persist(movie);
	}
	
	public Employee createEmployee(AddEmployeeView addEmployeeView) {
		Employee employee = new Employee();
		employee.setFirstName(addEmployeeView.getFirstName());
		employee.setLastName(addEmployeeView.getLastName());
		employee.setPosition(addEmployeeView.getPosition());
		return employee;
	}
	
	public static EmployeeController getInstance() {
		if(instance == null) {
			instance = new EmployeeController();
		}
		return instance;
	}
}
