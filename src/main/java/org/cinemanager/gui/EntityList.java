package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import org.cinemanager.entity.IEntity;

public class EntityList<T extends IEntity> extends JList<T> {

	private static final long serialVersionUID = 1L;
	
	private EntityFormatter<T> entityFormatter;
	private DeleteActionListenerCreator deleteActionListenerCreator;
	private BitSet deletedItems;
	
	/**
	 *	Creates an EntityList without delete button 
	 */
	public EntityList(List<T> elements, EntityFormatter<T> entityFormatter) {
		this(elements, entityFormatter, null);
	}

	public EntityList(List<T> elements, EntityFormatter<T> entityFormatter, DeleteActionListenerCreator deleteActionListenerCreator) {
		this.entityFormatter = entityFormatter;
		this.deleteActionListenerCreator = deleteActionListenerCreator;
		this.deletedItems = new BitSet(elements.size());
		
		createModel(elements);
		setCellRenderer(new EntityListCellRenderer(elements.size(), this));
	}
	
	public void addElement(T element) {
		((DefaultListModel<T>) getModel()).addElement(element);
		this.invalidate();
		setCellRenderer(new EntityListCellRenderer(getModel().getSize(), this));
	}
	
	@Override
	public T getSelectedValue() {
		if(isItemDeleted()) {
			JOptionPane.showMessageDialog(this, "This item was deleted and cannot be returned");
			return null;
		}
		return super.getSelectedValue();
	}

	private boolean isItemDeleted() {
		int index = getSelectedIndex();
		return index != -1 ? deletedItems.get(index) : false;
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
		
		/**
		 * Remember to call actionPerformed() on deleteSuccessfulCallback when delete was successful
		 */
		ActionListener create(Long id, ActionListener deleteSuccessfulCallback);
		
	}
	
	private class EntityListCellRenderer implements ListCellRenderer<T> {
		
		private static final int CELL_HEIGHT = 30;
		private static final int CELL_WIDTH = 750;
		JPanel[] panels;
		
		public EntityListCellRenderer(int size, JList<T> jList) {
			panels = new JPanel[size];
			jList.addMouseListener(new FuckingClicker());
		}
		
		@Override
		public Component getListCellRendererComponent(JList<? extends T> list, T element, int index, boolean isSelected, boolean cellHasFocus) {
			JPanel panel = getPanel(index);
			
			if(panel == null) {
				panel = createNewJPanel(list, element, index);
				setPanel(index, panel);
			}
			
			setColors(list, isSelected, panel);
			
			return panel;
		}
		
		private JPanel getPanel(int index) {
			return panels[index];
		}

		private void setPanel(int index, JPanel panel) {
			panels[index] = panel;
		}

		private JPanel createNewJPanel(JList<? extends T> list, T element, int index) {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setPreferredSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
			panel.setBorder(new EmptyBorder(3, 6, 3, 6));
			
			
			panel.setEnabled(list.isEnabled());
			panel.setFont(list.getFont());
			
			panel.add(new JLabel(entityFormatter.getLabelText(element)));
			
			if(deleteActionListenerCreator != null) {
				JButton deleteButton = new JButton("delete");
				deleteButton.addActionListener(deleteActionListenerCreator.create(element.getId(), new DeleteSuccessfulCallback(index)));
				panel.add(deleteButton, BorderLayout.EAST);
			}
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
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void onElementDeleted(int index) {
			JPanel panel = getPanel(index);
			for(Component comp : panel.getComponents()) {
				if(comp instanceof JLabel) {
					Map attributes = comp.getFont().getAttributes();
					attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
					comp.setFont( new Font(attributes) );
				} else if(comp instanceof JButton) {
					((JButton) comp).setEnabled(false);
				}
			}
			deletedItems.set(index);
		}
		
		private class DeleteSuccessfulCallback implements ActionListener {
			
			private final int index;
			
			public DeleteSuccessfulCallback(int index) {
				this.index = index;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				onElementDeleted(index);
			}
		}
		
		protected class FuckingClicker extends MouseAdapter {
			
			@Override
			public void mouseClicked(MouseEvent event) {
				clickButtonAt(event.getPoint());
			}
			
			private void clickButtonAt(Point point) {
				int index = EntityList.this.locationToIndex(point);
				if(index != -1) {
					JPanel panel = getPanel(index);
					Component comp = getComponentAt(panel, new Point(point.x, point.y % CELL_HEIGHT));
					if(comp instanceof JButton) {
						((JButton) comp).doClick(50);
					}
				}
			}
			
			private Component getComponentAt(Container parent, Point p) {
		        Component comp = null;
		        for (Component child : parent.getComponents()) {
		            if (child.getBounds().contains(p)) {
		                comp = child;
		            }
		        }
		        return comp;
		    }
		}
	}
}
