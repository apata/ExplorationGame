package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines the Frozen lake terrain.
 * 
 * @author 
 *
 */

class FrozenLakeTerrain extends Terrain {
	private static final long serialVersionUID = 1L;

	public FrozenLakeTerrain (){
		setName("Frozen lake");
		setColor(new Color(150, 150, 255, 255));
		setWeight(5);
		setHunger(5);
		setThirst(5);
		setWounds(-1);
		setMoveCost(1);
	}
}
