package etd.dungeon.game;

import java.awt.Graphics;
import java.util.LinkedList;

import etd.dungeon.game.interfaces.Objekt;
import etd.dungeon.resource.Texturen;

public class Controller {

	private static LinkedList<Objekt> controllerList = new LinkedList<Objekt>();

	private Texturen tex;
	private static Objekt oc;

	public Controller(Texturen t) {
		this.tex = t;

	}

	public static void update() {
		for (int i = 0; i < controllerList.size(); i++) {
			oc = controllerList.get(i);
			oc.update();
		}
	}

	public static void render(Graphics g) {
		for (int i = 0; i < controllerList.size(); i++) {
			oc = controllerList.get(i);
			oc.render(g);
		}
	}

	public static void addObjekt(Objekt pOc) {
		controllerList.add(pOc);
	}

	public static void removeObjekt(Objekt pOc) {
		controllerList.add(pOc);
	}
}
