package org.cinemanager.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.cinemanager.controller.MarathonController;
import org.cinemanager.entity.Employee;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Marathon;


public class AddMarathonView extends View<Marathon> { 
	
	private static final long serialVersionUID = 1L;
	
	private final ViewManager viewManager;
	private Employee employee;  
	private JTextField marathonNameTextField,employeeIDTextField,showingIDTextField;  
	private static final String APPLY_BUTTON_LABEL = "Save";
	private static final String CANCEL_BUTTON_LABEL = "Cancel"; 
	 
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
		chooseEmployeeButton.addActionListener(new ActionListener() {					/**ChooseEmployeeIDButton ActionListner **/
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		JPanel employeePanel = new JPanel(); 
		employeePanel.setLayout(new GridLayout(1,1));   
		 
		employeePanel.add(employeeLabel); 
		employeePanel.add(employeeIDTextField);  
		employeePanel.add(chooseEmployeeButton);
		 
		this.add(employeePanel);
	}
	public void addShowing() { 
		JLabel showingLabel = new JLabel(" ShowingID : "); 
		showingIDTextField= new JTextField(5); 
		showingIDTextField.setEditable(false);  
		JButton chooseShow = new JButton("Choose ShowingID");
		chooseShow.addActionListener(new ActionListener() {   				/** ChooseShowButton actionlistner **/
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				
			}
		});
		JPanel showingPanel = new JPanel(); 
		showingPanel .setLayout(new GridLayout(1,1));   
		 
		showingPanel.add(showingLabel); 
		showingPanel.add(showingIDTextField);   
		showingPanel.add(chooseShow);
		 
		this.add(showingPanel );
	}
	public String getName() { 
		return marathonNameTextField.getText();
	} 
	public Employee getEmployee() { 
		return employee;
	}
	@Override
	public boolean hasAnyChanges() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		
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
}
