package explorationgame;

import java.awt.Color;

public class WallTerrain extends Terrain {
	private static final long serialVersionUID = 1L;
	
	public WallTerrain() {
		setName("The Wall");
		setColor(new Color(40, 205, 175, 255));
		setWeight(0);
		setHunger(0);
		setThirst(0);
		setWounds(0);
		setMoveCost(-1);
	}

	@Override
	public String flavorText() {
		return "Finally, the Wall is in sight. Your men, despite the hardships and pain, prance around like young boys.\n" +
				"A horn answers your call, and the gates creak open.\n";
	}

}
