package enterTheDungeon.game.Oberklassen;

import java.awt.Rectangle;

import enterTheDungeon.resource.Texturen;

public class ExtendedObjectData extends StandardObjectData {

	protected int leben;
	protected double speed;
	private boolean right, left, up, down;

	public ExtendedObjectData(double pX, double pY, double pWidth, double pHeight, int pLeben, int pSpeed,
			Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);
		this.leben = pLeben;
		this.speed = pSpeed;
	}
	
	

	public int getLeben() {
		return leben;
	}

	public void setLeben(int leben) {
		this.leben = leben;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

}
