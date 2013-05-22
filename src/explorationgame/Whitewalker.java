package explorationgame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;

public class Whitewalker extends Actor implements ActorStatusListener {
	private static final long serialVersionUID = 1L;
	
	private Tile playerLocation;
	ImageIcon icon;
	
	final int maxTurnMoves = 24;
	
	public Whitewalker(String name, Tile playerTile) {
		
		impassableTerrain = new ArrayList<>(0);
		impassableTerrain.add(new ImpassableMountainsTerrain());
		impassableTerrain.add(new OceanTerrain());
		impassableTerrain.add(new WallTerrain());
		
		setName(name);
		setPlayerControlled(false);
		setTurns(0);
		setRow(-1);
		setCol(-1);
		icon = new ImageIcon("resources\\fallenbrother2.png");
		playerLocation = playerTile;
	}


	@Override
	void beginTurn() {
		setTurnMoves(maxTurnMoves);
		for (ActorStatusListener l : statusListeners) {
			l.pushText(getGameID() + " active!\n");
		}
		
		SimpleDirectedWeightedGraph<Tile, DefaultWeightedEdge> tileGraph = getGame().gameWorld.getTileGraph();
		List<DefaultWeightedEdge> edgeList = DijkstraShortestPath.findPathBetween(tileGraph, getCurrentTile(), playerLocation);
		if (edgeList != null) {
			for (DefaultWeightedEdge edge : edgeList)  {
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

	public boolean checkLegalMove(Tile tile) {
		for (Terrain t : impassableTerrain) {
			if (t.getClass().equals(tile.terrain.getClass())) {
				System.out.println("Impassable terrain!\n");
				return false;
			}
		}
		if (tile.terrain.getMoveCost() > getTurnMoves()) {
			return false;
		}
		return super.checkLegalMove(tile);
	}

	@Override
	void move(int row, int col) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void move(Tile tile) {
		setTurnMoves(getTurnMoves() - tile.terrain.getMoveCost() * 3); 
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
		if (playerLocation == getCurrentTile()) {
			System.out.println("A terrible white walker appears.");
		}
		
	}

	@Override
	public void pushText(String text) {
		// TODO Auto-generated method stub
		
	}

}
