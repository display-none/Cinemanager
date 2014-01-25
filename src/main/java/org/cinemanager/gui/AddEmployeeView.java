package org.cinemanager.gui;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.cinemanager.common.EmployeePosition;
import org.cinemanager.controller.EmployeeController;
import org.cinemanager.entity.Employee;


public class AddEmployeeView extends View<Employee> {

	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Save";
	private JTextField firstNameTextField, lastNameTextField; 
	private ButtonGroup employeePositionButtonGroup; 
	private EmployeeController controller = EmployeeController.getInstance(); 
	
	private AddEmployeeView(ViewManager viewManager) {  
		this.setLayout(new GridLayout(4,1)); 
		addLabel(); 
		addFirstNamePanel(); 
		addLastNamePanel(); 
		addPositionPanel();
	}

	private void addLabel() { 
		JLabel title = new JLabel(" Add new employee "); 
		title.setFont(new Font(null, Font.BOLD, 15));
		this.add(title);
	}
	
	private void addFirstNamePanel() { 
		JLabel firstname = new JLabel(" First Name : "); 
		firstNameTextField = new JTextField(10); 
		 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		panel.add(firstname); 
		panel.add(firstNameTextField);
		this.add(panel); 
	}

	private void addLastNamePanel() { 
		JLabel lastname = new JLabel(" Last Name : "); 
		lastNameTextField = new JTextField(10); 
		 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		panel.add(lastname); 
		panel.add(lastNameTextField);
		this.add(panel); 
	}
	
	private void addPositionPanel() { 
		JLabel position = new JLabel(" Position : ");  
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		panel.add(position);
		
		employeePositionButtonGroup= new ButtonGroup();
		for(EmployeePosition poition : EmployeePosition.values()) {
			JRadioButton radioButton = new JRadioButton(poition.toString());
			employeePositionButtonGroup.add(radioButton);
			panel.add(radioButton);
		}   
		employeePositionButtonGroup.setSelected(employeePositionButtonGroup.getSelection(), true);
		
		this.add(panel);
	}
	
	public String getFirstName() { 
		return firstNameTextField.getText();
	}
	
	public String getLastName() { 
		return lastNameTextField.getText();
	}
	
	public EmployeePosition getPosition() { 
		return EmployeePosition.valueOf((String) employeePositionButtonGroup.getSelection().getSelectedObjects()[0]);		//this is horrible
	}
	
	@Override
	public void doApplyAction() {
		controller.createAndPersistEmployee(this);
	}
	
	@Override
	public Employee doGetResultAction() {
		return controller.createEmployee(this);
	}
	
	@Override
	public String getApplyButtonLabel() {
		return APPLY_BUTTON_LABEL; 
	}
	
	@Override
	public void reset() {
		firstNameTextField.setText(""); 
		lastNameTextField.setText(""); 
		employeePositionButtonGroup.clearSelection();
	}
	

	public static ViewCreator<AddEmployeeView> getCreator() {
		return new AddEmployeeViewCreator();
	}
	
	private static class AddEmployeeViewCreator implements ViewCreator<AddEmployeeView> {

		@Override
		public AddEmployeeView createView(ViewManager viewManager) {
			return new AddEmployeeView(viewManager);
		}
		
	}
}
