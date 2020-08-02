package coolboys.net;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel {

	private JFrame spielFrame;
	private Draw draw;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static int screenwidth = (int) screenSize.getWidth();
	private static int screenheight = (int) screenSize.getHeight();
	private int x;
	private int y;
	private static int ym = screenheight / 2 - 100;
	private static int xm = screenwidth / 2 - 150;
	private Label Label1;
	public static BufferedImage background;
	private BufferedImage character;
	private BufferedImage schuss;
	private BufferedImage gegner;
	private int speed = 3;
	private boolean moveup = false;
	private boolean movedown = false;
	private boolean moveleft = false;
	private boolean moveright = false;
	private boolean shoot = false;
	private static Label coor;
	private static GUI gui;
	private Movement move;
	private Controller controller;
	private Texturen tex;

	public GUI() {

		tex = new Texturen(this);

		controller = new Controller(this, tex);

		new Spieler(500, 500, tex);

		move = new Movement(this);

		spielFrame = new JFrame();
		spielFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		spielFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		spielFrame.setLocationRelativeTo(null);

		draw = new Draw(this);
		draw.setBounds(0, 0, screenwidth, screenheight);
		draw.setVisible(true);
		spielFrame.add(draw);

		spielFrame.addKeyListener(new TastenEingabe(this));

		try {
			spielFrame.addMouseListener(new MausEingabe(this));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		spielFrame.setVisible(true);

		try {
			background = ImageIO.read(new File("Bilder/background.png"));
			spielFrame.addMouseListener(new MouseAdapter() {
			});
		} catch (IOException IO) {
			IO.printStackTrace();
			System.out.println("Bild konnte nicht geladen werden");
		}

	}

	// Input
	// Maus

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		String soundPath = "Sound\\feuerball.wav";
		Sound sound = new Sound();
		Sound.playSound(soundPath);
	}

	public void mouseReleased(MouseEvent e) {
		Controller.addSchuss(new Schuss(Spieler.getxPos() + 28, Spieler.getyPos() + 104,
				MausEingabe.getxPosMouse() - 25, MausEingabe.getyPosMouse() - 20, 0, 7, tex));
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	// Tastatur

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_W) {
			this.setMoveup(true);
		}

		if (e.getKeyCode() == KeyEvent.VK_S) {
			this.setMovedown(true);
		}

		if (e.getKeyCode() == KeyEvent.VK_D) {
			this.setMoveright(true);
		}

		if (e.getKeyCode() == KeyEvent.VK_A) {
			this.setMoveleft(true);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			this.setMoveup(false);
		}

		if (e.getKeyCode() == KeyEvent.VK_S) {
			this.setMovedown(false);
		}

		if (e.getKeyCode() == KeyEvent.VK_D) {
			this.setMoveright(false);
		}

		if (e.getKeyCode() == KeyEvent.VK_A) {
			this.setMoveleft(false);
		}
	}

	// getter und setter
	public static int getXPlayer() {
		return xm;
	}

	public static int getYPlayer() {
		return ym;
	}

	public void setXPlayer(int pxPlayer) {
		xm = pxPlayer;
	}

	public void setYPlayer(int pyPlayer) {
		ym = pyPlayer;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public static int getYm() {
		return ym;
	}

	public void setYm(int ym) {
		this.ym = ym;
	}

	public static int getXm() {
		return xm;
	}

	public void setXm(int xm) {
		this.xm = xm;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isMoveup() {
		return moveup;
	}

	public void setMoveup(boolean moveup) {
		this.moveup = moveup;
	}

	public boolean isMovedown() {
		return movedown;
	}

	public void setMovedown(boolean movedown) {
		this.movedown = movedown;
	}

	public boolean isMoveleft() {
		return moveleft;
	}

	public void setMoveleft(boolean moveleft) {
		this.moveleft = moveleft;
	}

	public boolean isMoveright() {
		return moveright;
	}

	public void setMoveright(boolean moveright) {
		this.moveright = moveright;
	}

	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	public int getScreenwidth() {
		return screenwidth;
	}

	public void setScreenwidth(int screenwidth) {
		this.screenwidth = screenwidth;
	}

	public int getScreenheight() {
		return screenheight;
	}

	public void setScreenheight(int screenheight) {
		this.screenheight = screenheight;
	}

	// public BufferedImage getBackground() {
	// return background;
	// }

	public void setBackground(BufferedImage background) {
		this.background = background;
	}

	public BufferedImage getCharacter() {
		return character;
	}

	public void setCharacter(BufferedImage character) {
		this.character = character;
	}

	public BufferedImage getSchuss() {
		return schuss;
	}

	public void setSchuss(BufferedImage bullet) {
		this.schuss = bullet;
	}

	public BufferedImage getGegner() {
		return gegner;
	}

	public void setGegner(BufferedImage gegner) {
		this.gegner = gegner;
	}

}
