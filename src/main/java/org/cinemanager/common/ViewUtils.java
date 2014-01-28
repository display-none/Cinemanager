package org.cinemanager.common;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ViewUtils {

	public static JPanel createNicePanel(int columns) {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setLayout(new GridLayout(1,columns, 15, 0));
		return panel;
	}
}
