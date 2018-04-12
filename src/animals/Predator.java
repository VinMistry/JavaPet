package animals;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import presentation.Animal;

/**
 * Predator abstract class allows predators that extend this class to gain access to the methods.
 * This reduces the amount of time spent creating new predator classes.
 * @author Vinesh Mistry Student Number: 1509170
 *
 */
public abstract class Predator implements Animal {
	protected JLabel animal;
	protected String name;
	protected boolean hunger;
	protected JPanel panel;
	protected JTextArea log;
	protected Graphics tongue;
	protected boolean hasPrey;
	
	 
	@Override
	public JLabel getIcon(){
		return animal;
	}
	
	@Override
	public void setIcon(JLabel im){
		animal = im;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the hunger
	 */
	public boolean isHunger() {
		return hunger;
	}


	/**
	 * @param hunger the hunger to set
	 */
	public void setHunger(boolean hunger) {
		this.hunger = hunger;
	}
	
	@Override
	public JPanel getPanel() {
		return panel;
	}


	/**
	 * @return the log
	 */
	public JTextArea getLog() {
		return log;
	}


	/**
	 * @param log the log to set
	 */
	public void setLog(JTextArea log) {
		this.log = log;
	}


	/**
	 * @return the tongue
	 */
	public Graphics getTongue() {
		return tongue;
	}


	/**
	 * @param tongue the tongue to set
	 */
	public void setTongue(Graphics tongue) {
		this.tongue = tongue;
	}


	/**
	 * @return the hasPrey
	 */
	public boolean hasPrey() {
		return hasPrey;
	}


	/**
	 * @param hasPrey the hasPrey to set
	 */
	public void setHasPrey(boolean hasPrey) {
		this.hasPrey = hasPrey;
	}
	
	@Override
	public void movement() {
		Random random = new Random();
		int xPos = random.nextInt(50 + 1 +50) - 50; 
		int yPos = random.nextInt(50 + 1 +50) - 50; 
		animal.setLocation(animal.getX()+xPos,animal.getY()+yPos);
		moveAway();
	}
	
	@Override
	public void moveAway() {
		if(animal.getX() > 700){
			animal.setLocation(animal.getX() - 600,animal.getY());
		}
		else if(animal.getX() < 50){
			animal.setLocation(animal.getX() + 600,animal.getY());
		}
		else if(animal.getY() > 300){
			animal.setLocation(animal.getX(),animal.getY() - 200);
		}
		else if(animal.getY() < 50){
			animal.setLocation(animal.getX(),animal.getY() + 200);
		}
	}

}
	

