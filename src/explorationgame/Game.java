package explorationgame;

import java.awt.Rectangle;
import java.util.HashMap;

import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class Game extends JScrollPane {
	public TileGrid gameWorld;
	public HashMap<String, Actor> actors;
	
	public Game(TileGrid tileGrid) {
		this.gameWorld = tileGrid;
		this.actors = new HashMap<>();
		this.setViewportView(gameWorld);
		
		// Remove scroll function from arrow keys.
		InputMap inputMap = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	    inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "do-nothing");
	    inputMap.put(KeyStroke.getKeyStroke("LEFT"), "do-nothing");
	    inputMap.put(KeyStroke.getKeyStroke("UP"), "do-nothing");
	    inputMap.put(KeyStroke.getKeyStroke("DOWN"), "do-nothing");
	}
	
	public void addActor(String gameID, Actor actor) {
		actor.setTileGrid(gameWorld);
		actor.setGameID(gameID);
		actors.put(gameID, actor);
		actor.placeMe();
	}
	
	public void addPlayer(String gameID, Player player) {
		addActor(gameID, player);
		player.gridMouseListener = new GridMouseListener(player);
		player.gridDispatcher = new GridDispatcher(player);
		player.gridDispatcher.enable();
		for (Tile[] row : gameWorld.getTiles()) {
			for (Tile tile : row) {
				tile.addMouseListener(player.gridMouseListener);
			}
		}
	}
	
	/**
	 * Centers JScrollpane view on player, if possible.
	 * 
	 * @param tile
	 */	
	public void moveView(Tile tile) {
		Rectangle viewportBounds = getVisibleRect();
		Rectangle tileBounds = tile.getBounds();
		tileBounds.x -= viewportBounds.width / 2;
		tileBounds.y -= viewportBounds.height / 2;
		tileBounds.width = viewportBounds.width;
		tileBounds.height = viewportBounds.height;
		this.scrollRectToVisible(tileBounds);
	}

	
	

}
