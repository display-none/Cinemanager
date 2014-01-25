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
	private JList<JLabel>employeeList;
	private EmployeeController controller = EmployeeController.getInstance(); ; 
	private RowsElement rowsElement = new RowsElement(); 
	private ShowEmployeesView(ViewManager viewManager) {
		 
		employeeList = new JList<JLabel>( createListModel() );
		employeeList.setCellRenderer( new EmployeeCellRender()); 
		 
		this.add(employeeList); 
		this.setVisible(true);
	} 
	public DefaultListModel<JLabel> createListModel(){ 
		List<Employee> list = controller.getAllEmployee();  
		System.out.println("Size : " + list.size());
		DefaultListModel<JLabel> listmodel = new DefaultListModel<JLabel>();
		for(Employee it : list) { 
			 
			listmodel.addElement(new RowsElement().getPanel(it));
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
class EmployeeCellRender extends DefaultListCellRenderer{
	public EmployeeCellRender() { 
		
	}
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) { 
		  JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); 
		  
		  return label;
	} 
	
} 
class RowsElement {  
	private JPanel panel; 
	private JButton button = new JButton("Delete");
	public RowsElement(){ 
		
	} 
	public JLabel getPanel(Employee employee){ 
		JLabel label = new JLabel(); 
		label.add(new JLabel(employee.getFirstName()));
		return label;
	}
}
