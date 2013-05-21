package explorationgame;

import java.awt.Color;

/**
 * Defines mountain terrain.
 * 
 * @author Artur
 *
 */
public class MountainsTerrain extends Terrain {
	
	public MountainsTerrain() {
		setName("Mountains");
		setColor(new Color(96, 96, 96, 255));
		setWeight(10);
		setHunger(10);
		setThirst(-5);
		setWounds(2);
		setMoveCost(3);
	}
}
