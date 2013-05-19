package explorationgame;

interface ActorStatusListener {
	void statusUpdated(int[] status);
	void actorAtTile(Tile tile);
	void dummyYell(String text);
}