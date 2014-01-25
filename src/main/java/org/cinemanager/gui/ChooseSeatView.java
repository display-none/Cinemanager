package org.cinemanager.gui;

import org.cinemanager.entity.Auditorium;
import org.cinemanager.entity.Booking;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Seat;

import com.google.common.base.CaseFormat;

public class ChooseSeatView extends View<Seat> {

	private static final long serialVersionUID = 1L;

	private ChooseSeatView(ViewManager viewManager, Auditorium auditorium) {
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
	public Seat doGetResultAction() {
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
	
	public static ViewCreator<ChooseSeatView> getCreator(Auditorium auditorium) {
		return new ChooseSeatViewCreator(auditorium);
	}
	
	private static class ChooseSeatViewCreator implements ViewCreator<ChooseSeatView> {

		private Auditorium auditorium;
		
		public ChooseSeatViewCreator(Auditorium auditorium) {
			this.auditorium = auditorium;
		}
		
		@Override
		public ChooseSeatView createView(ViewManager viewManager) {
			return new ChooseSeatView(viewManager, auditorium);
		}
		
	}
}
