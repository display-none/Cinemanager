package org.cinemanager.gui;

import org.cinemanager.entity.GroupTicket;
import org.cinemanager.entity.IEntity;

public class AddGroupTicketView extends View<GroupTicket> {

	private static final long serialVersionUID = 1L;

	private AddGroupTicketView(ViewManager viewManager) {
		// TODO Auto-generated constructor stub
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
	public GroupTicket doGetResultAction() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void handleRequestedResult(IEntity result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getApplyButtonLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCancelButtonLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
	
	public static ViewCreator<AddGroupTicketView> getCreator() {
		return new AddGroupTicketViewCreator();
	}
	
	private static class AddGroupTicketViewCreator implements ViewCreator<AddGroupTicketView> {

		@Override
		public AddGroupTicketView createView(ViewManager viewManager) {
			return new AddGroupTicketView(viewManager);
		}
		
	}
}
