package org.cinemanager.gui;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.cinemanager.common.MovieGenre;
import org.cinemanager.common.MovieVersion;
import org.cinemanager.controller.MovieController;
import org.cinemanager.entity.IEntity;
import org.cinemanager.entity.Movie;


public class AddMovieView extends View<Movie> { 

	private static final long serialVersionUID = 1L;
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final SimpleDateFormat dateParser = new SimpleDateFormat(DATE_FORMAT);
	private static final String APPLY_BUTTON_LABEL = "Save";
	private static final String CANCEL_BUTTON_LABEL = "Cancel";
	
	private JTextField titleTextField;
	private JTextField releaseDateTextField;
	private JTextField runtimeTextField;
	private JTextField minimalAgeTextField; 
	private JComboBox<MovieGenre> genreComboBox;
	private RadioGroup<MovieVersion> versionRadioGroup;
	
	private MovieController controller = MovieController.getInstance();
	
	private AddMovieView() {
		this.setLayout(new GridLayout(7, 2));
		
		addPanelTitle(); 
		addTitle(); 
		addReleaseDate(); 
		addRuntime(); 
		addMinimalAge();
		addVersion();    
		addGenre(); 
	}
	
	public String getTitle() {
		return titleTextField.getText();
	}
	
	public Date getReleaseDate() {
		String date = releaseDateTextField.getText();
		Date parsedDate = null;
		if(!(date.equals(""))) {
			try {
				parsedDate = dateParser.parse(date);
			} catch (ParseException e) {
				//TODO: throw or show sth
			}
		}
		return parsedDate;
	}
	
	public int getRuntime() {
		try {
			return Integer.parseInt(runtimeTextField.getText());
		} catch(NumberFormatException e) {
			//TODO: throw or show sth
			throw e;
		}
	}
	
	public int getMinimalAge() {
		try {
			return Integer.parseInt(minimalAgeTextField.getText());
		} catch(NumberFormatException e) {
			//TODO: throw or show sth
			throw e;
		}
	}
	
	public MovieGenre getGenre() {
		return (MovieGenre) genreComboBox.getSelectedItem();
	}
	
	public MovieVersion getVersion() {
		return versionRadioGroup.getSelected();
	}
	
	private void addPanelTitle() {
		JLabel panelTitle = new JLabel( " Add your new movie ");
		panelTitle.setFont(new Font(null, Font.BOLD, 15));
		this.add(panelTitle);
	}
	
	private void addTitle() {
		JLabel title = new JLabel(" Title : "); 
		titleTextField = new JTextField(10); 
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,2));  
		panel.add(title); 
		panel.add(titleTextField); 
		this.add(panel);
	}
	
	private void addReleaseDate() {
		JLabel releaseDate = new JLabel( " Release Date (Format yyyy-MM-dd ):  "); 
		releaseDateTextField = new JTextField(10);
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,2)); 
		panel.add(releaseDate);  
		panel.add(releaseDateTextField); 
		this.add(panel);
	}
	
	private void addRuntime() {
		JLabel runtime = new JLabel( " Runtime (minutes) :  "); 
		runtimeTextField = new JTextField(10); 
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,2)); 
		panel.add(runtime); 
		panel.add(runtimeTextField);
		this.add(panel);
	}
	
	private void addMinimalAge() {
		JLabel age = new JLabel( " Minimal Age :  "); 
		minimalAgeTextField = new JTextField(10); 
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,2));
		panel.add(age); 
		panel.add(minimalAgeTextField);
		this.add(panel);
	}
	
	private void addVersion() {
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1));
		
		JLabel version = new JLabel( " Version :  "); 
		panel.add(version);
		
		versionRadioGroup = new RadioGroup<>(MovieVersion.values());
		panel.add(versionRadioGroup);
		this.add(panel);
	}
	
	private void addGenre() {
		JLabel genreLabel = new JLabel( " Genre :  "); 
		genreComboBox = new JComboBox<MovieGenre>(MovieGenre.values()); 
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(1,1)); 
		panel.add(genreLabel); 
		panel.add(genreComboBox);
		this.add(panel);
	}
	
	@Override
	public void reset() {
		titleTextField.setText("");
		releaseDateTextField.setText("");
		runtimeTextField.setText("");
		minimalAgeTextField.setText("");
		genreComboBox.setSelectedIndex(0);
		versionRadioGroup.setFirstSelected();
	}
	
	@Override
	public boolean hasAnyChanges() {
		return 	!titleTextField.getText().isEmpty() ||
				!releaseDateTextField.getText().isEmpty() ||
				!runtimeTextField.getText().isEmpty() ||
				!minimalAgeTextField.getText().isEmpty() ||
				genreComboBox.getSelectedIndex() != 0 ||
				!versionRadioGroup.isFirstSelected();
	}

	@Override
	public void doApplyAction() {
		controller.createAndPersistMovie(this);
	}

	@Override
	public Movie doGetResultAction() {
		return controller.createMovie(this);
	}
	
	@Override
	public void handleRequestedResult(IEntity result) {
		//we do nothing cause this view does not request anything
	}

	@Override
	public String getApplyButtonLabel() {
		return APPLY_BUTTON_LABEL;
	}
	
	@Override
	public String getCancelButtonLabel() {
		return CANCEL_BUTTON_LABEL;
	}
	

	public static ViewCreator<AddMovieView> getCreator() {
		return new AddMovieViewCreator();
	}
	
	private static class AddMovieViewCreator implements ViewCreator<AddMovieView> {

		@Override
		public AddMovieView createView(ViewManager viewManager) {
			return new AddMovieView();
		}
		
	}
}
