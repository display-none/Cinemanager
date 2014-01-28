package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

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
 
	private JScrollPane scroll;  
	private ShowMarathonsView(ViewManager viewManager) {
	setLayout(new BorderLayout());
		
		List<Marathon> marathon = controller.getAllMarathons();
		
		marathonList = new EntityList<Marathon>(marathon, new MarathonFormatter(), new ActionListenerCreator());  
		scroll = new JScrollPane(marathonList);
		this.add(scroll);
	}
	
	@Override
	public boolean hasAnyChanges() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean areInputsValid() {
		throw new RuntimeException("zaimplementuj mnie. Patrz ShowMoviesView");
	}

	@Override
	public void doApplyAction() {

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
		public String getLabelText(Marathon entity) {
			return getLabelTextStatic(entity);
		}
		
		public static String getLabelTextStatic(Marathon entity) {
			return entity.getName() + " :  " + entity.getSupervisingEmployee().getFirstName() + " - " 
								    + entity.getSupervisingEmployee().getLastName()  
								    + " - " + entity.getSupervisingEmployee().getPosition().toString();
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
			if ( isUserSureToDeleteEntry() ) { 
				controller.deleteMarathon(id);
				callback.actionPerformed(null); 
			} 
		} 
		private boolean isUserSureToDeleteEntry() {
			return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(marathonList, "Are you sure you want to delete this entry?", null, JOptionPane.YES_NO_OPTION);
		}
	}
}
