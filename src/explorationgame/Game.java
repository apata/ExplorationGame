package explorationgame;

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

}
