package org.cinemanager.gui;

import org.cinemanager.entity.Auditorium;
import org.cinemanager.entity.IEntity;

public class ChooseAuditoriumView extends View<Auditorium> {

	private static final long serialVersionUID = 1L;

	private ChooseAuditoriumView(ViewManager viewManager) {
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
	public Auditorium doGetResultAction() {
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
	
	public static ViewCreator<ChooseAuditoriumView> getCreator() {
		return new ChooseAuditoriumViewCreator();
	}
	
	private static class ChooseAuditoriumViewCreator implements ViewCreator<ChooseAuditoriumView> {

		@Override
		public ChooseAuditoriumView createView(ViewManager viewManager) {
			return new ChooseAuditoriumView(viewManager);
		}
		
	}
}
