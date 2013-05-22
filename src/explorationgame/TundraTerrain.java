package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines the Tundra terrain.
 * 
 * @author 
 *
 */

class TundraTerrain extends Terrain {
	private static final long serialVersionUID = 1L;

	public TundraTerrain (){
		setName("Tundra");
		setColor(new Color(200, 200, 200, 255));
		setWeight(30);
		setHunger(5);
		setThirst(5);
		setWounds(-1);
		setMoveCost(2);
	}
}
