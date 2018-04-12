package animals;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * Cat class allows for the creation of a cat onto the gameUI
 * This class extends the Predator class and 
 * implements Runnable and Animal interfaces
 * @author Vinesh Mistry Student Number: 1509170
 *
 */
public class Cat extends Predator implements Runnable{

	private Mouse mouse;
	/**
	 * Parameter constructor allows for intialising the Frog 
	 * @param name Sets the name of the Frog
	 */
	public Cat(String name){
		this.name = name;
		hunger = false;
		hasPrey = true;
		animal = new JLabel(new ImageIcon("img/cat-2.png"));
		Random random = new Random();
		int x = random.nextInt(100 + 1 +700) +700; 
		int y = random.nextInt(100 + 1 +300) +300;
		animal.setBounds(x, y, 50, 72);
	}
	
	/**
	 * @return the fish
	 */
	public Mouse getmouse() {
		return mouse;
	}
	
	/**
	 * @param mouse the mouse to set
	 */
	@Override
	public void setPartner(Object o) {
		mouse = (Mouse) o;
	}
	
	@Override
	public void setPanel(JPanel panel){
		this.panel = panel;
		panel.add(animal);
		mouse.setPanel(panel);
	}

	/**
	 * Moves animal in direction of the prey 
	 */
	public void eat(){
		if(animal.getX() < mouse.getXPos()){
			animal.setLocation(animal.getX()+20,animal.getY());
			
		}
		if(animal.getX() > mouse.getXPos()){
			animal.setLocation(animal.getX()-20, animal.getY());
			
		}
		if(animal.getY() > mouse.getYPos()){
			animal.setLocation(animal.getX(), animal.getY()-20);
			
		}
		if(animal.getY() < mouse.getYPos()){
			animal.setLocation(animal.getX(), animal.getY()+20);
		
		}
	}
	
	/**
	 * Draws the tongue and removes prey from the panel
	 */
	private void releaseTongue(){
		Graphics2D g2d = (Graphics2D) getTongue().create();
		g2d.setColor(Color.PINK);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine(animal.getX()+10, animal.getY()+20, mouse.getXPos()+16, mouse.getYPos()+16);
		getPanel().remove(mouse.getIcon());
	}
	
	/**
	 * sets booleans to respective values and outputs log message
	 */
	private void afterTongue(){
		setHunger(false);
		setHasPrey(false);
		mouse.setStunned(true);
		log.append("\n"+getName() +" is no longer hungry");
	}
	
	/**
	 * Clears the area
	 */
	public void clear(){
		getTongue().clearRect(0, 0, 800, 400);
		getTongue().setColor(Color.WHITE);
		getTongue().fillRect(0, 0, 800, 400);
	}
	
	/**
	 * Checks the distance between the prey and pet, releases the tongue when the
	 * conditions are met
	 */
	public void checkDistance(){
		int distance = (int) Math.sqrt(Math.pow((animal.getX()- mouse.getXPos()), 2) + Math.pow(animal.getY() - mouse.getYPos(),2));
		if(distance <=100){
			releaseTongue();
			afterTongue();
		}
		
		/*
		if(mouse.getYPos() - animal.getY() <= 15  && mouse.getXPos() - animal.getX() <= 15){
			releaseTongue();
			afterTongue();
		}
		
		else if(animal.getX() - mouse.getXPos() <= 15 && animal.getY() - mouse.getYPos() <= 15){
			releaseTongue();
			afterTongue();
		}*/
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n[Frog Pet: name=" + name + ", hunger=" + hunger + " ]";
	}
	
	/**
	 * Run method from the Runnable class
	 */
	public void run(){
		while(!hunger){
			movement();
			moveAway();
			try {
				Thread.sleep(400);    
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(hunger && !mouse.isStunned()){
			eat();
			moveAway();
			checkDistance();
			try {
				Thread.sleep(800);    
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!hunger && mouse.isStunned()){
			clear();
			run();
		}
	}

}
