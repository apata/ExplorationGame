package explorationgame;

import java.awt.Color;

import javax.swing.BorderFactory;


public class DummyActor extends Actor {
	private static final long serialVersionUID = 1L;

	public DummyActor(String name) {
		setName(name);
		setPlayerControlled(false);
		setTurns(0);
		setRow(-1);
		setCol(-1);
	}
	
	@Override
	void beginTurn() {
		for (ActorStatusListener l : statusListeners) {
			l.pushText(getGameID() + " active!\n");
		}
		endTurn();
	}

	@Override
	void endTurn() {
		getGame().turnManager.nextActorTurn();
	}

	@Override
	void move(int row, int col) {
	}

	@Override
	void move(Tile tile) {
	}

	@Override
	void placeMe() {
		if (getRow() == -1 && getCol() == -1) {
			int startRow = (int) (Math.random() * getGame().gameWorld.getTiles().length);
			int startCol = (int) (Math.random() * getGame().gameWorld.getTiles()[startRow].length);
			
			setLocation(startRow, startCol);
		} else {
			setLocation(getRow(), getCol());
		}
		
		Tile currentTile = getCurrentTile();

		currentTile.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 0, 255), 6));
	}

}
