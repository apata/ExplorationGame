package explorationgame;

/**
 * Reads different terrain types and puts them into an array.
 * 
 * @author
 *
 */

class ReadTerrains {

	public static Terrain[] make_terrains() {
		Terrain[] tbd = {
				new PlainsTerrain(), 
				new MountainsTerrain(), 
				new ForestTerrain()
		};
		
		return tbd;
	}
}
