package org.cinemanager.gui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;


public class Main extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1050194729975759223L;
	private JMenuBar menubar;  
	private JMenu menu;   
	private JMenuItem menuitem;
	private Button apply_button; 
	private JPanel panel,panel_middle;
	private Ticket ticket;   
	private Booking booking;
	private Movie movie; 
	private Showing showing;
	private Employeer employeer;  
	private Marathon marathon;
	private JScrollBar scroll;
	public Main() { 
		super("CinemaManager");
		this.setSize(600, 400);  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		panel_middle = new JPanel();
		panel = new JPanel(); 
		panel_middle.setLayout(new GridLayout(7,2)); 
		scroll = new JScrollBar(); 
		scroll.add(panel_middle);
		panel.setLayout(new BorderLayout());   
		 
		panel.add(panel_middle,BorderLayout.CENTER);
		apply_button = new Button("Apply"); 
		apply_button.setFont(new Font("Font",Font.BOLD,20));
		apply_button.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/** 
				 * Funkcja ktora cos zrobi
				 */
				
			}  
			
		}); 
		panel.add( apply_button,BorderLayout.SOUTH);
	
		menubar = new JMenuBar();  
		/***********************************************************/ 
		menu = new JMenu("Ticket ");  
		menuitem = new JMenuItem("Add new ticket");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ticket = new Ticket(panel_middle);    
				ticket.add_new_ticket();
				panel.repaint();
				panel.revalidate();
				
			}  
			 
				 
		});  
		menu.add(menuitem);
		menuitem = new JMenuItem("GroupTicket"); 
		menuitem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ticket = new Ticket(panel_middle);    
				ticket.add_new_group_ticket();
				panel.repaint();
				panel.revalidate();
				
				
			}
		});
		menu.add(menuitem);  
		menubar.add(menu);
		/***********************************************************/  
		menu = new JMenu("Employee ");  
		menuitem = new JMenuItem("Add new employeer");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				employeer = new Employeer(panel_middle);  
				employeer.add_new_empolyeer(); 
				panel.repaint();
				panel.revalidate();
				
			}  
			 
				 
		});
		menu.add(menuitem);  
		menuitem = new JMenuItem("Delete employeer");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				employeer = new Employeer(panel_middle);  
				employeer.delete_employeer(); 
				panel.repaint();
				panel.revalidate();
				
			}  
			 
				 
		});
		menu.add(menuitem);  
		menuitem = new JMenuItem("Show all employeers");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				employeer = new Employeer(panel_middle);  
				employeer.print_all_employeers(); 
				panel.repaint();
				panel.revalidate();
				
			}  
			 
				 
		});
		menu.add(menuitem); 
		 
		 
		
		menubar.add(menu); 
		/***********************************************************/   
		menu = new JMenu("Movie ");  
		menuitem = new JMenuItem("Add new movie");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				movie = new Movie(panel_middle);  
				movie.add_movie(); 
				panel.repaint();
				panel.revalidate();
				
			}  
			 
				 
		});  
		menu.add(menuitem);  
		menuitem = new JMenuItem("Print or Delete movie");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				movie = new Movie(panel_middle);  
				movie.delete_movie(); 
				panel.repaint();
				panel.revalidate();
				
			}  
			 
				 
		}); 
		menu.add(menuitem);  
		menubar.add(menu);  
		/***********************************************************/    
		menu = new JMenu("Showing ");  
		menuitem = new JMenuItem("Add new show");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showing = new Showing(panel_middle); 
				showing.add_show();  
				panel.repaint();
				panel.revalidate();
				
			}  
			 
				 
		});  
		menu.add(menuitem);  
		menuitem = new JMenuItem("Display or Delete show");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}  
			 
				 
		});  
		menu.add(menuitem);  
		menubar.add(menu);  
		/***********************************************************/ 
		menu = new JMenu("Booking ");  
		menuitem = new JMenuItem("Make new booking");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				booking= new Booking(panel_middle); 
				booking.add_booking();  
				panel.repaint();
				panel.revalidate();
				
			}  
			 
				 
		});  
		menu.add(menuitem);  
		menuitem = new JMenuItem("Canel booking");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				booking= new Booking(panel_middle); 
				booking.canel_booking();  
				panel.repaint();
				panel.revalidate();
				
			}  
			 
				 
		});  
		menu.add(menuitem);  
		menuitem = new JMenuItem("Print all bookings");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}  
			 
				 
		}); 
		menu.add(menuitem);  
		menubar.add(menu);   
		/***********************************************************/  
		menu = new JMenu("Marathon ");  
		menuitem = new JMenuItem("Add new marathon");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				marathon= new Marathon(panel_middle); 
				marathon.add_marathon(); 
				panel.repaint();
				panel.revalidate();
				
			}  
			 
				 
		});  
		menu.add(menuitem);  
		menuitem = new JMenuItem("Display or delete marathons");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}  
			 
				 
		}); 
		menu.add(menuitem);  
		menubar.add(menu);   
		/***********************************************************/  
		panel.add(menubar,BorderLayout.NORTH);
		this.add(panel);
		this.setVisible(true);
	} 
	public static void main(String args[]) { 
		@SuppressWarnings("unused")
		Main main = new Main(); 
	}
}
