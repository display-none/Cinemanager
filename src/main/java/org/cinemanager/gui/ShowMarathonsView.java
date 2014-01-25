package org.cinemanager.gui;

import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Marathon;

public class ShowMarathonsView extends View<Marathon> {

	private static final long serialVersionUID = 1L;

	private ShowMarathonsView(ViewManager viewManager) {
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
	public Marathon doGetResultAction() {
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
	
	public static ViewCreator<ShowMarathonsView> getCreator() {
		return new ShowMarathonsViewCreator();
	}
	
	private static class ShowMarathonsViewCreator implements ViewCreator<ShowMarathonsView> {

		@Override
		public ShowMarathonsView createView(ViewManager viewManager) {
			return new ShowMarathonsView(viewManager);
		}
		
	}
}
