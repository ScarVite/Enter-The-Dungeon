package enterTheDungeon.resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

public class Filesystem {

	private static File mainPath = new File(System.getProperty("user.home"));

	public JSONObject readJsonFileasObject(String path) {
		File file = new File(mainPath, path);
		boolean empty = file.exists() && file.length() == 0;
		JSONParser parser = new JSONParser();
		System.out.println(empty);
		if (!empty) {
			try {
				Object obj = parser.parse(new FileReader(mainPath + path));
				JSONObject jsonObject = (JSONObject) obj;
				System.out.println(jsonObject);
				return jsonObject;
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public JSONArray readJsonFileasArray(String path) {
		File file = new File(mainPath, path);
		boolean empty = file.exists() && file.length() == 0;
		JSONParser parser = new JSONParser();
		if (!empty)
			try {
				Object obj = parser.parse(new FileReader(mainPath + path));
				JSONArray jsonArray = (JSONArray) obj;
				return jsonArray;
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}

	public boolean checkIfJsonArrayOrObject(String path) {
		File file = new File(mainPath, path);
		boolean empty = file.exists() && file.length() == 0;
		JSONParser parser = new JSONParser();
		if (!empty)
			try {
				Object obj = parser.parse(new FileReader(mainPath + path));
				return (obj instanceof JSONArray);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
	}

	public void createFolderIfNotExist(String path) {
		File Folder = new File(mainPath, path);
		if (!Folder.exists())
			Folder.mkdirs();
	}

	public void createFileIfNotExist(String filePath) {
		try {
			File file = new File(mainPath, filePath);
			if (!file.exists())
				file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkForFile(String path) {
		File file = new File(mainPath, path);
		return (file.exists() && !file.isDirectory());
	}

	public boolean compareFileContent(String filePath, String content) {
		try {
			File file = new File(mainPath, filePath);
			Scanner reader = new Scanner(file);
			boolean bool;
			if (reader.hasNextLine()) {
				String line = reader.nextLine().toString();
				bool = (line.equals(content));
			} else {
				System.out.println("No Content Inside File" + filePath);
				bool = false;
			}
			reader.close();
			return bool;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void writeTxtFile(String filePath, String content) {
		this.createFileIfNotExist(filePath);
		try (FileWriter fw = new FileWriter(mainPath + filePath)) {
			fw.write(content);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void writeJsonObjectToFile(String filePath, JSONObject obj) {
		this.createFileIfNotExist(filePath);
		try (FileWriter fw = new FileWriter(mainPath + filePath)) {
			fw.write(obj.toJSONString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void writeJsonArrayToFile(String filePath, JSONArray arr) {
		this.createFileIfNotExist(filePath);
		try (FileWriter fw = new FileWriter(mainPath + filePath)) {
			fw.write(arr.toJSONString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public static void SetMainPath(URI path) {
		mainPath = new File(path);
	}

}
