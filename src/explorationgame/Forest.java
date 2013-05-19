package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines the Forest terrain.
 * 
 * @author 
 *
 */

class Forest extends Terrain {
	private String name;
	private Color color;
	private int weight;
	private int hunger;
	private int thirst;
	private int wounds;
	
	public Forest (){
		this.name = new String("Forest");
		this.color = new Color(51, 102, 0, 255);
		this.weight = 10;
		this.hunger = -5;
		this.thirst = 5;
		this.wounds = 1;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public int getHunger() {
		return this.thirst;
	}
	
	public int getThirst() {
		return this.thirst;
	}
	
	public int getWounds() {
		return this.wounds;
	}
	
}
