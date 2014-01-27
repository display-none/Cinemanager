package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import org.cinemanager.controller.EmployeeController;
import org.cinemanager.entity.Employee;
import org.cinemanager.entity.IEntity;
import org.cinemanager.gui.EntityList.DeleteActionListenerCreator;
import org.cinemanager.gui.EntityList.EntityFormatter;

public class ShowEmployeesView extends View<Employee> {

	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Done";
	private static final String CANCEL_BUTTON_LABEL = "Back";
	
	private static JList<Employee> employeeList;
	private static final EmployeeController controller = EmployeeController.getInstance();
	 
	private ShowEmployeesView(ViewManager viewManager) {
		
		setLayout(new BorderLayout());
		
		List<Employee> employees = controller.getAllEmployees();
		
		employeeList = new EntityList<Employee>(employees, new EmployeeFormatter(), new ActionListenerCreator());  
		this.add(employeeList);
	} 

	@Override
	public boolean hasAnyChanges() {
		return false;
	}

	@Override
	public boolean areInputsValid() {
		throw new RuntimeException("zaimplementuj mnie. Patrz ShowMoviesView");
	}

	@Override
	public void doApplyAction() {

	}

	@Override
	public Employee doGetResultAction() {
		return employeeList.getSelectedValue();
	}
	
	@Override
	public void handleRequestedResult(IEntity result) {
		//this view does not request anything
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
	
	public static ViewCreator<ShowEmployeesView> getCreator() {
		return new ShowEmployeesViewCreator();
	}
	
	private static class ShowEmployeesViewCreator implements ViewCreator<ShowEmployeesView> {

		@Override
		public ShowEmployeesView createView(ViewManager viewManager) {
			return new ShowEmployeesView(viewManager);
		}
	}
	
	private static class EmployeeFormatter implements EntityFormatter<Employee> {

		@Override
		public String getLabelText(Employee entity) {
			return entity.getFirstName() + " " + entity.getLastName() + " - " + entity.getPosition().toString();
		}
		
	}
	
	private static class ActionListenerCreator implements DeleteActionListenerCreator {

		@Override
		public ActionListener create(Long id, ActionListener deleteSuccessfulCallback) {
			return new DeleteActionListener(id, deleteSuccessfulCallback);
		}
	}
	
	private static class DeleteActionListener implements ActionListener {
		
		private final Long id;
		private final ActionListener callback;
		
		public DeleteActionListener(Long id, ActionListener callback) {
			this.id = id;
			this.callback = callback;
		}

		@Override
		public void actionPerformed(ActionEvent e) { 
		    if (isUserSureToDeleteEntry() ) { 
				controller.deleteEmployee(id);
				callback.actionPerformed(null); 
		    } 
		}

		private boolean isUserSureToDeleteEntry() {
			return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(employeeList, "Are you sure you want to delete this entry?", null, JOptionPane.YES_NO_OPTION);
		}
	}
} 
