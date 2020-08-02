package coolboys.net;

import java.util.Timer;
import java.util.TimerTask;

public class Movement {

	private Timer movement;

	private GUI gui;

	private static int counter;

	public Movement(GUI pgui) {

		this.gui = pgui;

		movement = new Timer();

		movement.scheduleAtFixedRate(new TimerTask() {

			@Override

			public void run() {

				if (gui.isMoveup() == true) {

					if (Spieler.getyPos() > 0) {
						Spieler.setyPos(Spieler.getyPos() - gui.getSpeed());
					}
				}

				if (gui.isMovedown() == true) {

					if (Spieler.getyPos() <= gui.getScreenheight() - Spieler.getHeigth()) {
						Spieler.setyPos(Spieler.getyPos() + gui.getSpeed());
					}
				}

				if (gui.isMoveleft() == true) {

					if (Spieler.getxPos() > 0) {
						Spieler.setxPos(Spieler.getxPos() - gui.getSpeed());

					}
				}

				if (gui.isMoveright() == true) {

					if (Spieler.getxPos() <= gui.getScreenwidth() - Spieler.getWidth()) {
						Spieler.setxPos(Spieler.getxPos() + gui.getSpeed());

					}
				}

				updateAlles();

			}

		}, 0, 10);

	}

	private void updateAlles() {
		MausEingabe.update();
		Controller.update();
		Spieler.update();
		setCounter(getCounter() + 1);
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int pCounter) {
		counter = pCounter;
	}

}