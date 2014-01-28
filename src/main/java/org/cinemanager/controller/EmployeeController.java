package org.cinemanager.controller;

import java.util.List;

import org.cinemanager.dao.EmployeeDao;
import org.cinemanager.entity.Employee;
import org.cinemanager.gui.AddEmployeeView;

public class EmployeeController {
	
	private static EmployeeController instance;
	
	private EmployeeDao dao = new EmployeeDao();
	
	private EmployeeController() {
		
	}
	
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
	
	public List<Employee> getAllEmployees() { 
		return dao.getAllEmployees();
	}
	
	public void deleteEmployee(Long id) {
		dao.remove(id, Employee.class);
	}
	
	public static EmployeeController getInstance() {
		if(instance == null) {
			instance = new EmployeeController();
		}
		return instance;
	}
}
