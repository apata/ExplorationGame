package explorationgame;

import java.awt.Color;

public class WallTerrain extends Terrain {
	private static final long serialVersionUID = 1L;
	
	public WallTerrain() {
		setName("The Wall");
		setColor(new Color(40, 205, 175, 255));
		setWeight(30);
		setHunger(0);
		setThirst(0);
		setWounds(0);
		setMoveCost(0);
	}

}
