package org.cinemanager.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.cinemanager.common.EmployeePosition;
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
	
	private JTextField priceTextField; 
	private ButtonGroup ticketTypeButtonGroup;  
	private JButton choose_seat,choose_show;
	
	private Seat seat; 
	private Showing showing;

	private final TicketController controller = TicketController.getInstance();
	private final TicketPriceHelper ticketPriceHelper = new TicketPriceHelper();
	private final ViewManager viewManager;
	
	private AddTicketView(ViewManager viewManager) { 
		this.viewManager = viewManager;
		this.setLayout(new GridLayout(6,1)); 
		addPanelTitle(); 
		addSeat(); 
		addShow(); 
		addType(); 
		addPrice();
		addGroupTicket();     
	}  
	
	private void addPanelTitle() {
		JLabel panelTitle = new JLabel( " Add your new Ticket ");
		panelTitle.setFont(new Font(null, Font.BOLD, 15));
		this.add(panelTitle);
	}
	
	private void addSeat() { 
		JLabel panelSeat = new JLabel(" SeatID ");  
		choose_seat = new JButton("Choose seat");   /** select seat button **/
		 
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		panel.add(panelSeat);
		panel.add(choose_seat);	 
		 
		this.add(panel);
	}
	
	private void addGroupTicket() {
		
	}
	
	private void addPrice() { 
		JLabel pricelabel = new JLabel(" Ticket Price "); 
		priceTextField = new JTextField(5); 
		priceTextField.setEditable(false); 
		 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		panel.add(pricelabel); 
		panel.add(priceTextField); 
		
		this.add(panel);
	}
	
	private void addType() {  
		JPanel panel = new JPanel();  
		panel.setLayout(new GridLayout(1,1));
		
		ticketTypeButtonGroup = new ButtonGroup(); 
		for(TicketType ticketType : TicketType.values()) {
			JRadioButton radioButton = new JRadioButton(ticketType.toString());
			ticketTypeButtonGroup.add(radioButton);
			panel.add(radioButton);
			
			addPriceChangingActionListener(radioButton, ticketType);
		}
		this.add(panel);
	}

	private void addPriceChangingActionListener(JRadioButton radioButton, final TicketType ticketType) {
		radioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int newPriceInt = ticketPriceHelper.getPriceForTicketType(ticketType);
				String newPrice = String.valueOf((int)newPriceInt/100) + "." + String.valueOf(newPriceInt % 100 - newPriceInt % 10) + String.valueOf(newPriceInt % 10);
				priceTextField.setText(newPrice);
			}
		});
	}
	
	private void addShow() { 
		JLabel panelShow = new JLabel(" Show"); 	 
		choose_show = new JButton("Choose Show");     /** select show button **/
		 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		panel.add(panelShow); 
		panel.add(choose_show);	 
		 
		this.add(panel);
	}
	
	public Seat getSeat() { 
		return seat;
	}
	
	public Showing getShowing() { 
		return showing;
	}
	
	public TicketType getTicketType() { 
		return TicketType.valueOf((String) ticketTypeButtonGroup.getSelection().getSelectedObjects()[0]);		//this is horrible
	}
	
	@Override
	public boolean hasAnyChanges() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		
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
		//combo_box_ticket_type.setSelectedIndex(0); /** nie mogê tego ustawiæ **/
		ticketTypeButtonGroup.clearSelection();
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
}
