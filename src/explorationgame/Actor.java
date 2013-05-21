package explorationgame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents human or AI controlled objects in game world.
 * 
 * @author artur
 *
 */

public abstract class Actor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String gameID;
	private int row;
	private int col;
	private int moves;
	private boolean playerControlled;
	private boolean active;
	private Game game;
	
	List<ActorStatusListener> statusListeners = new ArrayList<ActorStatusListener>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGameID() {
		return gameID;
	}
	
	public void setGameID(String gameID) {
		this.gameID = gameID;
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
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
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
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Tile getCurrentTile() {
		try {
			return game.gameWorld.getTile(row, col);
		} catch (TileOutOfGameWorldException e) {
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
    
	/**
	 * Checks if targeted tile is next to actor's current tile.
	 */
	public boolean checkLegalMove(Tile tile) {
		int r = getRow();
		int c = getCol();
		
		boolean nextToActorRow = tile.row == r || tile.row == r - 1 || tile.row == r + 1;
		boolean nextToActorCol = tile.col == c || tile.col == c - 1 || tile.col == c + 1;
		
		if (nextToActorRow && nextToActorCol) {
			return true;
		} else {
			System.out.println("Target tile is not next to current location. Move not possible.");
			return false;
		}
	}
	
	public boolean checkLegalMove(int row, int col) {
		try {
			Tile tile = game.gameWorld.getTile(row, col);
			return checkLegalMove(tile);
		} catch (TileOutOfGameWorldException e) {
    		System.out.println("Target tile is out of gameworld. Move not possible.");
    		return false;
		}
	}
 
	abstract void beginTurn();
	abstract void endTurn();
	
	abstract void move(int row, int col);
	abstract void move(Tile tile);
	
	/**
	 * Places actor on TileGrid. Now used for initializing new game 
	 * (using new Actor() or new Actor(name) constructor). 
	 * Future use for loading savegame and placing actor at saved
	 * position. 
	 * 
	 * @param act
	 */
	abstract void placeMe();

}
