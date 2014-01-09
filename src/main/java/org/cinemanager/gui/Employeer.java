package org.cinemanager.gui;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Employeer { 
	private JPanel mainpanel,panel1,panel2,panel3; 
	private JTextField tf1,tf2;
	public Employeer(JPanel mainpanel) {  
		this.mainpanel = mainpanel; 
	}  
	public void add_new_empolyeer() { 
		mainpanel.removeAll();
		JLabel new_employeer = new JLabel(" Add new employeer ");  
		JLabel name = new JLabel(" Name ");  
		JLabel surname = new JLabel(" Surname ");  
		JLabel position = new JLabel(" Position ");  
		ButtonGroup bg = new ButtonGroup();  
		JRadioButton rb1,rb2,rb3;  
		panel1 = new JPanel(); 
		panel2 = new JPanel(); 
		panel3 = new JPanel(); 
		  
		panel1.setLayout(new GridLayout(1,1)); 
		panel2.setLayout(new GridLayout(1,1)); 
		panel3.setLayout(new GridLayout(1,1)); 
		 
	
		tf1 = new JTextField(10); 
		tf2 = new JTextField(10); 
		
		rb1 = new JRadioButton("JUNIOR"); 
		bg.add(rb1);  
		panel1.add(rb1); 
		 
		rb2 = new JRadioButton("SENIOR"); 
		bg.add(rb2);  
		panel1.add(rb2);  
		 
		rb3 = new JRadioButton("MANAGER"); 
		bg.add(rb3);  
		panel1.add(rb3); 
		
		
	    panel2.add(name); 
	    panel2.add(tf1); 
	     
	    panel3.add(surname); 
	    panel3.add(tf2); 
		 
		mainpanel.add(new_employeer); 
		mainpanel.add(position); 
		mainpanel.add(panel1); 
		mainpanel.add(panel2); 
		mainpanel.add(panel3);
		 
		
	} 
	public void delete_employeer() {  
		mainpanel.removeAll();
		JLabel new_employeer = new JLabel(" Delete employeer ");  
		JLabel name = new JLabel(" Name: ");  
		JLabel surname = new JLabel(" Surname: ");    
		panel1 = new JPanel(); 
		panel2 = new JPanel();    
	    panel1.setLayout(new GridLayout(1,1)); 
	    panel2.setLayout(new GridLayout(1,1));   
	    
		tf1 = new JTextField(10); 
		tf2 = new JTextField(10);  
		
		panel1.add(name); 
	    panel1.add(tf1); 
		     
	    panel2.add(surname); 
		panel2.add(tf2);  
		 
	
		mainpanel.add(new_employeer);  
		mainpanel.add(panel1); 
		mainpanel.add(panel2); 
	} 
	public void print_all_employeers() { 
		/** 
		 * Wybierz wszystkich pracownikow z bazy 
		 * Wrzuc do listy 
		 * Wrzuc liste na mainpanel 
		 * Dodac scrollbar
		 */
	} 
	public boolean cheat_data(String add_or_delete) { 
		boolean result = false;  
		if( tf1.getText().length() > 0 &&  tf2.getText().length() > 0 ) { 
			result = true;
		}
		return result;
	}
}
