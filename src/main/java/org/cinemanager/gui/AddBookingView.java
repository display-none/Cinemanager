package org.cinemanager.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.cinemanager.controller.BookingController;
import org.cinemanager.controller.ShowingController;
import org.cinemanager.entity.Booking;
import org.cinemanager.entity.Seat;
import org.cinemanager.entity.Showing;


public class AddBookingView extends View<Booking> {

	private static final long serialVersionUID = 1L;
	private final ViewManager viewManager; 
	private final BookingController bookingController = BookingController.getInstance(); 
	
	private JTextField showingIdTextField,seatIdTextField,dateTextField; 
	private JButton showingButton,seatButton; 
	private Seat seat; 
	private Showing showing; 
	private final SimpleDateFormat dateParser = new SimpleDateFormat(DATE_FORMAT);
	private static final String DATE_FORMAT = "yyyy-MM-dd";   
	private static final String APPLY_BUTTON_LABEL = "Save"; 
	private AddBookingView(ViewManager viewManager) {
		this.viewManager = viewManager; 
		this.setLayout(new GridLayout(4,1)); 
		addTitle(); 
		addShowingID(); 
		addSeatID(); 
		addDate();
	}  
	public Seat getSeat(){ 
		return seat;
	} 
	public Showing getShow() { 
		return showing;
	} 
	public Date getDate() { 
		String date = dateTextField.getText();
		Date parsedDate = null;
		if(!(date.equals(""))) {
			try {
				parsedDate = dateParser.parse(date);
			} catch (ParseException e) {
			}
		}
		return parsedDate;
	}
	public void addTitle() { 
		JLabel titleLabel = new JLabel("Add new Booking"); 
		titleLabel.setFont(new Font("Bold",Font.BOLD,15)); 
		 
		this.add(titleLabel); 
	}  
	public void addDate(){ 
		JLabel dateLabel = new JLabel(" Date :"); 
		dateTextField = new JTextField(10); 
		 
		JPanel thirdPanel = new JPanel(); 
		thirdPanel.setLayout(new GridLayout(1,1)); 
		 
		thirdPanel.add(dateLabel); 
		thirdPanel.add(dateTextField);
	}
	public void addSeatID() {
		JLabel seatIDLabel = new JLabel("Seat ID : "); 
		seatIdTextField = new JTextField(5); 
		seatIdTextField.setEditable(false); 
		seatButton = new JButton("Choose SeatID "); 
		seatButton.addActionListener( new ActionListener() { 				/**SeatButton listener **/
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		JPanel secondPanel = new JPanel(); 
		secondPanel.setLayout(new GridLayout(1,1)); 
		 
		secondPanel.add(seatIDLabel); 
		secondPanel.add(seatIdTextField); 
		secondPanel.add(seatButton ); 
		this.add(secondPanel);
	}
	public void addShowingID(){ 
		JLabel showindIDLabel = new JLabel("Show ID : "); 
		showingIdTextField = new JTextField(5); 
		showingIdTextField.setEditable(false); 
		showingButton = new JButton("Choose ShowID "); 
		showingButton.addActionListener( new ActionListener() { 				/**ShowingButton listener **/
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		JPanel firstPanel = new JPanel(); 
		firstPanel.setLayout(new GridLayout(1,1)); 
		 
		firstPanel.add(showindIDLabel); 
		firstPanel.add(showingIdTextField); 
		firstPanel.add(showingButton); 
		this.add(firstPanel);
	}
	@Override
	public void doApplyAction() {
		bookingController.createAndPersistBooking(this);
		
	}
	
	@Override
	public Booking doGetResultAction() {
		return bookingController.createBooking(this);
	}
	
	@Override
	public String getApplyButtonLabel() {
		return APPLY_BUTTON_LABEL; 
	}
	
	@Override
	public void reset() {
		dateTextField.setText("");
		
	}

	public static ViewCreator<AddBookingView> getCreator() {
		return new AddBookingViewCreator();
	}
	
	private static class AddBookingViewCreator implements ViewCreator<AddBookingView> {

		@Override
		public AddBookingView createView(ViewManager viewManager) {
			return new AddBookingView(viewManager);
		}
		
	}
}
