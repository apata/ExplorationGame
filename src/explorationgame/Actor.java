package explorationgame;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor {
	private String name;
	private int row;
	private int col;
	private int moves;
	private boolean playerControlled;
	private TileGrid tileGrid;
	
	List<ActorStatusListener> statusListeners = new ArrayList<ActorStatusListener>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public TileGrid getTileGrid() {
		return tileGrid;
	}

	public void setTileGrid(TileGrid tileGrid) {
		this.tileGrid = tileGrid;
	}

	public void setLocation(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public void setMoves(int moves) {
		this.moves = moves;
	}
	
	public void incrMoves() {
		this.moves += 1;
	}
	
	public boolean isPlayerControlled() {
		return playerControlled;
	}
	
	public void setPlayerControlled(boolean playerControlled) {
		this.playerControlled = playerControlled;
	}
	
	public Tile getCurrentTile() {
		try {
			return tileGrid.getTiles()[row][col];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
    /**
     * Method to add custom listeners to actor objects. Used by StatusLabel to get actor status values.
     * 
     * @param l
     */
    public void addStatusListener(ActorStatusListener l) {
        statusListeners.add(l);
    }
    
	abstract void doTurn();
	abstract void move(int row, int col);

}
