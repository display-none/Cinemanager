package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import org.cinemanager.entity.IEntity;

public class EntityList<T extends IEntity> extends JList<T> {

	private static final long serialVersionUID = 1L;
	
	private EntityFormatter<T> entityFormatter;
	private DeleteActionListenerCreator deleteActionListenerCreator;

	public EntityList(List<T> elements, EntityFormatter<T> entityFormatter, DeleteActionListenerCreator deleteActionListenerCreator) {
		this.entityFormatter = entityFormatter;
		this.deleteActionListenerCreator = deleteActionListenerCreator;
		
		createModel(elements);
		setCellRenderer(new EntityListCellRenderer()); 
	}

	private void createModel(List<T> elements) {
		DefaultListModel<T> listModel = new DefaultListModel<T>();
		for(T elem : elements) { 
			listModel.addElement(elem);
		}
		setModel(listModel);
	}
	
	public interface EntityFormatter<T extends IEntity> {
		
		String getLabelText(T entity);
	}
	
	public interface DeleteActionListenerCreator {
		
		ActionListener create(Long id);
		
	}
	
	private class EntityListCellRenderer implements ListCellRenderer<T> {
		
		@Override
		public Component getListCellRendererComponent(JList<? extends T> list, T element, int index, boolean isSelected, boolean cellHasFocus) {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setPreferredSize(new Dimension(750, 30));
			panel.setBorder(new EmptyBorder(3, 6, 3, 6));
			
			setColors(list, isSelected, panel);
			
			panel.setEnabled(list.isEnabled());
			panel.setFont(list.getFont());
			
			panel.add(new JLabel(entityFormatter.getLabelText(element)));
			
			JButton deleteButton = new JButton("delete");
			deleteButton.addActionListener(deleteActionListenerCreator.create(element.getId()));
			panel.add(deleteButton, BorderLayout.EAST);

			return panel;
		}

		private void setColors(JList<? extends T> list, boolean isSelected, JPanel panel) {
			if (isSelected) {
				panel.setBackground(list.getSelectionBackground());
				panel.setForeground(list.getSelectionForeground());
	        }
	        else {
	        	panel.setBackground(list.getBackground());
	        	panel.setForeground(list.getForeground());
	        }
		} 
	}
}
