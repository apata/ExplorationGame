package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines Terrain and it's properties. Provides simple constructor.
 * 
 * @author artur
 *
 */
abstract class Terrain {
	private String name;


//	public Terrain(String name, Color color, int weight, int hunger,
//			int thirst, int wounds) {
//		super();
//		this.name = name;
//		this.color = color;
//		this.weight = weight;
//		this.hunger = hunger;
//		this.thirst = thirst;
//		this.wounds = wounds;
//	}


	abstract String getName();
	
	abstract Color getColor();
	
	abstract int getWeight();
	
	abstract int getHunger();
	
	abstract int getThirst();
	
	abstract int getWounds();
	
	@Override
	public String toString() {
		return this.name;
	}
}
