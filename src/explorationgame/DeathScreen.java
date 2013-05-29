package explorationgame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeathScreen extends OverlayScreen implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private TextButton closeButton;	

	public DeathScreen(Game game) {
		super(game);

		closeButton = new TextButton("CLOSE");
		closeButton.addActionListener(this);
		closeButton.setPreferredSize(new Dimension(400, 50));
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
				"<br>Word of your struggles inspires many to come.</i>" +
				"<br>" +
				"<br>Your score has been recorded." +
				"<br>Click close to return to main menu.";
	}


}
