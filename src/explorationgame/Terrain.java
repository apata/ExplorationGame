package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines Terrain and it's properties. Provides simple constructor.
 * 
 * @author artur
 *
 */
class Terrain {
	String name;
	Color color;
	int weight;
	
	int hunger;
	int thirst;
	int wounds;
	
	public Terrain(String name, Color color, int weight, int hunger,
			int thirst, int wounds) {
		super();
		this.name = name;
		this.color = color;
		this.weight = weight;
		this.hunger = hunger;
		this.thirst = thirst;
		this.wounds = wounds;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
