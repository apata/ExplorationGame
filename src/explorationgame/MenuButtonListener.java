package explorationgame;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
		
		} else if (e.getSource().equals(mainMenuFrame.highScoreButton)) {
			int highScoreCount = 0;
			System.out.println("High scores.");
			JFrame highScoreFrame = new JFrame("High scores");
			highScoreFrame.setPreferredSize(new Dimension(400, 300));
		    
			try {
				highScoreCount = Highscore.readAllHighscores().size();
			}
			
			catch (IOException g) {
				System.out.println("Exception: " + g.getMessage());
			}
			GridLayout highScoreFrameLayout = new GridLayout(highScoreCount + 1, 1);
			highScoreFrame.setLayout(highScoreFrameLayout);
			
			highScoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			try {
				JLabel mainLabel = new JLabel("Current high scores");
				mainLabel.setFont(new Font("Serif", Font.PLAIN, 26));
				highScoreFrame.getContentPane().add(mainLabel); 
				for (Highscore s : Highscore.readAllHighscores()) {
					JLabel highScoreLabel = new JLabel(s.toString());
					highScoreLabel.setPreferredSize(new Dimension(300, 100));
					highScoreFrame.getContentPane().add(highScoreLabel); 
				}
			}
			catch (IOException f) {
				System.out.println("Exception: " + f.getMessage());
			}
			
			highScoreFrame.pack();
			highScoreFrame.setVisible(true);
			
			
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
