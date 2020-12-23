package enterTheDungeon.game.level;


import java.awt.Graphics;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.Oberklassen.StandardObjectData;
import enterTheDungeon.resource.Texturen;

public abstract class Powerup extends StandardObjectData {
	private boolean powerupAktiviert;
	private Powerup powerup;
	private boolean visible;

	protected Game game;



	public Powerup(double pX, double pY, double pWidth, double pHeight, Texturen pTex,Game pGame) {
		super(pX, pY, pWidth, pHeight, pTex);
		this.game = pGame;
		setVisible(true);
	}

	public void update() {
			
	}

	public void render(Graphics g) {
	}
	
	public abstract void effect();
	
	public boolean isPowerupAktiviert() {
		return powerupAktiviert;
	}

	public void setPowerupAktiviert(boolean powerupAktiviert) {
		this.powerupAktiviert = powerupAktiviert;
	}
	
	public Powerup getPowerup() {
		return powerup;
	}

	public void setPowerup(Powerup powerup) {
		this.powerup = powerup;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
