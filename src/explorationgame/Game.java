package explorationgame;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.util.LinkedHashMap;

import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;


public class Game extends JScrollPane {
	private static final long serialVersionUID = 1L;
	
	public TileGrid gameWorld;
	public TurnManager turnManager;
	public LinkedHashMap<String, Actor> actors;
	
	public GridDispatcher gridDispatcher;
	public GridMouseListener gridMouseListener;
	
	public Game(TileGrid tileGrid) {
		this.gameWorld = tileGrid;
		this.setViewportView(gameWorld);
		
		this.actors = new LinkedHashMap<>();
		this.turnManager = new TurnManager(this);
		
		// Create listeners.
		this.gridMouseListener = new GridMouseListener();
		this.gridDispatcher = new GridDispatcher();
		this.gridDispatcher.enable();

		// Add mouse listener to each tile of game world.
		for (Tile[] row : gameWorld.getTiles()) {
			for (Tile tile : row) {
				tile.addMouseListener(gridMouseListener);
			}
		}

		// Remove scroll function from arrow keys.
		InputMap inputMap = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	    inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "do-nothing");
	    inputMap.put(KeyStroke.getKeyStroke("LEFT"), "do-nothing");
	    inputMap.put(KeyStroke.getKeyStroke("UP"), "do-nothing");
	    inputMap.put(KeyStroke.getKeyStroke("DOWN"), "do-nothing");
	}
	
	public void addActor(String gameID, Actor actor) {
		actor.setGame(this);
		actor.setGameID(gameID);
		actors.put(gameID, actor);
		actor.placeMe();
	}
		
	/**
	 * Centers JScrollpane view on player, if possible.
	 * 
	 * @param tile
	 */	
	public void moveView(Tile tile) {
		Rectangle viewportBounds = getViewport().getVisibleRect();
		Rectangle tileBounds = tile.getBounds();
		
		tileBounds.x -= viewportBounds.width / 2;
		tileBounds.x += tileBounds.width / 2;
		
		tileBounds.y -= viewportBounds.height / 2;
		tileBounds.y += tileBounds.height / 2;
		
		tileBounds.width = viewportBounds.width;
		tileBounds.height = viewportBounds.height;
		
		((JComponent) gameWorld).scrollRectToVisible(tileBounds);
	}

	public void close() {
		((JFrame) this.getTopLevelAncestor()).dispose();

	}
	
	public static JFrame createGame(String actorName, int tg_w, int tg_h, int tg_cw) {
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
		gamePaneConstraints.ipadx = 600;
		gamePaneConstraints.ipady = 600;
		gamePaneConstraints.weightx = 1;
		gamePaneConstraints.weighty = 0.8;
		gamePaneConstraints.fill = GridBagConstraints.BOTH;
			    	    
	    // Attaches player to game.
		Player player = new Player(actorName);
		game.addActor("PLAYER0", player);
	    		
	    // Creates status text area.
	    StatusTextArea statusTextArea = new StatusTextArea();
	    player.addStatusListener(statusTextArea);
	    
	    // Adds White Walkers to game.
	    for (int i = 0; i < 4; i++) {
	    	Whitewalker ww = new Whitewalker("WW", player);
			game.addActor("WhiteWalker" + i, ww);
			ww.addStatusListener(statusTextArea);
		}

	    game.turnManager.addUpdateListener(statusTextArea);
	    
	    JScrollPane statusTextPane = new JScrollPane(statusTextArea);
		GridBagConstraints statusTextPaneConstraints = new GridBagConstraints();
		statusTextPaneConstraints.gridx = 0;
		statusTextPaneConstraints.gridy = 1;
		statusTextPaneConstraints.ipadx = 600;
		statusTextPaneConstraints.ipady = 200;
		statusTextPaneConstraints.weightx = 1;
		statusTextPaneConstraints.weighty = 0.15;
		statusTextPaneConstraints.fill = GridBagConstraints.BOTH;

	    statusTextPane.setPreferredSize(new Dimension(600, 200));

	    // Creates status label.
	    StatusLabel statusLabel = new StatusLabel(player);
	    player.addStatusListener(statusLabel);  
	    
		GridBagConstraints statusLabelConstraints = new GridBagConstraints();
		statusLabelConstraints.gridx = 0;
		statusLabelConstraints.gridy = 2;
		statusLabelConstraints.ipadx = 600;
		statusLabelConstraints.ipady = 20;
		statusLabelConstraints.weightx = 1;
		statusLabelConstraints.weighty = 0.05;
		statusLabelConstraints.fill = GridBagConstraints.BOTH;
		
	    gameFrame.getContentPane().add(game, gamePaneConstraints);
	    gameFrame.getContentPane().add(statusTextPane, statusTextPaneConstraints);
	    gameFrame.getContentPane().add(statusLabel, statusLabelConstraints);

	    gameFrame.setPreferredSize(new Dimension(600, 840));
	    
	    gameFrame.pack();
	    
	    gameFrame.setLocationByPlatform(true);
	    gameFrame.setVisible(true);
	    
	    game.moveView(player.getCurrentTile());
	    game.turnManager.manageTurn();
	    
	    return gameFrame;
	}


}
