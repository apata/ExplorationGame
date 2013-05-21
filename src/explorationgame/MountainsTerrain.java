package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines the Plains terrain.
 * 
 * @author 
 *
 */

class MountainsTerrain extends Terrain {
	private static final long serialVersionUID = 1L;

	public MountainsTerrain (){
		setName("Mountains");
		setColor(new Color(80, 80, 80, 255));
		setWeight(10);
		setHunger(-5);
		setThirst(5);
		setWounds(1);
		setMoveCost(3);
	}
}
