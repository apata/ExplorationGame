package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines the Plains terrain.
 * 
 * @author 
 *
 */

class PlainsTerrain extends Terrain {
	private static final long serialVersionUID = 1L;

	public PlainsTerrain (){
		setName("Plains");
		setColor(new Color(255, 255, 102, 255));
		setWeight(10);
		setHunger(-5);
		setThirst(0);
		setWounds(-1);
		setMoveCost(1);
	}
}
