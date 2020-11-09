package etd.dungeon.game.interfaces;

import java.awt.Graphics;

public interface Objekt {

	public void update();
	public void render(Graphics g);
	
	public double getX();
	public double getY();
	
	public double getWidth();
	public double getHeight();

}
