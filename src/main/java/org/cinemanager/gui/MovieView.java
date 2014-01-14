package org.cinemanager.gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class MovieView extends JPanel{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8603926006772846019L;
	private JPanel mainpanel,panel1,panel2,panel3,panel4,panel5,panel6;  
	private JTextField tf1,tf2,tf3,tf4; 
	private JComboBox<String> cbox; 
	public MovieView(JPanel mainpanel) { 
		this.mainpanel = mainpanel; 
		mainpanel.removeAll();
	} 
	public void add_movie() {    
		String [] category = new String [] { "Choose category","Comedy" , "Action movie" , "Animation" , "Crime " , "History" , " Thiller" , " Sci-Fi ", "Biography" } ;
		JLabel movie = new JLabel( " Add your new movie "); 
		JLabel title = new JLabel(" Tittle : "); 
		JLabel releaseDate = new JLabel( " Release Date (Format yyyy-MM-dd ):  "); 
		JLabel runtime = new JLabel( " Runtime (Format HH:mm) :  "); 
		JLabel age = new JLabel( " Minimal Age :  "); 
		JLabel category_label = new JLabel( " Category :  "); 
		  

		panel1 = new JPanel(); 
		panel1.setLayout(new GridLayout(1,1));  
		
		panel2 = new JPanel(); 
		panel2.setLayout(new GridLayout(1,1)); 
		 
		panel3 = new JPanel(); 
		panel3.setLayout(new GridLayout(1,1)); 
		 
		panel4 = new JPanel(); 
		panel4.setLayout(new GridLayout(1,1));
		
		panel5 = new JPanel(); 
		panel5.setLayout(new GridLayout(1,1)); 
		 
		panel6 = new JPanel(); 
		panel6.setLayout(new GridLayout(1,1)); 
		tf1 = new JTextField(10); 
		tf2 = new JTextField(10); 
		tf3 = new JTextField(10); 
		tf4 = new JTextField(10); 
		 
		ButtonGroup bg = new ButtonGroup();
		JRadioButton rb1,rb2,rb3; 
		rb1 = new JRadioButton("2D");  
		panel5.add(rb1); 
		bg.add(rb1); 
		 
		rb2 = new JRadioButton("3D");  
		panel5.add(rb2); 
		bg.add(rb2);  
		 
		rb3 = new JRadioButton("BOTH");  
		panel5.add(rb3); 
		bg.add(rb3);    
		 
		cbox = new JComboBox<String>(category); 
		cbox.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

					/** 
					 * Mo¿e siê przydaæ
					 */
				
			}
		}); 
		panel6.add(category_label); 
		panel6.add(cbox); 
		
		panel1.add(title); 
		panel1.add(tf1); 
		 
		panel2.add(releaseDate);  
		panel2.add(tf2); 
		 
		panel3.add(runtime); 
		panel3.add(tf3); 
		 
		panel4.add(age); 
		panel4.add(tf4);
		
		 
		mainpanel.add(movie); 
		mainpanel.add(panel1); 
		mainpanel.add(panel2); 
		mainpanel.add(panel3);
		mainpanel.add(panel4); 
		mainpanel.add(panel5); 
		mainpanel.add(panel6);
		
	}   
	public void delete_movie(){ 
		/** 
		 * Usuwanie bêdzie robione po wydrukowaniu wszystkich rekordów 
		 * Double click otworzy okienko z potwierdzeniem 
		 * Dodac scrollbar
		 */
	}
	public boolean check_data() { 
		boolean result = false; 
		 
		return result;
	}
}
