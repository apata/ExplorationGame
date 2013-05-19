package explorationgame;

import java.awt.Color;

/**
 * Reads different terrain types and puts them into an array.
 * 
 * @author
 *
 */

class ReadTerrains {

	public static Terrain[] make_terrains() {
		int terrain_count = 2;
		Terrain[] tbd = new Terrain[terrain_count];
		tbd[0] = new Forest();
		tbd[1] = new Plains();
		return tbd;
	}
}
