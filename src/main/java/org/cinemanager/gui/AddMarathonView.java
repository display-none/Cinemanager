package org.cinemanager.gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Marathon;


public class AddMarathonView extends View<Marathon> { 
	
	private static final long serialVersionUID = 1L;
	
	/*
	private JPanel mainpanel,panel1,panel2;
	private JTextField tf1,tf2;
	private JComboBox<Integer> cb;
	*/
	
	private final ViewManager viewManager;
	
	private AddMarathonView(ViewManager viewManager) {
		this.viewManager = viewManager;
	}
	
	/*
	public  AddMarathonView(JPanel panel ) { 
		this.mainpanel = panel; 
		mainpanel.removeAll(); 
	}  
	public void add_marathon() { 
		panel1 = new JPanel(); 
		panel2 = new JPanel();  
		 
		panel1.setLayout(new GridLayout(1,1)); 
		panel2.setLayout(new GridLayout(1,1)); 
		 
		tf1 = new JTextField(10); 
		tf2 = new JTextField(5);   
		tf2.setEditable(false);
		   
		panel1.add(new JLabel("Name of Marathon : ")); 
		panel1.add(tf1); 
		 
		panel2.add(new JLabel("EmployeeID : ")); 
		panel2.add(tf2); 
		cb = new JComboBox<Integer>(); 
		cb.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}); 
		panel2.add(cb); 
		
		mainpanel.add(new JLabel("Add new marathon ")); 
		mainpanel.add(panel1); 
		mainpanel.add(panel2); 
		
	} 
	public void delete_maratohn() {
	 
	}
	*/
	
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
	public Marathon doGetResultAction() {
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
