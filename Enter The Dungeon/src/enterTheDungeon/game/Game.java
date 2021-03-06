package enterTheDungeon.game;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;

import enterTheDungeon.resource.*;
import enterTheDungeon.api.Popup;
import enterTheDungeon.game.level.Falle;
import enterTheDungeon.game.level.Gegner;
import enterTheDungeon.game.level.Hindernis;
import enterTheDungeon.game.level.Portal;
import enterTheDungeon.game.level.Powerup;
import enterTheDungeon.game.level.raum.RaumOberklasse;
import enterTheDungeon.game.waffen.Schuss;
import enterTheDungeon.game.waffen.Waffe;
import enterTheDungeon.input.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JPanel {

	private int screenwidth = 1920;
	private int screenheight = 1080;
	private Texturen tex;
	private Spieler spieler;
	private Timer time;
	private Render render;
	private JFrame spiel;
	private ArrayList<Schuss> schussliste;
	private ArrayList<Gegner> gegnerliste;
	private ArrayList<Hindernis> hindernisliste;
	private ArrayList<Hindernis> hindernisOben, hindernisUnten, hindernisRechts, hindernisLinks;
	private ArrayList<Portal> portalliste;
	private ArrayList<Falle> fallenliste;
	private ArrayList<Powerup> powerupliste;
	private ArrayList<RaumOberklasse> raumliste;
	private Sound sound;
	private MausInput mausinput;
	private TastaturInput tastinput;
	private Waffe waffe;
	private int maxGegner;
	private int maxHindernis;
	private boolean pause = false;
//	private Portal portal;
//	private boolean isPortal = false;
	private Pausemenu pausemenu;
	private Mainmenu mainmenu;
	private boolean pausemenuOpen;
	private RaumOberklasse raum;
	private int rNr;
	private Filesystem filesystem;

	public Game(Mainmenu pmainmenu) {
		init();
		this.mainmenu = pmainmenu;

		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			@Override

			public void run() {
				if (!isPause()) {
					update();
				}
			}

		}, 0, 10);
	}

	private void update() {
		rNr = raum.getRaumNr();
		raumliste = raum.getRaumliste();
		raumliste.get(rNr).update();
		spieler.update();
		fallenliste = raumliste.get(rNr).getFallenliste();
		powerupliste = raumliste.get(rNr).getPowerupliste();
		gegnerliste = raumliste.get(rNr).getGegnerliste();
		hindernisliste = raumliste.get(rNr).getHindernisliste();
		setHindernisOben(raumliste.get(rNr).getHindernisOben());
		setHindernisUnten(raumliste.get(rNr).getHindernisUnten());
		setHindernisRechts(raumliste.get(rNr).getHindernisRechts());
		setHindernisLinks(raumliste.get(rNr).getHindernisLinks());
		portalliste = raumliste.get(rNr).getPortalliste();

		collision();

	}

	public void render(Graphics g) {
		g.drawImage(tex.hintergrund, 0, 0, getScreenwidth(), getScreenheight(), null);
//		for (int i = 0; i < gegnerliste.size(); i++) {
//			gegnerliste.get(i).render(g);
//		}
//		for (int i = 0; i < hindernisliste.size(); i++) {
//			hindernisliste.get(i).render(g);
//		}
		raumliste.get(rNr).render(g);
		spieler.render(g);

	}

	private void collision() {
		Rectangle spielerBounds;
		spielerBounds = spieler.getBounds();
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

//			for (Gegner gegner : gegnerliste) {
//				Rectangle geg = gegner.getBounds();
//				if (hindi.intersects(geg) && gegner.isDown()) {
//					gegner.setyPos(gegner.getyPos() - gegner.getSpeed());
//				}
//				if (hindi.intersects(geg) && gegner.isUp()) {
//					gegner.setyPos(gegner.getyPos() + gegner.getSpeed());
//				}
//				if (hindi.intersects(geg) && gegner.isLeft()) {
//					gegner.setxPos(gegner.getxPos() + gegner.getSpeed());
//				}
//				if (hindi.intersects(geg) && gegner.isRight()) {
//					gegner.setxPos(gegner.getxPos() - gegner.getSpeed());
//				}
//			}

		}

		for (Powerup powerup : powerupliste) {
			Rectangle powerupRect = powerup.getBounds();
			powerup.setBounds((int) powerup.getxPos() + 10, (int) powerup.getyPos() + 10, (int) powerup.getWidth() - 20,
					(int) powerup.getHeight() - 20);
			if (powerupRect.intersects(spielerBounds) && !powerup.isPowerupAktiviert()) {
				powerup = powerup.getPowerup();
				powerup.setPowerupAktiviert(true);
				powerup.effect();
				powerup.setVisible(false);
			}
		}	

			for (Falle falle : fallenliste) {
				boolean aufgeklappt = falle.isAufgeklappt();
				boolean getroffen = spieler.isGetroffenVonFalle();
				Rectangle falleRect = falle.getBounds();
				falle.setBounds((int) falle.getxPos() + 10, (int) falle.getyPos() + 10, (int) falle.getWidth() - 20,
						(int) falle.getHeight() - 20);
				if (falleRect.intersects(spielerBounds) && !getroffen && aufgeklappt) {
					spieler.setGetroffenVonFalle(true);
					spieler.setLeben(spieler.getLeben() - 1);
				}

			}
			for (int i = 0; i < portalliste.size(); i++) {
				Rectangle portalR = portalliste.get(i).getBounds();
				if (portalR.intersects(spielerBounds)) {
					spieler.setSpeed(3);
					int maxRaum = raum.getMaxRaum() - 1;
					boolean weiter = portalliste.get(i).getWeiter();
					rNr = raum.getRaumNr();
					// naechster Raum
					if (weiter) {
						rNr++;
						raumliste.get(rNr).removePortal(portalliste.get(i));
						raum.starteRaum(rNr);
						spieler.setxPos(raumliste.get(rNr).getxSpawn());
						spieler.setyPos(raumliste.get(rNr).getySpawn());

						// vorletzter Raum
					} else if (!weiter) {
						rNr--;
						raumliste.get(rNr).removePortal(portalliste.get(i));
						raum.starteRaum(rNr);
						spieler.setxPos(raumliste.get(rNr).getxSpawn());
						spieler.setyPos(raumliste.get(rNr).getySpawn());
					}
					schussliste.clear();
					raum.clearHindernisliste();
				}

			}

			if (spieler.getLeben() <= 0) {
				beendeSpiel();
				
			}
		}

	private void collisionSchussMitObject(Rectangle spC, int waffe) {
		for (int i = 0; i < schussliste.size(); i++) {
			Rectangle s = schussliste.get(i).getBounds();
			schussOutOfBounds(i);
			for (int b = 0; b < gegnerliste.size(); b++) {
				Rectangle geg = gegnerliste.get(b).getBounds();
				// schuss und gegner Ueberschneiden && spielerwaffe dann wird gegner getroffen
				if (geg.intersects(s) && waffe == 1) {
//					gegnerliste.remove(b);
					try {

						schussliste.remove(i);

					} catch (Exception e) {
						// TODO: handle exception
					}
					gegnerliste.get(b).setLeben(gegnerliste.get(b).getLeben() - getWaffenSchaden());
					System.out.println(getWaffenSchaden());
				}

				if (gegnerliste.get(b).getLeben() <= 0) {
					raumliste.get(rNr).removeGegner(gegnerliste.get(b));
					if (gegnerliste.isEmpty()) {
						raum.setGegnerliste(gegnerliste);
						// Portal fuer das naechste Level
						int max = raum.getMaxRaum() - 1;
						int nr = raum.getRaumNr();
						Portal portal;
						if (nr < max) {
							portal = new Portal(raumliste.get(rNr).getPortalX(),raumliste.get(rNr).getPortalY() , spieler.getWidth(), spieler.getHeight(), tex);
							portal.setWeiter(true);
							raumliste.get(rNr).addPortal(portal);
						}

//						// Portal fuer das vorherige Level
//						if (nr != 0) {
//							portal = new Portal(portalX,portalY , spieler.getWidth(), spieler.getHeight(), tex);
//							portal.setWeiter(false);
//							raumliste.get(rNr).addPortal(portal);
//						}
					}
				}

			}

			// schuss und gegner Ueberschneiden && gegnerwaffe dann wird spieler getroffen

			boolean spielerGetroffen = spieler.isGetroffenVonSchuss();
			if (spC.intersects(s) && waffe == 0 && !spielerGetroffen) {
				if (!schussliste.isEmpty()) {
					try {
						schussliste.remove(i);
					} catch (Exception e) {
						// TODO: handle exception
					}

					spieler.setGetroffenVonSchuss(true);
					spieler.setLeben(spieler.getLeben() - 1);

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

	private void schussOutOfBounds(int i) {
		if (schussliste.get(i).isOutOfBounds()) {
			schussliste.remove(i);
		}
	}

	public void beendeSpiel() {
//		 Music auf mainmenu music aendern
		// Music auf mainmenu music aendern
		if (sound.getHintergrundmusik()) {
			sound.getClip().stop();
			sound.playSound(filesystem.readFile("/sound/mainmenu.wav"));
		}
		mainmenu.setSpielOffen(false);
		hindernisliste.clear();
		fallenliste.clear();
		gegnerliste.clear();
		portalliste.clear();
		spiel.setVisible(false);
		spiel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	private void init() {
		filesystem = new Filesystem();
		sound = new Sound();
		if (sound.getHintergrundmusik()) {
			sound.playSound(filesystem.readFile("/sound/background.wav"));
			sound.getClip().loop(Clip.LOOP_CONTINUOUSLY);
		}
		gegnerliste = new ArrayList<Gegner>();
		schussliste = new ArrayList<Schuss>();
		powerupliste = new ArrayList<Powerup>();
		hindernisliste = new ArrayList<Hindernis>();
		hindernisOben = new ArrayList<Hindernis>();
		hindernisUnten = new ArrayList<Hindernis>();
		hindernisRechts = new ArrayList<Hindernis>();
		hindernisLinks = new ArrayList<Hindernis>();
		pausemenuOpen = true;
//		gegner = new Gegner(0, 0, 30, 30, 3, 3, tex, this);
		tex = new Texturen(this);
		spieler = new Spieler(200, 400, 35, 60, 6, 3, tex);
//		levelcreator = new LevelCreator(this, tex);
		raum = new RaumOberklasse(this, tex);
		mausinput = new MausInput(this);
		tastinput = new TastaturInput(this, pausemenu);
		spiel = new JFrame("Enter the Dungeon");

//		spiel.setExtendedState(JFrame.MAXIMIZED_BOTH);
		spiel.setBounds(0, 0, 1920, 1080);
		spiel.setFocusable(true);
		spiel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		spiel.setLocationRelativeTo(null);
		spiel.addKeyListener(tastinput);
		spiel.addMouseListener(mausinput);
		render = new Render(this);
//		render.setBounds(0, 0, screenwidth, screenheight);
		spiel.add(render);

		spiel.setVisible(true);

		baueLevel();

	}

	public void baueLevel() {
		Rectangle spielerBounds = spieler.getBounds();
		raum.clearHindernisliste();
		schussliste.clear();
		raum.erstelleLevel();
		raum.starteRaum(0);
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
		if (isPausemenuOpen()) {
			if (key == KeyEvent.VK_ESCAPE) {
				pausemenu = new Pausemenu(mainmenu, this);
				pause = !pause;
				setPausemenuOpen(false);
				if (sound.getHintergrundmusik()) {
					sound.getClip().stop();
				}
			}
		}
		if (pausemenu != null) {
			if (pausemenu.isSound() && sound.getHintergrundmusik()) {
				sound.getClip().start();
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
			sound.playSound(filesystem.readFile("/sound/feuerball-fixed.wav"));
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
	
	public int getSpielerLeben(){
		return spieler.getLeben();
	}
	public void setSpielerLeben(int leben) {
		spieler.setLeben(leben);
	}
	public int getWaffenSchaden() {
		waffe = spieler.getWaffe();
		return waffe.getSchaden();
	}
	
	public void setWaffenSchaden(int schaden) {
		System.out.println(schaden);
		waffe = spieler.getWaffe();
		waffe.setSchaden(schaden);
	}
	
	public double getSpeedSpieler() {
		return spieler.getSpeed();
	}
	public void setSpeedSpieler(double speed) {
		spieler.setSpeed(speed);
	}
	

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

	public Rectangle getSpielerBounds() {
		return spieler.getBounds();
	}

	public ArrayList<Hindernis> getHindernisListe() {
		return hindernisliste;
	}

	public ArrayList<Hindernis> getHindernisliste() {
		return hindernisliste;
	}

	public void setHindernisliste(ArrayList<Hindernis> hindernisliste) {
		this.hindernisliste = hindernisliste;
	}

	public ArrayList<Hindernis> getHindernisOben() {
		return hindernisOben;
	}

	public void setHindernisOben(ArrayList<Hindernis> hindernisOben) {
		this.hindernisOben = hindernisOben;
	}

	public ArrayList<Hindernis> getHindernisRechts() {
		return hindernisRechts;
	}

	public void setHindernisRechts(ArrayList<Hindernis> hindernisRechts) {
		this.hindernisRechts = hindernisRechts;
	}

	public ArrayList<Hindernis> getHindernisLinks() {
		return hindernisLinks;
	}

	public void setHindernisLinks(ArrayList<Hindernis> hindernisLinks) {
		this.hindernisLinks = hindernisLinks;
	}

	public ArrayList<Hindernis> getHindernisUnten() {
		return hindernisUnten;
	}

	public void setHindernisUnten(ArrayList<Hindernis> hindernisUnten) {
		this.hindernisUnten = hindernisUnten;
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

	public Rectangle spielerBounds() {
		return spieler.getBounds();
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

	public boolean isPausemenuOpen() {
		return pausemenuOpen;
	}

	public void setPausemenuOpen(boolean pausemenuopen) {
		pausemenuOpen = pausemenuopen;
	}

	public ArrayList<Powerup> getPowerupliste() {
		return powerupliste;
	}

	public void setPowerupliste(ArrayList<Powerup> powerupliste) {
		this.powerupliste = powerupliste;
	}

}
