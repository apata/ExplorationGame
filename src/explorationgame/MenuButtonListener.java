package explorationgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Implements ActionListener to check for main menu button presses.
 * 
 * @author artur
 *
 */
public class MenuButtonListener implements ActionListener {
	private MainMenuFrame mainMenuFrame;

	public MenuButtonListener(MainMenuFrame mainMenuFrame) {
		super();
		this.mainMenuFrame = mainMenuFrame;
	}
	
	
	/* (non-Javadoc)
	 * Defines actions according to buttonPress event source.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(mainMenuFrame.newGameButton)) {
			/* Creates new game, tries to disable main menu for the while and when the game closes, re-enable main menu.
			 * So far unsuccessful.
			 */

			System.out.println("New game.");
			
			String playerName = JOptionPane.showInputDialog("Enter player name: ");
			
			GameFactory newGameFactory = new GameFactory();
			
			JFrame newGameFrame = newGameFactory.createGame(playerName, mainMenuFrame.terrain_file_name, 60, 40, 30);
			newGameFrame.addWindowListener(mainMenuFrame);
			
		} else if (e.getSource().equals(mainMenuFrame.creditsButton)) {
			System.out.println("Credits.");
			JOptionPane.showMessageDialog(null, "This program was coded by Artur Pata, Lauri Kongas and Markus Loide in April / May 2013.","Credits",JOptionPane.PLAIN_MESSAGE);
			//
		} else if (e.getSource().equals(mainMenuFrame.exitButton)) {
			System.out.println("Exit.");
			System.exit(0);
		}
		
	}

}
