package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;

import org.cinemanager.controller.MarathonController;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Marathon;
import org.cinemanager.gui.EntityList.DeleteActionListenerCreator;
import org.cinemanager.gui.EntityList.EntityFormatter;

public class ShowMarathonsView extends View<Marathon> {

	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Done";
	private static final String CANCEL_BUTTON_LABEL = "Back";  
	private JList<Marathon> marathonList;
	private static final MarathonController controller = MarathonController.getInstance();
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
		// TODO Auto-generated method stub
		return null;
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
			return null;
		}
		
	}
	
	private static class ActionListenerCreator implements DeleteActionListenerCreator {

		@Override
		public ActionListener create(Long id) {
			return new DeleteActionListener(id);
		}
		
	}
	
	private static class DeleteActionListener implements ActionListener {
		
		private Long id;
		
		public DeleteActionListener(Long id) {
			this.id = id;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			controller.deleteMarathon(id);
		}
	}
}
