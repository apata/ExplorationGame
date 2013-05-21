package explorationgame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.DijkstraShortestPath;

public class Whitewalker extends Actor implements ActorStatusListener {
	private static final long serialVersionUID = 1L;
	
	private Tile playerLocation;
	ImageIcon icon;
	
	public Whitewalker(String name, Tile playerTile) {
		impassableTerrain = new ArrayList<>(0);
		impassableTerrain.add(new ImpassableMountainsTerrain());
		impassableTerrain.add(new OceanTerrain());
		setName(name);
		setPlayerControlled(false);
		setMoves(0);
		setRow(-1);
		setCol(-1);
		icon = new ImageIcon("resources\\fallenbrother2.png");
		playerLocation = playerTile;
	}


	@Override
	void beginTurn() {
		for (ActorStatusListener l : statusListeners) {
			l.pushText(getGameID() + " active!\n");
		}
		
		SimpleGraph<Tile, DefaultEdge> tileGraph = getGame().gameWorld.getTileGraph();
		List<DefaultEdge> edgeList = DijkstraShortestPath.findPathBetween(tileGraph, getCurrentTile(), playerLocation);
		if (edgeList != null) {
			for (DefaultEdge edge : edgeList)  {
				System.out.println(tileGraph.getEdgeSource(edge));
			}
		}
		
		endTurn();
	}


	@Override
	void endTurn() {
		getGame().turnManager.nextActorTurn();
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
		if (getRow() == -1 && getCol() == -1) {
			Tile startTile = null;
			while (true) {
				startTile = getGame().gameWorld.getRandomTile();
				if (checkLegalSpawnTile(startTile)) {
					break;
				}
			}
			setLocation(startTile.row, startTile.col);
		} else {
			setLocation(getRow(), getCol());
		}
		
		Tile currentTile = getCurrentTile();
		currentTile.setIcon(icon);
		
	}

	private boolean checkLegalSpawnTile(Tile tile) {
		if (checkPassableTile(tile)) {
			if (playerLocation.equals(tile)) {
				System.out.println("Player at spawn.");
				return false;
			} else { 
				return true; 
			}
		}
		return false;
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
