package explorationgame;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Represents player of game.
 * 
 * @author Artur
 *
 */

class Player extends Actor {
	private static final long serialVersionUID = 1L;
	
	private int hunger;
	private int thirst;
	private int wounds;
	
	
			
	final int maxHunger = 50;
	final int maxThirst = 50;
	final int maxWounds = 15;
	final int maxTurnMoves = 10;
	
//	final Terrain[] impassableTerrain = {
//			new ImpassableMountainsTerrain(),
//			new OceanTerrain()
//			};
		
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
		
	public Player() {
		impassableTerrain = new ArrayList<>(0);
		impassableTerrain.add(new ImpassableMountainsTerrain());
		impassableTerrain.add(new OceanTerrain());
		hunger = 0;
		thirst = 0;
		wounds = 0;
		setPlayerControlled(true);
		
		setTurns(0);
		icon = new ImageIcon("resources\\player.png");
		
		setRow(-1);
		setCol(-1);
	}
	
	public Player(String name) {
		this();
		setName(name);
	}
	
	public Player(String name, ImageIcon icon) {
		this();
		setName(name);
		this.icon = icon;
	}
	
	public Player(String name, String iconLocation) {
		this();
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
	
	public boolean checkTurnEnd() {
		if (getTurnMoves() <= 0) {
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
		int[] status = {hunger, thirst, wounds, getTurnMoves(), getTurns()};
		return status;
	}

	@Override
	void beginTurn() {
		incrementTurns();
		setTurnMoves(getTurnMoves() + maxTurnMoves);
		
		// Passes status to listeners.
		for (ActorStatusListener l : statusListeners) {
			l.statusUpdated(giveStatus());
			l.actorAtTile(getCurrentTile());
		}

		
		// Enables listeners for player input.
		getGame().gridDispatcher.setActor(this);
		getGame().gridMouseListener.setActor(this);
		getGame().gridDispatcher.setActive(true);
		getGame().gridMouseListener.setActive(true);
	}
			
	void endTurn() {
		// Disables player input.
		setActive(false);
		getGame().gridDispatcher.setActive(false);
		getGame().gridMouseListener.setActive(false);
		
		// Activates next actor turn.
		getGame().turnManager.nextActorTurn();
	}

	@Override
	public boolean checkLegalMove(Tile tile) {
		for (Terrain t : impassableTerrain) {
			if (t.getClass().equals(tile.terrain.getClass())) {
				System.out.println("Impassable terrain!\n");
				return false;
			}
		}
		if (tile.terrain.getMoveCost() > getTurnMoves()) {
			for (ActorStatusListener l : statusListeners) {
				l.pushText("You push well into the night. The next morning will be harsh!\n");
			}
		}
		return super.checkLegalMove(tile);
	}
	
	
	@Override
	void move(Tile targetTile) {		
		Tile currentTile = getCurrentTile();
		if (checkLegalMove(targetTile)) {
			// Removes player icon from current tile.
			currentTile.setIcon(null);
						
			// Sets player parameters.
			setLocation(targetTile.row, targetTile.col);
			
			// Informs tile of visit, changes tile border.
			targetTile = getCurrentTile();
			targetTile.visited += 1;
			targetTile.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0, 255), 2 * targetTile.visited));
			
			int thirst_increase = targetTile.terrain.getThirst() / targetTile.visited;
			int hunger_increase = targetTile.terrain.getHunger() / targetTile.visited;
			int wounds_increase = targetTile.terrain.getWounds() / targetTile.visited;

			setThirst(Math.max(getThirst() + thirst_increase, 0));
			setHunger(Math.max(getHunger() + hunger_increase, 0));
			setWounds(Math.max(getWounds() + wounds_increase, 0));
			
			setTurnMoves(getTurnMoves() - targetTile.terrain.getMoveCost());

			// Passes status change event to listeners.
			for (ActorStatusListener l : statusListeners) {
				l.statusUpdated(giveStatus());
				l.actorAtTile(currentTile);
			}
			
			// Sets icon of target tile.
			targetTile.setIcon(icon);
			getGame().moveView(targetTile);
			
			if (checkDeath()) {
				setActive(false);
				getGame().gridDispatcher.setActive(false);
				getGame().gridMouseListener.setActive(false);
				JOptionPane.showMessageDialog(null, "You have died.");
				getGame().close();
			}
			
			if (checkTurnEnd()) {
				endTurn();
			}
		}
	}

	@Override
	void move(int row, int col) {
		try {
			Tile targetTile = getGame().gameWorld.getTile(row, col);
			move(targetTile);
		} catch (TileOutOfGameWorldException e) {
			System.out.println("Target tile is out of gameworld. Move not possible.");
		}
	}		
	
	/**
	 * Places player on TileGrid. Now used for initializing new game 
	 * Future use for loading saved game and placing actor at saved
	 * position. 
	 * 
	 */	
	@Override
	void placeMe() {
		if (getRow() == -1 && getCol() == -1) {
			Tile startTile = null;
			while (true) {
				startTile = getGame().gameWorld.getRandomTile();
				if (checkPassableTile(startTile)) {
					break;
				}
			}
			setLocation(startTile.row, startTile.col);
		} else {
			setLocation(getRow(), getCol());
		}
		
		Tile currentTile = getCurrentTile();
		
		
		currentTile.visited += 1;
		currentTile.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0, 255), 2 * currentTile.visited));
		currentTile.setIcon(icon);
		
		getGame().moveView(currentTile);
	}

}
