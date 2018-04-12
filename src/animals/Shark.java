package animals;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


/**
 * Shark class allows for the creation of a Shark onto the gameUI
 * This class extends the Predator class and 
 * implements Runnable and Animal interfaces
 * @author Vinesh Mistry Student Number: 1509170
 *
 */
public class Shark extends Predator implements Runnable{
	
	private Fish fish;
	
	/**
	 * Parameter constructor allows for intialising the Shark 
	 * @param name Sets the name of the Shark
	 */
	public Shark(String name){
		this.name = name;
		hunger = false;
		hasPrey = true;
		animal = new JLabel(new ImageIcon("img/shark-4.png"));
		fish = new Fish();
		Random random = new Random();
		int x = random.nextInt(100 + 1 +700) +700; 
		int y = random.nextInt(100 + 1 +300) +300;
		animal.setBounds(x, y, 89, 45);
	}
	
	/**
	 * @return the fish
	 */
	public Fish getFish() {
		return fish;
	}

	@Override
	public void setPartner(Object o) {
		fish = (Fish) o;
	}
	 
	@Override
	public void setPanel(JPanel panel){
		this.panel = panel;
		panel.add(animal);
		fish.setPanel(panel);
	}
	
	/**
	 * Moves animal in direction of the prey 
	 */
	public void eat(){
		if(animal.getX() < fish.getXPos()){
			animal.setLocation(animal.getX()+20,animal.getY());
		}
		if(animal.getX() > fish.getXPos()){
			animal.setLocation(animal.getX()-20, animal.getY());
			
		}
		if(animal.getY() > fish.getYPos()){
			animal.setLocation(animal.getX(), animal.getY()-20);
			
		}
		if(animal.getY() < fish.getYPos()){
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
		g2d.drawLine(animal.getX(), animal.getY()+20, fish.getXPos()+16, fish.getYPos()+16);
		getPanel().remove(fish.getIcon());
	}
	
	/**
	 * sets booleans to respective values and outputs log message
	 */
	private void afterTongue(){
		setHunger(false);
		setHasPrey(false);
		fish.setStunned(true);
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
		int distance = (int) Math.sqrt(Math.pow((animal.getX()- fish.getXPos()), 2) + Math.pow(animal.getY() - fish.getYPos(),2));
		if(distance <=100){
			releaseTongue();
			afterTongue();
		}
	
		/*
		if(fish.getYPos() - animal.getY() <= 15  && fish.getXPos() - animal.getX() <= 15){
			releaseTongue();
			afterTongue();
		}
		
		else if(animal.getX() - fish.getXPos() <= 15 && animal.getY() - fish.getYPos() <= 15){
			releaseTongue();
			afterTongue();
		}*/
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n[Shark Pet: name= " + name + ", hunger= " + hunger + ", has Prey= " + hasPrey + " ]";
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
		while(hunger && !fish.isStunned()){
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
		if(!hunger && fish.isStunned()){
			clear();
			run();
		}
	}
}

