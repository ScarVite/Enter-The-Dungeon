package enterTheDungeon.game.waffen.typ;

import java.awt.Graphics;

import enterTheDungeon.game.Magazin;
import enterTheDungeon.game.Schuss;
import enterTheDungeon.game.waffen.Waffe;
import enterTheDungeon.resource.Texturen;

public class Pistole extends Waffe {

	private Pistole pistole;
	private Schuss schuss;

	public Pistole(double pX, double pY, double pWidth, double pHeight,Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);
		// TODO Auto-generated constructor stub
		setMagazinsize(13);
		schussarray = new Schuss [magazinsize];
		for (int i = 0; i < schussarray.length; i++) {
//			schuss = new Schuss(-100, -100, 25, 25, xPos, yPos, tex);
			schuss = new Schuss(1000, 1000, 25, 25, 1200, 1200, tex);
			schussarray[i] = schuss;
			
			xPos += 26;
		}
		setSchussnr(magazinsize - 2);
		magazin = new Magazin(pX, pY, 10, 10, magazinsize, this, pTex);
//		schussarray = new Schuss [magazinsize];

	}

	public void render(Graphics g) {
		g.drawImage(tex.pistole, (int) xPos, (int) yPos, (int) width, (int) height, null);
		magazin.render(g);
		for (int i = 0; i < schussarray.length; i++) {
			schussarray[i].render(g);
		}

	}

	public void schiessen(double pX, double pY) {
		schussnr = getSchussnr();
		if (schussnr < 0) {
			setSchussnr(magazinsize - 1);
		}
		schuss = new Schuss(berechneXMitte(), berechneYMitte(), 25, 25, pX, pY, tex);
		schussarray[schussnr] = schuss;
		setSchussnr(schussnr - 1);
		
	}

	public void update(double pX, double pY) {
		xPos = pX;
		yPos = pY;
		magazin.update(xPos, yPos - 20);
		for (int i = 0; i < schussarray.length; i++) {
			schussarray[i].update();
		}
	}

}
