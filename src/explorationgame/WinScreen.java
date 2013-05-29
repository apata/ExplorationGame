package explorationgame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinScreen extends OverlayScreen implements ActionListener {
	private static final long serialVersionUID = 1L;

	private TextButton closeButton;
	public static String text = 
			"<html><b><center>YOU HAVE WON!</center></b>" +
			"<br><i>You have finally reached the Wall." +
			"<br>A horn answers your signal and the gates creak open.</i>" +
			"<br>" +
			"<br>Your score has been recorded." +
			"<br>Click close to return to main menu.";

	
	public WinScreen(Game game) {
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
		return 	"<html><b><center>YOU HAVE WON!</center></b>" +
				"<br><i>You have finally reached the Wall." +
				"<br>A horn answers your signal and the gates creak open.</i>" +
				"<br>" +
				"<br>Your score has been recorded." +
				"<br>Click close to return to main menu.";
	}

}
