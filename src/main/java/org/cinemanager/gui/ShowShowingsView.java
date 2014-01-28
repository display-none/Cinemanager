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
	
	private ShowShowingsView(ViewManager viewManager, boolean withDeleteOption, boolean forBooking) {
		setLayout(new BorderLayout());
		
		List<Showing> showings;
		if(forBooking) {
			showings = controller.getAllBookableShowings();
		} else {
			showings = controller.getAllFutureShowings();
		}
		
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
	
	public static ViewCreator<ShowShowingsView> getCreator(boolean withDeleteOption, boolean forBooking) {
		return new ShowShowingsViewCreator(withDeleteOption, forBooking);
	}
	
	private static class ShowShowingsViewCreator implements ViewCreator<ShowShowingsView> {

		private boolean withDeleteOption;
		private boolean forBooking;
		
		public ShowShowingsViewCreator(boolean withDeleteOption, boolean forBooking) {
			this.withDeleteOption = withDeleteOption;
			this.forBooking = forBooking;
		}

		@Override
		public ShowShowingsView createView(ViewManager viewManager) {
			return new ShowShowingsView(viewManager, withDeleteOption, forBooking);
		}
		
	}
	public static class ShowingsFormatter implements EntityFormatter<Showing> {
		
		private static final String DATE_FORMAT = "HH:mm 'on' dd MMM yyyy";  
		private static final SimpleDateFormat dateParser = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);

		@Override
		public String getLabelText(Showing entity) {
			return getLabelTextStatic(entity);
		}
		private static String parseDate(Date date) {
			return dateParser.format(date);
		}

		private static String parseVersion(ShowingVersion version) {
			if(version == ShowingVersion.VERSION_2D) {
				return " 2D";
			} else {
				return " 3D";
			}
		} 
		public static String getLabelTextStatic(Showing entity) { 
			return entity.getMovie().getTitle() + 
					parseVersion(entity.getVersion()) +
					", " + parseDate(entity.getDate()) + 
					" on " + entity.getAuditorium().getName();
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
