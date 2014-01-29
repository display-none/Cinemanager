package org.cinemanager.gui;

import static org.cinemanager.common.ViewUtils.*;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.cinemanager.controller.MarathonController;
import org.cinemanager.entity.Employee;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Marathon;
import org.cinemanager.entity.Showing;
import org.cinemanager.gui.EntityList.DeleteActionListenerCreator;
import org.cinemanager.gui.ShowEmployeesView.EmployeeFormatter;
import org.cinemanager.gui.ShowShowingsView.ShowingsFormatter; 

import com.google.common.collect.Lists;


public class AddMarathonView extends View<Marathon> { 
	
	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Save";
	private static final String CANCEL_BUTTON_LABEL = "Cancel";  
	
	
	private final ViewManager viewManager;
	private JTextField marathonNameTextField,employeeIDTextField; 
	private static EntityList<Showing> showingList;
	
	private List<Showing> chosenShowings;
	private Employee employee;
	
	private final static MarathonController controller = MarathonController.getInstance();   
	
	private AddMarathonView(ViewManager viewManager) {
		this.viewManager = viewManager;
		
		this.setLayout(new GridLayout(2,1));
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));
		
		addTitle(panel); 
		addName(panel); 
		addEmployee(panel);  
		addShowing(panel);

		add(panel);
		
		chosenShowings = Lists.newArrayList();
		showingList = new EntityList<Showing>(chosenShowings, new ShowingsFormatter(), new ActionListenerCreator());
		
		JScrollPane scroll = new JScrollPane(showingList);
		this.add(scroll);
	}
	
	public void addTitle(JPanel mainPanel){
		JPanel panel = createNicePanel(1);
		JLabel titleLabel = new JLabel(" Add new marathon"); 
		titleLabel.setFont(new Font("Bold",Font.BOLD,15)); 
		 
		panel.add(titleLabel);
		mainPanel.add(panel);
	}
	
	public void addName(JPanel mainPanel) { 
		JPanel namePanel = createNicePanel(2);
		
		JLabel nameLabel = new JLabel("Name : "); 
		marathonNameTextField = new JTextField(10); 
		 
		namePanel.add(nameLabel); 
		namePanel.add(marathonNameTextField); 
		 
		mainPanel.add(namePanel);
	}
	
	public void addEmployee(JPanel mainPanel) { 
		JPanel employeePanel = createNicePanel(2);
		
		JLabel employeeLabel = new JLabel(" Employee : "); 
		employeeIDTextField = new JTextField(5); 
		employeeIDTextField.setEditable(false); 
		JButton chooseEmployeeButton = new JButton("Choose Employee"); 
		chooseEmployeeButton.addActionListener(new ChooseEmployeeActionListener());

		employeePanel.add(employeeLabel); 
		employeePanel.add(employeeIDTextField);  
		employeePanel.add(chooseEmployeeButton);
		 
		mainPanel.add(employeePanel);
	} 
	
	private void updateEmployeeDetails() {
		if(employee != null) {
			employeeIDTextField.setText(EmployeeFormatter.getLabelTextStatic(employee));
		} else {
			employeeIDTextField.setText("");
		}
	}
	
	public void addShowing(JPanel mainPanel) { 
		JPanel showingPanel = createNicePanel(3);
		
		JLabel showingLabel = new JLabel(" Showings : "); 
		JButton chooseShow = new JButton("Choose Showing");
		chooseShow.addActionListener(new ChooseShowingActionListener());				
		
		showingPanel.add(showingLabel); 
		showingPanel.add(chooseShow);
		 
		mainPanel.add(showingPanel );
	}
	
	private void addShowingToList(Showing showing) {
		if(!chosenShowings.contains(showing)) {
			chosenShowings.add(showing);
			showingList.addElement(showing);
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
				!employeeIDTextField.getText().isEmpty() ;
	}

	@Override
	public boolean areInputsValid() {
		return isMarathonFieldValid() && isEmployeeFieldValid() && areShowingsChosen(); 
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
	
	public boolean areShowingsChosen(){ 
		return !chosenShowings.isEmpty();
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
			addShowingToList((Showing) result);
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
			viewManager.requestResultFrom(ShowShowingsView.getCreator(false, false));
		}
	} 
	private class ActionListenerCreator implements DeleteActionListenerCreator {

		@Override
		public ActionListener create(Long id, ActionListener deleteSuccessfulCallback) {
			return new DeleteActionListener(id, deleteSuccessfulCallback);
		}
	}
	
	private class DeleteActionListener implements ActionListener {
		
		private final Long id;
		
		public DeleteActionListener(Long id, ActionListener callback) {
			this.id = id;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Showing toRemove = null;
			for(Showing showing : chosenShowings) {
				if(showing.getId() == id) {
					toRemove = showing;
				}
			}
			chosenShowings.remove(toRemove);
			
			showingList.removeElement(toRemove);
		} 
	} 
	
}
