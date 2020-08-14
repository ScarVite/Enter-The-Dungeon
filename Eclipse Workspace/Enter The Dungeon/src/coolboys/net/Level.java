package coolboys.net;

public class Level {
	int Difficulty;
	int Number;
	int EnemyNumbers;
	int rooms;
	Boolean Boss = false;
	String Name;
	
	Level(int Difficulty, int Number) {
		this.Difficulty = Difficulty;
		this.Number = Number;
		this.EnemyNumbers = (Difficulty * Number)/(Number / Difficulty);
		switch(Difficulty) {
			case 1: 
				if(Number % 20 == 0) this.Boss = true;
				break;
			case 2:
				break;
			case 3:
				break;
			default:
				System.out.println("Something went Wrong While Creating a Level.");
				System.out.println("Difficulty not Found");
				System.exit(2);
				break;
		}
	}
}
/* Als Rückgabe Mehr Dimensionales Array, mit dem man dann das level kreieren kann
 * [][1][5]
 * XXXOX_XXXXX_OXXX
 * XXXXXOXX____XXXX
 * XXXXXXXXOX__XXXX
*/