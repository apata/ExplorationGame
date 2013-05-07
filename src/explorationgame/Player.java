package explorationgame;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 * Represents human or AI controlled objects in game world. Currently used
 * as player. Probably will be abstract in future versions.
 * 
 * @author artur
 *
 */

class Player extends Actor {
	private int hunger;
	private int thirst;
	private int wounds;
		
	final int maxHunger = 50;
	final int maxThirst = 50;
	final int maxWounds = 15;
	
	ImageIcon icon;
	
	int getHunger() {
		return hunger;
	}
	
	void setHunger(int hunger) {
		this.hunger = hunger;
	}
	
	int getThirst() {
		return thirst;
	}
	
	void setThirst(int thirst) {
		this.thirst = thirst;
	}
	
	int getWounds() {
		return wounds;
	}
	
	void setWounds(int wounds) {
		this.wounds = wounds;
	}
		
	public Player(TileGrid tileGrid) {
		hunger = 0;
		thirst = 0;
		wounds = 0;
		setTileGrid(tileGrid);
		
		setMoves(0);
		icon = new ImageIcon("resources\\actor_icon.png");
		
		setRow(-1);
		setCol(-1);
	}
	
	public Player(TileGrid tileGrid, String name) {
		this(tileGrid);
		setName(name);
	}
	
	public Player(TileGrid tileGrid, String name, ImageIcon icon) {
		this(tileGrid);
		setName(name);
		this.icon = icon;
	}
	
	public Player(TileGrid tileGrid, String name, String iconLocation) {
		this(tileGrid);
		setName(name);
		this.icon = new ImageIcon(iconLocation);
	}

	
	/**
	 * Checks if actor is dead.
	 * 
	 * @return
	 */
	public boolean checkDeath() {
		if (hunger >= maxHunger) {
			return true;
		} else if (thirst >= maxThirst) {
			return true;
		} else if (wounds >= maxWounds) {
			return true;
		} else {
			return false;
		}
	}
		
	/**
	 * Invoked in moveActor(). Makes changes to actor values according to Terrain of input Tile.
	 * 
	 * @param tile
	 */
	public void visitTile(Tile tile) {
		tile.visited += 1;
	}
	
	/**
	 * Returns current status of actor.
	 * 
	 * @return
	 */
	public int[] giveStatus() {
		int[] status = {hunger, thirst, wounds, getMoves()};
		return status;
	}

	@Override
	void doTurn() {
		Tile curTile = getCurrentTile();
		curTile.visited += 1;
		curTile.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0, 255), 2 * curTile.visited));

		int thirst_increase = curTile.terrain.thirst / curTile.visited;
		int hunger_increase = curTile.terrain.hunger / curTile.visited;
		int wounds_increase = curTile.terrain.wounds / curTile.visited;

		System.out.println("H: " + hunger_increase + "; T: " + thirst_increase + "; W: " + wounds_increase);

		setThirst(Math.max(getThirst() + thirst_increase, 0));
		setHunger(Math.max(getHunger() + hunger_increase, 0));
		setWounds(Math.max(getWounds() + wounds_increase, 0));

		// Passes status change event to listeners.
		for (ActorStatusListener l : statusListeners) {
			l.statusUpdated(giveStatus());
			l.actorAtTile(curTile);
		}
		
	}

	@Override
	void move(int row, int col) {
		getCurrentTile().setIcon(null);
		incrMoves();
		setLocation(row, col);
		getCurrentTile().setIcon(icon);
		doTurn();
	}
}
