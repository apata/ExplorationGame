package explorationgame;

public class Whitewalker extends Actor implements ActorStatusListener {
	private static final long serialVersionUID = 1L;
	
	private Tile playerLocation;

	@Override
	void beginTurn() {
		
	}

	@Override
	void endTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void move(int row, int col) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void move(Tile tile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void placeMe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void statusUpdated(int[] status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actorAtTile(Tile tile) {
		this.playerLocation = tile;
		
	}

	@Override
	public void pushText(String text) {
		// TODO Auto-generated method stub
		
	}

}
