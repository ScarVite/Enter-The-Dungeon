package etd.dungeon.game;

public class IngameObjekt {

	protected static double x;
	protected static double y;
	protected static double speed;
	protected static double width;
	protected static double height;

	public IngameObjekt(double px, double py, double pSpeed) {
		setX(px);
		setY(py);
		setSpeed(pSpeed);
	}

	public static double getX() {
		return x;
	}

	public static void setX(double x) {
		IngameObjekt.x = x;
	}

	public static double getY() {
		return y;
	}

	public static void setY(double y) {
		IngameObjekt.y = y;
	}

	public static double getSpeed() {
		return speed;
	}

	public static void setSpeed(double speed) {
		IngameObjekt.speed = speed;
	}

	public static double getWidth() {
		return width;
	}

	public static void setWidth(double width) {
		IngameObjekt.width = width;
	}

	public static double getHeight() {
		return height;
	}

	public static void setHeight(double height) {
		IngameObjekt.height = height;
	}
	
}
