package coolboys.net;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

public class TastenEingabe implements KeyListener {

	private GUI gui;

	public TastenEingabe(GUI gui) {
		this.gui = gui;
	}

	public void keyTyped(KeyEvent e) {
		gui.keyTyped(e);
	}

	public void keyPressed(KeyEvent e) {
		gui.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		gui.keyReleased(e);
	}
}