package explorationgame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;

public class Whitewalker extends Actor implements ActorStatusListener {
	private static final long serialVersionUID = 1L;
	
//	private Tile playerLocation;
	private Player player;
	
	ImageIcon icon;
	
	final int maxTurnMoves = 24;
	
	public Whitewalker(String name, Player player) {
		
		impassableTerrain = new ArrayList<>(0);
		impassableTerrain.add(new ImpassableMountainsTerrain());
		impassableTerrain.add(new OceanTerrain());
		impassableTerrain.add(new WallTerrain());
		
		setName(name);
		setPlayerControlled(false);
		setRow(-1);
		setCol(-1);
		icon = new ImageIcon("resources\\fallenbrother2.png");
		this.player = player;
	}


	@Override
	void beginTurn() {
		setTurnMoves(maxTurnMoves);
		for (ActorStatusListener l : statusListeners) {
			l.pushText(getGameID() + " active!\n");
		}
		
		SimpleDirectedWeightedGraph<Tile, DefaultWeightedEdge> tileGraph = getGame().gameWorld.getTileGraph();
		List<DefaultWeightedEdge> edgeList = DijkstraShortestPath.findPathBetween(tileGraph, getCurrentTile(), player.getCurrentTile());
		if (edgeList != null) {
			for (DefaultWeightedEdge edge : edgeList)  {
				System.out.println(tileGraph.getEdgeSource(edge));
				if (move(tileGraph.getEdgeTarget(edge)) == true) {
					continue;
				} else {
					break;
				}
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
	boolean move(int row, int col) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean move(Tile targetTile) {
		Tile currentTile = getCurrentTile();
		if (checkLegalMove(targetTile)) {
			currentTile.setIcon(null);
			
			setLocation(targetTile.row, targetTile.col);
			targetTile = getCurrentTile();
			
			setTurnMoves(getTurnMoves() - targetTile.terrain.getMoveCost() * 3);
			
			if (targetTile == player.getCurrentTile()) {
				attack(player);
				return false;
			} else {
				targetTile.setIcon(icon);
				return true;
			}
		}
		return false;
		
	}
	
	public void attack(Player player) {
		player.setWounds(player.getWounds() + 5);
		for (ActorStatusListener l : statusListeners) {
			l.pushText("Three horns interrupt your fitful sleep. White walkers!\n");
			l.pushText("Only thanks to the indomitable will of your men " +
					"you manage to prevail against the ice-cold bastards.\n");
		}
		
		setLocation(-1, -1);
		placeMe();
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
			if (player.getCurrentTile().equals(tile)) {
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
		if (player.getCurrentTile() == getCurrentTile()) {
			attack(player);
		}
		
	}

	@Override
	public void pushText(String text) {
		// TODO Auto-generated method stub
		
	}

}
