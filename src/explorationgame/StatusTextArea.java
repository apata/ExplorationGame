package explorationgame;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class StatusTextArea extends JTextArea implements UpdateListener {
	public StatusTextArea() {
		super();
		this.setEditable(false);
	}
	
	public void append(String text) {
		super.append(text);
		//this.setCaretPosition(this.getCaretPosition() + text.length());
	}

	@Override
	public void statusUpdated(int[] status) {
		append("Your status has changed!\n");
		
	}

}
