package explorationgame;

import java.awt.Color;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
