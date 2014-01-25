package org.cinemanager.gui;

public interface ViewCreator<T> {

	T createView(ViewManager viewManager);
}
