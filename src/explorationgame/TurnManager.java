package explorationgame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class TurnManager implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Game game;
	public Iterator<Actor> turnOrder;
	private ArrayList<TurnUpdateListener> updateListeners;

	public TurnManager(Game game) {
		this.game = game;
		this.updateListeners = new ArrayList<>();
	}
	
	public void manageTurn() {
		turnOrder = game.actors.values().iterator();
		for (TurnUpdateListener l : updateListeners) {
			l.newTurn();
		}
		System.out.println("New turn.");
		nextActorTurn();
	}
	
	public void nextActorTurn() {
		if (turnOrder.hasNext()) {
			Actor nextActor = turnOrder.next();
			for (TurnUpdateListener l : updateListeners) {
				l.nextActorTurn(nextActor);
			}
			System.out.println("Next actor: " + nextActor.getGameID() + " at " + nextActor.getCurrentTile());
			nextActor.beginTurn();
		} else {
			manageTurn();
		}
	}
	
	public void addUpdateListener(TurnUpdateListener l) {
		updateListeners.add(l);
	}
}