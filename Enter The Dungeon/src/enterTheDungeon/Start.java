package enterTheDungeon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import enterTheDungeon.api.Networking;
import enterTheDungeon.api.Setup;
import enterTheDungeon.game.Mainmenu;
import enterTheDungeon.game.User;
import enterTheDungeon.resource.Filesystem;

public class Start {

	private static Filesystem filesystem = new Filesystem();

	public static void main(String args[]) {
		// Networking.login("Email", "Peter");
		initSystems();
		if (filesystem.checkForFile("/Files/User.json"))
			User.setUser(filesystem.readJsonFileasObject("/Files/User.json"));
		if (filesystem.compareFileContent("/Files/KeyValid.txt", "Hiermit-wird-das-Spiel-aktiviert")) {
			new Mainmenu();
		} else {
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					new Setup().setVisible(true);
				}
			});
		}
	}

	@SuppressWarnings("unchecked")
	private static void initSystems() {
		// Networking.checkPing();
		Filesystem.SetMainPath(System.getProperty("user.home"));
		filesystem.createFolderIfNotExist("/Files");
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		if (!filesystem.checkForFile("/Files/Settings.json")) {
			obj.put("music", true);
			filesystem.writeJsonObjectToFile("/Files/Settings.json", obj);
		}
		obj.clear();
		if (!filesystem.checkForFile("/Files/User.json")) {
			obj.put("music", true);
			filesystem.writeJsonObjectToFile("/Files/User.json", obj);
		}
		obj.clear();
		if (!filesystem.checkForFile("/Files/Save.json")) {
			filesystem.writeJsonArrayToFile("/Files/Save.json", arr);
		}
	}

}
