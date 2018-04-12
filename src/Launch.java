import presentation.GameUI;

public class Launch {
	/**
	 * Launches the Game
	 * @param args - Arguments of type String
	 */
	public static void main(String[] args) {
		System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
		GameUI game = new GameUI();
		// TODO Auto-generated method stub
		Thread ui= new Thread(game);
		ui.start();
	}

}
