package explorationgame;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
//import java.awt.GridBagLayout;
//import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
			ArrayList<Highscore> highScores = Highscore.readAllHighscores();
			int highScoreCount = highScores.size();
			System.out.println("High scores.");
			JFrame highScoreFrame = new JFrame("High scores");
			
			GridLayout highScoreFrameLayout = new GridLayout(highScoreCount + 1, 1);
			highScoreFrameLayout.setVgap(10);
			highScoreFrame.setLayout(highScoreFrameLayout);
			
			highScoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			JLabel mainLabel = new JLabel("HIGH SCORES", JLabel.CENTER);
			mainLabel.setFont(new Font("Arial", Font.BOLD, 26));
			highScoreFrame.getContentPane().add(mainLabel); 
			for (Highscore s : highScores) {
				JLabel highScoreLabel = new JLabel(s.toString(), JLabel.CENTER);
				highScoreLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20));
				highScoreLabel.setPreferredSize(new Dimension(300, 40));
				highScoreFrame.getContentPane().add(highScoreLabel); 
			}
			
			highScoreFrame.setPreferredSize(new Dimension(325, 50 + highScoreCount * 50));
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
