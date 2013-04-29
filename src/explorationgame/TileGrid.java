package explorationgame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * Creates grid of tiles as an extension of JPanel. 
 * 
 * @author artur
 *
 */

@SuppressWarnings("serial")
class TileGrid extends JPanel {
	public static final int default_rows = 20;
	public static final int default_cols = 20;
	public static final int default_cell_width = 20;
	
	private Tile[][] tiles;
	private KeyEventDispatcher keyEventDispatcher;
	
	/**
	 * Currently, only one Actor can be attached to the TileGrid - the player.
	 */
	Actor actor;
	
	/**
	 * Defines custom KeyEventDispatcher and relevant KeyEvents and their actions.
	 * 
	 * @author artur
	 *
	 */
	private class GridDispatcher implements KeyEventDispatcher {
		
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			int row = actor.getRow();
			int col = actor.getCol();
			
			if (e.getID() == KeyEvent.KEY_RELEASED) {
                System.out.print("Released: ");
                System.out.println(e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD8 || e.getKeyCode() == KeyEvent.VK_UP) {
                	moveActor(row - 1, col);
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
                	moveActor(row - 1, col + 1);
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6 || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                	moveActor(row, col + 1);
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                	moveActor(row + 1, col + 1);
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2 || e.getKeyCode() == KeyEvent.VK_DOWN) {
                	moveActor(row + 1, col);
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                	moveActor(row + 1, col - 1);
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4 || e.getKeyCode() == KeyEvent.VK_LEFT) {
                	moveActor(row, col - 1);
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
                	moveActor(row - 1, col - 1);
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                	moveActor(row, col);
                }
			}
			return false;
		}
    }
	
	public TileGrid(TerrainServer ts) {
		this(default_rows, default_cols, default_cell_width, ts);
	}
	
	/**
	 * Main constructor of the game - creates the JPanel representation of game world, 
	 * invokes Tile constructor using input from TerrainServer.
	 * 
	 * @param rows
	 * @param cols
	 * @param cell_width
	 * @param ts
	 */
	
	public TileGrid(int rows, int cols, int cell_width, TerrainServer ts) {		
		tiles = new Tile[rows][cols];
		GridMouseListener gridMouseListener = new GridMouseListener(this);
		Dimension tilePrefSize = new Dimension(cell_width, cell_width);
		setLayout(new GridLayout(rows, cols));
		
		for (int row = 0; row < tiles.length; row++) {
			for (int col = 0; col < tiles[row].length; col++) {
				Tile tile = new Tile(ts.next());
				tile.row = row;
				tile.col = col;
				tile.visited = 0;
				String text = tile.getToolTipText();
				tile.setToolTipText(text + "<br><b>Coordinates: </b>" + row + ", " + col);
				tile.addMouseListener(gridMouseListener);
				tile.setPreferredSize(tilePrefSize);
				add(tile);
				tiles[row][col] = tile;
			}
		}
		
		keyEventDispatcher = new GridDispatcher();
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(keyEventDispatcher);

	}
	
	public Tile[][] getTiles() {
		return tiles;
	}
	
	/**
	 * Gets current Actor's tile reference from actor position.
	 * 
	 * @return
	 */
	public Tile getActorTile() {
		return tiles[actor.getRow()][actor.getCol()];
	}
	
	
	/**
	 * Places actor on TileGrid. Now used for initializing new game 
	 * (using new Actor() or new Actor(name) constructor). 
	 * Future use for loading savegame and placing actor at saved
	 * position. 
	 * 
	 * @param act
	 */
	public void placeActor(Actor act) {
		this.actor = act;
		if (actor.getRow() == -1 && actor.getCol() == -1) {
			int startRow = (int) (Math.random() * tiles.length);
			int startCol = (int) (Math.random() * tiles[startRow].length);
			
			actor.setLoc(startRow, startCol);
			actor.visitTile(tiles[startRow][startCol]);
		} else {
			actor.setLoc(actor.getRow(), actor.getCol());
			actor.visitTile(getActorTile());
		}
		getActorTile().setIcon(actor.icon);
	}
	
	/**
	 * Moves character that is passed to KeyEvent handler, 
	 * which checks if the tile exists in TileGrid.tiles[][] matrix, 
	 * catching ArrayIndexOutOfBoundsError if thrown.
	 * 
	 * @param row
	 * @param col
	 */
	public void moveActor(int row, int col) {
		try {
			moveActor(tiles[row][col]);
    	} catch (ArrayIndexOutOfBoundsException error) {
    		System.out.println("Target tile is out of gameworld. Move not possible.");
		}
	}
	
	/**
	 * Moves character to argument Tile object, which is known to exist
	 * in TileGrid.tiles[][] matrix. Known because the two functions that call it 
	 * perform the checks.
	 * 
	 * @param tile
	 */
	public void moveActor(Tile tile) {
		getActorTile().setIcon(null);
		actor.setLoc(tile.row, tile.col);
		actor.visitTile(tile);
		getActorTile().setIcon(actor.icon);
		moveView(tile);
		
		if (actor.checkDeath()) {
			KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			manager.removeKeyEventDispatcher(keyEventDispatcher);
			
			JOptionPane.showMessageDialog(null, "You have died.", "INFO", JOptionPane.PLAIN_MESSAGE);
			
			exit();
			//System.out.print("Actor is dead. ");
		}
	}
	
	/**
	 * Centers JScrollpane view on player, if possible.
	 * 
	 * @param tile
	 */	
	public void moveView(Tile tile) {
		Rectangle viewportBounds = ((JViewport) this.getParent()).getViewRect();
		Rectangle tileBounds = tile.getBounds();
		tileBounds.x -= viewportBounds.width / 2;
		tileBounds.y -= viewportBounds.height / 2;
		tileBounds.width = viewportBounds.width;
		tileBounds.height = viewportBounds.height;
		this.scrollRectToVisible(tileBounds);
	}

	/**
	 * Invoked from GridMouseListener mousePressed function. Checks if targeted tile is next to
	 * actor's current tile.
	 */
	public void tilePressed(Tile tile) {
		int ar = actor.getRow();
		int ac = actor.getCol();
		
		boolean nextToActorRow = tile.row == ar || tile.row == ar - 1 || tile.row == ar + 1;
		boolean nextToActorCol = tile.col == ac || tile.col == ac - 1 || tile.col == ac + 1;
		
		if (nextToActorRow && nextToActorCol) {
			moveActor(tile);
		} else {
			System.out.println("Target tile is not next to current location. Move not possible.");
		}
	}
	
	
	/**
	 * Exits JVM. Called when actor.checkDeath() == true from moveActor(Tile t) function. 
	 * In future versions hopefully disables Game Window and enables Main Menu again.
	 * 
	 */
	public void exit() {
		System.out.println("Exiting. ");
		// ((JFrame) this.getTopLevelAncestor()).dispose();
		System.exit(0);
		
//        WindowEvent wev = new WindowEvent((JFrame) this.getTopLevelAncestor(), WindowEvent.WINDOW_CLOSING);
//        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}