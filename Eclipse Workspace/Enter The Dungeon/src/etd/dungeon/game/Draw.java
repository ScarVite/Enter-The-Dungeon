package etd.dungeon.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Draw extends JLabel {
	

	private static final long serialVersionUID = 1L;
	private Game game;
	
	public Draw(Game game) {
		this.game = game;
	}

	protected void paintComponent(Graphics g) {
		

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Bilder werden angezeigt

		game.render(g);
		repaint();
	}
}