package enterTheDungeon.game.waffen;

import java.awt.Color;
import java.awt.Graphics;

import enterTheDungeon.game.Schuss;
import enterTheDungeon.resource.Texturen;

public class Magazin extends Waffe {

//	private Schuss[] schussarray;
	private Texturen tex;
	private Schuss schuss;
	private Waffe typ;

	public Magazin(double pX, double pY, double pWidth, double pHeight, int pMagazinsize, Waffe pTyp, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);
//		schussarray = new Schuss[pMagazinsize];
		tex = pTex;
		this.typ = pTyp;
		this.magazinsize = typ.getMagazinsize();
	}

	public void update(double pX,double pY) {
		this.xPos = pX;
		this.yPos = pY;
		schussnr = typ.getSchussnr();
		for (int i = 0; i < schussliste.size(); i++) {
			schussliste.get(i).update();
		}
	}

	public void render(Graphics g) {
		g.setColor(new Color(0).GREEN);
		int x = (int) getxPos();
		for(int i = 0; i < magazinsize - 1; i++) {
			g.setColor(new Color(0).GREEN);
			g.drawRect(x, (int) yPos, (int) width, (int) height);
//			g.setColor(new Color(0).RED);
//			g.fillRect(x, (int) yPos, (int) width, (int) height);
			x += height + 1;
		}
		x = (int) getxPos();
		for(int i = 0; i < schussnr + 1; i++) {
			g.setColor(new Color(0).RED);
			g.fillRect(x, (int) yPos, (int) width, (int) height);
			x += height + 1;
		}
//		for (int i = 0; i < schussarray.length; i++) {
//			g.setColor(new Color(0).GREEN);
//			schussarray[i].render(g);
//			g.drawRect(x, (int) yPos, 25, 25);
//			x += 26;
//		}

	}
	
//	public Schuss[] getSchuss() {
//		return schussarray;
//	}
//	
//	public void setSchuss(Schuss [] pMagazin) {
//		this.schussarray = pMagazin;
//	}

}
