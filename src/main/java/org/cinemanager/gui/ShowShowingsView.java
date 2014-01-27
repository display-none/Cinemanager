package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import org.cinemanager.controller.ShowingController;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Showing;
import org.cinemanager.gui.EntityList.DeleteActionListenerCreator;
import org.cinemanager.gui.EntityList.EntityFormatter;

public class ShowShowingsView extends View<Showing> {

	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Done";
	private static final String CANCEL_BUTTON_LABEL = "Back";
	
	private static JList<Showing> showingsList;
	private static final ShowingController controller = ShowingController.getInstance(); 
	 
	private static final String [] objects= new String[] { "Yes" , "No" }; 
	
	private ShowShowingsView(ViewManager viewManager) {
		setLayout(new BorderLayout());
		
		List<Showing> showings= controller.getAllShowings();
		
		showingsList = new EntityList<Showing>( showings, new ShowingsFormatter(), new ActionListenerCreator());  
		this.add(showingsList);
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
		return  showingsList.getSelectedValue();
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
	
	public static ViewCreator<ShowShowingsView> getCreator() {
		return new ShowShowingsViewCreator();
	}
	
	private static class ShowShowingsViewCreator implements ViewCreator<ShowShowingsView> {

		@Override
		public ShowShowingsView createView(ViewManager viewManager) {
			return new ShowShowingsView(viewManager);
		}
		
	}
	private static class ShowingsFormatter implements EntityFormatter<Showing> {

		@Override
		public String getLabelText(Showing entity) {
			return null;
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
			int n = JOptionPane.showOptionDialog(showingsList, "Are you sure do you want delete this record ? \n  "+"Movie: "+ showingsList.getSelectedValue().getMovie().getTitle() + " , Date: "+showingsList.getSelectedValue().getDate().toString()+ " , AuditoriumID: "+showingsList.getSelectedValue().getAuditorium().getId()+" \n Version: "+ showingsList.getSelectedValue().getVersion().toString()+" , EmployeeID:"+showingsList.getSelectedValue().getSupervisingEmployee().getId(), "Confirmation",  JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, objects, null); 
			if (n == JOptionPane.YES_OPTION ) { 
				controller.deleteShowing(id);
				callback.actionPerformed(null); 
			} 
		}
	}
}
