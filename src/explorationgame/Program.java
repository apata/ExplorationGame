package explorationgame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Contains the main method, which creates a JFrame to contain MainMenuFrame. 
 * NB! Currently, the exit() which is called on death of actor does not work
 * properly - JVM may remain running. Terminate with task-manager or don't
 * die.
 * 
 * @author artur
 * 
 *
 */
public class Program {

	/**
	 * Creates main menu.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createMainMenu();
			}
		});
	}
	
	public static void createMainMenu() {
		JFrame program = new JFrame("Main menu");
		program.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		program.setLayout(new BorderLayout(5, 5));
		
		MainMenuPanel mainMenuFrame = new MainMenuPanel(new Dimension(300, 60));
		
		program.getContentPane().add(mainMenuFrame);
		
		program.pack();
		program.setLocationByPlatform(true);
		program.setVisible(true);
	}

}
