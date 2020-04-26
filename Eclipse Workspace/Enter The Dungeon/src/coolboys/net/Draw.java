package coolboys.net;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Draw extends JLabel {

	private static final long serialVersionUID = 1L;
	
	public ArrayList<BufferedImage> bulletlist = new ArrayList<>();

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		//g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Bilder werden angezeigt

		for (int i = 0; i < 3; i++) {

			g.drawImage(GUI.bi1, 0, 0, GUI.screenwidth, GUI.screenheight, null);

			g.drawImage(GUI.bi2, GUI.xm, GUI.ym, 300, 200, null);
			
			if (GUI.shoot == true) {
				
				bulletlist.add(GUI.bi3);

				g.drawImage(GUI.bi3,(int) Attack.xPosStartShoot - 15,(int) Attack.yPosStartShoot - 30, 20, 20, null);
				
				Attack.update();
				
			}
			

		}
		

		
		
		


		repaint();

	}

}