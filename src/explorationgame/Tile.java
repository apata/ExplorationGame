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
		setBackground(terrain.color);
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setBorder(BorderFactory.createEmptyBorder());
		setToolTipText("<html><u><b>" + terrain.name + "</u></b>" +
						"<br><b>Hunger: </b>" + terrain.hunger +
						"<br><b>Thirst: </b>" + terrain.thirst +
						"<br><b>Wounds: </b>" + terrain.wounds);
		}
}
