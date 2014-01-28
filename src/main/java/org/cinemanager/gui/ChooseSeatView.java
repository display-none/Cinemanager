package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.cinemanager.entity.Auditorium;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Seat;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class ChooseSeatView extends View<Seat> {

	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Done";
	private static final String CANCEL_BUTTON_LABEL = "Back";
	
	private TextArea selectedSeatTextArea;
	private final Table<Integer, Integer, Seat> seatTable;
	
	private int selectedRow = -1;
	private int selectedColumn = -1;

	private ChooseSeatView(ViewManager viewManager, Auditorium auditorium) {
		setLayout(new BorderLayout());
		
		List<Seat> seats = auditorium.getSeats();
		
		int rows = getMaxRow(seats);
		int columns = getMaxNumber(seats);
		
		seatTable = createSeatTable(seats, rows, columns);
		
		addScreenLabel();
		addSeatGrid(rows, columns, seatTable);
		addSelectedIndicator();
	}

	private void addScreenLabel() {
		add(new JLabel("screen"), BorderLayout.NORTH);
	}

	private void addSeatGrid(int rows, int columns, Table<Integer, Integer, Seat> seatTable) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(rows, columns));
		
		for(int i = 0; i < columns; i++) {
			for(int j = 0; j < rows; j++) {
				if(seatTable.contains(j, i)) {
					panel.add(createChooseSeatButton(j, i));
				} else {
					panel.add(new JLabel());
				}
			}
		}
		add(panel);
	}
	
	private void addSelectedIndicator() {
		add(new JLabel("selected: "));
		
		selectedSeatTextArea = new TextArea();
		selectedSeatTextArea.setEditable(false);
		add(selectedSeatTextArea);
	}
	
	private void updateSelectedSeatIndicator() {
		selectedSeatTextArea.setText(convertNumberToLetter(selectedRow) + selectedColumn);
	}

	private Table<Integer, Integer, Seat> createSeatTable(List<Seat> seats, int rows, int columns) {
		Table<Integer, Integer, Seat> seatTable = HashBasedTable.create(rows, columns);
		for(Seat seat : seats) {
			seatTable.put(seat.getRow(), seat.getNumber(), seat);
		}
		return seatTable;
	}
	
	private JButton createChooseSeatButton(int row, int column) {
		JButton button = new JButton(convertNumberToLetter(row) + column);
		button.addActionListener(new ChooseSeatListener(row, column));
		button.setBorder(new EmptyBorder(3, 3, 3, 3));
		return button;
	}

	private int getMaxRow(List<Seat> seats) {
		int max = 0;
		for(Seat seat : seats) {
			if(seat.getRow() > max) {
				max = seat.getRow();
			}
		}
		return max;
	}
	
	private int getMaxNumber(List<Seat> seats) {
		int max = 0;
		for(Seat seat : seats) {
			if(seat.getNumber() > max) {
				max = seat.getNumber();
			}
		}
		return max;
	}
	
	private static String convertNumberToLetter(int i) {
		return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(i, i+1);
	}

	@Override
	public boolean hasAnyChanges() {
		return selectedColumn != -1 || selectedRow != -1;
	}

	@Override
	public boolean areInputsValid() {
		if(selectedColumn == -1 || selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "You didn't select any seat");
			return false;
		}
		return true;
	}

	@Override
	public void doApplyAction() {
		//does nothing
	}

	@Override
	public Seat doGetResultAction() {
		return seatTable.get(selectedRow, selectedColumn);
	}
	
	@Override
	public void handleRequestedResult(IEntity result) {
		//this view does not request anything
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
	
	public static ViewCreator<ChooseSeatView> getCreator(Auditorium auditorium) {
		return new ChooseSeatViewCreator(auditorium);
	}
	
	private static class ChooseSeatViewCreator implements ViewCreator<ChooseSeatView> {

		private Auditorium auditorium;
		
		public ChooseSeatViewCreator(Auditorium auditorium) {
			this.auditorium = auditorium;
		}
		
		@Override
		public ChooseSeatView createView(ViewManager viewManager) {
			return new ChooseSeatView(viewManager, auditorium);
		}
		
	}
	
	private class ChooseSeatListener implements ActionListener {
		
		private final int row;
		private final int column;
		
		public ChooseSeatListener(int row, int column) {
			this.row = row;
			this.column = column;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedRow = row;
			selectedColumn = column;
			updateSelectedSeatIndicator();
		}
	}
}
