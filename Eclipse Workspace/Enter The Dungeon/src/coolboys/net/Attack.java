package coolboys.net;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class Attack implements MouseListener {

	private static int xPos;
	private static int yPos;

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)){
		setxPos(e.getX());
		yPos = e.getY();
		
		GUI.shoot = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void setxPos(int pxPos) {
		Attack.xPos = pxPos;
	}

	public static int getxPos() {
		return xPos;
	}

	public static int getyPos() {
		return yPos;
	}

	public static void setyPos(int pyPos) {
		Attack.xPos = pyPos;
	}

}
