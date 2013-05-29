package explorationgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartScreen extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public TextButton startButton;
	
	private Game game;
	
	public StartScreen(Game game) {
		super();
		this.game = game;
		setOpaque(true);
		
		Color color = new Color(77, 77, 77, 150);
		setBackground(color);
		
//		setLayout(new GridLayout(2, 1, 10, 10));
		setLayout(new BorderLayout(0, 0));
		
		JLabel startText = new JLabel();
		startText.setFont(new Font("Arial", Font.PLAIN, 26));
		startText.setForeground(Color.WHITE);
		startText.setHorizontalAlignment(JLabel.CENTER);
		startText.setVerticalAlignment(JLabel.CENTER);
		startText.setText("<html><b><center>WELCOME TO THE GAME!</center></b>" +
				"<br><justify><i>You are the leader of an expedition in the Far North." +
				"<br>Unfortunately, things have gone wrong." +
				"<br>Now your job is to get back south.</i>" +
				"<br>" +
				"<br>Use your mouse, arrow keys or numpad keys to move." +
				"<br>Keep a close watch on your men and supplies and beware...</justify>" +
				"<br>" +
				"<br><i>There are evil things lurking in the night.</i>");
		
		startText.setPreferredSize(new Dimension(400, 400));
		add(startText, BorderLayout.NORTH);
		
		startButton = new TextButton("START");
		startButton.addActionListener(this);
		startButton.setPreferredSize(new Dimension(400, 50));
		add(startButton, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			setVisible(false);
			game.start();
		}
	}
	
	public void setVisible(boolean visible) {
		if (visible) {
		    game.setHorizontalScrollBarPolicy(Game.HORIZONTAL_SCROLLBAR_NEVER);
		    game.setVerticalScrollBarPolicy(Game.VERTICAL_SCROLLBAR_NEVER);
		    super.setVisible(visible);
		} else {
		    game.setHorizontalScrollBarPolicy(Game.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    game.setVerticalScrollBarPolicy(Game.VERTICAL_SCROLLBAR_AS_NEEDED);
		    super.setVisible(visible);
		}
	}

//	@Override
//	public void componentHidden(ComponentEvent e) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void componentMoved(ComponentEvent e) {
//		this.setBounds(((JComponent) e.getSource()).getBounds());		
//	}
//
//	@Override
//	public void componentResized(ComponentEvent e) {
//		this.setBounds(((JComponent) e.getSource()).getBounds());
//	}
//
//	@Override
//	public void componentShown(ComponentEvent e) {
//		// TODO Auto-generated method stub
//	}
	
//	public static void main(String[] args) {
//		JFrame jframe = new JFrame("test");
//		StartScreen startScreen = new StartScreen();
//		jframe.getContentPane().add(startScreen);
//		startScreen.setVisible(true);
//		jframe.pack();
//		jframe.setVisible(true);
//		
//	}

}
