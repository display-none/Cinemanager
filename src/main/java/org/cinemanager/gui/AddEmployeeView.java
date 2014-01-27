package org.cinemanager.gui;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.cinemanager.common.EmployeePosition;
import org.cinemanager.controller.EmployeeController;
import org.cinemanager.entity.Employee;
import org.cinemanager.entity.IEntity;


public class AddEmployeeView extends View<Employee> {

	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Save";
	private static final String CANCEL_BUTTON_LABEL = "Cancel";
	
	private JTextField firstNameTextField, lastNameTextField; 
	private RadioGroup<EmployeePosition> employeePositionRadioGroup;
	
	private final EmployeeController controller = EmployeeController.getInstance(); 
	
	private AddEmployeeView() {
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
		
		employeePositionRadioGroup= new RadioGroup<>(EmployeePosition.values());
		panel.add(employeePositionRadioGroup);
		
		this.add(panel);
	}
	
	public String getFirstName() { 
		return firstNameTextField.getText();
	}
	
	public String getLastName() { 
		return lastNameTextField.getText();
	}
	
	public EmployeePosition getPosition() { 
		return employeePositionRadioGroup.getSelected();
	}
	
	@Override
	public boolean hasAnyChanges() {
		return  !firstNameTextField.getText().isEmpty() || 
				!lastNameTextField.getText().isEmpty() || 
				!employeePositionRadioGroup.isFirstSelected();
	}
	
	@Override
	public void doApplyAction() {  
		if( firstNameTextField.getText().length() > 0 && lastNameTextField.getText().length() > 0) {
			controller.createAndPersistEmployee(this);   
		}
	}
	
	@Override
	public Employee doGetResultAction() {  
			return controller.createEmployee(this); 
	}
	
	@Override
	public void handleRequestedResult(IEntity result) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getApplyButtonLabel() {
		return APPLY_BUTTON_LABEL; 
	}
	
	@Override
	public String getCancelButtonLabel() {
		return CANCEL_BUTTON_LABEL;
	}
	
	@Override
	public void reset() {
		firstNameTextField.setText(""); 
		lastNameTextField.setText(""); 
		employeePositionRadioGroup.setFirstSelected();
	}
	

	public static ViewCreator<AddEmployeeView> getCreator() {
		return new AddEmployeeViewCreator();
	}
	
	private static class AddEmployeeViewCreator implements ViewCreator<AddEmployeeView> {

		@Override
		public AddEmployeeView createView(ViewManager viewManager) {
			return new AddEmployeeView();
		}
		
	}
}
