package explorationgame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 * Represents human or AI controlled objects in game world. Currently used
 * as player. Probably will be abstract in future versions.
 * 
 * @author artur
 *
 */

class Actor {
	String name;
	private int hunger;
	private int thirst;
	private int wounds;
	
	private int row;
	private int col;
	
	int moves;
	
	final int maxHunger = 50;
	final int maxThirst = 50;
	final int maxWounds = 15;
	
	ImageIcon icon;
	
    List<UpdateListener> listeners = new ArrayList<UpdateListener>();

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
	
	int getRow() {
		return row;
	}
	
	void setRow(int row) {
		this.row = row;
	}
	
	int getCol() {
		return col;
	}
	void setCol(int col) {
		this.col = col;
	}
	
	public Actor() {
		hunger = 0;
		thirst = 0;
		wounds = 0;
		
		moves = 0;
		icon = new ImageIcon("resources\\actor_icon.png");
		
		row = -1;
		col = -1;
	}
	
	public Actor(String name) {
		this();
		this.name = name;
	}
	
	public Actor(String name, ImageIcon icon) {
		this();
		this.name = name;
		this.icon = icon;
	}
	
	public Actor(String name, String iconLocation) {
		this();
		this.name = name;
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
     * Method to add custom listeners to actor objects. Used by StatusLabel to get actor status values.
     * 
     * @param l
     */
    public void addListener(UpdateListener l) {
        listeners.add(l);
    }
	
    
	/**
	 * Invoked in moveActor(). Makes changes to actor values according to Terrain of input Tile.
	 * 
	 * @param tile
	 */
	public void visitTile(Tile tile) {
		tile.visited += 1;
		tile.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0, 255), 2 * tile.visited));
		// tile.setBorder(BorderFactory.createEtchedBorder(new Color(150, 0, 0, 100), new Color(150, 0, 0, 255)));
		
		int thirst_increase = tile.terrain.thirst / tile.visited;
		int hunger_increase = tile.terrain.hunger / tile.visited;
		int wounds_increase = tile.terrain.wounds / tile.visited;
		
		System.out.println("H: " + hunger_increase + "; T: " + thirst_increase + "; W: " + wounds_increase);
		
		// Does not let the actor values go below 0.
		setThirst(Math.max(getThirst() + thirst_increase, 0));
		setHunger(Math.max(getHunger() + hunger_increase, 0));
		setWounds(Math.max(getWounds() + wounds_increase, 0));
		
		// Passes status change event to listeners.
		for (UpdateListener l : listeners) {
			l.statusUpdated(giveStatus());
		}
	}
	
	/**
	 * Shortcut to change actor position values. Invoked in moveActor.
	 * 
	 * @param row
	 * @param col
	 */
	public void setLoc(int row, int col) {
		moves += 1;
		setRow(row);
		setCol(col);
	}
	
	
	/**
	 * Returns current status of actor.
	 * 
	 * @return
	 */
	public int[] giveStatus() {
		int[] status = {hunger, thirst, wounds, moves};
		return status;
	}
}
