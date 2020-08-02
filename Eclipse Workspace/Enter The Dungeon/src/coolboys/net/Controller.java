package coolboys.net;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {

	private static LinkedList<Schuss> schussliste = new LinkedList<Schuss>();
	private static LinkedList<CollisionDetection> collisionliste = new LinkedList<CollisionDetection>();
	private static LinkedList<Gegner> gegnerliste = new LinkedList<Gegner>();
	private static CollisionDetection collision;
	private static Gegner gegner;
	private static Schuss schuss;
	private GUI gui;
	private Texturen tex;

	public Controller(GUI gui, Texturen t) {
		this.gui = gui;
		this.tex = t;
		
		for(int x = 0; x < gui.getScreenwidth() - 95; x += (int) (Math.random() * 300 + 160)) {
			addGegner(new Gegner(x, 0, 1000, 800, (int) (Math.random() * 4000 + 900) , 1, tex));
		}

	}

	public static void update() {

		for (int i = 0; i < schussliste.size(); i++) {
			schuss = schussliste.get(i);
			schuss.update();
		}

		for (int i = 0; i < gegnerliste.size(); i++) {
			gegner = gegnerliste.get(i);
			gegner.update();
		}

	}

//		for (int i = 0; i < collisionliste.size(); i++) {
//			collision = collisionliste.get(i);
//			collision.update();
//		}

	public static void render(Graphics g) {

		for (int i = 0; i < schussliste.size(); i++) {
			schuss = schussliste.get(i);
			schuss.render(g);
		}
		
		for(int i = 0; i < gegnerliste.size(); i++) {
			gegner = gegnerliste.get(i);
			gegner.render(g);
		}
	}

	// Schuss hinzufügen
	public static void addSchuss(Schuss pBullet) {
		schussliste.add(pBullet);
	}

	// Schuss entfernen
	public void removeSchuss(Schuss pBullet) {
		schussliste.remove(pBullet);
	}

	// Gegner hinzufügen
	public static void addGegner(Gegner pGegner) {
		gegnerliste.add(pGegner);
	}

	// Gegner entfernen
	public void removeGegner(Gegner pGegner) {
		gegnerliste.remove(pGegner);
	}

	public static void addCollision(CollisionDetection pCollision) {
		collisionliste.add(pCollision);
	}

	public static void removeCollision(CollisionDetection pCollision) {
		collisionliste.remove(pCollision);
	}

	// Alle Schüsse entfernen
	public void removeAlleSchuss(Schuss pBullet) {
		for (int i = 0; i < gegnerliste.size(); i++) {
			removeAlleSchuss(pBullet);
		}
	}

	// Alle Gegner entfernen
	public void removeAlleGegner(Gegner pGegner) {
		for (int i = 0; i < gegnerliste.size(); i++) {
			removeGegner(pGegner);
		}
	}

}
