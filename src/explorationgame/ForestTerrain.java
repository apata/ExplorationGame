package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines the Forest terrain.
 * 
 * @author 
 *
 */

class ForestTerrain extends Terrain {
	
	public ForestTerrain (){
		setName("Forest");
		setColor(new Color(51, 102, 0, 255));
		setWeight(50);
		setHunger(-5);
		setThirst(5);
		setWounds(1);
		setMoveCost(2);
	}
}
