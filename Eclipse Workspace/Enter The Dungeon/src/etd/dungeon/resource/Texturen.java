package etd.dungeon.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import etd.dungeon.game.Game;


public class Texturen {
	public BufferedImage gegner;
	public BufferedImage schuss;
	public BufferedImage hintergrund;
	public static BufferedImage spieler;
	private Game game;
	
	public Texturen(Game game) {
		
		getTextures();
	}
	
	private void getTextures() {

		try {
			spieler = ImageIO.read(new File("Bilder/Spieler.png"));
			gegner = ImageIO.read(new File("Bilder/Gegner.png"));
			schuss = ImageIO.read(new File("Bilder/Schuss.png"));
			hintergrund = ImageIO.read(new File("Bilder/Hintergrund.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
