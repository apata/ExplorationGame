package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines the Mountains terrain.
 * 
 * @author 
 *
 */

class MountainsTerrain extends Terrain {
	private static final long serialVersionUID = 1L;

	public MountainsTerrain (){
		setName("Mountains");
		setColor(new Color(90, 90, 90, 255));
		setWeight(10);
		setHunger(5);
		setThirst(-10);
		setWounds(2);
		setMoveCost(3);
	}
}
