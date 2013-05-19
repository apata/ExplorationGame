package explorationgame;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class GridDispatcher implements KeyEventDispatcher {
	private Actor actor;
	private boolean enabled;
	
	public GridDispatcher(Actor actor) {
		super();
		this.actor = actor;
		this.enabled = true;
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		int row = actor.getRow();
		int col = actor.getCol();
		
		if (e.getID() == KeyEvent.KEY_RELEASED && enabled) {
            System.out.print("Released: ");
            System.out.println(e.getKeyCode());
            if (e.getKeyCode() == KeyEvent.VK_NUMPAD8 || e.getKeyCode() == KeyEvent.VK_UP) {
            	actor.move(row - 1, col);
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
            	actor.move(row - 1, col + 1);
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6 || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            	actor.move(row, col + 1);
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
            	actor.move(row + 1, col + 1);
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2 || e.getKeyCode() == KeyEvent.VK_DOWN) {
            	actor.move(row + 1, col);
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
            	actor.move(row + 1, col - 1);
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4 || e.getKeyCode() == KeyEvent.VK_LEFT) {
            	actor.move(row, col - 1);
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
            	actor.move(row - 1, col - 1);
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
            	actor.move(row, col);
            }
		}
		return false;
	}
	
	public void enable() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
	}
}
