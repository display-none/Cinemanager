package org.cinemanager;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import org.cinemanager.controller.AuditoriumController;
import org.cinemanager.dao.AuditoriumDao;
import org.cinemanager.dao.Dao;
import org.cinemanager.entity.Auditorium;
import org.cinemanager.entity.IEntity;
import org.cinemanager.gui.AddBookingView;
import org.cinemanager.gui.AddEmployeeView;
import org.cinemanager.gui.AddGroupTicketView;
import org.cinemanager.gui.AddMarathonView;
import org.cinemanager.gui.AddMovieView;
import org.cinemanager.gui.AddShowingView;
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
	private static final String FRAME_TITLE = "Cinemanager";
	
	private JMenuBar menubar;
	private Button applyButton; 
	private Button cancelButton;
	private JPanel mainPanel;
	
	private LinkedList<View<? extends IEntity>> viewStack = Lists.newLinkedList();
	
	public Main() {
		super(FRAME_TITLE);
		setBounds(100, 50, 1000, 500);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createGui() {
		setLayout(new BorderLayout());
		  
		addMenu();
		addMainPanel();
		addButtons();
		
		bindEnterKeyToApplyAction();
	}

	public void showGui() {
		this.setVisible(true);
	}
	
	private void addMenu() {
		menubar = new JMenuBar();
		
		createTicketMenu();
		createEmployeeMenu();
		createMovieMenu();
		createShowingMenu();
		createBookingMenu();
		createMarathonMenu();
		
		add(menubar,BorderLayout.NORTH);
	}

	private void addMainPanel() {
		JScrollBar scroll = new JScrollBar(); 
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		scroll.add(mainPanel);
		add(mainPanel, BorderLayout.CENTER);
	}

	private void addButtons() {
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
		buttonsPanel.setBorder(new EmptyBorder(7, 7, 7, 7));
		
		applyButton = new Button("Apply");
		applyButton.setFont(new Font("Font",Font.BOLD,20));
		applyButton.addActionListener(new ApplyActionListener());
		applyButton.setVisible(false);
		buttonsPanel.add(applyButton);
		
		cancelButton = new Button("Cancel");
		cancelButton.setFont(new Font("Font",Font.BOLD,20));
		cancelButton.addActionListener(new CancelActionListener());
		cancelButton.setVisible(false);
		buttonsPanel.add(cancelButton);
		
		add(buttonsPanel, BorderLayout.SOUTH);
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
		menu.add(getMenuItem("Show all employees", ShowEmployeesView.getCreator(true)));
		
		menubar.add(menu);
	}
	
	private void createMovieMenu() {
		JMenu menu = new JMenu("Movie");
		
		menu.add(getMenuItem("Add new movie", AddMovieView.getCreator()));
		menu.add(getMenuItem("Show all movies", ShowMoviesView.getCreator(true)));
		
		menubar.add(menu);
	}
	
	private void createShowingMenu() {
		JMenu menu = new JMenu("Showing");
		
		menu.add(getMenuItem("Add new showing", AddShowingView.getCreator()));
		menu.add(getMenuItem("Show all showings", ShowShowingsView.getCreator(true)));
		
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
		
		applyButton.setLabel(view.getApplyButtonLabel());
		applyButton.setVisible(true);
		
		cancelButton.setLabel(view.getCancelButtonLabel());
		cancelButton.setVisible(true);
		
		repaint();
		revalidate();
	}
	
	private void removeView() {
		mainPanel.removeAll();
		
		applyButton.setVisible(false);
		cancelButton.setVisible(false);
		
		repaint();
		revalidate();
	}

	private void bindEnterKeyToApplyAction() {
		mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "enter pressed");
		mainPanel.getActionMap().put("enter pressed", new AbstractAction() {
			
			private ApplyActionListener listener = new ApplyActionListener();
			
	        @Override
	        public void actionPerformed(ActionEvent ae) {
	            if(!viewStack.isEmpty()) {
	            	listener.actionPerformed(null);
	            }
	        }
	    });
	}
	
	private boolean areThereChangesInCurrentView() {
		return areThereChagesInView(viewStack.getLast());
	}
	
	private boolean areThereChagesInView(View<? extends IEntity> view) {
		return view.hasAnyChanges();
	}

	private boolean isUserWillingToDiscardChanges() {
		return JOptionPane.showConfirmDialog(this, "Any unsaved changes will be discarded.\n Do you want to continue?", "Warning", 
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
	}
	
	
	@Override
	public <T extends View<? extends IEntity>> void requestResultFrom(ViewCreator<T> viewCreator) {
		T view = viewCreator.createView(this);
		viewStack.addLast(view);
		setCurrentView(view);
	}

	private class PanelLaunchingListener<T extends View<? extends IEntity>> implements ActionListener {
		
		private ViewCreator<T> viewCreator;
		
		public PanelLaunchingListener(ViewCreator<T> viewCreator) {
			this.viewCreator = viewCreator;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!viewStack.isEmpty()) {
				if(areThereChangesInViews() && !isUserWillingToDiscardChanges()) {
					return;
				}
				viewStack.clear();
			}
			
			T view = viewCreator.createView(Main.this);
			viewStack.addLast(view);
			setCurrentView(view);
		}

		private boolean areThereChangesInViews() {
			for(View<? extends IEntity> view : viewStack) {
				if(areThereChagesInView(view)) {
					return true;
				}
			}
			return false;
		}
	}
	
	private class ApplyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(viewStack.getLast().areInputsValid()) {
				View<? extends IEntity> view = viewStack.removeLast();
				
				if(viewStack.isEmpty()) {
					view.doApplyAction();
					removeView();
				} else {
					View<? extends IEntity> previousView = viewStack.getLast();
					
					IEntity result = view.doGetResultAction();
					previousView.handleRequestedResult(result);
					setCurrentView(previousView);
				}
			}
		}
	}
	
	private class CancelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!areThereChangesInCurrentView() || isUserWillingToDiscardChanges()) {
				viewStack.removeLast();
				
				if(viewStack.isEmpty()) {
					removeView();
				} else {
					setCurrentView(viewStack.getLast());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		initializeJpa(main);
		main.createGui();
		assureJpaClosedAtShutdown(main);
	}
	
	private static void insertDataIntoDatabaseIfNeeded() {
		AuditoriumController.getInstance().insertAuditoriaIfDbEmpty();
	}

	private static void initializeJpa(final Main main) {
		(new Thread() {
			@Override
			public void run() {
				Dao.initialize();
				main.showGui();
				insertDataIntoDatabaseIfNeeded();
			}
		}).start();
	}
	
	private static void assureJpaClosedAtShutdown(Main main) {
		main.addWindowStateListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Dao.close();
			}
		});
	}
}
