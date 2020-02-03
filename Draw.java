package enterTheDungeon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class Draw extends JLabel {

	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Bilder werden angezeigt

		for (int i = 0; i < 3; i++) {

			g.drawImage(GUI.bi1, 0, 0, GUI.screenwidth, GUI.screenheight, null);

			g.drawImage(GUI.bi2, GUI.xm, GUI.ym, 300, 200, null);

			if (GUI.shoot == true) {

				g.drawImage(GUI.bi3, GUI.xsm, GUI.ysm, 20, 20, null);

			}

		}

		repaint();

	}

}