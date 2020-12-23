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
	public BufferedImage powerUp;
	private Game game;

	public Texturen(Game game) {
		this.game = game;
		getTextures();
	}

	private void getTextures() {
		try {
			Filesystem filesystem = new Filesystem();
			spieler = ImageIO.read(filesystem.readFile("/images/Spieler.png"));
			gegner = ImageIO.read(filesystem.readFile("/images/Gegner.png"));
			schuss = ImageIO.read(filesystem.readFile("/images/Schuss.png"));
			hintergrund = ImageIO.read(filesystem.readFile("/images/Background1.png"));
			shotgun = ImageIO.read(filesystem.readFile("/images/Shotgun.png"));
			pistole = ImageIO.read(filesystem.readFile("/images/SciFiPistole.png"));
			hindernis = ImageIO.read(filesystem.readFile("/images/Hindernis.png"));
			feuerball1 = ImageIO.read(filesystem.readFile("/images/Feuerball1.png"));
			feuerball2 = ImageIO.read(filesystem.readFile("/images/Feuerball2.png"));
			portal = ImageIO.read(filesystem.readFile("/images/Portal.png"));
			falleZU = ImageIO.read(filesystem.readFile("/images/FalleZu.png"));
			falleBereit = ImageIO.read(filesystem.readFile("/images/FalleBereit.png"));
			falleOffen = ImageIO.read(filesystem.readFile("/images/FalleOffen.png"));
			powerUp = ImageIO.read(filesystem.readFile("/images/Powerup.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
