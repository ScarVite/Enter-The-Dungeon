package enterTheDungeon;

import java.util.Timer;
import java.util.TimerTask;

public class Movement {

	Timer movement;

	public Movement() {

		movement = new Timer();

		movement.scheduleAtFixedRate(new TimerTask() {

			@Override

			public void run() {

				if (GUI.moveup == true) {

					if (GUI.ym > -20) {

						GUI.ym -= GUI.speedup;

					}

				} else if (GUI.movedown == true) {

					if (GUI.ym <= GUI.screenheight - 220) {

						GUI.ym += GUI.speeddown;

					}

				}

				if (GUI.moveleft == true) {

					if (GUI.xm > -120) {

						GUI.xm -= GUI.speedleft;

					}

				} else if (GUI.moveright == true) {

					if (GUI.xm <= GUI.screenwidth - 220) {

						GUI.xm += GUI.speedright;

					}

				}
				
				// Hab hier nen Bissel mit dem Schießen rum probiert aber lösen wir jetzt ja anders 

				if (GUI.shoot && GUI.moveleft == true) {

					if (GUI.xsm > -120) {

						GUI.xsm -= GUI.shootspeed;

					}

				} else if (GUI.shoot && GUI.moveright == true) {

					if (GUI.xsm <= 1700) {

						GUI.xsm += GUI.shootspeed;

					}

					if (GUI.shoot && GUI.moveup == true) {

						if (GUI.xsm > -20) {

							GUI.xsm -= GUI.shootspeed;

						}

					} else if (GUI.shoot && GUI.movedown == true) {

						if (GUI.xsm <= 860) {

							GUI.xsm += GUI.shootspeed;

						}

					}

				}

			}

		}, 0, 10);

	}

}