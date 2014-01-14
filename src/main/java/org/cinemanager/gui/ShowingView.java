package org.cinemanager.gui;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class ShowingView {  
	private JPanel mainpanel; 
	private JComboBox<String> cb1,cb2,cb3,cb4; 
	private JTextField tf1; 
	public ShowingView(JPanel mainpanel) { 
		this.mainpanel = mainpanel; 
		mainpanel.removeAll();
	} 
	public void add_show() {
		JLabel show = new JLabel( "Add new showing :  ");  
		JLabel movieID = new JLabel( " Movie ID : ");
		JLabel date = new JLabel( " Showing Date (Format yyyy-MM-dd ): "); 
		JLabel auditorium = new JLabel(" Auditorium :"); 
		JLabel version = new JLabel( " Version : "); 
		JLabel employeeID = new JLabel( " Employee ID : "); 
		JLabel marathonID = new JLabel( " Marathon ID : "); 
		 
		JPanel panel1,panel2,panel3,panel4,panel5,panel6; 
		 
		panel1 = new JPanel(); 
		panel1.setLayout(new GridLayout()); 
		 
		panel2 = new JPanel(); 
		panel2.setLayout(new GridLayout());
		 
		panel3 = new JPanel(); 
		panel3.setLayout(new GridLayout()); 
		 
		panel4 = new JPanel(); 
		panel4.setLayout(new GridLayout()); 
		 
		panel5 = new JPanel(); 
		panel5.setLayout(new GridLayout()); 
		 
		panel6 = new JPanel(); 
		panel6.setLayout(new GridLayout());   
		   
		String[] movieIDs = new String [] { "PUSTO"};
		cb1 = new JComboBox<String>(movieIDs);
		panel1.add(movieID); 
		panel1.add(cb1); 
		 
		tf1 = new JTextField(10); 
		panel2.add(date); 
		panel2.add(tf1); 
		 
		panel3.add(auditorium); 
		String [] auditoriumm = new String [] { "1" , "2" , "3" , "4" , "5" };
		cb4 = new JComboBox<String>(auditoriumm);  
		panel3.add(cb4);
		 
		panel4.add(version);
		ButtonGroup bg = new ButtonGroup(); 
		JRadioButton rb1,rb2,rb3; 
		 
		rb1 = new JRadioButton("2D");  
		bg.add(rb1);
		panel4.add(rb1); 
		 
		rb2 = new JRadioButton("3D");  
		bg.add(rb2);
		panel4.add(rb2); 
		 
		rb3 = new JRadioButton("BOTH");  
		bg.add(rb3);
		panel4.add(rb3); 
		  
		String [] employeers = new String [] { "PUSTO" };
		cb2 = new JComboBox<String>(employeers); 
		panel5.add(employeeID); 
		panel5.add(cb2);  
		 
		String [] marathons = new String [] { "PUSTO" };
		cb3 = new JComboBox<String>(marathons); 
		panel5.add(marathonID); 
		panel5.add(cb3);  
		 
		
		mainpanel.add(show);  
		mainpanel.add(panel1); 
		mainpanel.add(panel2); 
		mainpanel.add(panel3); 
		mainpanel.add(panel4);
		mainpanel.add(panel5); 
		mainpanel.add(panel6);
		
		
	} 
	public void delete_show() { 
		/** 
		 * Print z bazy potem double click pokaze potwierdzenie
		 */
	} 
 }
