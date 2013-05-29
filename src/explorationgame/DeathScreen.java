package explorationgame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class DeathScreen extends OverlayScreen implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private TextButton closeButton;	

	public DeathScreen(Game game) {
		super(game);

		closeButton = new TextButton("CLOSE");
		closeButton.addActionListener(this);
		closeButton.setPreferredSize(new Dimension(400, 50));
		
		Action closeAction = new AbstractAction("Close") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				getGame().close();				
			}
		};
		
		closeButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, KeyEvent.KEY_RELEASED), "released SPACE");
		closeButton.getActionMap().put("released SPACE", closeAction);

		add(closeButton, BorderLayout.SOUTH);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == closeButton) {
			getGame().close();
		}
	}

	@Override
	public String flavorText() {
		return 	"<html><b><center>YOU HAVE LOST!</center></b>" +
				"<br><i>The merciless cold has cost you your lives." +
				"<br>A later expedition finds your remains." +
				"<br>Tales of your struggles inspire many.</i>" +
				"<br>" +
				"<br>Your score has been recorded." +
				"<br>Click close to return to main menu.";
	}


}
