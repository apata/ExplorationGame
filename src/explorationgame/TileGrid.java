package explorationgame;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
 

/**
 * Creates grid of tiles as an extension of JPanel. 
 * 
 * @author artur
 *
 */

class TileGrid extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public static final int default_rows = 20;
	public static final int default_cols = 20;
	public static final int default_cell_width = 20;
	
	private Tile[][] tiles;
	private SimpleDirectedWeightedGraph<Tile, DefaultWeightedEdge> tileGraph;
	
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
	
	public SimpleDirectedWeightedGraph<Tile, DefaultWeightedEdge> getTileGraph() {
		return tileGraph;
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
		tileGraph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Dimension tilePrefSize = new Dimension(cell_width, cell_width);
		setLayout(new GridLayout(rows, cols));
		
		for (int row = 0; row < tiles.length; row++) {
			for (int col = 0; col < tiles[row].length; col++) {
				Tile tile = null;
				
				if (col == 0 || col == tiles[row].length - 1) {
					tile = new Tile(new OceanTerrain());
				} else if (row == 0) {
					tile = new Tile(new ImpassableMountainsTerrain());
				} else if (row == tiles.length - 1) {
					tile = new Tile(new WallTerrain());
				} else {
					tile = new Tile(ts.next());
				}
				
				tile.row = row;
				tile.col = col;
				tile.visited = 0;
				
				tile.setToolTipText(tile.getToolTipText() + "<br><b>Coordinates: </b>" + row + ", " + col);
				tile.setPreferredSize(tilePrefSize);
				
				tiles[row][col] = tile;
				
				// Adds tile as node to tile graph.
				tileGraph.addVertex(tile);
			}
		}

		for (Tile[] row : tiles) {
			for (Tile sourceTile : row) {
				// Adds tile to JPanel.
				add(sourceTile);
				
				// Creates edges in tile graph.
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						try {
							Tile targetTile = getTile(sourceTile.row + i, sourceTile.col + j);
							if (sourceTile.terrain.getMoveCost() == -1 || targetTile.terrain.getMoveCost() == -1) {
								continue;
							}
							DefaultWeightedEdge e = tileGraph.addEdge(sourceTile, targetTile);
							tileGraph.setEdgeWeight(e, targetTile.terrain.getMoveCost());
						} catch (Exception e) {
							//e.printStackTrace();
							continue;
						}
					}
				}
			}
		}
	}
	
	public Tile getRandomTile() {
		int row = (int) (Math.random() * tiles.length);
		int col = (int) (Math.random() * tiles[row].length);
		return tiles[row][col];
	}
}