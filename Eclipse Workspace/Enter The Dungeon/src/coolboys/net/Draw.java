package coolboys.net;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Draw extends JLabel {
	

	private static final long serialVersionUID = 1L;
	private GUI gui;
	
	public Draw(GUI gui) {
		this.gui = gui;
	}

	protected void paintComponent(Graphics g) {
		

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Bilder werden angezeigt

		g.drawImage(GUI.background, 0, 0, gui.getScreenwidth(), gui.getScreenwidth(), null);
		Spieler.render(g);
		Controller.render(g);
		repaint();
	}
}