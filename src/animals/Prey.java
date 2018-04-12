package animals;

import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Prey abstract class allows preys that extend this class to gain access to the methods.
 * This reduces the amount of time spent creating new prey classes.
 * @author Vinesh Mistry Student Number: 1509170
 *
 */
public abstract class Prey{
	protected JLabel prey;
	protected JPanel panel;
	protected boolean stunned;
	
	
	/**
	 * 
	 * @return the X position of the prey
	 */
	public int getXPos(){
		return prey.getX();
	}
	/**
	 * 
	 * @return the Y position of the prey
	 */
	public int getYPos(){
		return prey.getY();
	}
	
	/**
	 * 
	 * @return The JLabel that stores the image
	 */
	public JLabel getIcon(){
		return prey;
	}
	
	/**
	 * @return the stunned
	 */
	public boolean isStunned() {
		return stunned;
	}

	/**
	 * @param stunned the stunned to set
	 */
	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}
	
	/**
	 * 
	 * @return the panel the prey is on
	 */
	public JPanel getPanel() {
		return panel;
	}
	
	/**
	 * 
	 * @param j JPanel to add the prey to
	 */
	public void setPanel(JPanel j) {
		panel = j;
		j.add(prey);
	}
	
	/**
	 * Keeps the prey moving randomly
	 */
	public void movement(){
		Random random = new Random();
		int x = random.nextInt(20 + 1 +20) - 20; 
		int y = random.nextInt(20 + 1 +20) - 20; 
		prey.setLocation(prey.getX()+x,prey.getY()+y);
		moveAway();
		}
	
	/**
	 * Keeps the prey within the screen
	 */
	public void moveAway(){
		if(prey.getX() > 700){
			prey.setLocation(prey.getX() - 700,prey.getY());
		}
		else if(prey.getX() < 50){
			prey.setLocation(prey.getX() + 400,prey.getY());
		}
		else if(prey.getY() > 300){
			prey.setLocation(prey.getX(),prey.getY() - 300);
		}
		else if(prey.getY() < 50){
			prey.setLocation(prey.getX(),prey.getY() + 300);
		}
		else{
			return;
		}
	}
	
	
}
