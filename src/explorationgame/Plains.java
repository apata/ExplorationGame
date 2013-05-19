package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines the Plains terrain.
 * 
 * @author 
 *
 */

class Plains extends Terrain {

	public Plains (){
		setName("Plains");
		setColor(new Color(255, 255, 102, 255));
		setWeight(10);
		setHunger(-5);
		setThirst(0);
		setWounds(-1);
	}
}
