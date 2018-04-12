package animals;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Mouse class allows for the creation of a mouse onto the gameUI
 * @author Vinesh Mistry Student Number: 1509170
 *
 */
public class Mouse extends Prey implements Runnable{
	
		/**
		 * Default constructor
		 */
		public Mouse(){
			stunned = false;
			prey = new JLabel(new ImageIcon("img/mouse-2.png"));
			Random random = new Random();
			int x = random.nextInt(100 + 1 +700) +700; 
			int y = random.nextInt(100 + 1 +300) +300;
			prey.setBounds(x, y, 48, 48);	
		}
		
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "\n[ Prey: Linked pet =" + getXPos() + getYPos() + getIcon()  + " ]";
		}
		
		/**
		 * Run method from the Runnable class
		 */
		public void run(){
			while(!stunned){
				movement();
				moveAway();
				try {
					Thread.sleep(200);    
				} 
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}

		
