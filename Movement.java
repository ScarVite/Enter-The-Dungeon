package EnterTheDungeon;

import java.util.Timer;
import java.util.TimerTask;

public class Movement {

	Timer movement;

	public Movement() {
		movement = new Timer();

		movement.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {

				if (Var.moveup == true) {
					if (Var.ym > -20) {
						Var.ym -= Var.speedup;
					}
				} else if (Var.movedown == true) {
					if (Var.ym <= 860) {
						Var.ym += Var.speeddown;
					}
				}

				if (Var.moveleft == true) {
					if (Var.xm > -120) {
						Var.xm -= Var.speedleft;
					}
				} else if (Var.moveright == true) {
					if (Var.xm <= 1700) {
						Var.xm += Var.speedright;
					}
				}

				if (Var.shoot && Var.moveleft == true) {
					if (Var.xsm > -120) {
						Var.xsm -= Var.shootspeed;
					}
				} else if (Var.shoot && Var.moveright == true) {
					if (Var.xsm <= 1700) {
						Var.xsm += Var.shootspeed;
					}
					if (Var.shoot && Var.moveup == true) {
						if (Var.xsm > -20) {
							Var.xsm -= Var.shootspeed;
						}
					} else if (Var.shoot && Var.movedown == true) {
						if (Var.xsm <= 860) {
							Var.xsm += Var.shootspeed;
						}

					}
				}
			
			}
		}, 0, 10);
	
	}

}


