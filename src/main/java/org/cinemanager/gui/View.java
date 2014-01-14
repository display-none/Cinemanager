package org.cinemanager.gui;

public interface View<T> {

	void doApplyAction();
	
	T doGetResultAction();
	
	String getApplyButtonLabel();
	
	void reset();
}
