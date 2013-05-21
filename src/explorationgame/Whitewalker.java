package explorationgame;

import java.util.List;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.alg.DijkstraShortestPath;

public class Whitewalker extends Actor implements ActorStatusListener {
	private static final long serialVersionUID = 1L;
	
	private Tile playerLocation;

	@Override
	void beginTurn() {
		List<DefaultEdge> edgeList = DijkstraShortestPath.findPathBetween(getGame().gameWorld.getTileGraph(), getCurrentTile(), playerLocation);
		for (DefaultEdge edge : edgeList)  {
			move((Tile) edge.getTarget());
		}
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
