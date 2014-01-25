package org.cinemanager.gui;

import org.cinemanager.entity.IEntity;

public interface ViewManager {

	<T extends View<? extends IEntity>> void requestResultFrom(ViewCreator<T> viewCreator);

}
