package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines the Hills terrain.
 * 
 * @author 
 *
 */

class HillsTerrain extends Terrain {
	private static final long serialVersionUID = 1L;

	public HillsTerrain (){
		setName("Hills");
		setColor(new Color(139, 119, 101, 255));
		setWeight(20);
		setHunger(-5);
		setThirst(2);
		setWounds(1);
		setMoveCost(4);
	}
}
