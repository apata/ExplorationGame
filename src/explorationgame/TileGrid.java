package explorationgame;

import java.awt.Dimension;
import java.awt.GridLayout;
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
	
	public Tile[][] getTiles() {
		return tiles;
	}
	
	public Tile getTile(int row, int col) throws TileOutOfGameWorldException {
		try {
			return tiles[row][col];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new TileOutOfGameWorldException();
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
				//tile.addMouseListener(gridMouseListener);
				tile.setPreferredSize(tilePrefSize);
				add(tile);
				tiles[row][col] = tile;
			}
		}
	}
	
	
	/**
	 * Exits JVM. Called when actor.checkDeath() == true from moveActor(Tile t) function. 
	 * In future versions hopefully disables Game Window and enables Main Menu again.
	 * 
	 */
	public void exit() {
		System.out.println("Exiting. ");
//		((JFrame) this.getTopLevelAncestor()).dispose();
		System.exit(0);
		
//        WindowEvent wev = new WindowEvent((JFrame) this.getTopLevelAncestor(), WindowEvent.WINDOW_CLOSING);
//        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}