package enterTheDungeon.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import enterTheDungeon.game.Game;

public class Texturen {
	public BufferedImage gegner;
	public BufferedImage schuss;
	public BufferedImage feuerball1;
	public BufferedImage feuerball2;
	public BufferedImage hintergrund;
	public BufferedImage spieler;
	public BufferedImage shotgun;
	public BufferedImage pistole;
	public BufferedImage hindernis;
	public BufferedImage portal;
	public BufferedImage falleZU;
	public BufferedImage falleBereit;
	public BufferedImage falleOffen;
	private Game game;

	public Texturen(Game game) {

		getTextures();
	}

	private void getTextures() {

		try {
			spieler = ImageIO.read(new File("Bilder/Spieler.png"));
			gegner = ImageIO.read(new File("Bilder/Gegner.png"));
			schuss = ImageIO.read(new File("Bilder/Schuss.png"));
			hintergrund = ImageIO.read(new File("Bilder/Background1.png"));
			shotgun = ImageIO.read(new File("Bilder/Shotgun.png"));
			pistole = ImageIO.read(new File("Bilder/SciFiPistole.png"));
			hindernis = ImageIO.read(new File("Bilder/Hindernis.png"));
			feuerball1 = ImageIO.read(new File("Bilder/Feuerball1.png"));
			feuerball2 = ImageIO.read(new File("Bilder/Feuerball2.png"));
			portal = ImageIO.read(new File("Bilder/Portal.png"));
			falleZU = ImageIO.read(new File("Bilder/FalleZu.png"));
			falleBereit = ImageIO.read(new File("Bilder/FalleBereit.png"));
			falleOffen = ImageIO.read(new File("Bilder/FalleOffen.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
