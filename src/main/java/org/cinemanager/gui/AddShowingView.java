package org.cinemanager.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.cinemanager.common.ShowingVersion;
import org.cinemanager.controller.ShowingController;
import org.cinemanager.entity.Auditorium;
import org.cinemanager.entity.Employee;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Movie;
import org.cinemanager.entity.Showing;


public class AddShowingView extends View<Showing> {
	
	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Save"; 
	private static final String CANCEL_BUTTON_LABEL = "Cancel";
	private static final String DATE_FORMAT = "yyyy-MM-dd";  
	private static final SimpleDateFormat dateParser = new SimpleDateFormat(DATE_FORMAT);
	
	private JTextField  movieTextField,textfieldDate,auditoriumTextField,employeeTextField; 
	private RadioGroup<ShowingVersion> versionRadioGroup; 
	private Movie movie;  
	private Auditorium auditorium; 
	private Employee employee;
	
	private final ShowingController showingController = ShowingController.getInstance();
	private final ViewManager viewManager;
	
	private AddShowingView(ViewManager viewManager) {
		this.viewManager = viewManager;
		this.setLayout(new GridLayout(7,1));  
		
		addTitle();
		addMovie();
		addVersion();
		addAuditorium();
		addEmployee(); 
		addDate();
	}
	
	public void addTitle(){ 
		JLabel showingTitleLabel = new JLabel("Add new Showing"); 
		showingTitleLabel.setFont(new Font("Bold",Font.BOLD,15)); 
		 
		this.add(showingTitleLabel);
	}

	public void addMovie() { 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1));
		
		JLabel movieLabel = new JLabel(" Movie : ");   		
		panel.add(movieLabel); 
		
		movieTextField = new JTextField(10); 
		movieTextField.setEditable(false); 
		panel.add(movieTextField); 
		 
		JButton chooseMovieButton = new JButton("Choose Movie");  
		chooseMovieButton.addActionListener(new ChooseMovieActionListener());
		panel.add(chooseMovieButton);
		 
		  
		this.add(panel);	
	}
	
	private void updateMovieDetails() {
		if(movie != null) {
			movieTextField.setText(movie.getTitle());
		} else {
			movieTextField.setText("");
		}
	}

	public void addDate() { 
		JLabel dateLabel = new JLabel(" Showing Date (yyyy-MM-dd) : "); 
		textfieldDate = new JTextField(10); 
		 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		 
		panel.add(dateLabel); 
		panel.add(textfieldDate); 
		 
		this.add(panel);
	}

	public void addAuditorium(){
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1));
		
		JLabel auditoriumLabel = new JLabel("Auditorium : "); 
		panel.add(auditoriumLabel);
		
		auditoriumTextField = new JTextField(10); 
		auditoriumTextField.setEditable(false); 
		panel.add(auditoriumTextField);
		
		JButton auditoriumButton = new JButton("Choose Auditorium");  
		auditoriumButton.addActionListener(new ChooseAuditoriumActionListener());
		panel.add(auditoriumButton);  
		 
		this.add(panel); 
	}
	
	private void updateAuditoriumDetails() {
		if(auditorium != null) {
			auditoriumTextField.setText(auditorium.getName());
		} else {
			auditoriumTextField.setText("");
		}
	}

	public void addVersion() { 
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1));
		
		JLabel versionLabel = new JLabel("Version: ");
		panel.add(versionLabel);
		 
		versionRadioGroup= new RadioGroup<>(ShowingVersion.values());
		panel.add(versionRadioGroup);
		
		this.add(panel);
	}

	public void addEmployee(){ 
		JPanel panel = new JPanel();  
		panel.setLayout(new GridLayout(1,1));
		
		JLabel empolyeeLabel = new JLabel("Supervising employee : "); 
		panel.add(empolyeeLabel);
		
		employeeTextField = new JTextField(10); 
		employeeTextField.setEditable(false); 
		panel.add(employeeTextField);
		
		JButton chooseEmployeeButton = new JButton("Choose Employee");  
		chooseEmployeeButton.addActionListener(new ChooseEmployeeActionListener());
		panel.add(chooseEmployeeButton); 
		 
		this.add(panel);
	}
	
	private void updateEmployeeDetails() {
		if(employee != null) {
			employeeTextField.setText(employee.getFirstName() + " " + employee.getLastName());
		} else {
			employeeTextField.setText("");
		}
	}
	
	public Date getDate() {    
		String date = textfieldDate.getText();
		Date parsedDate = null;
		if(!(date.equals(""))) {
			try {
				parsedDate = dateParser.parse(date);
			} catch (ParseException e) {
			}
		}
		return parsedDate;
	}

	public Movie getMovie(){   
		return movie;
	}

	public Auditorium getAuditorium() { 
		return auditorium;
	}
	
	public Employee getEmployee() { 
		return employee;
	}

	public ShowingVersion getVersion(){ 
		return versionRadioGroup.getSelected();
	}
	
	@Override
	public boolean hasAnyChanges() {
		return !movieTextField.getText().isEmpty() || 
				!textfieldDate.getText().isEmpty() ||  
				!auditoriumTextField.getText().isEmpty() || 
				!employeeTextField.getText().isEmpty() || 
				!versionRadioGroup.isFirstSelected() ;
	}
	
	@Override
	public void doApplyAction() {
		showingController.createAndPersistShowing(this);
		
	}
	
	@Override
	public Showing doGetResultAction() {
		return showingController.createShowing(this);
	}
	
	@Override
	public void handleRequestedResult(IEntity result) {
		if(result instanceof Movie) {
			movie = (Movie) result;
			updateMovieDetails();
		} else if(result instanceof Auditorium) {
			auditorium = (Auditorium) result;
			updateAuditoriumDetails();
		} else if(result instanceof Employee) {
			employee = (Employee) result;
			updateEmployeeDetails();
		}
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
		 movieTextField.setText(""); 
		 textfieldDate.setText(""); 
		 auditoriumTextField.setText(""); 
		
	}
	
	public static ViewCreator<AddShowingView> getCreator() {
		return new AddShowingViewCreator();
	}
	
	private static class AddShowingViewCreator implements ViewCreator<AddShowingView> {

		@Override
		public AddShowingView createView(ViewManager viewManager) {
			return new AddShowingView(viewManager);
		}
	}
	
	private class ChooseMovieActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			viewManager.requestResultFrom(ShowMoviesView.getCreator());
		}
	}
	
	private class ChooseAuditoriumActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			viewManager.requestResultFrom(ChooseAuditoriumView.getCreator());
		}
	}
	
	private class ChooseEmployeeActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			viewManager.requestResultFrom(ShowEmployeesView.getCreator());
		}
	}
 }
