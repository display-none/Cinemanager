package org.cinemanager;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;


import org.cinemanager.gui.BookingView;
import org.cinemanager.gui.EmployeerView;
import org.cinemanager.gui.MarathonView;
import org.cinemanager.gui.AddMovieView;
import org.cinemanager.gui.ShowingView;
import org.cinemanager.gui.TicketView;

import com.google.common.collect.Lists;

public class Main extends JFrame implements WindowListener { 
	private JMenuBar menubar;  
	private JMenu menu;   
	private JMenuItem menuitem;
	private Button apply_button; 
	private JPanel panel,panel_middle;
	private TicketView ticket;   
	private BookingView booking;
	private AddMovieView movie; 
	private ShowingView showing;
	private EmployeerView employeer;  
	private MarathonView marathon;
	private JScrollBar scroll;   
	private EntityManagerFactory emf; 
	private static EntityManager em; 
	private static EntityTransaction tx;
	public Main ( )  {  						/** create gui interface **/  
		super("CinemaManager");
		this.setSize(600, 400);  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		  
		addWindowListener(this);
		panel_middle = new JPanel();
		panel = new JPanel(); 
		panel_middle.setLayout(new BorderLayout()); 
		scroll = new JScrollBar(); 
		scroll.add(panel_middle);
		panel.setLayout(new BorderLayout());   
		 
		panel.add(panel_middle,BorderLayout.CENTER);
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
		menu = new JMenu("Ticket ");  
		menuitem = new JMenuItem("Add new ticket");  
		menuitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ticket = new TicketView(panel_middle);    
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
				ticket = new TicketView(panel_middle);    
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
				employeer = new EmployeerView(panel_middle);  
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
				employeer = new org.cinemanager.gui.EmployeerView(panel_middle);  
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
				employeer = new org.cinemanager.gui.EmployeerView(panel_middle);  
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
				movie = new AddMovieView();  
				movie.reset();
				panel_middle.removeAll();
				panel_middle.add(movie);
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
				showing = new org.cinemanager.gui.ShowingView(panel_middle); 
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
				booking= new BookingView(panel_middle); 
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
				booking= new BookingView(panel_middle); 
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
				marathon= new org.cinemanager.gui.MarathonView(panel_middle); 
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
	
	public static void main(String[] args) {
		Main main = new Main();
		
		/*Movie movie = new Movie();
		movie.setTitle("sth");
		movie.setGenre(MovieGenre.ACTION);
		movie.setReleaseDate(new Date());
		movie.setRuntime(123);
		movie.setVersion(MovieVersion.BOTH);
		em.persist(movie);
		
		Ticket t1 = new Ticket();
		Ticket t2 = new Ticket();
		Ticket t3 = new Ticket();
		GroupTicket gt = new GroupTicket();
		gt.setTickets(Lists.newArrayList(t1, t2, t3));
		em.persist(gt);
		
		Auditorium auditorium = new Auditorium();
		em.persist(auditorium);
		
		Employee employee = new Employee();
		employee.setFirstName("dupa");
		employee.setLastName("maryna");
		employee.setPosition(EmployeePosition.MANAGER);
		em.persist(employee);
		
		Showing showing = new Showing();
		showing.setAuditorium(auditorium);
		showing.setDate(new Date());
		showing.setSupervisingEmployee(employee);
		showing.setVersion(ShowingVersion.VERSION_2D);
		showing.setMovie(movie);
		em.persist(showing);
		
		Seat seat = new Seat();
		seat.setAuditorium(auditorium);
		seat.setRow(0);
		seat.setNumber(0);
		em.persist(seat);
		
		Booking booking = new Booking();
		booking.setExpirationDate(new Date());
		booking.setShowing(showing);
		booking.setSeat(seat);
		em.persist(booking);
		
		Ticket ticket = new Ticket();
		ticket.setPrice(3.5f);
		ticket.setSeat(seat);
		ticket.setShowing(showing);
		ticket.setType(TicketType.NORMAL);
		em.persist(ticket);
		
		em.flush();
		em.clear();
		Query query = em.createQuery("select m from Movie m");
		List<Movie> results = query.getResultList();
		
		System.out.println(results.get(0).getTitle());*/
	}
	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("OPENED");
		emf = Persistence.createEntityManagerFactory("openjpa");
		em = emf.createEntityManager(); 
		tx = em.getTransaction();
		tx.begin();
	}
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("CLOSING"); 
		tx.commit();
		em.close();
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("CLOSED");
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
