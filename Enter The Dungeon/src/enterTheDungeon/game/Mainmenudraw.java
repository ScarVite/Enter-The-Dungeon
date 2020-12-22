package enterTheDungeon.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class Mainmenudraw extends JLabel {
	

	private static final long serialVersionUID = 1L;
	private Mainmenu mainmenu;
	
	public Mainmenudraw(Mainmenu mainmenu) {
		this.mainmenu = mainmenu;
	}

	protected void paintComponent(Graphics g) {
		

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Bilder werden angezeigt
		
		mainmenu.render(g);
		repaint();
	}
}