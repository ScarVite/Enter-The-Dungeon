package enterTheDungeon;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

public class TastenEingabe implements KeyListener {

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_W) {

			GUI.moveup = true;

		}

		if (e.getKeyCode() == KeyEvent.VK_S) {

			GUI.movedown = true;

		}

		if (e.getKeyCode() == KeyEvent.VK_D) {

			GUI.moveright = true;

		}

		if (e.getKeyCode() == KeyEvent.VK_A) {

			GUI.moveleft = true;

		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			GUI.shoot = true;

		}

	}

	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_W) {

			GUI.moveup = false;

		}

		if (e.getKeyCode() == KeyEvent.VK_S) {

			GUI.movedown = false;

		}

		if (e.getKeyCode() == KeyEvent.VK_D) {

			GUI.moveright = false;

		}

		if (e.getKeyCode() == KeyEvent.VK_A) {

			GUI.moveleft = false;

		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			GUI.shoot = false;

		}

	}

}