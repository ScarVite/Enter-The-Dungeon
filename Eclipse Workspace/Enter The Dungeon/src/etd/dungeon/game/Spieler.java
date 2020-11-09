package etd.dungeon.game;

import java.awt.Graphics;

import etd.dungeon.game.interfaces.Objekt;
import etd.dungeon.resource.Texturen;

public class Spieler extends IngameObjekt{

	private static Texturen tex;

	private static Spieler spieler;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private MausInput maus;

	Spieler(double x, double y, double speed, Texturen pTex) {
		super(x, y, speed);
		this.tex = pTex;
		setWidth(70);
		setHeight(125);
	}

	public void update() {
		x = getX();
		y = getY();
		speed = getSpeed();
	}

	public void render(Graphics g) {
		g.drawImage(tex.spieler, (int) x, (int) y, (int) width, (int) height, null);
	}
	
	public void schiessen() {
		Controller.addObjekt(new Schuss(spieler.getX(), spieler.getY(), MausInput.getxMaus(), MausInput.getyMaus(), 2.5, tex));
	}

	public void laufen() {
		if (isLeft()) {
			setX(getX() - getSpeed());
		}

		if (isRight()) {
			setX(getX() + getSpeed());
		}

		if (isUp()) {
			setY(getY() - getSpeed());
		}

		if (isDown()) {
			setY(getY() + getSpeed());
		}
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean pLeft) {
		this.left = pLeft;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean pRight) {
		this.right = pRight;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean pUp) {
		this.up = pUp;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean pDown) {
		this.down = pDown;
	}

}
