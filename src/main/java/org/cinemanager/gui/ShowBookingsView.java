package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;

import org.cinemanager.controller.BookingController;
import org.cinemanager.entity.Booking;
import org.cinemanager.entity.IEntity;
import org.cinemanager.gui.EntityList.DeleteActionListenerCreator;
import org.cinemanager.gui.EntityList.EntityFormatter;

public class ShowBookingsView extends View<Booking> {

	private static final long serialVersionUID = 1L;
	 
	private static final String APPLY_BUTTON_LABEL = "Done";
	private static final String CANCEL_BUTTON_LABEL = "Back"; 
	private JList<Booking> bookingList;
	private static final BookingController controller = BookingController.getInstance();
	private ShowBookingsView(ViewManager viewManager) {
		setLayout(new BorderLayout());
		
		List<Booking> booking = controller.getAllBookings();
		
		bookingList = new EntityList<Booking>(booking, new BookingFormatter(), new ActionListenerCreator());  
		this.add(bookingList);
	}
	
	@Override
	public boolean hasAnyChanges() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doApplyAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public Booking doGetResultAction() {
		// TODO Auto-generated method stub
		return null;
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

		@Override
		public String getLabelText(Booking entity) {
			return null;
		}
		
	}
	
	private static class ActionListenerCreator implements DeleteActionListenerCreator {

		@Override
		public ActionListener create(Long id) {
			return new DeleteActionListener(id);
		}
		
	}
	
	private static class DeleteActionListener implements ActionListener {
		
		private Long id;
		
		public DeleteActionListener(Long id) {
			this.id = id;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			controller.deleteBooking(id);
		}
	}
}
