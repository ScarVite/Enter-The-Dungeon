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
	private Schuss schuss;
	private ArrayList<Schuss> schussliste;
	private ArrayList<Gegner> gegnerliste;
	private ArrayList<Hindernis> hindernisliste;
	private Gegner gegner;
	private Sound sound;
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
		for (int i = 0; i < schussliste.size(); i++) {
			Rectangle s = schussliste.get(i).getBounds();

			for (int b = 0; b < gegnerliste.size(); b++) {
				Rectangle g = gegnerliste.get(b).getBounds();

				if (s.intersects(g)) {
					System.out.println("getroffen");
					gegnerliste.remove(b);
					schussliste.remove(i);
				}
			}
			
			for(Hindernis hindernis : hindernisliste) {
				Rectangle hindi = hindernis.getBounds();
				if(s.intersects(hindi)) {
					schussliste.remove(i);
				}
				
			}
		}
		for (Hindernis hindernis : hindernisliste) {
			Rectangle hindi = hindernis.getBounds();
			if(hindi.intersects(spC) && spieler.isDown()) {
				spieler.setyPos(spieler.getyPos() - spieler.getSpeed());
			}
			if(hindi.intersects(spC) && spieler.isUp()) {
				spieler.setyPos(spieler.getyPos() + spieler.getSpeed());
			}
			if(hindi.intersects(spC) && spieler.isLeft()) {
				spieler.setxPos(spieler.getxPos() + spieler.getSpeed());
			}
			if(hindi.intersects(spC) && spieler.isRight()) {
				spieler.setxPos(spieler.getxPos() - spieler.getSpeed());
			}
			
		}
	}

//		} else {
//			gegner.setVisible(true);
//		}
//	}

	private void init() {
		gegnerliste = new ArrayList<Gegner>();
		schussliste = new ArrayList<Schuss>();
		hindernisliste = new ArrayList<Hindernis>();
		tex = new Texturen(this);
		spieler = new Spieler(400, 400, 75, 125, 3, 3, tex);
//		gegner = new Gegner(700, 700, 65, 65, 3, 2, tex);
//		gegnerliste.add(gegner);
//		waffe = new Waffe(0, 0, 20, 20, tex);
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
		setAnzGegner(3);
		setAnzHindernis(2);
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
		sound = new Sound();
		String soundPath = "Sound\\Feuerball.wav";
		sound.playSound(soundPath);
		sound.getClip().start();
		spieler.schiessen(mausinput.getxMaus(), mausinput.getyMaus());

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
