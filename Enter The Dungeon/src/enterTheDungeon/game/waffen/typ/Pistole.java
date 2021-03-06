package enterTheDungeon.game.waffen.typ;

import java.awt.Graphics;

import enterTheDungeon.game.waffen.Magazin;
import enterTheDungeon.game.waffen.Schuss;
import enterTheDungeon.game.waffen.Waffe;
import enterTheDungeon.resource.Texturen;

public class Pistole extends Waffe {

	private Schuss schuss;

	public Pistole(double pX, double pY, double pWidth, double pHeight, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);
		// TODO Auto-generated constructor stub
		setMagazinsize(13);
		setSchussarray(this.schussliste);
		setSchussnr(magazinsize - 2);
		magazin = new Magazin(30, 1000, 15, 15, magazinsize, this, pTex);
		setReichweite(500);
		setTyp(this);
//		schussarray = new Schuss [magazinsize];

	}

	public void render(Graphics g) {
		g.drawImage(tex.pistole, (int) xPos, (int) yPos, (int) width, (int) height, null);
		if (isRenderable()) {
			magazin.render(g);
		}
		for (int i = 0; i < schussliste.size(); i++) {
			schussliste.get(i).render(g);
		}

	}

	public void schiessen(double pX, double pY) {
		schussnr = getSchussnr();
		if (schussnr < 0) {
			setSchussnr(magazinsize - 1);
		}
		schuss = new Schuss(berechneXMitte(), berechneYMitte(), 25, 25, pX, pY, reichweite,tex);
		schussliste.add(schuss);
		setSchussnr(schussnr - 1);

	}
	
//	public void bossSchiessen(double x1, double x2, double y1, double y2) {
//		schussnr = getSchussnr();
//		if (schussnr < 0) {
//			setSchussnr(magazinsize - 1);
//		}
//		schuss = new Schuss(x1, y1, 25, 25, x2, y2, reichweite,tex);
//		schussliste.add(schuss);
//		setSchussnr(schussnr - 1);
//	}

	public void update(double pX, double pY) {
		xPos = pX;
		yPos = pY;
		magazin.update();
		setSchussarray(schussliste);
		for (int i = 0; i < schussliste.size(); i++) {
			if (schussliste.get(i).getBild() == 1) {
				schussliste.get(i).setBild(schussliste.get(i).getBild() + 1);
			} else {
				schussliste.get(i).setBild(1);
			}
			schussliste.get(i).update();
		}
	}

}
