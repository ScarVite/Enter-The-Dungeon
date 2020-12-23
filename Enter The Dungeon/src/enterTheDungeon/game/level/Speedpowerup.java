package enterTheDungeon.game.level;


import java.awt.Graphics;

import enterTheDungeon.game.Game;
import enterTheDungeon.resource.Texturen;

public class Speedpowerup extends Powerup {
	private double effect;
	


	public Speedpowerup(double pX, double pY, double pWidth, double pHeight, Texturen pTex,Game pGame) {
		super(pX, pY, pWidth, pHeight, pTex, pGame);
		setPowerup(this);
	}

	public void update() {
		
	}

	public void render(Graphics g) {
		if(isVisible()) {
			g.drawImage(tex.powerUp, (int) xPos, (int) yPos, (int) width, (int) height, null);
		}
	}

	public double getEffect() {
		return effect;
	}

	public void set(double speed) {
		this.effect = speed;
	}

	@Override
	public void effect() {
		System.out.println("Powerup Aktiviert");
		game.setSpeedSpieler(5);
	}

	
}
