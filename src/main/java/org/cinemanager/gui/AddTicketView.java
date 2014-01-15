package org.cinemanager.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.cinemanager.common.TicketType;
import org.cinemanager.controller.TicketController;
import org.cinemanager.entity.Seat;
import org.cinemanager.entity.Showing;
import org.cinemanager.entity.Ticket;


public class AddTicketView extends JPanel implements View<Ticket>{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = -7042826431619503918L;
	private JTextField textfieldprice; 
	private ButtonGroup button_group;  
	private JComboBox<TicketType> combo_box_ticket_type;
	private JButton choose_seat,choose_show;
	private Map<String,Integer> price_list = new TreeMap<String,Integer>(); 
	private Seat seat; 
	private Showing showing; 
	private TicketController controller = TicketController.getInstance(); 
	private final String APPLY_BUTTON_LABEL = "Save";
	public AddTicketView() { 
		this.setLayout(new GridLayout(6,1)); 
		addPanelTitle(); 
		addSeat(); 
		addShow(); 
		addType(); 
		addPrice();
		addGroupTicket();     
		
		price_list.put("NORMAL",20); 
		price_list.put("STUDENT",10); 
		price_list.put("CHILD",5); 
		price_list.put("SENIOR",10);
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
		textfieldprice = new JTextField(5); 
		textfieldprice.setEditable(false); 
		 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		panel.add(pricelabel); 
		panel.add(textfieldprice); 
		
		this.add(panel);
		
	}
	private void addType() {  
		JPanel panel = new JPanel();  
		panel.setLayout(new GridLayout(1,1));
		button_group = new ButtonGroup(); 
		for(TicketType tickettype : TicketType.values()) {
			JRadioButton radioButton = new JRadioButton(tickettype .toString());
			button_group.add(radioButton);
			panel.add(radioButton);
		} 
		this.add(panel);
	}
	private void addShow() { 
		JLabel panelShow = new JLabel(" ShowID"); 	 
		choose_show = new JButton("Choose Show");     /** select show button **/
		 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		panel.add(panelShow); 
		panel.add(choose_show);	 
		 
		this.add(panel);
	}
	private void addPanelTitle() {
		JLabel panelTitle = new JLabel( " Add your new Ticket ");
		panelTitle.setFont(new Font(null, Font.BOLD, 15));
		this.add(panelTitle);
	}
	public String getprice(String who) { 
		 
		return String.valueOf( price_list.get(who) + " z³ " );
	}  
	public Seat getseatid() { 
		return seat;
	} 
	public Showing getshowid() { 
		return showing;
	} 
	public TicketType gettype() { 
		return (TicketType) combo_box_ticket_type.getSelectedItem();
	} 
	public int getprice() { 
		return Integer.parseInt( textfieldprice.getText());
	} 
	@Override
	public void doApplyAction() {
		controller.createAndPersistMovie(this);
		
	}
	@Override
	public Ticket doGetResultAction() {
		return controller.createMovie(this);
	}
	@Override
	public String getApplyButtonLabel() {
		return APPLY_BUTTON_LABEL;
	}
	@Override
	public void reset() {
		//combo_box_ticket_type.setSelectedIndex(0); /** nie mogê tego ustawiæ **/
		button_group.clearSelection();
		textfieldprice.setText(""); 
		
		
	}
}
