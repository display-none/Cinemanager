package org.cinemanager.gui;

import org.cinemanager.entity.Employee;

public class DeleteEmployeeView extends View<Employee> {

	private static final long serialVersionUID = 1L;

	private DeleteEmployeeView(ViewManager viewManager) {
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
	
	public static ViewCreator<DeleteEmployeeView> getCreator() {
		return new DeleteEmployeeViewCreator();
	}
	
	private static class DeleteEmployeeViewCreator implements ViewCreator<DeleteEmployeeView> {

		@Override
		public DeleteEmployeeView createView(ViewManager viewManager) {
			return new DeleteEmployeeView(viewManager);
		}
		
	}
}
