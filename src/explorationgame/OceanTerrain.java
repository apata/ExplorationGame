package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines the Ocean terrain.
 * 
 * @author 
 *
 */

class OceanTerrain extends Terrain {
	private static final long serialVersionUID = 1L;
	
	public OceanTerrain (){
		setName("Ocean");
		setColor(new Color(0, 0, 200, 255));
		setWeight(0);
		setHunger(0);
		setThirst(0);
		setWounds(0);
		setMoveCost(-1);
	}

	@Override
	public String flavorText() {
		return "Cold waves lap these icy shores, and no man without a caravel may transverse it.\n";
	}
}
