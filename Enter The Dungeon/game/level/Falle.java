package enterTheDungeon.game.level;

import java.awt.Graphics;

import enterTheDungeon.game.Oberklassen.StandardObjectData;
import enterTheDungeon.resource.Texturen;

public class Falle extends StandardObjectData {

	private int animation = 1;
	private int ctr = 0;
	private boolean aufgeklappt = false; 

	public Falle(double pX, double pY, double pWidth, double pHeight, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);
		// TODO Auto-generated constructor stub
	}

	public void update() {

		ctr++;
			if (ctr >= 200) {
				setAnimation(getAnimation() + 1);
				ctr = 0;
			}
			if (animation == 5) {
				ctr = 0;
				setAnimation(1);
			}
			
			if(animation == 3) {
				setAufgeklappt(true);
			}
			else {
				setAufgeklappt(false);
			}
			
	}

	public void render(Graphics g) {
		if (animation == 1) {
			g.drawImage(tex.falleZU, (int) xPos, (int) yPos, (int) width, (int) height, null);
		}
		if (animation == 2 || animation == 4) {
			g.drawImage(tex.falleBereit, (int) xPos, (int) yPos, (int) width, (int) height, null);
		}
		if (animation == 3) {
			g.drawImage(tex.falleOffen, (int) xPos, (int) yPos, (int) width, (int) height, null);
		}
	}

	private int getAnimation() {
		return animation;
	}

	public void setAnimation(int pAnimation) {
		this.animation = pAnimation;
	}

	public boolean isAufgeklappt() {
		return aufgeklappt;
	}

	public void setAufgeklappt(boolean aufgeklappt) {
		this.aufgeklappt = aufgeklappt;
	}

}
