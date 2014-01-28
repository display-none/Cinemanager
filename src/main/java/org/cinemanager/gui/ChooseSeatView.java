package org.cinemanager.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.cinemanager.controller.BookingController;
import org.cinemanager.controller.TicketController;
import org.cinemanager.entity.Auditorium;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Seat;
import org.cinemanager.entity.Showing;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public class ChooseSeatView extends View<Seat> {

	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Done";
	private static final String CANCEL_BUTTON_LABEL = "Back";
	
	private JLabel selectedSeatLabel;
	private final Table<Integer, Integer, Seat> seatTable;
	
	private int selectedRow = -1;
	private int selectedColumn = -1;
	
	private final TicketController ticketController = TicketController.getInstance();
	private final BookingController bookingController = BookingController.getInstance();

	private ChooseSeatView(ViewManager viewManager, Showing showing) {
		setLayout(new BorderLayout());
		
		List<Seat> seats = showing.getAuditorium().getSeats();
		Set<Pair<Integer>> takenSeats = getTakenSeats(showing);
		
		int rows = getMaxRow(seats);
		int columns = getMaxNumber(seats);
		
		seatTable = createSeatTable(seats, rows, columns);
		
		addScreenLabel();
		addSeatGrid(rows, columns, seatTable, takenSeats);
		addSelectedIndicator();
	}

	private Set<Pair<Integer>> getTakenSeats(Showing showing) {
		Set<Pair<Integer>> takenSeats = Sets.newHashSet();
//		fo
//		takenSeats.addAll(ticketController.getSeatsTakenForShowing(showing.getId()));
//		takenSeats.addAll(bookingController.getBookedSeatsForShowing(showing.getId()));
		return takenSeats;
	}

	private void addScreenLabel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("screen"));
		add(panel, BorderLayout.NORTH);
	}

	private void addSeatGrid(int rows, int columns, Table<Integer, Integer, Seat> seatTable, Set<Pair<Integer>> takenSeats) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(rows, columns, 2, 2));
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(seatTable.contains(i, j)) {
					if(seatTable.get(i, j).isVip()) {
						panel.add(createChooseSeatButtonVip(i, j, takenSeats));
					} else {
						panel.add(createChooseSeatButton(i, j, takenSeats));
					}
				} else {
					panel.add(new JLabel());
				}
			}
		}
		add(panel);
	}
	
	private void addSelectedIndicator() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("selected: "));
		
		selectedSeatLabel = new JLabel();
		panel.add(selectedSeatLabel);
		
		add(panel, BorderLayout.SOUTH);
	}
	
	private void updateSelectedSeatIndicator() {
		selectedSeatLabel.setText(convertNumberToLetter(selectedRow) + selectedColumn);
	}

	private Table<Integer, Integer, Seat> createSeatTable(List<Seat> seats, int rows, int columns) {
		Table<Integer, Integer, Seat> seatTable = HashBasedTable.create(rows, columns);
		for(Seat seat : seats) {
			seatTable.put(seat.getRow(), seat.getNumber(), seat);
		}
		return seatTable;
	}
	
	private JButton createChooseSeatButtonVip(int row, int column, Set<Pair<Integer>> takenSeats) {
		JButton button = createChooseSeatButton(row, column, takenSeats);
		button.setForeground(Color.red);
		return button;
	}
	
	private JButton createChooseSeatButton(Integer row, Integer column, Set<Pair<Integer>> takenSeats) {
		JButton button = new JButton(convertNumberToLetter(row) + column);
		button.addActionListener(new ChooseSeatListener(row, column));
		if(takenSeats.contains(new Pair(row, column))) {
			button.setEnabled(false);
		}
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
	
	public static ViewCreator<ChooseSeatView> getCreator(Showing showing) {
		return new ChooseSeatViewCreator(showing);
	}
	
	private static class ChooseSeatViewCreator implements ViewCreator<ChooseSeatView> {

		private Showing showing;
		
		public ChooseSeatViewCreator(Showing showing) {
			this.showing = showing;
		}
		
		@Override
		public ChooseSeatView createView(ViewManager viewManager) {
			return new ChooseSeatView(viewManager, showing);
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
	
	private static class Pair<T> {
		
		private final T x;
		private final T y;
		
		public Pair(T x, T y) {
			this.x = x;
			this.y = y;
		}
	}
}
