package explorationgame;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * Creates JLabel type object to represent one game tile.
 * 
 * @author artur
 *
 */
@SuppressWarnings("serial")
class Tile extends JLabel {
	Terrain terrain;
	int row;
	int col;
	int visited;
		
	public Tile(Terrain t) {
		super();
		terrain = t;
		setOpaque(true);
		setFocusable(true);
		setBackground(terrain.getColor());
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setBorder(BorderFactory.createEmptyBorder());
		setToolTipText("<html><u><b>" + terrain.getName() + "</u></b>" +
						"<br><b>Hunger: </b>" + terrain.getHunger() +
						"<br><b>Thirst: </b>" + terrain.getThirst() +
						"<br><b>Wounds: </b>" + terrain.getWounds() +
						"<br><b>Cost to move: </b>" + terrain.getMoveCost());
		}
}
