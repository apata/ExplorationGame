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
	
	public StatusLabel(Player actor) {
		int[] temp = {actor.maxHunger, actor.maxThirst, actor.maxWounds};
		max_values = temp;
		actor_name = actor.getName();
		this.setText(actor_name + " status ::: Hunger = " + actor.getHunger() + " (max: " + max_values[0] + 
				") | Thirst = " + actor.getThirst() + " (max: " + max_values[1] + 
				") | Wounds = " + actor.getWounds() + " (max: " + max_values[2] + 
				") | Total moves = " + actor.getMoves());
	}
	
	@Override
	public void statusUpdated(int[] status) {
		this.setText(actor_name + " status ::: Hunger = " + status[0] + " (max: " + max_values[0] + 
				") | Thirst = " + status[1] + " (max: " + max_values[1] + 
				") | Wounds = " + status[2] + " (max: " + max_values[2] + 
				") | Total moves = " + status[3]);
		this.repaint();
	}

	@Override
	public void actorAtTile(Tile tile) {
		// TODO Auto-generated method stub
		
	}

}
