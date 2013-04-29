package explorationgame;

import java.awt.*;
import javax.swing.*;

/**
 * Implements main menu with new game, credits and exit button as an extension of JPanel.
 * 
 * @author artur
 *
 */
@SuppressWarnings("serial")
public class MainMenuFrame extends JPanel implements UpdateListener {
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
	
	/* Tries to implement the checkDeath function in a way that is accessible to the
	 * main menu frame. So far unsuccessful.
	 * @see explorationgame.UpdateListener#statusUpdated(int[])
	 */
	@Override
	public void statusUpdated(int[] status) {
		Actor dummyActor = new Actor();
		dummyActor.setHunger(status[0]);
		dummyActor.setThirst(status[1]);
		dummyActor.setWounds(status[2]);
		
		if (dummyActor.checkDeath()) {
			this.getTopLevelAncestor().setVisible(true);
		}
	}
}
