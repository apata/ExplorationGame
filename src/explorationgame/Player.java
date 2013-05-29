package explorationgame;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 * Represents player of game.
 * 
 * @author Artur
 *
 */

/**
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
	final int maxWounds = 25;
	final int maxTurnMoves = 10;		
	ImageIcon icon;
	
	int getHunger() {
		return hunger;
	}
	
	void setHunger(int hunger) {
		this.hunger = hunger;
		if (checkDeath()) {
			kill();
		}
	}
	
	int getThirst() {
		return thirst;
	}
	
	void setThirst(int thirst) {
		this.thirst = thirst;
		if (checkDeath()) {
			kill();
		}
	}
	
	int getWounds() {
		return wounds;
	}
	
	void setWounds(int wounds) {
		this.wounds = wounds;
		if (checkDeath()) {
			kill();
		}
	}
	
	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		if (active) {
			getGame().gridDispatcher.setActive(true);
			getGame().gridMouseListener.setActive(true);
			getGame().gridDispatcher.setActor(this);
			getGame().gridMouseListener.setActor(this);
		} else {
			getGame().gridDispatcher.setActive(false);
			getGame().gridMouseListener.setActive(false);
			getGame().gridDispatcher.setActor(null);
			getGame().gridMouseListener.setActor(null);
		}
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
		icon = new ImageIcon("resources\\spearman.png");
		
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
	 * Checks if player is dead.
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
	 * Checks if player has moves left.
	 * 
	 * @return
	 */
	public boolean checkTurnEnd() {
		if (getTurnMoves() <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Checks if player is on wall terrain.
	 * 
	 * @return
	 */
	public boolean checkVictory() {
		if (getCurrentTile().getClass().equals(new WallTerrain().getClass())) {
			return true;
		} else {
			return false;
		}
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
		setActive(true);
		setTurnMoves(getTurnMoves() + maxTurnMoves);
		
		// Passes status to listeners.
		for (ActorStatusListener l : statusListeners) {
			l.statusUpdated(giveStatus());
			l.actorAtTile(getCurrentTile());
		}

		// Enables player input.
//		getGame().gridDispatcher.setActor(this);
//		getGame().gridMouseListener.setActor(this);
//		getGame().gridDispatcher.setActive(true);
//		getGame().gridMouseListener.setActive(true);
	}
	
	@Override
	void endTurn() {
		// Disables player input.
		setActive(false);
//		getGame().gridDispatcher.setActive(false);
//		getGame().gridMouseListener.setActive(false);
		
		for (ActorStatusListener l : statusListeners) {
			l.pushText("Darkness engulfs the primal landscape.\n");
		}
		
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
	boolean move(int row, int col) {
		try {
			Tile targetTile = getGame().gameWorld.getTile(row, col);
			return move(targetTile);
		} catch (TileOutOfGameWorldException e) {
			System.out.println("Target tile is out of gameworld. Move not possible.");
			return false;
		}
	}		

	@Override
	boolean move(Tile targetTile) {		
		Tile currentTile = getCurrentTile();
		if (checkLegalMove(targetTile)) {
			// Removes player icon from current tile.
			currentTile.setIcon(null);
						
			// Sets player parameters.
			setLocation(targetTile.row, targetTile.col);
			
			// Informs tile of visit, changes tile border.
			targetTile = getCurrentTile();
			
			if (checkVictory()) {
				win();
				return true;
			}

			targetTile.visited += 1;
			targetTile.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0, 255), 2 * targetTile.visited));
			
			int thirst_increase = targetTile.terrain.getThirst() / targetTile.visited;
			int hunger_increase = targetTile.terrain.getHunger() / targetTile.visited;
			int wounds_increase = targetTile.terrain.getWounds() / targetTile.visited;

			setThirst(Math.max(getThirst() + thirst_increase, 0));
			setHunger(Math.max(getHunger() + hunger_increase, 0));
			setWounds(Math.max(getWounds() + wounds_increase, 0));
			
			setTurnMoves(getTurnMoves() - targetTile.terrain.getMoveCost());

			// Sets icon of target tile.
			targetTile.setIcon(icon);
			getGame().moveView(targetTile);
			
			
			// Passes status change event to listeners.
			for (ActorStatusListener l : statusListeners) {
				l.statusUpdated(giveStatus());
				l.actorAtTile(targetTile);
			}
						
			
			if (checkTurnEnd()) {
				endTurn();
				return true;
			}	
			
			return true;
		} else {
			return false;
		}
	}

	private void win() {				
//		if (isActive()) {
//			for (ActorStatusListener l : statusListeners) {
//				l.pushText("You have finally reached the Wall! Congratulations! \n" +
//						"Close the game window to immortalize your name in the Great Hall.\n");
//			}	
//				
//			try {
//				Player victoryPlayer = new Player(getName());
//				victoryPlayer.setTurns(getTurns() + 50);
//				Highscore hs = new Highscore(victoryPlayer);
//				hs.writeToFile();
//			}
//			catch (IOException e) {
//				System.out.println("Error writing data to file: " + e);
//			}
//		}
	
	
		setActive(false);
		getGame().gridDispatcher.setActive(false);
		getGame().gridMouseListener.setActive(false);
		
		for (ActorStatusListener l : statusListeners) {
		l.pushText("You have finally reached the Wall! Congratulations! \n" +
				"Close the game window to immortalize your name in the Halls.\n");
		}		
	
		try {
			Player victoryPlayer = new Player(getName());
			victoryPlayer.setTurns(getTurns() + 50);
			Highscore hs = new Highscore(victoryPlayer);
			hs.writeToFile();
		} catch (IOException e) {
			System.out.println("Error writing data to file: " + e);
		}
	}
	
	private void kill() {
		setActive(false);
		getGame().gridDispatcher.setActive(false);
		getGame().gridMouseListener.setActive(false);
				
		for (ActorStatusListener l : statusListeners) {
			l.pushText("You have died!\n" +
					"Close the game window to immortalize your name in the Halls of the Dead.\n");
		}	
		try {
			Player deadPlayer = new Player(getName());
			deadPlayer.setTurns(getTurns());
			Highscore hs = new Highscore(deadPlayer);
			hs.writeToFile();
		} catch (IOException e) {
			System.out.println("Error writing data to file: " + e);
		}
		
	
//		getGame().close();
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
				if (startTile.row >= getGame().gameWorld.getTiles().length - 10) {
					continue;
				}
				
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
		
//		getGame().moveView(currentTile);
	}

}
