package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines the Forest terrain.
 * 
 * @author 
 *
 */

class OceanTerrain extends Terrain {
	
	public OceanTerrain (){
		setName("Ocean");
		setColor(new Color(0, 0, 200, 255));
		setWeight(30);
		setHunger(0);
		setThirst(0);
		setWounds(0);
		setMoveCost(-1);
	}
}
