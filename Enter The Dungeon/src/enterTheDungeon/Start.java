package enterTheDungeon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import enterTheDungeon.api.Networking;
import enterTheDungeon.api.ProgressBar;
import enterTheDungeon.api.Register;
import enterTheDungeon.api.Setup;
import enterTheDungeon.game.Mainmenu;
import enterTheDungeon.game.Oberklassen.User;
import enterTheDungeon.resource.Filesystem;

public class Start {

	public static void main(String args[]) {
		// Networking.login("Email", "Peter");
		Filesystem filesystem = initSystems();
		downloadFiles(filesystem);
		if (filesystem.fileNotEmpty("/Files/User.json"))
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
	private static Filesystem initSystems() {
		Networking.checkPing();
		Filesystem.SetMainPath(System.getProperty("user.home"));
		Filesystem filesystem = new Filesystem();
		filesystem.createFolderIfNotExist("/files");
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		if (!filesystem.checkForFile("/files/Settings.json")) {
			obj.put("music", true);
			filesystem.writeJsonObjectToFile("/files/Settings.json", obj);
		}
		obj.clear();
		if (!filesystem.checkForFile("/files/User.json")) {
			filesystem.createFileIfNotExist("/files/User.json");
		}
		obj.clear();
		if (!filesystem.checkForFile("/files/Save.json")) {
			filesystem.writeJsonArrayToFile("/files/Save.json", arr);
		}
		return filesystem;
	}
	
	private static void downloadFiles(Filesystem filesystem) {
		JSONObject filelist = (JSONObject) Networking.getJSONObject("https://api.scarvite.de/etd-list.json");
		JSONArray soundfiles = (JSONArray) filelist.get("sound");
		JSONArray imagefiles = (JSONArray) filelist.get("images");
		ProgressBar progress = new ProgressBar(imagefiles.size() + soundfiles.size());
		int prog = 0;
		for (Object sound : soundfiles) {
			progress.update("Downloading : " + sound.toString(), ++prog);
			if (!filesystem.checkForFile("/sound/" + sound.toString())) {
				Networking.downloadSoundandSave("https://api.scarvite.de/sound/" + sound.toString(),
						sound.toString());
			}
			progress.update(" - Done \n", -1);
		}
		for (Object image : imagefiles) {
			progress.update("Downloading : " + image.toString(), ++prog);
			if (!filesystem.checkForFile("/images/" + image.toString())) {
				filesystem.saveImage(Networking.downloadImage("https://api.scarvite.de/image/" + image.toString()), image.toString());
			}
			progress.update(" - Done \n", -1);
		}
		progress.yeet();
	}

}
