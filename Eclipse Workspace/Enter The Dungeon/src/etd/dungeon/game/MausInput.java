package etd.dungeon.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class MausInput implements MouseListener {

	private Game game;
	private static double xMaus;
	private static double yMaus;

	public MausInput(Game pGame) {
		this.game = pGame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		game.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		game.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (SwingUtilities.isLeftMouseButton(e)) {
			setxMaus(e.getX());
			setyMaus(e.getY());
			xMaus = getxMaus();
			yMaus = getyMaus();
			game.mouseReleased(e);
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		game.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		game.mouseExited(e);
	}

	public static double getxMaus() {
		return xMaus;
	}

	public void setxMaus(double xMaus) {
		this.xMaus = xMaus;
	}

	public static double getyMaus() {
		return yMaus;
	}

	public void setyMaus(double yMaus) {
		this.yMaus = yMaus;
	}

}
