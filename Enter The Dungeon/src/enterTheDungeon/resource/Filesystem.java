package enterTheDungeon.resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Filesystem {

	private static File mainPath = new File(System.getProperty("user.home"));

	public JSONObject readJsonFileasObject(String path) {
		File file = new File(mainPath, path);
		boolean empty = file.exists() && file.length() == 0;
		JSONParser parser = new JSONParser();
		if (!empty) {
			try {
				Object obj = parser.parse(new FileReader(mainPath + path));
				JSONObject jsonObject = (JSONObject) obj;
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
	
	public File readFile(String filePath) {
		System.out.println("hier");
		return new File(mainPath, filePath);
	}

	
	public void saveImage(Image image, String name) {
		this.createFolderIfNotExist("/images");
		//this.createFileIfNotExist(mainPath + "/images/" + name);
		File imagefile = new File(mainPath + "/images/" + name); 
		try {
			ImageIO.write(this.toBufferedImage(image), "png", imagefile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public File getMainPath() {
		return mainPath;
	}
	
	public boolean fileNotEmpty(String filePath) {
		return (new File(mainPath, filePath).length() > 0);
	}

	public static void SetMainPath(String path) {
		File Folder = new File(path, "/Enter-The-Dungeon");
		if (!Folder.exists())
			Folder.mkdirs();
		mainPath = new File(path, "/Enter-The-Dungeon");
	}
	
	private BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}

}
