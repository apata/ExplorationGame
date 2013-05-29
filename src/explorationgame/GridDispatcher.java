package explorationgame;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.Serializable;

public class GridDispatcher implements KeyEventDispatcher, Serializable {
	private static final long serialVersionUID = 1L;
	
	private Actor actor;
	private boolean active;
	
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public GridDispatcher() {
		super();
		active = false;
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {		
		if (e.getID() == KeyEvent.KEY_RELEASED && active) {
			int row = actor.getRow();
			int col = actor.getCol();

            System.out.print("Released: ");
            System.out.print(e.getKeyCode());
            System.out.println("Actor moved.");
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
        } else if (e.getID() == KeyEvent.KEY_RELEASED && !active) {
            System.out.print("Released: ");
            System.out.print(e.getKeyCode());
            System.out.println(" Dispatcher inactive, actor not moved.");
            
        	return false;
        }
		return false;
	}
	
	public void enable() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
	}
}
