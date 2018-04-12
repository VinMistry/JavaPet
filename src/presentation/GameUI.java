package presentation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import animals.*;
/**
 * GameUI class allows for the creation of a UI for the game
 * @author Vinesh Mistry Student Number: 1509170
 *
 */

public class GameUI extends JFrame implements ActionListener, Runnable{
	private Animal frog , shark, cat;
	private static final long serialVersionUID = 1L;
	private JPanel main, petBox, controlBox, logBox, flyBox;
	private JButton make, hungry, reset;
	private JTextField name;
	private JTextArea log;
	private Fly fly;
	private Fish fish;
	private Mouse mouse;
	private JComboBox<String> combo;
	private String[] aniNames = new String[] {"Frog", "Shark", "Cat"};
	private ArrayList<Animal> petHome = new ArrayList<Animal>();
	private ArrayList<Fly> flyHome = new ArrayList<Fly>();
	private ArrayList<Fish> fishHome = new ArrayList<Fish>();
	private ArrayList<Mouse> mouseHome = new ArrayList<Mouse>();

	
	/**
	 * creates all aspects of the content pane
	 * @return the contentPane
	 */
	 public JPanel createContent() {
		 //Create the main panel
		 main = new JPanel();
		 
		 //Create petBox panel
		 petBox = new JPanel();
		 petBox.setLayout(new BorderLayout());
		 petBox.setPreferredSize(new Dimension(800, 400));
		 petBox.setBackground(Color.WHITE);
		 
		 flyBox =  new JPanel();
		 petBox.add(flyBox, BorderLayout.CENTER);
		 flyBox.setLayout(null);
		 flyBox.setSize(new Dimension(800, 400));
		 flyBox.setOpaque(false);
		 flyBox.setBackground(Color.white);
		 
		 
		
		 //Create the controlBox panel and JButtons
		 controlBox = new JPanel();
		 combo = new JComboBox<>(aniNames);
		 
		 name = new JTextField("Enter Name", 15);
		 make = new JButton("Make Pet");
		 hungry = new JButton("Hungry");
		 reset = new JButton("Reset");
		 
		 //Assign action listener to buttons
		 make.addActionListener(this);
		 hungry.addActionListener(this);
		 reset.addActionListener(this);
		 combo.addActionListener(this);
		 //Add Buttons to the controlBox panel
		 controlBox.add(name);
		 controlBox.add(make);
		 controlBox.add(hungry);
		 controlBox.add(reset);
		 controlBox.add(combo);
		 
		 //Create logBox panel and the JTextArea
		 logBox = new JPanel();
		 logBox.setLayout(new GridLayout());
		 logBox.setPreferredSize(new Dimension(800,400));
		 logBox.setBackground(Color.WHITE);
		 log = new JTextArea(100, 100);
		 log.setBackground(Color.WHITE);
		 log.setSize(new Dimension(800,400));
		 logBox.add(log);
		 
		 main.add(petBox, BorderLayout.NORTH);
		 main.add(controlBox);
		 main.add(logBox);
		 return main; 
	 }
	
 	/**
	 * Creates and shows the GUI
	 */
	 private static void createAndShowGUI() {
		 JFrame frame = new JFrame("Pet and Prey");
		 GameUI gui = new GameUI();
		 frame.setContentPane(gui.createContent());
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setSize(900, 1000);
		 frame.setResizable(false);
		 frame.setVisible(true);
    }
	 /**
	  * Method to define what happens when an action listeners is used
	  */
	 public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == make){
			 if (name.getText().equals("") || name.getText().equals("Enter Name") ){
				log.setText("Please Insert a Name");
				log.append("\nCurrent Pets: " + petHome.toString() );
				JOptionPane.showMessageDialog(petBox, "Please Enter Name","Empty Name Error", JOptionPane.OK_OPTION);
			 }
			 else{
				 if(combo.getSelectedItem() == "Frog"){
					 AnimalFactoryMaker factory = new AnimalFactoryMaker();
					 frog = factory.getPredator("FROG", name.getText());
					 fly = new Fly();
					 frog.setPartner(fly);
					 frog.setPanel(flyBox);
					 frog.setTongue(flyBox.getGraphics());
					 frog.setLog(log);
					 petHome.add(frog);
					 flyHome.add(fly);
					 log.setText("Thank you for playing");
					 log.append("\nAdded Pet Name: " + frog.getName());
					 log.append("\nCurrent Pets: " + petHome.toString() );
					 new Thread((Runnable) frog).start();
					 new Thread((Runnable)fly).start();
				 }
				 if(combo.getSelectedItem() == "Shark"){
					 AnimalFactoryMaker factory = new AnimalFactoryMaker();
					 shark = factory.getPredator("SHARK", name.getText());
					 fish = new Fish();
					 shark.setPartner(fish);
					 shark.setPanel(flyBox);
					 shark.setTongue(flyBox.getGraphics());
					 shark.setLog(log);
					 petHome.add(shark);
					 fishHome.add(fish);
					 log.setText("Thank you for playing");
					 log.append("\nAdded Pet Name: " + shark.getName());
					 log.append("\nCurrent Pets: " + petHome.toString() );
					 new Thread((Runnable) shark).start();
					 new Thread((Runnable)fish).start();
				 }
				 if(combo.getSelectedItem() == "Cat"){
					 AnimalFactoryMaker factory = new AnimalFactoryMaker();
					 cat = factory.getPredator("CAT", name.getText());
					 mouse = new Mouse();
					 cat.setPartner(mouse);
					 cat.setPanel(flyBox);
					 cat.setTongue(flyBox.getGraphics());
					 cat.setLog(log);
					 petHome.add(cat);
					 mouseHome.add(mouse);
					 log.setText("Thank you for playing");
					 log.append("\nAdded Pet Name: " + cat.getName());
					 log.append("\nCurrent Pets: " + petHome.toString() );
					 new Thread((Runnable)cat).start();
					 new Thread((Runnable)mouse).start();
				 }
			 }	
		 }
		 else if(e.getSource() == hungry){
			 for(Animal p: petHome){
				 if(p.hasPrey() == true){
					 p.setHunger(true);
				 }
				 log.append("\n"+ p.getName()+ " is now Hungry");
				 log.append("\nCurrent Pets: " + petHome.toString() );
			 }
		 }
		 else if(e.getSource() == reset){
			petBox.removeAll();
			flyBox.removeAll();
			petBox.validate();
			flyBox.validate();
			petBox.repaint();
			flyBox.repaint();
			petBox.setForeground(Color.WHITE);
			petBox.add(flyBox);
		    log.setText("");
		    petHome.clear();
		    System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
		 }
	}
	        
	 public void run() {
		 createAndShowGUI();
		 try{
			 Thread.sleep(600);
		 }
		 catch (Exception e) {
			// TODO: handle exception
		 }
	 }
	
}

