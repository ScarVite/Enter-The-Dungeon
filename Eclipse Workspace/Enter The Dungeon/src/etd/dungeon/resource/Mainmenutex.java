package etd.dungeon.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import etd.dungeon.game.Mainmenu;

public class Mainmenutex {
	public static BufferedImage mainmenubild;
	private Mainmenu mainmenu;
	public Mainmenutex(Mainmenu mainmenu) {
		getTextures();
	}
	private void getTextures() {

				try {
					mainmenubild = ImageIO.read(new File("Bilder/mainmenu.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
	}

