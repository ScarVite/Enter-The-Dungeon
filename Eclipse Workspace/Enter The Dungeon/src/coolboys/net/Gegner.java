
package coolboys.net;

import java.awt.Graphics;

public class Gegner {

	private float xGegner;
	private float yGegner;
	private float tangente;
	private float geschwindigkeit;
	private float xZiel;
	private float yZiel;
	private int bild;
	private static int width;
	private static int heigth;
	private int feuerrate;
	private long timer;

	private GUI gui;
	private Texturen tex;

	public Gegner(float pXStart, float pYStart, float pXZiel, float pYZiel, int pfeuerrate, float geschwindigkeit,
			Texturen t) {
		this.xGegner = pXStart;
		this.yGegner = pYStart;
		this.xZiel = pXZiel;
		this.yZiel = pYZiel;
		this.feuerrate = pfeuerrate;
		this.geschwindigkeit = geschwindigkeit;

		this.tex = t;

		setWidth(95);
		setHeigth(95);

		timer = System.currentTimeMillis();

		// Berechnung für die Schusslinie

	}

	public void update() {

		width = getWidth();
		heigth = getHeigth();
		xGegner += geschwindigkeit;
//		 Umso kleiner die Feuerrate ist, desto öfter wird in einer Sekunde gefeuert;
		if (System.currentTimeMillis() - timer > feuerrate) {
			timer += feuerrate;

			xZiel = (int) Math.random() * Spieler.getxPos()
					+ (Spieler.getxPos() + Spieler.getWidth() - (int) (Math.random() * Spieler.getWidth() + 1));

			yZiel = (int) Math.random() * Spieler.getyPos()
					+ (Spieler.getyPos() + Spieler.getHeigth() - (int) (Math.random() * Spieler.getHeigth() + 1));

			Controller.addSchuss(new Schuss(xGegner + 75, yGegner + 67, xZiel, yZiel, 1, 6, tex));

			xGegner = (float) (Math.random() * 1500 + 1);
			yGegner = (float) (Math.random() * 900 + 1);
		}
	}

	public void render(Graphics g) {

		g.drawRect((int) xGegner + 10, (int) yGegner, width - 30, heigth);
		g.drawImage(tex.gegner, (int) xGegner, (int) yGegner, width, heigth, null);
	}

	public static int getWidth() {
		return width;
	}

	public void setWidth(int pWidth) {
		this.width = pWidth;
	}

	public static int getHeigth() {
		return heigth;
	}

	public void setHeigth(int pHeigth) {
		this.heigth = pHeigth;
	}

}
