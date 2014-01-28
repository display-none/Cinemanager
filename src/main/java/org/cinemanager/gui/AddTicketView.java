package org.cinemanager.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.cinemanager.common.TicketType;
import org.cinemanager.controller.TicketController;
import org.cinemanager.controller.TicketPriceHelper;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Seat;
import org.cinemanager.entity.Showing;
import org.cinemanager.entity.Ticket;


public class AddTicketView extends View<Ticket> { 

	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Save";
	private static final String CANCEL_BUTTON_LABEL = "Cancel";
	private static final String SHOWING_DATE_FORMAT = "dd MMM 'at' hh:mm";
	private final SimpleDateFormat showingDateParser = new SimpleDateFormat(SHOWING_DATE_FORMAT);
	
	private JTextField priceTextField,seatTextField,showingTextField; 
	private RadioGroup<TicketType> ticketTypeRadioGroup;  
	private JButton chooseSeatButton, chooseShowingButton;
	private Seat seat;
	private Showing showing;

	private final TicketController controller = TicketController.getInstance();
	private final TicketPriceHelper ticketPriceHelper = new TicketPriceHelper();
	private final ViewManager viewManager;
	
	private AddTicketView(ViewManager viewManager) { 
		this.viewManager = viewManager;
		this.setLayout(new GridLayout(6,1));
		
		addPanelTitle(); 
		addShowing(); 
		addSeat(); 
		addType(); 
		addPrice();
	}  
	
	private void addPanelTitle() {
		JLabel panelTitle = new JLabel( " Add your new Ticket ");
		panelTitle.setFont(new Font(null, Font.BOLD, 15));
		this.add(panelTitle);
	}
	
	private void addSeat() { 
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
	
	private void addPrice() { 
		JLabel priceLabel = new JLabel(" Ticket Price "); 
		priceTextField = new JTextField(5); 
		priceTextField.setEditable(false); 
		 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		panel.add(priceLabel); 
		panel.add(priceTextField);
		
		updatePrice();
		
		this.add(panel);
	}
	
	private void updatePrice() {
		int newPriceInt = ticketPriceHelper.getPriceForTicketType(ticketTypeRadioGroup.getSelected());
		String newPrice = String.valueOf((int)newPriceInt/100) + "." + String.valueOf(newPriceInt % 100 - newPriceInt % 10) + String.valueOf(newPriceInt % 10);
		priceTextField.setText(newPrice);
	}
	
	private void addType() {  
		JPanel panel = new JPanel();  
		panel.setLayout(new GridLayout(1,1));
		
		JLabel ticketTypeLabel = new JLabel("Type : ");
		panel.add(ticketTypeLabel);
		
		ticketTypeRadioGroup = new RadioGroup<>(TicketType.values(), new PriceChangingActionListener());
		panel.add(ticketTypeRadioGroup);
		
		this.add(panel);
	}

	private void addShowing() { 
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
	
	public Seat getSeat() { 
		return seat;
	}
	
	public Showing getShowing() { 
		return showing;
	}
	
	public TicketType getTicketType() { 
		return ticketTypeRadioGroup.getSelected();
	}
	
	@Override
	public boolean hasAnyChanges() {
		return  !seatTextField.getText().isEmpty() || 
				!showingTextField.getText().isEmpty() || 
				!ticketTypeRadioGroup.isFirstSelected();
	}

	@Override
	public boolean areInputsValid() {
		return isSeatFieldValid() && isShowingFieldValid() ;
	} 
	public boolean isSeatFieldValid(){ 
		if( seatTextField.getText().isEmpty() ) {
			JOptionPane.showMessageDialog(this, "You must choose Seat ID"); 
			return false;
		} 
		return true;
	} 
	public boolean isShowingFieldValid() {
		if( showingTextField.getText().isEmpty() ) {
			JOptionPane.showMessageDialog(this, "You must choose Showing ID"); 
			return false;
		} 
		return true;
	} 
	@Override
	public void doApplyAction() {
		controller.createAndPersistTicket(this);
	}
	
	@Override
	public Ticket doGetResultAction() {
		return controller.createTicket(this);
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
		seatTextField.setText("");
		showingTextField.setText("");
		ticketTypeRadioGroup.setFirstSelected();
		priceTextField.setText(""); 
	}

	public static ViewCreator<AddTicketView> getCreator() {
		return new AddTicketViewCreator();
	}
	
	private static class AddTicketViewCreator implements ViewCreator<AddTicketView> {

		@Override
		public AddTicketView createView(ViewManager viewManager) {
			return new AddTicketView(viewManager);
		}
	}
	
	private class PriceChangingActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			updatePrice();
		}
	}
	
	private class ChooseSeatActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(showing == null) {
				JOptionPane.showMessageDialog(AddTicketView.this, "Select showing first");
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
