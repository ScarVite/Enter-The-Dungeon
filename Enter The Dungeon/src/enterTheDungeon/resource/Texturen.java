package enterTheDungeon.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import enterTheDungeon.game.Game;

public class Texturen {
	public BufferedImage gegner;
	public BufferedImage schuss;
	public BufferedImage hintergrund;
	public BufferedImage spieler;
	public BufferedImage shotgun;
	public BufferedImage pistole;
	public BufferedImage hindernis;
	private Game game;

	public Texturen(Game game) {

		getTextures();
	}

	private void getTextures() {

		try {
			spieler = ImageIO.read(new File("Bilder/Spieler.png"));
			gegner = ImageIO.read(new File("Bilder/Gegner.png"));
			schuss = ImageIO.read(new File("Bilder/Schuss.png"));
			hintergrund = ImageIO.read(new File("Bilder/Background.png"));
			shotgun = ImageIO.read(new File("Bilder/Shotgun.png"));
			pistole = ImageIO.read(new File("Bilder/SciFiPistole.png"));
			hindernis = ImageIO.read(new File("Bilder/Hindernis.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
