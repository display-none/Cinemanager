package org.cinemanager.gui;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.cinemanager.common.EmployeePosition;
import org.cinemanager.common.MovieVersion;
import org.cinemanager.controller.EmployeeController;
import org.cinemanager.controller.MovieController;
import org.cinemanager.entity.Employee;


public class AddEmployeeView extends JPanel implements View<Employee>{
	private JTextField textfieldfirstname,textfieldlastname; 
	private ButtonGroup buttongroup; 
	private JComboBox<EmployeePosition> combo_box_emploee_position; 
	private EmployeeController controller = EmployeeController.getInstance(); 
	private final String APPLY_BUTTON_LABEL = "Save";
	public AddEmployeeView() {  
		this.setLayout(new GridLayout(4,1)); 
		addLabel(); 
		addFirstNamePanel(); 
		addLastNamePanel(); 
		addPositionPanel();
	}  
	public boolean cheat_data(String add_or_delete) { 
		boolean result = false;  
		if( textfieldfirstname.getText().length() > 0 &&  textfieldlastname.getText().length() > 0 ) { 
			result = true;
		}
		return result;
	}  
	private void addLabel() { 
		JLabel title = new JLabel(" Add new employee "); 
		title.setFont(new Font(null, Font.BOLD, 15));
		this.add(title);
	}  
	private void addFirstNamePanel() { 
		JLabel firstname = new JLabel(" First Name : "); 
		textfieldfirstname = new JTextField(10); 
		 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		panel.add(firstname); 
		panel.add(textfieldfirstname);
		this.add(panel); 
	} 
	private void addLastNamePanel() { 
		JLabel lastname = new JLabel(" Last Name : "); 
		textfieldlastname = new JTextField(10); 
		 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		panel.add(lastname); 
		panel.add(textfieldlastname);
		this.add(panel); 
	} 
	private void addPositionPanel() { 
		JLabel position = new JLabel(" Position : ");  
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		panel.add(position);
		buttongroup= new ButtonGroup();
		for(EmployeePosition poition : EmployeePosition.values()) {
			JRadioButton radioButton = new JRadioButton(poition.toString());
			buttongroup.add(radioButton);
			panel.add(radioButton);
		}   
		buttongroup.setSelected(buttongroup.getSelection(), true);
		
		this.add(panel);
	}
	public String getFirstName() { 
		return String.valueOf(textfieldfirstname.getText());
	} 
	public String getLastName() { 
		return String.valueOf(textfieldlastname.getText());
	} 
	public EmployeePosition getPosition() { 
		return (EmployeePosition) combo_box_emploee_position.getSelectedItem();
	}
	@Override
	public void doApplyAction() {
		controller.createAndPersistMovie(this);
		
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
		textfieldfirstname.setText(""); 
		textfieldlastname.setText(""); 
	//	combo_box_emploee_position.setSelectedIndex(0);
	//	buttongroup.clearSelection();
		
	}
}
