package explorationgame;

import java.util.ArrayList;

/**
 * Reads in Terrains from Terrain[] array and serves them randomly.
 * 
 * @author artur
 *
 */
class TerrainServer {
	int total_weight;
	ArrayList<Integer> terrain_weights = new ArrayList<>();
	ArrayList<Terrain> terrain_list = new ArrayList<>();
	
	public TerrainServer(Terrain[] terrain_array) {
		for (Terrain terrain : terrain_array) {
			terrain_list.add(terrain);
			total_weight += terrain.getWeight();
			terrain_weights.add(total_weight);
		}
	}
	
	
	/**
	 * Serves random weighed Terrain.
	 * 
	 * @return
	 */
	public Terrain next() {
		double terrain_nr = Math.random() * total_weight;
		for (int i = 0; i < terrain_weights.size(); i++) {
			if (terrain_nr <= terrain_weights.get(i)) {
				return terrain_list.get(i);
			}
		}
		return null;
	}
}
