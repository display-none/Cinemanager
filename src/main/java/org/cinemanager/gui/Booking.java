package org.cinemanager.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Booking {
	private JPanel mainpanel,panel1,panel2,panel4; 
	private JTextField tf1,tf2,tf3;
	private JButton choose_showing,choose_seat,search;
	public  Booking(JPanel panel ) { 
		this.mainpanel = panel; 
		mainpanel.removeAll(); 
	} 
	public void add_booking() { 
		JLabel booking = new JLabel("Add new Booking  ");  
		JLabel seatID= new JLabel("SeatID : ");  
		JLabel showId = new JLabel("ShowID :  ");  
		JLabel date2 = new JLabel("Expiration Date of Booking : "); 
		JLabel date2_field = new JLabel(" Please choose your booking date previously !");
		
		panel1 = new JPanel(); 
		panel2 = new JPanel();  
		panel4 = new JPanel(); 
		 
		panel1.setLayout(new GridLayout(1,1)); 
		panel2.setLayout(new GridLayout(1,1)); 
		panel4.setLayout(new GridLayout(1,1)); 
		 
		tf1 = new JTextField(5); 
		tf1.setEditable(false);
		tf2 = new JTextField(5);  
		tf2.setEditable(false);
		tf3 = new JTextField(5);
		 
		choose_seat = new JButton("Choose SEAT"); 
		choose_seat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/** 
				 * Coœ tam
				 */
				
			}
		});  
		 
		choose_showing = new JButton("Choose SHOWING"); 
		choose_showing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/** 
				 * Coœ tam
				 */
				
			}
		});  
		panel1.add(seatID); 
		panel1.add(tf1); 
		panel1.add(choose_seat);  
		 
		panel2.add(showId); 
		panel2.add(tf2); 
		panel2.add(choose_showing);	  
		panel4.add(date2); 
		panel4.add(date2_field); 
		
		mainpanel.add(booking); 
		mainpanel.add(panel1);  
		mainpanel.add(panel2);  
		mainpanel.add(panel4); 
	} 
	public void canel_booking() { 
		JLabel booking = new JLabel("Cannel your Booking ");  
		JLabel date = new JLabel("Date (Format yyyy-MM-dd) : "); 
		JLabel seatID= new JLabel("SeatID : ");  
		JLabel showId = new JLabel("ShowID :  ");   
		 
		
		panel1 = new JPanel(); 
		panel2 = new JPanel();  
		
		panel4 = new JPanel(); 
		 
		panel1.setLayout(new GridLayout(1,1)); 
		panel2.setLayout(new GridLayout(1,1)); 
		panel4.setLayout(new GridLayout(1,1));  
		 
		tf1 = new JTextField(5);
		tf2 = new JTextField(5);   
		tf2.setEditable(false);
		tf3 = new JTextField(5); 
		tf3.setEditable(false);
		
		choose_seat = new JButton("Choose SEAT"); 
		choose_seat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/** 
				 * Coœ tam
				 */
				
			}
		});  
		 
		choose_showing = new JButton("Choose SHOWING"); 
		choose_showing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/** 
				 * Coœ tam
				 */
				
			}
		});    
		 
		search = new JButton("SEARCH IN DATABASE"); 
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/** 
				 * Wyszukanie i wrzucenie na panel;
				 */
				
			}
		});
 		panel1.add(date); 
		panel1.add(tf1); 
		
		panel2.add(seatID); 
		panel2.add(tf2); 
		panel2.add(choose_seat);  
		 
		panel4.add(showId); 
		panel4.add(tf3); 
		panel4.add(choose_showing);  
		 
		panel4.add(search);
		 
		
		mainpanel.add(booking); 
		mainpanel.add(panel1); 
		mainpanel.add(panel2);
		mainpanel.add(panel4); 
	
	} 
	public void display_all_bookings() {
		
	}
}
