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
				new TundraTerrain(), 
				new MountainsTerrain(), 
				new ForestTerrain(),
				new HillsTerrain(),
				new ImpassableMountainsTerrain(),
				new FrozenLakeTerrain(),
				new SnowyForestTerrain()
		};
		
		return tbd;
	}
}
