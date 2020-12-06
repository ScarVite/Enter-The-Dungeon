package enterTheDungeon.game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import enterTheDungeon.resource.Sound;
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
	private static JFrame gui;
	private Sound sound;
	private JPanel mainmenu;
	private JPanel button;
	public static JFrame gamewindow = new JFrame();
	private Mainmenutex Mainmenutex;
	private ImageIcon imageIcon;
	private Mainmenudraw mainmenudraw;

	// Mainmenu kreieren und Buttons hinzufï¿½gen
	public Mainmenu() {
		// Hauptmenü Musik wird abgerufen und in einer Schleife abgespielt
		sound = new Sound();
		String soundPath = "Sound\\Mainmenu.wav";
		sound.playSound(soundPath);
		sound.getClip().loop(Clip.LOOP_CONTINUOUSLY);

		screenWidth = 800;
		screenHeight = 600;
		mainmenudraw = new Mainmenudraw(this);
		mainmenudraw.setBounds(0, 0, 800, 600);
		Mainmenutex = new Mainmenutex(this);

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
		g.drawImage(Mainmenutex.mainmenubild, 0, 0, 800, 600, null);
	}

	public void Einstellung() {

		options = new JPanel();
		options.setLayout(null);
		gui = new JFrame("Enter the Dungeon Einstellung");
		gui.setSize(screenWidth, screenHeight);
		options.add(soundButton);
		options.add(backButton);
		options.add(mainmenudraw);
		gui.add(options);
		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);
	

	}

	public void Credits() {
		options = new JPanel();
		options.setLayout(null);
		gui = new JFrame("Enter the Dungeon Einstellung");
		gui.setSize(screenWidth, screenHeight);
		options.add(backButton);
		options.add(mainmenudraw); //Mainmenü hintergrund
		gui.add(options);
		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);
	}

	private void gameWindow() {
		gamewindow.add(new Game());
	}

	private void createMainMenuButtons() {
		//Hauptmenü
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

		//Untermenüs

		soundButton = new JButton("Ton aus");
		soundButton.setBounds(310, 225, 160, 40);
		soundButton.addActionListener(this);

		soundAnButton = new JButton("Ton An");
		soundAnButton.setBounds(310, 225, 160, 40);
		soundAnButton.addActionListener(this);

		backButton = new JButton("Zurück");
		backButton.setBounds(310, 295, 160, 40);
		backButton.addActionListener(this);

	}

	@Override
	// Button wird gedrückt wird
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == startButton) {
			screenWidth = 1920;
			screenHeight = 1080;
			gameWindow();
			sound.getClip().stop();
			if (sound.getHintergrundmusik()==true) {
				sound = new Sound();
				String soundPath = "Sound\\background.wav";
				sound.playSound(soundPath);
				sound.getClip().loop(Clip.LOOP_CONTINUOUSLY);
				sound.getClip().start();
			} else {
				sound.getClip().stop();
			}
		}

		if (e.getSource() == closeButton) {
			sound.getClip().stop();
			System.exit(0);
		}
		if (e.getSource() == backButton) {
			options.setVisible(false);
			gui.setVisible(false);
		}

		if (e.getSource() == optionsButton) {
			Einstellung();
		}

		if (e.getSource() == creditsButton) {
			Credits();
		}
		if (e.getSource() == soundButton) {
			sound.setHintergrundmusik(false);
			sound.getClip().stop();
			options.add(soundAnButton);
			options.remove(soundButton);
			options.add(mainmenudraw); //Mainmenü hintergrund wird hier bei An und Aus nochmals auf das JPanel geadded sonst wird das Bild nicht angezeigt
		}
		if (e.getSource() == soundAnButton) {
			sound.setHintergrundmusik(true);
			sound.getClip().start();
			options.remove(soundAnButton);
			options.add(soundButton);
			options.add(mainmenudraw);

		}

	}

}
