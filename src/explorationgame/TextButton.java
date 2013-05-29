package explorationgame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class TextButton extends JButton {
	private static final long serialVersionUID = 1L;

	public TextButton (String text) {
        super(text);
        
        setFont(new Font("Arial", Font.BOLD, 26));
        setBorder(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
		setForeground(Color.WHITE);
		setBackground(new Color(77, 77, 77, 255));
		setFocusPainted(false);
		setRolloverEnabled(false);
		setOpaque(false);
	}
}
