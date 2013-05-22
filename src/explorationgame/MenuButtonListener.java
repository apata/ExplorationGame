package explorationgame;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Window;
//import java.awt.GridBagLayout;
//import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
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
	JButton highScoreClearButton;

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
			System.out.println("New game.");
			
			String playerName = JOptionPane.showInputDialog("Enter player name: ");
			if (playerName != null) {
				JFrame newGameFrame = Game.createGame(playerName, 60, 40, 30);
				newGameFrame.addWindowListener(mainMenuFrame);
			} else {
				System.out.println("Name entry canceled.");
			}
						
		
		} else if (e.getSource().equals(mainMenuFrame.highScoreButton)) {
			int highScoreCount = 0;
			ArrayList<Highscore> highScores = new ArrayList<>(0);
			if ((new File("resources\\highscore.dat")).exists()) {
				highScores = Highscore.readAllHighscores();
				highScoreCount = highScores.size();
			}
			System.out.println("High scores.");
			JFrame highScoreFrame = new JFrame("High scores");
			
			
			GridLayout highScoreFrameLayout = new GridLayout(highScoreCount + 2, 1);
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
			
			class ClearButtonListener implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource().equals(highScoreClearButton)) {
						Highscore.deleteHighscores();
						((Window) ((JButton) e.getSource()).getTopLevelAncestor()).dispose();
					}
				}
			}
			highScoreClearButton = new JButton("Clear");
			highScoreClearButton.setPreferredSize(new Dimension(300, 40));
			highScoreClearButton.addActionListener(new ClearButtonListener());
			highScoreFrame.getContentPane().add(highScoreClearButton);
			
			
			highScoreFrame.setPreferredSize(new Dimension(325, 50 + highScoreCount * 50 + 50));
			highScoreFrame.pack();
			highScoreFrame.setVisible(true);
			
			
		} else if (e.getSource().equals(mainMenuFrame.creditsButton)) {
			System.out.println("Credits.");
			JOptionPane.showMessageDialog(null, "This program was coded by Artur Pata, Lauri Kongas and Markus Loide in April / May 2013.", "Credits", JOptionPane.PLAIN_MESSAGE);
			
		} else if (e.getSource().equals(mainMenuFrame.exitButton)) {
			System.out.println("Exit.");
			System.exit(0);
		}
		
	}

}
