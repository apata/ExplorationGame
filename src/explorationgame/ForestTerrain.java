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
	private static final long serialVersionUID = 1L;
	
	public ForestTerrain (){
		setName("Forest");
		setColor(new Color(51, 102, 0, 255));
		setWeight(30);
		setHunger(-7);
		setThirst(5);
		setWounds(1);
		setMoveCost(3);
	}

	@Override
	public String flavorText() {
		return "Progress through the icy forest is slow, but at least there are some squirrels to eat.\n";
	}
}
