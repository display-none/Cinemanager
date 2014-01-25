package org.cinemanager.gui;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.cinemanager.common.EmployeePosition;
import org.cinemanager.common.ShowingVersion;
import org.cinemanager.controller.ShowingController;
import org.cinemanager.entity.Auditorium;
import org.cinemanager.entity.Employee;
import org.cinemanager.entity.Marathon;
import org.cinemanager.entity.Movie;
import org.cinemanager.entity.Showing;


public class AddShowingView  extends View<Showing> {    
	private JTextField  textfieldMovieID,textfieldDate,textfieldAuditorium,textfieldEmployeeID,textfieldMarathonID; 
	private ButtonGroup buttonGroupVersion; 
	private ShowingController showingController;  
	private JButton buttonEmploeeID,buttonMarathonID;
	private static final String APPLY_BUTTON_LABEL = "Save"; 
	private static final String DATE_FORMAT = "yyyy-MM-dd";  
	private Movie movie;  
	private Marathon marathon; 
	private Auditorium auditorium; 
	private Employee employee;
	private final SimpleDateFormat dateParser = new SimpleDateFormat(DATE_FORMAT);
	public AddShowingView() { 
		this.setLayout(new GridLayout(7,1));  
		addDate(); 
		addTitle(); 
		addMovieID(); 
		addAuditorium();  
		addEmployeeID(); 
		addMarathonID();  
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
		JPanel fourthPanelVersion = new JPanel(); 
		fourthPanelVersion.setLayout(new GridLayout(1,1)); 
		 
		buttonGroupVersion= new ButtonGroup();
		for(ShowingVersion poition : ShowingVersion.values()) {
			JRadioButton radioButton = new JRadioButton(poition.toString());
			buttonGroupVersion.add(radioButton);
			fourthPanelVersion.add(radioButton);
		}   
		buttonGroupVersion.setSelected(buttonGroupVersion.getSelection(), true); 
		 
		this.add(fourthPanelVersion);
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
	public void addMarathonID(){ 
		JLabel marathonLabel = new JLabel("MarathonID : "); 
		textfieldMarathonID = new JTextField(10); 
		textfieldMarathonID.setEditable(false); 
		buttonMarathonID = new JButton("Choose EmployeeID"); 
		 
		JPanel sixthpanelEmployee = new JPanel();  
		sixthpanelEmployee.setLayout(new GridLayout(1,1)); 
		sixthpanelEmployee.add(marathonLabel); 
		sixthpanelEmployee.add(textfieldMarathonID); 
		sixthpanelEmployee.add(buttonMarathonID); 
		 
		this.add(sixthpanelEmployee);
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
	public Marathon getMarathon(){ 
		return marathon;
	} 
	public ShowingVersion getVersion(){ 
		return (ShowingVersion) buttonGroupVersion.getSelection().getSelectedObjects()[0]; 
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
	public String getApplyButtonLabel() {
		return APPLY_BUTTON_LABEL; 
	}
	@Override
	public void reset() {
		 textfieldMovieID.setText(""); 
		 textfieldDate.setText(""); 
		 textfieldAuditorium.setText(""); 
		
	} 
 }
