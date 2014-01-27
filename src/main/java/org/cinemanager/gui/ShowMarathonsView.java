package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import org.cinemanager.controller.MarathonController;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Marathon;
import org.cinemanager.gui.EntityList.DeleteActionListenerCreator;
import org.cinemanager.gui.EntityList.EntityFormatter;

public class ShowMarathonsView extends View<Marathon> {

	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Done";
	private static final String CANCEL_BUTTON_LABEL = "Back";  
	private static JList<Marathon> marathonList;
	private static final MarathonController controller = MarathonController.getInstance(); 
	 
	private static final String [] objects= new String[] { "Yes" , "No" };
	private ShowMarathonsView(ViewManager viewManager) {
	setLayout(new BorderLayout());
		
		List<Marathon> marathon = controller.getAllMarathons();
		
		marathonList = new EntityList<Marathon>(marathon, new MarathonFormatter(), new ActionListenerCreator());  
		this.add(marathonList);
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
		return marathonList.getSelectedValue();
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
	
	public static ViewCreator<ShowMarathonsView> getCreator() {
		return new ShowMarathonsViewCreator();
	}
	
	private static class ShowMarathonsViewCreator implements ViewCreator<ShowMarathonsView> {

		@Override
		public ShowMarathonsView createView(ViewManager viewManager) {
			return new ShowMarathonsView(viewManager);
		}
		
	} 

	private static class MarathonFormatter implements EntityFormatter<Marathon> {

		@Override
		public String getLabelText(Marathon entity) {						/** Dopisaæ **/
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
			int n = JOptionPane.showOptionDialog(marathonList, "Are you sure do you want delete this record ? \n  "+marathonList.getSelectedValue().getName() +"  "+ marathonList.getSelectedValue().getSupervisingEmployee().getId(), "Confirmation",  JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, objects, null); 
			if (n == JOptionPane.YES_OPTION ) { 
				controller.deleteMarathon(id);
				callback.actionPerformed(null); 
			} 
		}
	}
}
