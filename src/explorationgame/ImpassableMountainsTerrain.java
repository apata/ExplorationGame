package explorationgame;

import java.awt.Color;

/**
 * Defines mountain terrain.
 * 
 * @author Artur
 *
 */

public class ImpassableMountainsTerrain extends Terrain {
	private static final long serialVersionUID = 1L;
	
	public ImpassableMountainsTerrain() {
		setName("Impassable mountains");
		setColor(new Color(70, 70, 70, 255));
		setWeight(10);
		setHunger(0);
		setThirst(0);
		setWounds(0);
		setMoveCost(-1);
	}
}
