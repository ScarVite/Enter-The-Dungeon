package enterTheDungeon.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import enterTheDungeon.game.Mainmenu;

public class Mainmenutex {
	public static BufferedImage mainmenubild;
	private Mainmenu mainmenu;
	public Mainmenutex(Mainmenu mainmenu) {
		getTextures();
		this.mainmenu = mainmenu;
	}
	private void getTextures() {
		Filesystem filesystem = new Filesystem();
				try {
					mainmenubild = ImageIO.read(filesystem.readFile("/images/mainmenu.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
	}

