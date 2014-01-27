package org.cinemanager.gui;

import javax.swing.JPanel;

import org.cinemanager.entity.IEntity;

public abstract class View<T> extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public abstract boolean hasAnyChanges();
	
	public abstract boolean areInputsValid();

	public abstract void doApplyAction();
	
	public abstract T doGetResultAction();
	
	public abstract void handleRequestedResult(IEntity result);
	
	public abstract String getApplyButtonLabel();
	
	public abstract String getCancelButtonLabel();
	
	public abstract void reset();
}
