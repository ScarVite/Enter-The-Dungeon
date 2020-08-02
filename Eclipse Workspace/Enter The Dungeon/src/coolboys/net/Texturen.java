package coolboys.net;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texturen {
	
	public BufferedImage gegner;
	public BufferedImage schuss;
	public BufferedImage hintergrund;
	public static BufferedImage spieler;
	private GUI gui;
	
	public Texturen(GUI gui) {
		
		getTextures();
	}
	
	private void getTextures() {

		try {
			spieler = ImageIO.read(new File("Bilder/Spieler.png"));
			gegner = ImageIO.read(new File("Bilder/Gegner.png"));
			schuss = ImageIO.read(new File("Bilder/schuss.png"));
			hintergrund = ImageIO.read(new File("Bilder/background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
