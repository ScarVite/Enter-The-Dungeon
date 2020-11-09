package etd.dungeon.game;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import etd.dungeon.resource.Texturen;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JPanel {

	private static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenwidth = (int) screensize.getWidth();
	private int screenheight = (int) screensize.getHeight();
	private Texturen tex;
	private Spieler spieler;
	private Timer time;
	private Draw draw;
	private JFrame spiel;
	private Schuss schuss;
	private Controller c;

	public Game() {
		init();
			
		for (int i = 0; i < 5; i++) {
			Controller.addObjekt(new Gegner(tex,  i * 400, 40, 3));
		}
		

		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			@Override

			public void run() {
				spieler.laufen();
				spieler.update();
				c.update();
			}

		}, 0, 10);
	}

	public void render(Graphics g) {
		g.drawImage(tex.hintergrund, 0, 0, getScreenwidth(), getScreenheight(), null);
		spieler.render(g);
		c.render(g);
	}

	private void init() {
		tex = new Texturen(this);
		c = new Controller(tex);
		spieler = new Spieler(500, 500, 3, tex);
		spiel = new JFrame("Enter the Dungeon");

		spiel.setExtendedState(JFrame.MAXIMIZED_BOTH);
		spiel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		spiel.setLocationRelativeTo(null);
		spiel.addKeyListener(new TastaturInput(this));
		spiel.addMouseListener(new MausInput(this));
		draw = new Draw(this);
		draw.setBounds(0, 0, screenwidth, screenheight);
		spiel.add(draw);

		spiel.setVisible(true);
		
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
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		spieler.schiessen();
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public int getScreenwidth() {
		return screenwidth;
	}

	public int getScreenheight() {
		return screenheight;
	}

}
