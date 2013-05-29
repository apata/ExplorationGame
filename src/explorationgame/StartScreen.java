package explorationgame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends OverlayScreen implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private TextButton startButton;
	
	public StartScreen(Game game) {
		super(game);
		
		startButton = new TextButton("START");
		startButton.addActionListener(this);
		startButton.setPreferredSize(new Dimension(400, 50));
		add(startButton, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			setVisible(false);
			getGame().start();
		}
	}

	@Override
	public String flavorText() {
		return 	"<html><b><center>WELCOME TO THE GAME!</center></b>" +
				"<br><i>You are the leader of an expedition in the Far North." +
				"<br>Unfortunately, things have gone wrong." +
				"<br>Now your job is to get back south.</i>" +
				"<br>" +
				"<br>Use your mouse, arrow keys or numpad keys to move." +
				"<br>Keep a close watch on your men and supplies and beware..." +
				"<br>" +
				"<br><i>There are evil things lurking in the night.</i>";

	}
}
