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
	private TileGrid tileGrid;

	public GridMouseListener(TileGrid tg) {
		tileGrid = tg;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Tile sourceTile = (Tile) e.getSource();
			System.out.print("Pressed " + sourceTile.terrain.name + " at " + sourceTile.row + ", " + sourceTile.col + ". ");
			tileGrid.tilePressed(sourceTile);
			}
		}
	}
