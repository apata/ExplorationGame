package explorationgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

abstract public class OverlayScreen extends JPanel {
	private static final long serialVersionUID = 1L;

	private Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public OverlayScreen(Game game) {
		super();
		this.setGame(game);
		setOpaque(true);
		setVisible(false);
		
		Color color = new Color(77, 77, 77, 150);
		setBackground(color);
		
		setLayout(new BorderLayout(0, 0));
		
		JLabel textLabel = new JLabel();
		textLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		textLabel.setForeground(Color.WHITE);
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setVerticalAlignment(JLabel.CENTER);
		textLabel.setText(flavorText());
		
		textLabel.setPreferredSize(new Dimension(400, 400));
		add(textLabel, BorderLayout.NORTH);

	}

	abstract public String flavorText();
	
	public void setVisible(boolean visible) {
		if (visible) {
		    getGame().setHorizontalScrollBarPolicy(Game.HORIZONTAL_SCROLLBAR_NEVER);
		    getGame().setVerticalScrollBarPolicy(Game.VERTICAL_SCROLLBAR_NEVER);
		    super.setVisible(visible);
		} else {
		    getGame().setHorizontalScrollBarPolicy(Game.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    getGame().setVerticalScrollBarPolicy(Game.VERTICAL_SCROLLBAR_AS_NEEDED);
		    super.setVisible(visible);
		}
	}


}
