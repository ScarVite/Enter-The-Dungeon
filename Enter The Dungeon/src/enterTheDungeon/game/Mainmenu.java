package enterTheDungeon.game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

import enterTheDungeon.resource.Sound;
import enterTheDungeon.resource.Filesystem;
import enterTheDungeon.resource.Mainmenutex;

public class Mainmenu extends JFrame implements ActionListener {

	// Variablen
	public static int screenWidth = 0;
	public static int screenHeight = 0;

	private JButton startButton;
	private JButton closeButton;
	private JButton optionsButton;
	private JButton creditsButton;
	

	private JButton soundButton;
	private JButton soundAnButton;
	private JButton backButton;
	private JPanel options;
	private JPanel credits;
	private static JFrame gui;
	private Sound sound = new Sound();
	private JPanel mainmenu;
	private JPanel button;
	public static JFrame gamewindow = new JFrame();
	private Filesystem filesystem = new Filesystem();
	private JSONObject settingsObj = (JSONObject) filesystem.readJsonFileasObject("/Files/Settings.json");
	private Mainmenutex mainmenutex;
	private ImageIcon imageIcon;
	private Mainmenudraw mainmenudraw;

	// Mainmenu kreieren und Buttons hinzuf�gen
	@SuppressWarnings("unchecked")
	public Mainmenu() {
		if(settingsObj == null) {
			settingsObj = new JSONObject();
			settingsObj.put("music", true);
			filesystem.writeJsonObjectToFile("/Files/Settings.json", settingsObj);
		}
		// Hauptmen� Musik wird abgerufen und in einer Schleife abgespielt
		if((boolean) settingsObj.get("music")) {
			sound.playSound("Sound\\Mainmenu.wav");
			sound.getClip().loop(Clip.LOOP_CONTINUOUSLY);
		}
		else 
			sound.setHintergrundmusik(false);
		screenWidth = 800;
		screenHeight = 600;
		mainmenudraw = new Mainmenudraw(this);
		mainmenudraw.setBounds(0, 0, 800, 600);
		mainmenutex = new Mainmenutex(this);

		gui = new JFrame("Enter the Dungeon");

		mainmenu = new JPanel();
		mainmenu.setLayout(null);

		createMainMenuButtons();

		mainmenu.add(startButton);
		mainmenu.add(closeButton);
		mainmenu.add(optionsButton);
		mainmenu.add(creditsButton);
		mainmenu.add(mainmenudraw);
		gui.setSize(screenWidth, screenHeight);
		gui.add(mainmenu);
		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		gui.setVisible(true);
	}

	public void render(Graphics g) {
		g.drawImage(mainmenutex.mainmenubild, 0, 0, 800, 600, null);
	}

	public void Einstellung() {
		options = new JPanel();
		options.setLayout(null);
		if(sound.getHintergrundmusik() && (boolean) settingsObj.get("music")) options.add(soundButton);
		else options.add(soundAnButton);
		options.add(backButton);
		options.add(mainmenudraw);
		gui.add(options);
		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);
	}

	public void Credits() {
		credits = new JPanel();
		credits.setLayout(null);
		credits.add(backButton);		credits.add(mainmenudraw); //Mainmen� hintergrund
		gui.add(credits);
		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);	
		}

	private void gameWindow() {
		gamewindow.add(new Game(this));
	}

	private void createMainMenuButtons() {
		// Hauptmen�
		startButton = new JButton("Spielen");
		startButton.setBounds(310, 155, 160, 40);
		startButton.addActionListener(this);
		//Bild auf den Button zeichnen
		imageIcon = new ImageIcon("Bilder/startbutton.png");
		startButton.setIcon(imageIcon);
		//Boarder 
		startButton.setBorderPainted(false);
		
		
		creditsButton = new JButton("Credits");
		creditsButton.setBounds(310, 225, 160, 40);
		creditsButton.addActionListener(this);
//		creditsButton.setIcon(new ImageIcon("Bilder/creditsbutton.png"));
		creditsButton.setBorderPainted(false);

		optionsButton = new JButton("Einstellungen");
		optionsButton.setBounds(310, 295, 160, 40);
		optionsButton.addActionListener(this);
//		optionsButton.setIcon(new ImageIcon("Bilder/optionsbutton.png"));
		optionsButton.setBorderPainted(false);
		
		closeButton = new JButton("Beenden");
		closeButton.setBounds(310, 365, 160, 40);
		closeButton.addActionListener(this);
//		closeButton.setIcon(new ImageIcon("Bilder/closebutton.png"));
		closeButton.setBorderPainted(false);

		// Untermen�s

		soundButton = new JButton("Ton aus");
		soundButton.setBounds(310, 225, 160, 40);
		soundButton.addActionListener(this);

		soundAnButton = new JButton("Ton An");
		soundAnButton.setBounds(310, 225, 160, 40);
		soundAnButton.addActionListener(this);

		backButton = new JButton("Zur�ck");
		backButton.setBounds(310, 295, 160, 40);
		backButton.addActionListener(this);
		

	}
	public JButton getCloseButton() {
		return closeButton;
	}

	public JButton getOptionsButton() {
		return optionsButton;
	}

	public JButton getCreditsButton() {
		return creditsButton;
	}

	public JButton getBackButton() {
		return backButton;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	// Button wird gedr�ckt
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == startButton) {
			screenWidth = 1920;
			screenHeight = 1080;
			gameWindow();
			if (sound.getHintergrundmusik()) {
				sound.getClip().stop();
				String soundPath = "Sound/background.wav";
				sound.playSound(soundPath);
				sound.getClip().loop(Clip.LOOP_CONTINUOUSLY);
				sound.getClip().start();
			}
		}

		if (e.getSource() == closeButton) {
			if(sound.getHintergrundmusik()) sound.getClip().stop();
			System.exit(0);
		}
		if (e.getSource() == backButton) {
			mainmenu.setVisible(true);
			mainmenu.add(mainmenudraw);
		}
		
		if (e.getSource() == optionsButton) {
			Einstellung();
			mainmenu.setVisible(false);
		}

		if (e.getSource() == creditsButton) {
			Credits();
			mainmenu.setVisible(false);	
		}
		if (e.getSource() == soundButton) {
			if(sound.getHintergrundmusik()) sound.getClip().stop();
			sound.setHintergrundmusik(false);
			settingsObj.put("music", false);
			System.out.println(settingsObj);
			filesystem.writeJsonObjectToFile("/Files/Settings.json", settingsObj);
			options.add(soundAnButton);
			options.remove(soundButton);
			options.add(mainmenudraw); // Mainmen� hintergrund wird hier bei An und Aus nochmals auf das JPanel geadded
										// sonst wird das Bild nicht angezeigt
		}
		if (e.getSource() == soundAnButton) {
			if(sound.getHintergrundmusik()) sound.getClip().start();
			else {
				sound.playSound("Sound\\Mainmenu.wav");
				sound.getClip().loop(Clip.LOOP_CONTINUOUSLY);
			}
			sound.setHintergrundmusik(true);
			settingsObj.put("music", true);
			filesystem.writeJsonObjectToFile("/Files/Settings.json", settingsObj);
			options.remove(soundAnButton);
			options.add(soundButton);
			options.add(mainmenudraw);

		}

	}

}
