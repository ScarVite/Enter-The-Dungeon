package coolboys.net;

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

			}

		}, 0, 10);

	}

}