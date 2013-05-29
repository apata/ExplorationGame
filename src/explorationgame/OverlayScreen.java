package explorationgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

abstract public class OverlayScreen extends JPanel implements FocusListener, ComponentListener {
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
		this.addComponentListener(this);
		this.setGame(game);
		
		setOpaque(true);
		setFocusable(true);
		setRequestFocusEnabled(true);
		
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
	
	@Override
	public void setVisible(boolean visible) {
		if (visible) {
		    getGame().setHorizontalScrollBarPolicy(Game.HORIZONTAL_SCROLLBAR_NEVER);
		    getGame().setVerticalScrollBarPolicy(Game.VERTICAL_SCROLLBAR_NEVER);
		    super.setVisible(visible);
		    requestFocusInWindow();
		} else {
		    getGame().setHorizontalScrollBarPolicy(Game.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    getGame().setVerticalScrollBarPolicy(Game.VERTICAL_SCROLLBAR_AS_NEEDED);
		    super.setVisible(visible);
		}
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		if (isVisible()) {
			requestFocusInWindow();
		}
	}
	
	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {
		requestFocusInWindow();
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {}
	
	@Override
	public void componentResized(ComponentEvent e) {}
	
	@Override
	public void componentHidden(ComponentEvent e) {}
	
}
