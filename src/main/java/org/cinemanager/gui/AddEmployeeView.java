package org.cinemanager.gui;

import static org.cinemanager.common.ViewUtils.*;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		this.setLayout(new GridLayout(8, 1));
		
		addTitleLabel(); 
		addFirstNamePanel(); 
		addLastNamePanel(); 
		addPositionPanel();
	}

	private void addTitleLabel() {
		JPanel panel = createNicePanel(1);
		JLabel titleLabel = new JLabel(" Add new employee "); 
		titleLabel.setFont(new Font(null, Font.BOLD, 15));
		panel.add(titleLabel);
		this.add(panel);
	}
	
	private void addFirstNamePanel() { 
		JPanel panel = createNicePanel(2);
		
		JLabel firstname = new JLabel(" First Name : "); 
		firstNameTextField = new JTextField(10); 
		 
		panel.add(firstname); 
		panel.add(firstNameTextField);
		this.add(panel); 
	}

	private void addLastNamePanel() { 
		JPanel panel = createNicePanel(2);
		
		JLabel lastname = new JLabel(" Last Name : "); 
		lastNameTextField = new JTextField(10); 
		 
		panel.add(lastname); 
		panel.add(lastNameTextField);
		this.add(panel); 
	}
	
	private void addPositionPanel() { 
		JPanel panel = createNicePanel(2);
		
		JLabel position = new JLabel(" Position : ");  
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
	
	public boolean isFirstNameValid() { 
		if(firstNameTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "First name field cannot be empty"); 
			return false;
		}
		return true;
	}
	
	public boolean isLastNameValid() { 
		if(lastNameTextField.getText().isEmpty()) {  
			JOptionPane.showMessageDialog(this, "Last name field cannot be empty"); 
			return false;
		}
		return true;
	}
	
	@Override
	public boolean areInputsValid() {
		return isFirstNameValid() && isLastNameValid();
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
