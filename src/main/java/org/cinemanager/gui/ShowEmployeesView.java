package org.cinemanager.gui;

import org.cinemanager.entity.Employee;

public class ShowEmployeesView extends View<Employee> {

	private static final long serialVersionUID = 1L;

	private ShowEmployeesView(ViewManager viewManager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doApplyAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public Employee doGetResultAction() {
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
	
	public static ViewCreator<ShowEmployeesView> getCreator() {
		return new ShowEmployeesViewCreator();
	}
	
	private static class ShowEmployeesViewCreator implements ViewCreator<ShowEmployeesView> {

		@Override
		public ShowEmployeesView createView(ViewManager viewManager) {
			return new ShowEmployeesView(viewManager);
		}
		
	}
}
