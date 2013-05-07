package explorationgame;

interface ActorStatusListener {
	void statusUpdated(int[] status);
	void actorAtTile(Tile tile);
}
