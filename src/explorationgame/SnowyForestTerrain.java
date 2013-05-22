package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines the Snowy Forest terrain.
 * 
 * @author 
 *
 */

class SnowyForestTerrain extends Terrain {
	private static final long serialVersionUID = 1L;

	public SnowyForestTerrain (){
		setName("Snowy forest");
		setColor(new Color(150, 255, 150, 255));
		setWeight(15);
		setHunger(5);
		setThirst(5);
		setWounds(2);
		setMoveCost(3);
	}
}
