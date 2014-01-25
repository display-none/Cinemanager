package org.cinemanager;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;






import org.cinemanager.entity.IEntity;
import org.cinemanager.gui.AddGroupTicketView;
import org.cinemanager.gui.AddShowingView;
import org.cinemanager.gui.AddBookingView;
import org.cinemanager.gui.AddEmployeeView;
import org.cinemanager.gui.AddMarathonView;
import org.cinemanager.gui.AddMovieView;
import org.cinemanager.gui.AddTicketView;
import org.cinemanager.gui.ShowBookingsView;
import org.cinemanager.gui.ShowEmployeesView;
import org.cinemanager.gui.ShowMarathonsView;
import org.cinemanager.gui.ShowMoviesView;
import org.cinemanager.gui.ShowShowingsView;
import org.cinemanager.gui.View;
import org.cinemanager.gui.ViewCreator;
import org.cinemanager.gui.ViewManager;

import com.google.common.collect.Lists;


public class Main extends JFrame implements ViewManager { 
	
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar;  
	private Button apply_button; 
	private JPanel panel,mainPanel;
	private JScrollBar scroll;
	

	private LinkedList<View<? extends IEntity>> viewStack = Lists.newLinkedList();
	
	
	public Main ( )  {  						/** create gui interface **/  
		super("Cinemanager");
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
		
		createTicketMenu();
		createEmployeeMenu();
		createMovieMenu();
		createShowingMenu();
		createBookingMenu();
		createMarathonMenu();
		
		
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
		menu.add(getMenuItem("Show all employees", ShowEmployeesView.getCreator()));
		
		menubar.add(menu);
	}
	
	private void createMovieMenu() {
		JMenu menu = new JMenu("Movie");
		
		menu.add(getMenuItem("Add new movie", AddMovieView.getCreator()));
		menu.add(getMenuItem("Show all movies", ShowMoviesView.getCreator()));
		
		menubar.add(menu);
	}
	
	private void createShowingMenu() {
		JMenu menu = new JMenu("Showing");
		
		menu.add(getMenuItem("Add new showing", AddShowingView.getCreator()));
		menu.add(getMenuItem("Show all showings", ShowShowingsView.getCreator()));
		
		menubar.add(menu);
	}
	
	private void createBookingMenu() {
		JMenu menu = new JMenu("Booking");
		
		menu.add(getMenuItem("Add new booking", AddBookingView.getCreator()));
		menu.add(getMenuItem("Show all bookings", ShowBookingsView.getCreator()));
		
		menubar.add(menu);
	}
	
	private void createMarathonMenu() {
		JMenu menu = new JMenu("Marathon");
		
		menu.add(getMenuItem("Add new marathon", AddMarathonView.getCreator()));
		menu.add(getMenuItem("Show all marathons", ShowMarathonsView.getCreator()));
		
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
