package org.cinemanager.gui;

import static org.cinemanager.common.ViewUtils.*;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import org.cinemanager.gui.ShowShowingsView.ShowingsFormatter;


public class AddBookingView extends View<Booking> {

	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Save";
	private static final String CANCEL_BUTTON_LABEL = "Cancel";

	private JTextField showingTextField, seatTextField; 
	private Seat seat; 
	private Showing showing; 
	
	private final BookingController bookingController = BookingController.getInstance(); 
	private final ViewManager viewManager; 
	
	private AddBookingView(ViewManager viewManager) {
		this.viewManager = viewManager; 
		this.setLayout(new GridLayout(8, 1));
		
		addTitle();
		addShowing();
		addSeat();
	}
	
	public Seat getSeat(){ 
		return seat;
	}
	
	public Showing getShowing() { 
		return showing;
	}
	
	public void addTitle() {
		JPanel panel = createNicePanel(1);
		JLabel titleLabel = new JLabel("Add new Booking"); 
		titleLabel.setFont(new Font("Bold",Font.BOLD,15)); 
		
		panel.add(titleLabel);
		this.add(panel); 
	}
	
	public void addSeat() {
		JPanel panel = createNicePanel(3);
		 
		JLabel seatLabel = new JLabel(" Seat ");  
		panel.add(seatLabel);
		
		seatTextField = new JTextField();
		seatTextField.setEditable(false);
		panel.add(seatTextField);
		
		JButton chooseSeatButton = new JButton("Choose seat");
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
		JPanel panel = createNicePanel(3);
		
		JLabel showingLabel = new JLabel(" Showing "); 	 
		panel.add(showingLabel); 
		
		showingTextField = new JTextField();
		showingTextField.setEditable(false);
		panel.add(showingTextField);
		
		JButton chooseShowingButton = new JButton("Choose Showing ");
		chooseShowingButton.addActionListener(new ChooseShowingActionListener());
		panel.add(chooseShowingButton);	 
		 
		this.add(panel);
	}
	
	private void updateShowingDetails() {
		if(showing != null) {
			showingTextField.setText(ShowingsFormatter.getLabelTextStatic(showing));
		}
	}
	
	@Override
	public boolean hasAnyChanges() {
		return !showingTextField.getText().isEmpty() || 
			   !seatTextField.getText().isEmpty();
	}
	
	@Override
	public boolean areInputsValid() {
		return isShowingFieldValid() && isSeatFieldValid();
	}
	
	public boolean isShowingFieldValid() { 
		if(showing == null) { 
			JOptionPane.showMessageDialog(this, "You must choose Showing"); 
			return false;
		}
		return true;
	}
	
	public boolean isSeatFieldValid() {    
		if(seat == null) { 
			JOptionPane.showMessageDialog(this, "You must choose Seat"); 
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
				viewManager.requestResultFrom(ChooseSeatView.getCreator(showing));
			}
		}
	}
	
	private class ChooseShowingActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			viewManager.requestResultFrom(ShowShowingsView.getCreator(false, true));
		}
	}
}
