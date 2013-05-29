package explorationgame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Implements main menu with new game, credits and exit button as an extension of JPanel.
 * 
 * @author artur
 *
 */

public class MainMenuPanel extends JPanel implements WindowListener, ActionListener {
	private static final long serialVersionUID = 1L;
	
	public final Dimension menu_window_default_dimension = new Dimension(600, 200);
	
	JButton newGameButton;
	JButton creditsButton;
	JButton exitButton;
	JButton highScoreButton;
	JButton highScoreClearButton;
	
	private JFrame gameFrame;
	/** 
	 * Creates main menu panel, adds button objects. Ties MenuButtonListener to panel 
	 * and adds listener for each button.
	 * 
	 * @param terrainFileName
	 * @param dim
	 */
	public MainMenuPanel(Dimension dim) {
		setLayout(new GridLayout(4, 1));
				
		newGameButton = new JButton("New game");
		newGameButton.setPreferredSize(dim);
		
		highScoreButton = new JButton("High scores");
		highScoreButton.setPreferredSize(dim);
		
		creditsButton = new JButton("Credits");
		creditsButton.setPreferredSize(dim);
		
		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(dim);
		
		newGameButton.addActionListener(this);
		highScoreButton.addActionListener(this);
		creditsButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		add(newGameButton);
		add(highScoreButton);
		add(creditsButton);
		add(exitButton);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(newGameButton)) {
			System.out.println("New game.");
			
			String playerName = JOptionPane.showInputDialog("Enter player name: ");
			if (playerName != null) {
				gameFrame = Game.createGame(playerName, 30, 40, 30);
				gameFrame.addWindowListener(this);
			} else {
				System.out.println("Name entry canceled.");
			}
						
		
		} else if (e.getSource().equals(highScoreButton)) {
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
			
			highScoreClearButton = new JButton("Clear");
			highScoreClearButton.setPreferredSize(new Dimension(300, 40));
			highScoreClearButton.addActionListener(this);
			highScoreFrame.getContentPane().add(highScoreClearButton);
			
			
			highScoreFrame.setPreferredSize(new Dimension(325, 50 + highScoreCount * 50 + 50));
			highScoreFrame.pack();
			highScoreFrame.setVisible(true);
			
		} else if (e.getSource().equals(highScoreClearButton)) {
			Highscore.deleteHighscores();
			((Window) ((JButton) e.getSource()).getTopLevelAncestor()).dispose();	
			
		} else if (e.getSource().equals(creditsButton)) {
			System.out.println("Credits.");
			JOptionPane.showMessageDialog(null, "This program was programmed by Artur Pata, Lauri Kongas and Markus Loide in April / May 2013.", "Credits", JOptionPane.PLAIN_MESSAGE);
			
		} else if (e.getSource().equals(exitButton)) {
			System.out.println("Exit.");
			System.exit(0);
			
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		this.getTopLevelAncestor().setVisible(true);
		gameFrame = null;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		this.getTopLevelAncestor().setVisible(false);
	}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	
}
