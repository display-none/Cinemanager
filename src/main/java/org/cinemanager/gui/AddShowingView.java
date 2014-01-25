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
import org.cinemanager.entity.Marathon;
import org.cinemanager.entity.Movie;
import org.cinemanager.entity.Showing;


public class AddShowingView extends View<Showing> {
	
	private static final long serialVersionUID = 1L;
	private static final String APPLY_BUTTON_LABEL = "Save"; 
	private static final String CANCEL_BUTTON_LABEL = "Cancel";
	private static final String DATE_FORMAT = "yyyy-MM-dd";  
	private static final SimpleDateFormat dateParser = new SimpleDateFormat(DATE_FORMAT);
	
	private JTextField  textfieldMovieID,textfieldDate,textfieldAuditorium,textfieldEmployeeID,textfieldMarathonID; 
	private RadioGroup<ShowingVersion> versionRadioGroup; 
	private JButton buttonEmploeeID,buttonMarathonID;
	private Movie movie;  
	private Auditorium auditorium; 
	private Employee employee;
	
	private final ShowingController showingController = ShowingController.getInstance();
	private final ViewManager viewManager;
	
	private AddShowingView(ViewManager viewManager) {
		this.viewManager = viewManager;
		this.setLayout(new GridLayout(7,1));  
		
		addDate(); 
		addTitle(); 
		addMovieID(); 
		addAuditorium();  
		addEmployeeID(); 
		addVersion(); 
	}
	
	public void addTitle(){ 
		JLabel showingTitleLabel = new JLabel("Add new Showing"); 
		showingTitleLabel.setFont(new Font("Bold",Font.BOLD,15)); 
		 
		this.add(showingTitleLabel);
	}

	public void addMovieID() { 
		JLabel movieIDLabel = new JLabel(" MovieID : ");   		
		JButton buttonMovieID = new JButton("Choose MovieID");  
		buttonMovieID.addActionListener( new ActionListener() {					/**ActionListener MovieIDbutton **/
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
			}
		});
		textfieldMovieID = new JTextField(10); 
		textfieldMovieID.setEditable(false); 
		JPanel firstPanelMovieID = new JPanel(); 
		firstPanelMovieID.setLayout(new GridLayout(1,1)); 
		 
		firstPanelMovieID.add(movieIDLabel); 
		firstPanelMovieID.add(textfieldMovieID); 
		firstPanelMovieID.add(buttonMovieID);
		 
		  
		this.add(firstPanelMovieID);	
	}

	public void addDate() { 
		JLabel dateLabel = new JLabel(" Show Date (yyyy-MM-dd) : "); 
		textfieldDate = new JTextField(10); 
		 
		JPanel secondPanelDate = new JPanel(); 
		secondPanelDate.setLayout(new GridLayout(1,1)); 
		 
		secondPanelDate.add(dateLabel); 
		secondPanelDate.add(textfieldDate); 
		 
		this.add(secondPanelDate);
	}

	public void addAuditorium(){
		JLabel auditoriumLabel = new JLabel("AuditoriumID : "); 
		textfieldAuditorium = new JTextField(10); 
		textfieldAuditorium.setEditable(false); 
		JButton auditoriumButton = new JButton("Choose Auditorium ID");  
		auditoriumButton.addActionListener( new ActionListener() {							/** ActionListener AuditoriumButton **/
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		 
		JPanel thirdPanelAuditorium = new JPanel(); 
		thirdPanelAuditorium.setLayout(new GridLayout(1,1)); 
		  
		thirdPanelAuditorium.add(auditoriumLabel); 
		thirdPanelAuditorium.add(textfieldAuditorium); 
		thirdPanelAuditorium.add(auditoriumButton);  
		
		this.add(thirdPanelAuditorium); 
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

	public void addEmployeeID(){ 
		JLabel empolyeeLabel = new JLabel("EmployeeID : "); 
		textfieldEmployeeID = new JTextField(10); 
		textfieldEmployeeID.setEditable(false); 
		buttonEmploeeID = new JButton("Choose EmployeeID");  
		buttonEmploeeID.addActionListener( new ActionListener() {					/**ActionListener EmployeeIDButton **/
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		 
		JPanel fifthpanelEmployee = new JPanel();  
		fifthpanelEmployee.setLayout(new GridLayout(1,1)); 
		fifthpanelEmployee.add(empolyeeLabel); 
		fifthpanelEmployee.add(textfieldEmployeeID); 
		fifthpanelEmployee.add(buttonEmploeeID); 
		 
		this.add(fifthpanelEmployee);
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
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void doApplyAction() {
		showingController.createAndPersistShowing(this);
		
	}
	
	@Override
	public Showing doGetResultAction() {
		return showingController.createShow(this);
	}
	
	@Override
	public void handleRequestedResult(IEntity result) {
		// TODO Auto-generated method stub
		
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
		 textfieldMovieID.setText(""); 
		 textfieldDate.setText(""); 
		 textfieldAuditorium.setText(""); 
		
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
 }
