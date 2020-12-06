package enterTheDungeon.game.waffen.typ;

import java.awt.Graphics;

import enterTheDungeon.game.Schuss;
import enterTheDungeon.game.waffen.Magazin;
import enterTheDungeon.game.waffen.Waffe;
import enterTheDungeon.resource.Texturen;

public class Pistole extends Waffe {

	private Pistole pistole;
	private Schuss schuss;

	public Pistole(double pX, double pY, double pWidth, double pHeight, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);
		// TODO Auto-generated constructor stub
		setMagazinsize(13);
		setSchussarray(this.schussliste);
		setSchussnr(magazinsize - 2);
		magazin = new Magazin(pX, pY, 10, 10, magazinsize, this, pTex);

		setTyp(this);
//		schussarray = new Schuss [magazinsize];

	}

	public void render(Graphics g) {
		if (isVisible()) {
			g.drawImage(tex.pistole, (int) xPos, (int) yPos, (int) width, (int) height, null);
			magazin.render(g);
			for (int i = 0; i < schussliste.size(); i++) {
				schussliste.get(i).render(g);
			}
		}

	}

	public void schiessen(double pX, double pY) {
		schussnr = getSchussnr();
		if (schussnr < 0) {
			setSchussnr(magazinsize - 1);
		}
		schuss = new Schuss(berechneXMitte(), berechneYMitte(), 25, 25, pX, pY, tex);
		schussliste.add(schuss);
		setSchussnr(schussnr - 1);

	}

	public void update(double pX, double pY) {
		xPos = pX;
		yPos = pY;
		magazin.update(xPos, yPos - 20);
		for (Schuss schuss: schussliste) {
			if (schuss.getBild() == 1) {
				schuss.setBild(2);
			} else {
				schuss.setBild(1);
			}

			schuss.update();

		}
	}

}
