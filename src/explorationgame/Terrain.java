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
	private Color color;
	private int weight;
	private int hunger;
	private int thirst;
	private int wounds;
	private int moveCost;
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getHunger() {
		return hunger;
	}
	
	public void setHunger(int hunger) {
		this.hunger = hunger;
	}
	
	public int getThirst() {
		return thirst;
	}
	
	public void setThirst(int thirst) {
		this.thirst = thirst;
	}
	
	public int getWounds() {
		return wounds;
	}
	
	public void setWounds(int wounds) {
		this.wounds = wounds;
	}
	
	public int getMoveCost() {
		return moveCost;
	}

	public void setMoveCost(int moveCost) {
		this.moveCost = moveCost;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
