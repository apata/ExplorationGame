package explorationgame;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

/**
 * Implements main menu with new game, credits and exit button as an extension of JPanel.
 * 
 * @author artur
 *
 */

public class MainMenuFrame extends JPanel implements WindowListener {
	private static final long serialVersionUID = 1L;
	
	public final Dimension menu_window_default_dimension = new Dimension(600, 200);
	public final String terrain_file_name = "resources\\terrains.xml";
	
	JButton newGameButton;
	JButton creditsButton;
	JButton exitButton;
	
	/** 
	 * Creates main menu panel, adds button objects. Ties MenuButtonListener to panel 
	 * and adds listener for each button.
	 * 
	 * @param terrainFileName
	 * @param dim
	 */
	public MainMenuFrame(Dimension dim) {
		setLayout(new GridLayout(3, 1));
		//
		
		MenuButtonListener menuButtonListener = new MenuButtonListener(this);
		
		newGameButton = new JButton("New game");
		newGameButton.setPreferredSize(dim);
		
		creditsButton = new JButton("Credits");
		creditsButton.setPreferredSize(dim);
		
		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(dim);
		
		newGameButton.addActionListener(menuButtonListener);
		creditsButton.addActionListener(menuButtonListener);
		exitButton.addActionListener(menuButtonListener);
		
		add(newGameButton);
		add(creditsButton);
		add(exitButton);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		this.getTopLevelAncestor().setVisible(true);
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
