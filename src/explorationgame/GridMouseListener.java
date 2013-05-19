package explorationgame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Defines MouseListener that wraps around TileGrid.
 * 
 * @author artur
 *
 */
public class GridMouseListener extends MouseAdapter {
	private Actor actor;
	private boolean active;
	
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public GridMouseListener() {
		super();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && active) {
			Tile sourceTile = (Tile) e.getSource();
			System.out.print("Pressed " + sourceTile.terrain.getName() + " at " + sourceTile.row + ", " + sourceTile.col + ". ");
			actor.move(sourceTile);
			}
		}

	}
