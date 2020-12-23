package enterTheDungeon.game.waffen;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Graphics;

import enterTheDungeon.game.Oberklassen.StandardObjectData;
import enterTheDungeon.game.waffen.typ.Pistole;
import enterTheDungeon.resource.Texturen;

public class Waffe extends StandardObjectData {

	private Schuss schuss;
	protected Magazin magazin;
	private Waffe typ;
	protected int magazinsize;
	protected int schaden;
	protected int feuerrate;
	protected int schussnr;
	protected ArrayList<Schuss> schussliste;
	protected boolean renderable;
	protected int reichweite;

	public Waffe(double pX, double pY, double pWidth, double pHeight, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);

		schussliste = new ArrayList<Schuss>();
	}

//	public void createWaffe(Pistole pistole) {
//		this.typ = pistole;
//	}

	public int getReichweite() {
		return reichweite;
	}

	public void setReichweite(int reichweite) {
		this.reichweite = reichweite;
	}

	// Getter und Setter
	public ArrayList<Schuss> getSchussarray() {
		return schussliste;
	}

	public void setSchussarray(ArrayList<Schuss> pschusslist) {
		this.schussliste = pschusslist;
	}

	public int getSchussnr() {
		return schussnr;
	}

	public void setSchussnr(int pSchussnr) {
		this.schussnr = pSchussnr;
	}

	public int getMagazinsize() {
		return magazinsize;
	}

	public void setMagazinsize(int magazinsize) {
		this.magazinsize = magazinsize;
	}

	public int getSchaden() {
		return schaden;
	}

	public void setSchaden(int schaden) {
		this.schaden = schaden;
	}

	public int getFeuerrate() {
		return feuerrate;
	}

	public void setFeuerrate(int feuerrate) {
		this.feuerrate = feuerrate;
	}

	public boolean isRenderable() {
		return renderable;
	}

	public void setRenderable(boolean renderable) {
		this.renderable = renderable;
	}

	public Waffe getTyp() {
		return typ;
	}

	public void setTyp(Waffe typ) {
		this.typ = typ;
	}
}
