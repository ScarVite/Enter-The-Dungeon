package EnterTheDungeon;

import java.awt.Label;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Var {

	static int x[] = new int[3];
	static int y;
	static int ym = 400 , ysm = 480;
	static int xm = 790 , xsm = 950;
	static Label Label1;
	static BufferedImage bi1, bi2, bi3;
	static int speedup = 3, speeddown = 3, speedleft = 3, speedright = 3;
	static boolean moveup = false, movedown = false, moveleft = false, moveright = false, shoot = false;
	static int shootspeed = 20;

	public Var() {

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
