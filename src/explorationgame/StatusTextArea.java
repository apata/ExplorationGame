package explorationgame;

import javax.swing.JTextArea;

public class StatusTextArea extends JTextArea implements ActorStatusListener, TurnUpdateListener {
	private static final long serialVersionUID = 1L;
	
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
		//append("Your status has changed! ");	
	}

	@Override
	public void actorAtTile(Tile tile) {
//		int thirst_increase = tile.terrain.getThirst() / tile.visited;
//		int hunger_increase = tile.terrain.getHunger() / tile.visited;
//		int wounds_increase = tile.terrain.getWounds() / tile.visited;
		
//		append("[H: " + hunger_increase + 
//				"; T: " + thirst_increase + 
//				"; W: " + wounds_increase + "]\n");		
		
		append(tile.terrain.flavorText());
	}

	@Override
	public void nextActorTurn(Actor actor) {}

	@Override
	public void newTurn() {
		append("A new day dawns.\n");
	}

	@Override
	public void pushText(String text) {
		append(text);
		
	}

}
