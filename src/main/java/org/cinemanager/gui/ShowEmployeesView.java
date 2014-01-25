package org.cinemanager.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import org.cinemanager.controller.EmployeeController;
import org.cinemanager.entity.Employee;
import org.cinemanager.entity.IEntity;

public class ShowEmployeesView extends View<Employee> {

	private static final long serialVersionUID = 1L;
	private JList<Employee>employeeList;
	private EmployeeController controller = EmployeeController.getInstance(); ; 
	private ShowEmployeesView(ViewManager viewManager) {
		 
		employeeList = new JList<Employee>( createListModel() );
		employeeList.setCellRenderer( new EmployeeCellRender()); 
		 
		this.add(employeeList); 
	} 
	public DefaultListModel<Employee> createListModel(){ 
		List<Employee> employees = controller.getAllEmployees();  
		System.out.println("Size : " + employees.size());
		DefaultListModel<Employee> listmodel = new DefaultListModel<Employee>();
		for(Employee employee : employees) { 
			 
			listmodel.addElement(employee);
		}
		return listmodel;
	} 
	public void createString() { 
		
	}
	@Override
	public boolean hasAnyChanges() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doApplyAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public Employee doGetResultAction() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void handleRequestedResult(IEntity result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getApplyButtonLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCancelButtonLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
	
	public static ViewCreator<ShowEmployeesView> getCreator() {
		return new ShowEmployeesViewCreator();
	}
	
	private static class ShowEmployeesViewCreator implements ViewCreator<ShowEmployeesView> {

		@Override
		public ShowEmployeesView createView(ViewManager viewManager) {
			return new ShowEmployeesView(viewManager);
		}
		
	}
} 
class EmployeeCellRender extends JPanel implements ListCellRenderer<Employee> {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Employee> list, Employee value, int index, boolean isSelected,
			boolean cellHasFocus) {
		add(new JLabel(value.getFirstName() + " " + value.getLastName() + " - " + value.getPosition().toString()));
		add(new JButton("some button"));
		return this;
	} 
	
} 
