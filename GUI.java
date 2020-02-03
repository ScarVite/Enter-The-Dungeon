package enterTheDungeon;

import java.awt.Dimension;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel {

	static JFrame jf;

	static Draw draw;
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int screenwidth = (int) screenSize.getWidth(), screenheight = (int) screenSize.getHeight();
	static int x;
	static int y;
	static int ym = screenheight/2-100, ysm = screenheight/2-10; // Die Ysm und Xsm sind zum Schuss testen gewesen kann man also entfernen
	static int xm = screenwidth/2-150, xsm = screenwidth/2-10;
	static Label Label1;
	static BufferedImage bi1, bi2, bi3;
	static int speedup = 3, speeddown = 3, speedleft = 3, speedright = 3;
	static boolean moveup = false, movedown = false, moveleft = false, moveright = false, shoot = false;
	static int shootspeed = 20;

	public GUI() {

		jf = new JFrame();

		jf.setSize(screenwidth, screenheight);

		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jf.setLocationRelativeTo(null);

		draw = new Draw();

		draw.setBounds(0, 0, screenwidth, screenheight);

		draw.setVisible(true);

		jf.add(draw);

		Label1 = new Label();

		Label1.setBounds(0, 0, screenwidth, screenheight);

		jf.add(Label1);

		Label1.setVisible(true);

		jf.addKeyListener(new TastenEingabe());

		jf.setVisible(true);

		try {

			bi1 = ImageIO.read(new File("Bilder/Background.png"));

			bi2 = ImageIO.read(new File("Bilder/Chrakter.png"));

			bi3 = ImageIO.read(new File("Bilder/shoot.png"));

		} catch (IOException IO) {

			IO.printStackTrace();

			System.out.println("Bild konnte nicht geladen werden");

		}

	}

}
