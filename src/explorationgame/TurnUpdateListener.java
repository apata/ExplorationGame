package explorationgame;

public interface TurnUpdateListener {
	void nextActorTurn(Actor actor);
	void newTurn();
}
