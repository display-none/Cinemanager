package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.cinemanager.common.ShowingVersion;
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
	  
	private JScrollPane scroll; 
	
	private ShowShowingsView(ViewManager viewManager, boolean withDeleteOption) {
		setLayout(new BorderLayout());
		
		List<Showing> showings = controller.getAllShowings();
		
		if(withDeleteOption) {
			showingsList = new EntityList<Showing>(showings, new ShowingsFormatter(), new ActionListenerCreator());
		} else {
			showingsList = new EntityList<Showing>(showings, new ShowingsFormatter());
		} 
		scroll = new JScrollPane(showingsList);
		this.add(scroll);
	}
	
	@Override
	public boolean hasAnyChanges() {
		return !showingsList.isSelectionEmpty();
	}

	@Override
	public boolean areInputsValid() {
		return true;
	}

	@Override
	public void doApplyAction() {
		
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
	
	public static ViewCreator<ShowShowingsView> getCreator(boolean withDeleteOption) {
		return new ShowShowingsViewCreator(withDeleteOption);
	}
	
	private static class ShowShowingsViewCreator implements ViewCreator<ShowShowingsView> {

		private boolean withDeleteOption;
		
		public ShowShowingsViewCreator(boolean withDeleteOption) {
			this.withDeleteOption = withDeleteOption;
		}

		@Override
		public ShowShowingsView createView(ViewManager viewManager) {
			return new ShowShowingsView(viewManager, withDeleteOption);
		}
		
	}
	private static class ShowingsFormatter implements EntityFormatter<Showing> {
		
		private static final String DATE_FORMAT = "HH:mm 'on' dd MMM yyyy";  
		private static final SimpleDateFormat dateParser = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);

		@Override
		public String getLabelText(Showing entity) {
			return entity.getMovie().getTitle() + 
					parseVersion(entity.getVersion()) +
					", " + parseDate(entity.getDate()) + 
					" on " + entity.getAuditorium().getName();
		}

		private String parseDate(Date date) {
			return dateParser.format(date);
		}

		private String parseVersion(ShowingVersion version) {
			if(version == ShowingVersion.VERSION_2D) {
				return " 2D";
			} else {
				return " 3D";
			}
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
			if (isUserSureToDeleteEntry()) { 
				controller.deleteShowing(id);
				callback.actionPerformed(null); 
			} 
		} 
		private boolean isUserSureToDeleteEntry() {
			return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(showingsList, "Are you sure you want to delete this entry?", null, JOptionPane.YES_NO_OPTION);
		}
	}
}
