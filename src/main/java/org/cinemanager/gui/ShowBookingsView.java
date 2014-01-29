package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.cinemanager.common.ShowingVersion;
import org.cinemanager.controller.BookingController;
import org.cinemanager.entity.Booking;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Seat;
import org.cinemanager.gui.EntityList.DeleteActionListenerCreator;
import org.cinemanager.gui.EntityList.EntityFormatter;

public class ShowBookingsView extends View<Booking> {

	private static final long serialVersionUID = 1L;
	 
	private static final String APPLY_BUTTON_LABEL = "Done";
	private static final String CANCEL_BUTTON_LABEL = "Back"; 
	private static JList<Booking> bookingList;
	private static final BookingController controller = BookingController.getInstance();   
	 
	private JScrollPane scroll;  
	private ShowBookingsView(ViewManager viewManager) {
		setLayout(new BorderLayout());
		
		List<Booking> booking = controller.getAllBookings();
		
		bookingList = new EntityList<Booking>(booking, new BookingFormatter(), new ActionListenerCreator());  
		scroll = new JScrollPane(bookingList);
		this.add(scroll);
	}
	
	@Override
	public boolean hasAnyChanges() {
		return false;
	}

	@Override
	public boolean areInputsValid() {
		throw new RuntimeException("zaimplementuj mnie. Patrz ShowMoviesView");
	}

	@Override
	public void doApplyAction() {

	}

	@Override
	public Booking doGetResultAction() {
		return  bookingList.getSelectedValue();
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
		// TODO Auto-generated method stub

	}
	
	public static ViewCreator<ShowBookingsView> getCreator() {
		return new ShowBookingsViewCreator();
	}
	
	private static class ShowBookingsViewCreator implements ViewCreator<ShowBookingsView> {

		@Override
		public ShowBookingsView createView(ViewManager viewManager) {
			return new ShowBookingsView(viewManager);
		}
		
	} 
	
	private static class BookingFormatter implements EntityFormatter<Booking> {
		private static final String DATE_FORMAT = "HH:mm 'on' dd MMM yyyy";  
		private static final SimpleDateFormat dateParser = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH); 
		
		@Override
		public String getLabelText(Booking entity) {
			return "booking for " + entity.getShowing().getMovie().getTitle() + parseVersion(entity.getShowing().getVersion()) + 
					" at " + parseDate(entity.getShowing().getDate()) + 
					" seat " + parseSeat(entity.getSeat());
		}
		
		private String parseVersion(ShowingVersion version) {
			if(version == ShowingVersion.VERSION_2D) {
				return " 2D";
			} else {
				return " 3D";
			}
		}

		private String parseDate(Date date) {
			return dateParser.format(date);
		}

		private String parseSeat(Seat seat) {
			return convertNumberToLetter(seat.getRow()) + seat.getNumber() + (seat.isVip() ? " vip" : "");
		}
		
		private static String convertNumberToLetter(int i) {
			return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(i, i+1);
		}
	}
	
	private static class ActionListenerCreator implements DeleteActionListenerCreator {

		@Override
		public ActionListener create(Long id, ActionListener deleteSuccessfulCallback) {
			return new DeleteActionListener(id, deleteSuccessfulCallback);
		}
	}
	
	private static class DeleteActionListener implements ActionListener {
		
		private final Long id;
		private final ActionListener callback;
		
		public DeleteActionListener(Long id, ActionListener callback) {
			this.id = id;
			this.callback = callback;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if ( isUserSureToDeleteEntry() ) { 
				controller.deleteBooking(id);
				callback.actionPerformed(null); 
			} 
		} 
		private boolean isUserSureToDeleteEntry() {
			return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(bookingList, "Are you sure you want to delete this entry?", null, JOptionPane.YES_NO_OPTION);
		}
	}
}
