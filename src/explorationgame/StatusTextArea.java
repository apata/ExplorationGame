package explorationgame;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class StatusTextArea extends JTextArea implements ActorStatusListener {
	public StatusTextArea() {
		super();
		this.setEditable(false);
	}
	
	public void append(String text) {
		super.append(text);
		//this.setCaretPosition(this.getCaretPosition() + text.length());
	}

	@Override
	public void statusUpdated(int[] status) {
		append("Your status has changed!\n");
		
	}

	@Override
	public void actorAtTile(Tile tile) {
		int thirst_increase = tile.terrain.getThirst() / tile.visited;
		int hunger_increase = tile.terrain.getHunger() / tile.visited;
		int wounds_increase = tile.terrain.getWounds() / tile.visited;
		
		append("Hunger increased by " + hunger_increase + 
				"; thirst increased by " + thirst_increase + 
				"; wounds increased by " + wounds_increase + ".\n");
		// TODO Auto-generated method stub
		
	}

}
