package enterTheDungeon.game;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;

import enterTheDungeon.resource.*;
import enterTheDungeon.game.level.LevelCreator;
import enterTheDungeon.game.waffen.Waffe;
import enterTheDungeon.input.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JPanel {

	private static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenwidth = (int) screensize.getWidth();
	private int screenheight = (int) screensize.getHeight();
	private Texturen tex;
	private Spieler spieler;
	private Timer time;
	private Render render;
	private JFrame spiel;
	private ArrayList<Schuss> schussliste;
	private ArrayList<Gegner> gegnerliste;
	private ArrayList<Hindernis> hindernisliste;
	private Sound sound = new Sound();
	private MausInput mausinput;
	private TastaturInput tastinput;
	private Waffe waffe;
	private LevelCreator levelcreator;
	private int maxGegner;
	private int maxHindernis;
	private boolean pause = false;

	public Game() {
		init();

		for (int i = 0; i < 5; i++) {
		}

		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			@Override

			public void run() {
				update();
			}

		}, 0, 10);
	}

	private void update() {
		spieler.update();
		gegnerliste = levelcreator.getGegnerliste();
		hindernisliste = levelcreator.getHindernisliste();
		for (Gegner gegner : gegnerliste) {
			gegner.update();
		}
		for (Hindernis hindernis : hindernisliste) {
			hindernis.update();
		}
		collision();

	}

	public void render(Graphics g) {
		g.drawImage(tex.hintergrund, 0, 0, getScreenwidth(), getScreenheight(), null);
		for (Gegner gegner : gegnerliste) {
			gegner.render(g);
		}
		for (Hindernis hindernis : hindernisliste) {
			hindernis.render(g);
		}
		spieler.render(g);
	}

	private void collision() {
		Rectangle spC = spieler.getBounds();
		waffe = spieler.getWaffe();
		schussliste = waffe.getSchussarray();
		collisionSchussMitObject(spC, 1);

		for (Gegner gegner : gegnerliste) {
			waffe = gegner.getWaffe();
			schussliste = waffe.getSchussarray();
			collisionSchussMitObject(spC, 0);
		}

		for (Hindernis hindernis : hindernisliste) {
			Rectangle hindi = hindernis.getBounds();
			if (hindi.intersects(spC) && spieler.isDown()) {
				spieler.setyPos(spieler.getyPos() - spieler.getSpeed());
			}
			if (hindi.intersects(spC) && spieler.isUp()) {
				spieler.setyPos(spieler.getyPos() + spieler.getSpeed());
			}
			if (hindi.intersects(spC) && spieler.isLeft()) {
				spieler.setxPos(spieler.getxPos() + spieler.getSpeed());
			}
			if (hindi.intersects(spC) && spieler.isRight()) {
				spieler.setxPos(spieler.getxPos() - spieler.getSpeed());
			}

		}
	}

	private void collisionSchussMitObject(Rectangle spC, int waffe) {
		for (int i = 0; i < schussliste.size(); i++) {
			Rectangle s = schussliste.get(i).getBounds();

			for (Gegner gegner : gegnerliste) {
				Rectangle g = gegner.getBounds();

				// schuss und gegner überschneiden && spielerwaffe dann wird gegner getroffen
				if (g.intersects(s) && waffe == 1) {
//					gegnerliste.remove(b);
					gegner.setLeben(gegner.getLeben() - 1);
					schussliste.remove(i);
				}
			}
			// schuss und gegner überschneiden && gegnerwaffe dann wird spieler getroffen
			if (spC.intersects(s) && waffe == 0) {
				schussliste.remove(i);
				spieler.setLeben(spieler.getLeben() - 1);
				if (spieler.getLeben() == 0) {
					beendeSpiel();
				}
			}

			for (Hindernis hindernis : hindernisliste) {
				Rectangle hindi = hindernis.getBounds();
				if (s.intersects(hindi)) {
					schussliste.remove(i);
				}
			}
		}

		for (int i = 0; i < gegnerliste.size(); i++) {
			if (gegnerliste.get(i).getLeben() == 0) {
				gegnerliste.remove(i);
			}
		}

		if (gegnerliste.size() == 0) {
			baueLevel();
			for (int i = 0; i < hindernisliste.size(); i++) {
				hindernisliste.remove(i);
			}
		}
	}

	private void removeGegner() {

	}

	private void beendeSpiel() {
		spiel.setVisible(false);
		spiel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		System.out.println("beednet");
	}

	private void init() {
		gegnerliste = new ArrayList<Gegner>();
		schussliste = new ArrayList<Schuss>();
		hindernisliste = new ArrayList<Hindernis>();
//		gegner = new Gegner(0, 0, 30, 30, 3, 3, tex, this);
		tex = new Texturen(this);
		spieler = new Spieler(400, 400, 75, 125, 3, 3, tex);
		setAnzHindernis(5);
		setAnzGegner(5);
		levelcreator = new LevelCreator(this, tex);
		mausinput = new MausInput(this);
		tastinput = new TastaturInput(this);
		spiel = new JFrame("Enter the Dungeon");

		spiel.setExtendedState(JFrame.MAXIMIZED_BOTH);
		spiel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		spiel.setLocationRelativeTo(null);
		spiel.addKeyListener(tastinput);
		spiel.addMouseListener(mausinput);
		render = new Render(this);
		render.setBounds(0, 0, screenwidth, screenheight);
		spiel.add(render);

		spiel.setVisible(true);

		baueLevel();

	}

	public void baueLevel() {
		setAnzGegner(10);
		setAnzHindernis(5);
		levelcreator.createLevel();
	}

	// TastaturInput

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			spieler.setUp(true);
		}
		if (key == KeyEvent.VK_A) {
			spieler.setLeft(true);
		}
		if (key == KeyEvent.VK_S) {
			spieler.setDown(true);
		}
		if (key == KeyEvent.VK_D) {
			spieler.setRight(true);
		}
		if (key == KeyEvent.VK_ESCAPE) {
			pause = true;
			while (pause) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			spieler.setUp(false);
		}
		if (key == KeyEvent.VK_A) {
			spieler.setLeft(false);
		}
		if (key == KeyEvent.VK_S) {
			spieler.setDown(false);
		}
		if (key == KeyEvent.VK_D) {
			spieler.setRight(false);
		}
	}

	// MausInput

	public void mouseClicked(MouseEvent e) {
		spieler.schiessen(mausinput.getxMaus(), mausinput.getyMaus());
		if (sound.getHintergrundmusik()) {
			String soundPath = "Sound\\Feuerball.wav";
			sound.playSound(soundPath); 
			sound.getClip().start();
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// Getter und Setter
	public double getxPosSpieler() {
		return spieler.getxPos();
	}

	public double getyPosSpieler() {
		return spieler.getyPos();
	}

	public double getWidthSpieler() {
		return spieler.getWidth();
	}

	public double getHeightSpieler() {
		return spieler.getHeight();
	}

	public int getAnzGegner() {
		return maxGegner;
	}

	public void setAnzGegner(int pMaxGegner) {
		this.maxGegner = pMaxGegner;
	}

	public int getAnzHindernis() {
		return maxHindernis;
	}

	public void setAnzHindernis(int pMaxHindernis) {
		this.maxHindernis = pMaxHindernis;
	}

	public int getScreenwidth() {
		return screenwidth;
	}

	public int getScreenheight() {
		return screenheight;
	}

}
