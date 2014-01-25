package org.cinemanager.gui;

import org.cinemanager.entity.Booking;

public class ShowBookingsView extends View<Booking> {

	private static final long serialVersionUID = 1L;

	private ShowBookingsView(ViewManager viewManager) {
		// TODO Auto-generated constructor stub
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
	public String getApplyButtonLabel() {
		// TODO Auto-generated method stub
		return null;
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
}
