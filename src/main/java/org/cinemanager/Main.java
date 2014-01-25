package org.cinemanager;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;













import org.cinemanager.entity.IEntity;
import org.cinemanager.gui.AddGroupTicketView;
import org.cinemanager.gui.BookingView;
import org.cinemanager.gui.AddEmployeeView;
import org.cinemanager.gui.DeleteEmployeeView;
import org.cinemanager.gui.MarathonView;
import org.cinemanager.gui.AddMovieView;
import org.cinemanager.gui.ShowEmployeesView;
import org.cinemanager.gui.ShowingView;
import org.cinemanager.gui.AddTicketView;
import org.cinemanager.gui.View;
import org.cinemanager.gui.ViewCreator;
import org.cinemanager.gui.ViewManager;

import com.google.common.collect.Lists;


public class Main extends JFrame implements ViewManager { 
	
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar;  
	private JMenu menu;   
	private JMenuItem menuitem;
	private Button apply_button; 
	private JPanel panel,mainPanel;
	private AddTicketView ticket;   
	private BookingView booking;
	private AddMovieView movie; 
	private ShowingView showing;
	private AddEmployeeView employee;  
	private MarathonView marathon;
	private JScrollBar scroll;
	

	private LinkedList<View<? extends IEntity>> viewStack = Lists.newLinkedList();
	
	
	public Main ( )  {  						/** create gui interface **/  
		super("CinemaManager");
	}
	
	private void createGui() {
		this.setSize(600, 400);  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		  
		mainPanel = new JPanel();
		panel = new JPanel(); 
		mainPanel.setLayout(new BorderLayout()); 
		scroll = new JScrollBar(); 
		scroll.add(mainPanel);
		panel.setLayout(new BorderLayout());   
		 
		panel.add(mainPanel,BorderLayout.CENTER);
		apply_button = new Button("Apply"); 
		apply_button.setFont(new Font("Font",Font.BOLD,20));
		apply_button.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}  
			
		}); 
		panel.add( apply_button,BorderLayout.SOUTH);
	
		
		
		menubar = new JMenuBar();  
		/***********************************************************/ 
		JMenu menu; 
		JMenuItem menuitem;
		
		createTicketMenu();
		createEmployeeMenu();
		
		
		
		
		/***********************************************************/   
		menu = new JMenu("Movie ");  
		menuitem = new JMenuItem("Add new movie");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				movie = new AddMovieView();  
				movie.reset();
				mainPanel.removeAll();
				mainPanel.add(movie);
				panel.repaint();
				panel.revalidate();
				
			}  
			 
				 
		});  
		menu.add(menuitem);  
		menuitem = new JMenuItem("Print or Delete movie");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				movie = new org.cinemanager.gui.AddMovieView(panel_middle);  
//				movie.delete_movie(); 
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
				showing = new org.cinemanager.gui.ShowingView(mainPanel); 
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
				booking= new BookingView(mainPanel); 
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
				booking= new BookingView(mainPanel); 
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
				marathon= new org.cinemanager.gui.MarathonView(mainPanel); 
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

	private void createTicketMenu() {
		JMenu menu = new JMenu("Ticket");
		
		menu.add(getMenuItem("Add new ticket", AddTicketView.getCreator()));
		menu.add(getMenuItem("Add new group ticket", AddGroupTicketView.getCreator()));  
		
		menubar.add(menu);
	}
	
	private void createEmployeeMenu() {
		JMenu menu = new JMenu("Emplyoee");
		
		menu.add(getMenuItem("Add new employee", AddEmployeeView.getCreator()));
		menu.add(getMenuItem("Delete employee", DeleteEmployeeView.getCreator()));
		menu.add(getMenuItem("Show all employees", ShowEmployeesView.getCreator()));
		
		menubar.add(menu);
	}

	private <T extends View<? extends IEntity>> JMenuItem getMenuItem(String label, ViewCreator<T> viewCreator) {
		JMenuItem menuitem = new JMenuItem(label);  
		menuitem.addActionListener(new PanelLaunchingListener<T>(viewCreator));
		return menuitem;
	}
	
	
	
	
	
	private void setCurrentView(View<? extends IEntity> view) {
		mainPanel.removeAll();
		mainPanel.add(view);
		panel.repaint();
		panel.revalidate();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.createGui();
	}
	


	private class PanelLaunchingListener<T extends View<? extends IEntity>> implements ActionListener {
		
		private ViewCreator<T> viewCreator;
		
		public PanelLaunchingListener(ViewCreator<T> viewCreator) {
			this.viewCreator = viewCreator;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!viewStack.isEmpty()) {
				if(isUserWillingToDiscardChanges() == JOptionPane.CANCEL_OPTION) {
					return;
				}
				viewStack.clear();
			}
			
			T view = viewCreator.createView(Main.this);
			viewStack.addLast(view);
			setCurrentView(view);
		}

		private int isUserWillingToDiscardChanges() {
			return JOptionPane.showConfirmDialog(Main.this, "Any changes in current view will be discarded.\n Do you want to continue?", "Warning", 
					JOptionPane.OK_CANCEL_OPTION);
		}
	}
}
