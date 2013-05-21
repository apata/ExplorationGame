package explorationgame;

import javax.swing.JLabel;

/**
 * Creates updatable label that displays actor status.
 * 
 * @author artur
 *
 */
@SuppressWarnings("serial")
public class StatusLabel extends JLabel implements ActorStatusListener {
	int[] max_values;
	String actor_name;
	
	public StatusLabel(Player player) {
		int[] temp = {player.maxHunger, player.maxThirst, player.maxWounds};
		max_values = temp;
		actor_name = player.getName();
		this.setText(actor_name + " status ::: Hunger = " + player.getHunger() + " (max: " + max_values[0] + 
				") | Thirst = " + player.getThirst() + " (max: " + max_values[1] + 
				") | Wounds = " + player.getWounds() + " (max: " + max_values[2] + 
				") | Moves left = " + player.getTurnMoves() + 
				" | Turns = " + player.getMoves());
	}
	
	@Override
	public void statusUpdated(int[] status) {
		this.setText(actor_name + " status ::: Hunger = " + status[0] + " (max: " + max_values[0] + 
				") | Thirst = " + status[1] + " (max: " + max_values[1] + 
				") | Wounds = " + status[2] + " (max: " + max_values[2] + 
				") | Moves left = " + status[3] + 
				" | Turns = " + status[4]);

		this.repaint();
	}

	@Override
	public void actorAtTile(Tile tile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushText(String text) {
		// TODO Auto-generated method stub
		
	}

}
