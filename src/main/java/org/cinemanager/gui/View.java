package org.cinemanager.gui;

import javax.swing.JPanel;

public abstract class View<T> extends JPanel {

	public abstract void doApplyAction();
	
	public abstract T doGetResultAction();
	
	public abstract String getApplyButtonLabel();
	
	public abstract void reset();
}
