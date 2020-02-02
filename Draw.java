package EnterTheDungeon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


import javax.swing.JLabel;

public class Draw extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (int i = 0; i < Var.x.length; i++) {
			g.drawImage(Var.bi1, 0, 0, 1920, 1080, null);
			g.drawImage(Var.bi2, Var.xm, Var.ym, 300, 200, null);
			if (Var.shoot == true) {
				g.drawImage(Var.bi3, Var.xsm, Var.ysm, 20, 20, null);
				}

		}

		repaint();

	}

}
