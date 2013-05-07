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
	 */
	public JFrame createGame(String actorName, String terrainFileName, int tg_w, int tg_h, int tg_cw) {
		Terrain[] terrain_array = ReadTerrainsXML.make_terrains(ReadTerrainsXML.parse_xml(terrainFileName));
		TerrainServer ts = new TerrainServer(terrain_array);
		
		TileGrid tg = new TileGrid(tg_w, tg_h, tg_cw, ts);
		Player player = new Player(tg, actorName);
		
		tg.placeActor(player);
		
	    StatusLabel statusLabel = new StatusLabel(tg.player);
	    tg.player.addStatusListener(statusLabel);  
	    
	    // --- Creates game window. ---
		JFrame gameFrame = new JFrame("Game");
	    
		// Sets layout.
		GridBagLayout gameFrameLayout = new GridBagLayout();
		gameFrame.setLayout(gameFrameLayout);
		
		// Sets X button function.
		gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Creates scroll bars for game field.
		JScrollPane gameScrollPane = new JScrollPane(tg);
		GridBagConstraints gameScrollPaneConstraints = new GridBagConstraints();
		gameScrollPaneConstraints.gridx = 0;
		gameScrollPaneConstraints.gridy = 0;
		gameScrollPaneConstraints.ipadx = 460;
		gameScrollPaneConstraints.ipady = 600;
		gameScrollPaneConstraints.weightx = 1;
		gameScrollPaneConstraints.weighty = 0.8;
		gameScrollPaneConstraints.fill = GridBagConstraints.BOTH;
		

	    gameScrollPane.setPreferredSize(game_scroll_field_default_dimension);
	    
	    // Remove scroll function from arrow keys. Redefined in TileGrid moveView().
	    InputMap inputMap = gameScrollPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	    inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "do-nothing");
	    inputMap.put(KeyStroke.getKeyStroke("LEFT"), "do-nothing");
	    inputMap.put(KeyStroke.getKeyStroke("UP"), "do-nothing");
	    inputMap.put(KeyStroke.getKeyStroke("DOWN"), "do-nothing");
	    
	    // Creates status text area.
	    StatusTextArea statusTextArea = new StatusTextArea();
	    tg.player.addStatusListener(statusTextArea);
	    statusTextArea.append("");
	    
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
	    
		GridBagConstraints statusLabelConstraints = new GridBagConstraints();
		statusLabelConstraints.gridx = 0;
		statusLabelConstraints.gridy = 2;
		statusLabelConstraints.ipadx = 460;
		statusLabelConstraints.ipady = 20;
		statusLabelConstraints.weightx = 1;
		statusLabelConstraints.weighty = 0.05;
		statusLabelConstraints.fill = GridBagConstraints.BOTH;
		
	    gameFrame.getContentPane().add(gameScrollPane, gameScrollPaneConstraints);
	    gameFrame.getContentPane().add(statusTextPane, statusTextPaneConstraints);
	    gameFrame.getContentPane().add(statusLabel, statusLabelConstraints);

	    gameFrame.setPreferredSize(game_window_default_dimension);
	    
	    gameFrame.pack();
	    
	    gameFrame.setLocationByPlatform(true);
	    gameFrame.setVisible(true);
	    
	    tg.moveView(tg.player.getCurrentTile());
		
	    return gameFrame;
	}
	
	
}
