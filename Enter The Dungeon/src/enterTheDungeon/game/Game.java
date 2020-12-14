package enterTheDungeon.game;

import javax.swing.JFrame;
import javax.swing.JPanel;

import enterTheDungeon.resource.*;
import enterTheDungeon.game.level.LevelCreator;
import enterTheDungeon.game.level.Portal;
import enterTheDungeon.game.waffen.Waffe;
import enterTheDungeon.input.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
	private ArrayList<Portal> portalliste;
	private Sound sound;
	private MausInput mausinput;
	private TastaturInput tastinput;
	private Waffe waffe;
	private LevelCreator levelcreator;
	private int maxGegner;
	private int maxHindernis;
	private boolean pause = false;
//	private Portal portal;
//	private boolean isPortal = false;
	private Pausemenu pausemenu;
	private Mainmenu mainmenu;

	public Game(Mainmenu pmainmenu) {
		init();
		this.mainmenu = pmainmenu;
		for (int i = 0; i < 5; i++) {
		}

		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			@Override

			public void run() {
				if(!isPause()) {
					update();
				}
			}

		}, 0, 10);
	}

	private void update() {
		spieler.update();
		gegnerliste = levelcreator.getGegnerliste();
		hindernisliste = levelcreator.getHindernisliste();
		portalliste = levelcreator.getPortalliste();
		for (Gegner gegner : gegnerliste) {
			gegner.update();
		}
		for (Hindernis hindernis : hindernisliste) {
			hindernis.update();
		}
		for(Portal portal : portalliste) {
			portal.update();
		}
		collision();

	}

	public void render(Graphics g) {
		g.drawImage(tex.hintergrund, 0, 0, getScreenwidth(), getScreenheight(), null);
		for (int i = 0; i < gegnerliste.size(); i++) {
			gegnerliste.get(i).render(g);
		}
		for (int i = 0; i < hindernisliste.size(); i++) {
			hindernisliste.get(i).render(g);
		}
		for (int i = 0; i < portalliste.size(); i++) {
			portalliste.get(i).render(g);
		}
		spieler.render(g);
	}

	private void collision() {
		Rectangle spielerBounds = spieler.getBounds();
		waffe = spieler.getWaffe();
		schussliste = waffe.getSchussarray();
		collisionSchussMitObject(spielerBounds, 1);

		for (Gegner gegner : gegnerliste) {
			waffe = gegner.getWaffe();
			schussliste = waffe.getSchussarray();
			collisionSchussMitObject(spielerBounds, 0);
		}

		for (Hindernis hindernis : hindernisliste) {
			Rectangle hindi = hindernis.getBounds();
			if (hindi.intersects(spielerBounds) && spieler.isDown()) {
				spieler.setyPos(spieler.getyPos() - spieler.getSpeed());
			}
			if (hindi.intersects(spielerBounds) && spieler.isUp()) {
				spieler.setyPos(spieler.getyPos() + spieler.getSpeed());
			}
			if (hindi.intersects(spielerBounds) && spieler.isLeft()) {
				spieler.setxPos(spieler.getxPos() + spieler.getSpeed());
			}
			if (hindi.intersects(spielerBounds) && spieler.isRight()) {
				spieler.setxPos(spieler.getxPos() - spieler.getSpeed());
			}

		}
		
		for(int i = 0; i < portalliste.size(); i++) {
			Rectangle portalR = portalliste.get(i).getBounds();
			if(portalR.intersects(spielerBounds)) {
//				portalliste.remove(i);
				levelcreator.removePortal(portalliste.get(i));
				baueLevel();
			}
		}
	}

	private void collisionSchussMitObject(Rectangle spC, int waffe) {
		for (int i = 0; i < schussliste.size(); i++) {
			Rectangle s = schussliste.get(i).getBounds();

			for (int b = 0; b < gegnerliste.size(); b++) {
				Rectangle g = gegnerliste.get(b).getBounds();

				// schuss und gegner überschneiden && spielerwaffe dann wird gegner getroffen
				if (g.intersects(s) && waffe == 1) {
//					gegnerliste.remove(b);
					gegnerliste.get(b).setLeben(gegnerliste.get(b).getLeben() - 1);
					try {
						schussliste.remove(i);
					} catch (Exception e) {
						System.out.println("Fehler1");
					}
				}

				// schuss und gegner überschneiden && gegnerwaffe dann wird spieler getroffen
				if (spC.intersects(s) && waffe == 0) {
					if (!schussliste.isEmpty()) {
						try {
							schussliste.remove(i);
						} catch (Exception e) {
							System.out.println("Fehler");
						}
						spieler.setLeben(spieler.getLeben() - 1);
						if (spieler.getLeben() == 0) {
							beendeSpiel();
						}
					}
				}

				if (gegnerliste.get(b).getLeben() == 0) {
					levelcreator.removeGegner(gegnerliste.get(b));
					if (gegnerliste.isEmpty()) {
						levelcreator.addPortal(new Portal(50, 50, spieler.getWidth(), spieler.getHeight(), tex));
					}
				}

			}

			for (int j = 0; j < hindernisliste.size(); j++) {
				Rectangle hindi = hindernisliste.get(j).getBounds();
				if (!schussliste.isEmpty()) {
					if (s.intersects(hindi)) {
						try {
							schussliste.remove(i);
						} catch (Exception e) {
							System.out.println("Fehler");
						}
					}
				}
			}
		}

	}

	private void beendeSpiel() {
		// Music auf mainmenu music �ndern
		spiel.setVisible(false);
		spiel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		System.out.println("Spieler tot");
	}

	private void init() {
		sound = new Sound();
		gegnerliste = new ArrayList<Gegner>();
		schussliste = new ArrayList<Schuss>();
		hindernisliste = new ArrayList<Hindernis>();
//		gegner = new Gegner(0, 0, 30, 30, 3, 3, tex, this);
		tex = new Texturen(this);
		spieler = new Spieler(400, 400, 35, 60, 3, 3, tex);
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
	
	public boolean levelCreator(){
		boolean collision = false;
		gegnerliste = levelcreator.getGegnerliste();
		hindernisliste = levelcreator.getHindernisliste();
		for(Hindernis hindernis: hindernisliste) {
			Rectangle hindi = hindernis.getBounds();
			for(Gegner gegner : gegnerliste) {
				Rectangle geg = gegner.getBounds();
			}
		}
		
		return collision;
	}

	public void baueLevel() {
		Rectangle spielerBounds = spieler.getBounds();
		levelcreator.clearHindernisliste();
		schussliste.clear();
		setAnzGegner(2);
		setAnzHindernis(5);
		levelcreator.setSpielerRect(spielerBounds);
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
			pausemenu = new Pausemenu(mainmenu, null);
			setPause(!isPause());
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
	
	public Rectangle spielerBounds() {
		return spieler.getBounds();
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
	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

}
