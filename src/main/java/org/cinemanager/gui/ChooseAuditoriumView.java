package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JList;

import org.cinemanager.controller.AuditoriumController;
import org.cinemanager.entity.Auditorium;
import org.cinemanager.entity.IEntity;
import org.cinemanager.gui.EntityList.EntityFormatter;

public class ChooseAuditoriumView extends View<Auditorium> {

	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Done";
	private static final String CANCEL_BUTTON_LABEL = "Back";
	
	private JList<Auditorium> auditoriumList;
	
	private static final AuditoriumController controller = AuditoriumController.getInstance();

	private ChooseAuditoriumView(ViewManager viewManager) {
		setLayout(new BorderLayout());
		
		List<Auditorium> auditoria = controller.getAuditoria();
		
		auditoriumList = new EntityList<Auditorium>(auditoria, new AuditoriumFormatter());  
		this.add(auditoriumList);
	}
	
	@Override
	public boolean hasAnyChanges() {
		throw new RuntimeException("zaimplementuj mnie. Patrz AddMovieView");
	}

	@Override
	public boolean areInputsValid() {
		return !auditoriumList.isSelectionEmpty();
	}

	@Override
	public void doApplyAction() {

	}

	@Override
	public Auditorium doGetResultAction() {
		return auditoriumList.getSelectedValue();
	}
	
	@Override
	public void handleRequestedResult(IEntity result) {
		
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
	
	public static ViewCreator<ChooseAuditoriumView> getCreator() {
		return new ChooseAuditoriumViewCreator();
	}
	
	private static class ChooseAuditoriumViewCreator implements ViewCreator<ChooseAuditoriumView> {

		@Override
		public ChooseAuditoriumView createView(ViewManager viewManager) {
			return new ChooseAuditoriumView(viewManager);
		}
		
	}
	
	private static class AuditoriumFormatter implements EntityFormatter<Auditorium> {

		@Override
		public String getLabelText(Auditorium entity) {
			return entity.getName() + parseIsAirConditioned(entity.isAirConditioned()) + 
					parseIsSupporting3D(entity.isSupporting3D()) + 
					parseHasAccessabilityFeatures(entity.hasAccessabilityFeatures());
		}

		private String parseIsAirConditioned(boolean isAirConditioned) {
			return isAirConditioned ? ", air conditioned" : "";
		}
		
		private String parseIsSupporting3D(boolean isSupporting3D) {
			return isSupporting3D ? ", with 3D" : "";
		}
		
		private String parseHasAccessabilityFeatures(boolean hasAccessabilityFeatures) {
			return hasAccessabilityFeatures ? ", with accessability features" : "";
		}
		
	}
}
