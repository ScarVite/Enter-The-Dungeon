package EnterTheDungeon;

import java.util.Timer;
import java.util.TimerTask;

public class Shooting {

	Timer shooting;

	public Shooting() {
		shooting = new Timer();

		shooting.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {

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
	