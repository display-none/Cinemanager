package org.cinemanager.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioGroup<T> extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private T elements[];
	private HackedButtonGroup buttonGroup = new HackedButtonGroup();
	
	public RadioGroup(T[] elements) {
		this(elements, null);
	}
	
	public RadioGroup(T[] elements, ActionListener radioActionListener) {
		if(elements.length == 0) {
			throw new RuntimeException("Zero length array passed. We no like");
		}
		this.elements = elements;
		
		setLayout(new GridLayout());
		
		for(T elem : elements) {
			JRadioButton radioButton = new JRadioButton(elem.toString());
			radioButton.addActionListener(radioActionListener);
			buttonGroup.add(radioButton);
			add(radioButton);
		}
		setFirstSelected();
	}

	public void setFirstSelected() {
		buttonGroup.setFirstSelected();
	}

	public T getSelected() {
		return elements[buttonGroup.getSelectedIndex()];
	}
	
	public boolean isFirstSelected() {
		return buttonGroup.getSelectedIndex() == 0;
	}
	
	private class HackedButtonGroup extends ButtonGroup {
		
		private static final long serialVersionUID = 1L;

		public int getSelectedIndex() {
			for(int i = 0; i < buttons.size(); i++) {
				if(buttons.get(i).isSelected()) {
					return i;
				}
			}
			throw new RuntimeException("impossible. Noting is selected");
		}

		public void setFirstSelected() {
			setSelected(buttons.get(0).getModel(), true);
		}
	}
	
}
