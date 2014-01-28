package org.cinemanager.gui;

import static org.cinemanager.common.ValidatingHelper.isValidDate;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.cinemanager.controller.BookingController;
import org.cinemanager.entity.Booking;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Seat;
import org.cinemanager.entity.Showing;


public class AddBookingView extends View<Booking> {

	private static final long serialVersionUID = 1L;
	private static final String DATE_FORMAT = "yyyy-MM-dd";   
	private final SimpleDateFormat dateParser = new SimpleDateFormat(DATE_FORMAT);
	private static final String APPLY_BUTTON_LABEL = "Save";
	private static final String CANCEL_BUTTON_LABEL = "Cancel";
	private static final String SHOWING_DATE_FORMAT = "dd MMM 'at' hh:mm";
	private final SimpleDateFormat showingDateParser = new SimpleDateFormat(SHOWING_DATE_FORMAT);

	private JTextField showingTextField, seatTextField, dateTextField; 
	private JButton chooseShowingButton, chooseSeatButton; 
	private Seat seat; 
	private Showing showing; 
	
	private final BookingController bookingController = BookingController.getInstance(); 
	private final ViewManager viewManager; 
	
	private AddBookingView(ViewManager viewManager) {
		this.viewManager = viewManager; 
		this.setLayout(new GridLayout(4,1));
		
		addTitle();
		addShowing();
		addSeat();
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
	
	public void addDate() { 
		JLabel dateLabel = new JLabel(" Date (Format yyyy-MM-dd) :"); 
		dateTextField = new JTextField(10); 
		 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		 
		panel.add(dateLabel); 
		panel.add(dateTextField); 
		 
		this.add(panel);
	} 
	
	public void addSeat() {
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		 
		JLabel seatLabel = new JLabel(" Seat ");  
		panel.add(seatLabel);
		
		seatTextField = new JTextField();
		seatTextField.setEditable(false);
		panel.add(seatTextField);
		
		chooseSeatButton = new JButton("Choose seat");
		chooseSeatButton.addActionListener(new ChooseSeatActionListener());
		panel.add(chooseSeatButton);	 
		 
		this.add(panel);
	}
	
	private void updateSeatDetails() {
		if(seat != null) {
			seatTextField.setText("seat " + convertNumberToLetter(seat.getRow()) + " " + seat.getNumber());
		}
	}

	private String convertNumberToLetter(int i) {
		return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(i, i+1);
	}
	
	public void addShowing(){ 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1));
		
		JLabel showingLabel = new JLabel(" Showing "); 	 
		panel.add(showingLabel); 
		
		showingTextField = new JTextField();
		showingTextField.setEditable(false);
		panel.add(showingTextField);
		
		chooseShowingButton = new JButton("Choose Showing ");
		chooseShowingButton.addActionListener(new ChooseShowingActionListener());
		panel.add(chooseShowingButton);	 
		 
		this.add(panel);
	}
	
	private void updateShowingDetails() {
		if(showing != null) {
			showingTextField.setText(showing.getMovie().getTitle() + " on " + showingDateParser.format(showing.getDate()));
		}
	}
	
	@Override
	public boolean hasAnyChanges() {
		return !showingTextField.getText().isEmpty() || 
			   !seatTextField.getText().isEmpty() ||  
			   !dateTextField.getText().isEmpty();
	}
	
	@Override
	public boolean areInputsValid() {
		return isShowingFieldValid() && isSeatFieldValid() && isDateFieldValid();
	}
	public boolean isShowingFieldValid() { 
		if( showingTextField.getText().isEmpty()) { 
			JOptionPane.showMessageDialog(this, "You must choose Showing"); 
			return false;
		}
		return true;
	} 
	public boolean isSeatFieldValid() {    
		if( seatTextField.getText().isEmpty() ) { 
			JOptionPane.showMessageDialog(this, "You must choose Seat"); 
			return false;
		}
		return true;
	} 
	public boolean isDateFieldValid() { 
		if(dateTextField.getText().length() > 0  || !isValidDate(dateTextField.getText(), DATE_FORMAT)) {
			JOptionPane.showMessageDialog(this, "DateField has incorrect data");
			return false;
		}
		return true;
		
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
	public void handleRequestedResult(IEntity result) {
		if(result instanceof Showing) {
			showing = (Showing) result;
			updateShowingDetails();
		} else if (result instanceof Seat) {
			seat = (Seat) result;
			updateSeatDetails();
		}
	}
	
	@Override
	public String getApplyButtonLabel() {
		return APPLY_BUTTON_LABEL; 
	}
	
	@Override
	public String getCancelButtonLabel() {
		return CANCEL_BUTTON_LABEL;
	}
	
	@Override
	public void reset() {
		showingTextField.setText(""); 
		seatTextField.setText(""); 
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

	private class ChooseSeatActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(showing == null) {
				JOptionPane.showMessageDialog(AddBookingView.this, "Select showing first");
			} else {
				viewManager.requestResultFrom(ChooseSeatView.getCreator(showing.getAuditorium()));
			}
		}
	}
	
	private class ChooseShowingActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			viewManager.requestResultFrom(ShowShowingsView.getCreator());
		}
	}
}
