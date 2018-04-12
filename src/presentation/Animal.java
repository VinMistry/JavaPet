package presentation;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * Interface allows for classes that implement it to initialise themselves.
 * @author Vinesh Mistry Student No: 1509170
 *
 */
public interface Animal {
	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * @param name the name to set
	 */
	public void setName(String name);
	/**
	 * @return the hasPrey
	 */
	public boolean hasPrey();

	/**
	 * @return the hunger
	 */
	public boolean isHunger();

	/**
	 * @param hunger the hunger to set
	 */
	public void setHunger(boolean hunger);
	/**
	 * @param hasPrey the hasPrey to set
	 */
	public void setHasPrey(boolean hasPrey);
	/**
	 * Get image icon in the form of a JLabel
	 */
	public JLabel getIcon();

	/**
	 * 
	 * @return JPanel the animal is added to
	 */
	public JPanel getPanel();
	/**
	 * Sets the JPanel the animal is to be added to 
	 */
	public void setPanel(JPanel j);
	/**
	 * Move the animal
	 */
	public void movement();
	
	/**
	 * Allows for the animal to move away from the edge of the area
	 */
	public void moveAway();
	/**
	 * @return the tongue
	 */
	public Graphics getTongue();
	/**
	 * @param tongue the tongue to set
	 */
	public void setTongue(Graphics tongue);
	
	/**
	 * Sets partner to the predator
	*/
	public void setPartner(Object o);
	
	/**
	 * 
	 * @param log to display output messages
	 */
	public void setLog(JTextArea log);
	/**
	 * Sets a new icon for the predator
	 */
	public void setIcon(JLabel im);
	
}