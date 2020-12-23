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
	public BufferedImage speedpowerUp;
	public BufferedImage schadenpowerUp;
	public BufferedImage heartpowerUp;
	private Game game;

	public Texturen(Game game) {
		this.game = game;
		getTextures();
	}

	private void getTextures() {
		try {
			Filesystem filesystem = new Filesystem();
			spieler = ImageIO.read(filesystem.readFile("/images/spieler.png"));
			gegner = ImageIO.read(filesystem.readFile("/images/gegner.png"));
			schuss = ImageIO.read(filesystem.readFile("/images/schuss.png"));
			hintergrund = ImageIO.read(filesystem.readFile("/images/background1.png"));
			shotgun = ImageIO.read(filesystem.readFile("/images/shotgun.png"));
			pistole = ImageIO.read(filesystem.readFile("/images/scifi-pistole.png"));
			hindernis = ImageIO.read(filesystem.readFile("/images/hindernis.png"));
			feuerball1 = ImageIO.read(filesystem.readFile("/images/feuerball1.png"));
			feuerball2 = ImageIO.read(filesystem.readFile("/images/feuerball2.png"));
			portal = ImageIO.read(filesystem.readFile("/images/portal.png"));
			falleZU = ImageIO.read(filesystem.readFile("/images/falle-zu.png"));
			falleBereit = ImageIO.read(filesystem.readFile("/images/falle-bereit.png"));
			falleOffen = ImageIO.read(filesystem.readFile("/images/falle-offen.png"));
      speedpowerUp = ImageIO.read(filesystem.readFile("/images/powerup.png"));
			schadenpowerUp = ImageIO.read(filesystem.readFile("/images/schaden.png"));
			heartpowerUp = ImageIO.read(filesystem.readFile("/images/heart.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
