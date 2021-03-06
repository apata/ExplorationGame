package explorationgame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Defines MouseListener that wraps around TileGrid.
 * 
 * @author artur
 *
 */
public class GridMouseListener extends MouseAdapter implements Serializable {
	private static final long serialVersionUID = 1L;

	private TileGrid gameWorld;
	private Actor actor;
	private boolean active;
		
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public GridMouseListener(TileGrid gameWorld) {
		super();
		this.gameWorld = gameWorld;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && active) {
			Tile sourceTile = (Tile) e.getSource();
			System.out.print("Pressed " + sourceTile.terrain.getName() + " at " + sourceTile.row + ", " + sourceTile.col + ". ");
			actor.move(sourceTile);
		} else if (!active) {
			System.out.println(" Mouse listener inactive. Actor not moved.");
		}	
	}
	
	public void enable() {
		for (Tile[] row : gameWorld.getTiles()) {
			for (Tile t : row) {
				t.addMouseListener(this);
			}
		}
	}
	
	public void disable() {
		for (Tile[] row : gameWorld.getTiles()) {
			for (Tile t : row) {
				t.removeMouseListener(this);
			}
		}
	}

}
