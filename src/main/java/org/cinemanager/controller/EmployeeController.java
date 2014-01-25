package org.cinemanager.controller;

import org.cinemanager.dao.EmployeeDao;
import org.cinemanager.entity.Employee;
import org.cinemanager.gui.AddEmployeeView;

public class EmployeeController {
private static EmployeeController instance;
	
	private EmployeeDao dao = new EmployeeDao();
	
	public void createAndPersistMovie(AddEmployeeView addEmployeeView) {
		Employee movie = createEmployee(addEmployeeView);
		dao.persist(movie);
	}
	
	public Employee createEmployee(AddEmployeeView addemployeeView) {
		Employee employee = new Employee();
		employee.setFirstName(addemployeeView.getFirstName());
		employee.setLastName(addemployeeView.getLastName());
		employee.setPosition(addemployeeView.getPosition());
		return employee;
	}
	
	public static EmployeeController getInstance() {
		if(instance == null) {
			instance = new EmployeeController();
		}
		return instance;
	}
}
