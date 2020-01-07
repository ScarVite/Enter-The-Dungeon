package coolboys.net;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Constructor;

public class Steuerung implements KeyListener  {

	public static void main(String[] args) {
		Setup Oberfläche = new Setup();
		int keyR = 0;
		int keyL = 0;
		int keyO = 0;
		int keyU = 0;
		Oberfläche
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyChar() + " pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyChar() + " released");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyChar() + " typed");
	}

}
