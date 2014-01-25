package org.cinemanager.gui;

import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Showing;

public class ShowShowingsView extends View<Showing> {

	private static final long serialVersionUID = 1L;

	private ShowShowingsView(ViewManager viewManager) {
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
	public Showing doGetResultAction() {
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
	
	public static ViewCreator<ShowShowingsView> getCreator() {
		return new ShowShowingsViewCreator();
	}
	
	private static class ShowShowingsViewCreator implements ViewCreator<ShowShowingsView> {

		@Override
		public ShowShowingsView createView(ViewManager viewManager) {
			return new ShowShowingsView(viewManager);
		}
		
	}
}
