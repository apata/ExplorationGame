package explorationgame;

import java.awt.*;

import javax.swing.*;

/**
 * Creates Game Window.
 * 
 * @author artur
 *
 */
public class GameFactory {
	public static final Dimension game_scroll_field_default_dimension = new Dimension(600, 460);
	public static final Dimension text_scroll_field_default_dimension = new Dimension(200, 460);
	public static final Dimension game_window_default_dimension = new Dimension(640, 480);
		
	/**
	 * Creates the game window by combining a JScrollPane of TileGrid in a JFrame. Draws statuslabel under it.
	 * Will draw other components as necessary in future versions.
	 * 
	 * @param actorName
	 * @param terrainFileName
	 * @param tg_w
	 * @param tg_h
	 * @param tg_cw
	 * @return 
	 * @return
	 */
	public JFrame createGame(String actorName, String terrainFileName, int tg_w, int tg_h, int tg_cw) {
		//Terrain[] terrain_array = ReadTerrainsXML.make_terrains(ReadTerrainsXML.parse_xml(terrainFileName));
		Terrain[] terrain_array = ReadTerrains.make_terrains();
		TerrainServer ts = new TerrainServer(terrain_array);
		
		TileGrid gameWorld = new TileGrid(tg_w, tg_h, tg_cw, ts);
		
	    
	    // --- Creates game window. ---
		JFrame gameFrame = new JFrame("Game");
	    
		// Sets layout.
		GridBagLayout gameFrameLayout = new GridBagLayout();
		gameFrame.setLayout(gameFrameLayout);
		
		// Sets X button function.
		gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Creates game field.
		Game game = new Game(gameWorld);
		GridBagConstraints gamePaneConstraints = new GridBagConstraints();
		gamePaneConstraints.gridx = 0;
		gamePaneConstraints.gridy = 0;
		gamePaneConstraints.ipadx = 460;
		gamePaneConstraints.ipady = 600;
		gamePaneConstraints.weightx = 1;
		gamePaneConstraints.weighty = 0.8;
		gamePaneConstraints.fill = GridBagConstraints.BOTH;
			    	    
	    // Attaches player to game.
		Player player = new Player(actorName);
		game.addActor("PLAYER0", player);
	    
		
		// Attaches dummy actors to game.
		
	    // Creates status text area.
	    StatusTextArea statusTextArea = new StatusTextArea();
	    player.addStatusListener(statusTextArea);
	    
	    for (int i = 0; i < 4; i++) {
	    	DummyActor d = new DummyActor("DA");
			game.addActor("DUMMYACTOR" + i, d);
			d.addStatusListener(statusTextArea);
		}

	    game.turnManager.addUpdateListener(statusTextArea);
	    
	    JScrollPane statusTextPane = new JScrollPane(statusTextArea);
		GridBagConstraints statusTextPaneConstraints = new GridBagConstraints();
		statusTextPaneConstraints.gridx = 0;
		statusTextPaneConstraints.gridy = 1;
		statusTextPaneConstraints.ipadx = 460;
		statusTextPaneConstraints.ipady = 100;
		statusTextPaneConstraints.weightx = 1;
		statusTextPaneConstraints.weighty = 0.15;
		statusTextPaneConstraints.fill = GridBagConstraints.BOTH;

	    statusTextPane.setPreferredSize(text_scroll_field_default_dimension);

	    // Creates status label.
	    StatusLabel statusLabel = new StatusLabel(player);
	    player.addStatusListener(statusLabel);  
	    
		GridBagConstraints statusLabelConstraints = new GridBagConstraints();
		statusLabelConstraints.gridx = 0;
		statusLabelConstraints.gridy = 2;
		statusLabelConstraints.ipadx = 460;
		statusLabelConstraints.ipady = 20;
		statusLabelConstraints.weightx = 1;
		statusLabelConstraints.weighty = 0.05;
		statusLabelConstraints.fill = GridBagConstraints.BOTH;
		
	    gameFrame.getContentPane().add(game, gamePaneConstraints);
	    gameFrame.getContentPane().add(statusTextPane, statusTextPaneConstraints);
	    gameFrame.getContentPane().add(statusLabel, statusLabelConstraints);

	    gameFrame.setPreferredSize(game_window_default_dimension);
	    
	    gameFrame.pack();
	    
	    gameFrame.setLocationByPlatform(true);
	    gameFrame.setVisible(true);
	    
	    game.moveView(player.getCurrentTile());
	    game.turnManager.manageTurn();
	    
	    return gameFrame;
	}
	
	
}
