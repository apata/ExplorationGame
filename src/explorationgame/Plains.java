package explorationgame;

import java.awt.Color;

/**
 * 
 * Defines the Plains terrain.
 * 
 * @author 
 *
 */

class Plains extends Terrain {
	private String name;
	private Color color;
	private int weight;
	private int hunger;
	private int thirst;
	private int wounds;
	
	public Plains (){
		this.name = new String("Forest");
		this.color = new Color(255, 255, 102, 255);
		this.weight = 100;
		this.hunger = -5;
		this.thirst = 0;
		this.wounds = -1;
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
