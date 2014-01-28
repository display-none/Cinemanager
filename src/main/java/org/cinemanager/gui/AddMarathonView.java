package org.cinemanager.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.cinemanager.controller.MarathonController;
import org.cinemanager.entity.Auditorium;
import org.cinemanager.entity.Employee;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Marathon;
import org.cinemanager.entity.Movie;
import org.cinemanager.entity.Showing;
import org.cinemanager.gui.ShowShowingsView.ShowingsFormatter; 
import org.cinemanager.gui.ShowEmployeesView.EmployeeFormatter;
import org.cinemanager.gui.ShowMoviesView.MovieFormatter; 


public class AddMarathonView extends View<Marathon> { 
	
	private static final long serialVersionUID = 1L;
	
	private final ViewManager viewManager;
	private JTextField marathonNameTextField,employeeIDTextField,showingIDTextField;  
	private static final String APPLY_BUTTON_LABEL = "Save";
	private static final String CANCEL_BUTTON_LABEL = "Cancel";  
	 
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";  
	private static final SimpleDateFormat dateParser = new SimpleDateFormat(DATE_FORMAT); 
	
	private Employee employee;
	private Showing showing; 
	
	private final MarathonController controller = MarathonController.getInstance(); 
	private AddMarathonView(ViewManager viewManager) {
		this.viewManager = viewManager; 
		this.setLayout(new GridLayout(4,1));
		addTitle(); 
		addName(); 
		addEmployee();  
		addShowing();
	}  
	public void addTitle(){ 
		JLabel titleLabel = new JLabel(" Add new marathon"); 
		titleLabel.setFont(new Font("Bold",Font.BOLD,15)); 
		 
		this.add(titleLabel);
	} 
	public void addName() { 
		JLabel nameLabel = new JLabel("Name : "); 
		marathonNameTextField = new JTextField(10); 
		 
		JPanel namePanel = new JPanel(); 
		namePanel.setLayout(new GridLayout(1,1));  
		 
		namePanel.add(nameLabel); 
		namePanel.add(marathonNameTextField); 
		 
		this.add(namePanel);
	}  
	public void addEmployee() { 
		JLabel employeeLabel = new JLabel(" EmployeeID : "); 
		employeeIDTextField = new JTextField(5); 
		employeeIDTextField.setEditable(false); 
		JButton chooseEmployeeButton = new JButton("Choose EmployeeID"); 
		chooseEmployeeButton.addActionListener(new ChooseEmployeeActionListener());

		JPanel employeePanel = new JPanel(); 
		employeePanel.setLayout(new GridLayout(1,1));   
		 
		employeePanel.add(employeeLabel); 
		employeePanel.add(employeeIDTextField);  
		employeePanel.add(chooseEmployeeButton);
		 
		this.add(employeePanel);
	} 
	private void updateEmployeeDetails() {
		if(employee != null) {
			employeeIDTextField.setText(EmployeeFormatter.getLabelTextStatic(employee));
		} else {
			employeeIDTextField.setText("");
		}
	}
	public void addShowing() { 
		JLabel showingLabel = new JLabel(" ShowingID : "); 
		showingIDTextField= new JTextField(5); 
		showingIDTextField.setEditable(false);  
		JButton chooseShow = new JButton("Choose ShowingID");
		chooseShow.addActionListener(new ChooseShowingActionListener());				
		
		JPanel showingPanel = new JPanel(); 
		showingPanel .setLayout(new GridLayout(1,1));   
		 
		showingPanel.add(showingLabel); 
		showingPanel.add(showingIDTextField);   
		showingPanel.add(chooseShow);
		 
		this.add(showingPanel );
	} 
	private void updateShowingDetails() {
		if(showing != null) {
			showingIDTextField.setText(ShowingsFormatter.getLabelTextStatic(showing));
		} else {
			showingIDTextField.setText("");
		}
	}
	public String getName() { 
		return marathonNameTextField.getText();
	} 
	public Employee getEmployee() { 
		return employee;
	}
	@Override
	public boolean hasAnyChanges() {
		return 	!marathonNameTextField.getText().isEmpty() || 
				!employeeIDTextField.getText().isEmpty() || 
				!showingIDTextField.getText().isEmpty();  
	}

	@Override
	public boolean areInputsValid() {
		return isMarathonFieldValid() && isEmployeeFieldValid() && isShowingFieldValid(); 
	}  
	public boolean isMarathonFieldValid() {
		if( marathonNameTextField.getText().isEmpty()) { 
			JOptionPane.showMessageDialog(this, "Choose marathon name !"); 
			return false;
		}
		return true;
	}
	public boolean isEmployeeFieldValid(){ 
		return employee != null;
	}
	public boolean isShowingFieldValid(){ 
		return showing != null;
	}
	@Override
	public void doApplyAction() {
		 controller.createAndPersistMarathon(this);
		
	}
	
	@Override
	public Marathon doGetResultAction() {
		return controller.createMarathon(this);
	}
	
	@Override
	public void handleRequestedResult(IEntity result) {
		if(result instanceof Showing) {
			showing = (Showing) result; 
			updateShowingDetails();
		} else if(result instanceof Employee) {
			employee = (Employee) result;
			updateEmployeeDetails();
		}
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
		marathonNameTextField.setText("");
		employeeIDTextField.setText("");
		showingIDTextField.setText("");
		
	}
	

	public static ViewCreator<AddMarathonView> getCreator() {
		return new AddMarathonViewCreator();
	}
	
	private static class AddMarathonViewCreator implements ViewCreator<AddMarathonView> {

		@Override
		public AddMarathonView createView(ViewManager viewManager) {
			return new AddMarathonView(viewManager);
		}
		
	} 
	private class ChooseEmployeeActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			viewManager.requestResultFrom(ShowEmployeesView.getCreator(false));
		}
	}
	
	private class ChooseShowingActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			viewManager.requestResultFrom(ShowShowingsView.getCreator(false));
		}
	}
}
