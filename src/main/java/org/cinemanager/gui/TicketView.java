package org.cinemanager.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class TicketView extends JPanel{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = -7042826431619503918L;
	private JPanel mainpanel,panel1,panel2,panel3,panel4; 
	private JRadioButton rb1,rb2,rb3,rb4; 
	private JTextField tf1,tf2,tf3; 
	private ButtonGroup bg;  
	private JButton badd_new_ticket;
	private JButton choose_seat,choose_show;
	private Map<String,Integer> price_list = new TreeMap<String,Integer>();
	public TicketView(JPanel panel ) { 
		this.mainpanel = panel;
		mainpanel.removeAll(); 
		/** 
		 * Examples of prices
		 */
		price_list.put("NORMAL",20); 
		price_list.put("STUDENT",10); 
		price_list.put("CHILD",5); 
		price_list.put("SENIOR",10);
	} 
	public void add_new_ticket() {   
		//mainpanel.removeAll();
		JLabel addticket = new JLabel("Add new ticket");  
		JLabel seat = new JLabel("Seat : ");
		JLabel showId = new JLabel("ShowID : ");   
		JLabel price = new JLabel("Price : ");   
		
		panel1 = new JPanel(); 
		panel1.setLayout(new GridLayout(1,1)); 
		  
		bg = new ButtonGroup(); 
		
		rb1 = new JRadioButton("NORMAL");  
		rb1.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 tf3.setText( getprice("NORMAL"));
				
			} 
		 
		
		}); 
		bg.add(rb1);
		panel1.add(rb1); 
		rb2 = new JRadioButton("STUDENT");  
		rb2.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 tf3.setText( getprice("STUDENT"));
				
			} 
		 
		
		}); 
		bg.add(rb2);
		panel1.add(rb2);  
		rb3 = new JRadioButton("CHILD");  
		rb3.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 tf3.setText( getprice("CHILD"));
				
			} 
		 
		
		}); 
		bg.add(rb3);
		panel1.add(rb3);  
		rb4 = new JRadioButton("SENIOR");  
		rb4.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 tf3.setText( getprice("SENIOR"));
				
			} 
		 
		
		}); 
		bg.add(rb4);
		panel1.add(rb4);   
		 
		tf1 = new JTextField(5);  
		tf1.setEditable(false);
		tf2 = new JTextField(5);  
		tf2.setEditable(false);
		tf3 = new JTextField(5);   
		tf3.setEditable(false);
		 
		panel3 = new JPanel(); 
		panel3.setLayout( new GridLayout(1,1) );  
		choose_seat  = new JButton("Choose seat");  
		choose_seat.setFont(new Font("Font",Font.BOLD,15));
		choose_seat.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/** 
				 * WYŒWIETLANIE PANELU OD MIESJC
				 */
				
			}
		});
		panel3.add(seat); 
		panel3.add(tf1);  
		panel3.add(choose_seat);
		 
		panel4 = new JPanel(); 
		panel4.setLayout( new GridLayout(1,1) );  
		choose_show  = new JButton("Choose show");  
		choose_show.setFont(new Font("Font",Font.BOLD,15));
		choose_show.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/** 
				 * WYŒWIETLANIE PANELU OD FILMU
				 */
				
			}
		});
		panel4.add(showId); 
		panel4.add(tf2);   
		panel4.add(choose_show);
		 
		panel2 = new JPanel(); 
		panel2.setLayout( new GridLayout(1,1) ); 
		panel2.add(price); 
		panel2.add(tf3);  
		 
		
		
		mainpanel.add(addticket); 
		mainpanel.add(panel1);  
		mainpanel.add(panel4); 
		mainpanel.add(panel3); 
		mainpanel.add(panel2);
		
	}   
	public void add_new_group_ticket() { 
		mainpanel.removeAll(); 
		 
		 
		tf1 = new JTextField(10); 
		panel2 = new JPanel(); 
		panel2.setLayout(new GridLayout(1,1));   
		panel2.add(new JLabel("Name of Group Ticket : " )); 
		panel2.add(tf1);
		 
		panel1 = new JPanel(); 
		panel1.setLayout(new GridLayout(1,1));  
		
		  
		bg = new ButtonGroup(); 
		
		rb1 = new JRadioButton("NORMAL");  
		rb1.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 tf3.setText( getprice("NORMAL"));
				
			} 
		 
		
		}); 
		bg.add(rb1);
		panel1.add(rb1); 
		rb2 = new JRadioButton("STUDENT");  
		rb2.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 tf3.setText( getprice("STUDENT"));
				
			} 
		 
		
		}); 
		bg.add(rb2);
		panel1.add(rb2);  
		rb3 = new JRadioButton("CHILD");  
		rb3.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 tf3.setText( getprice("CHILD"));
				
			} 
		 
		
		}); 
		bg.add(rb3);
		panel1.add(rb3);  
		rb4 = new JRadioButton("SENIOR");  
		rb4.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 tf3.setText( getprice("SENIOR"));
				
			} 
		 
		
		}); 
		bg.add(rb4);
		panel1.add(rb4);    
		 
		badd_new_ticket = new JButton("Add new Ticket");  
		badd_new_ticket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add_new_ticket(); 
				refresh();
				
			}
		});
		panel3 = new JPanel(); 
		panel3.setLayout(new GridLayout(1,1)); 
		panel3.add(badd_new_ticket); 
		
		mainpanel.add(new JLabel(" Add new Group Ticket ")); 
		mainpanel.add(panel2); 
		mainpanel.add(panel1); 
		mainpanel.add(panel3);
	}
	public String getprice(String who) { 
		 
		return String.valueOf( price_list.get(who) + " z³ " );
	} 
	public void refresh() {  
		mainpanel.removeAll();
		mainpanel.repaint(); 
		mainpanel.revalidate();
	}
}
