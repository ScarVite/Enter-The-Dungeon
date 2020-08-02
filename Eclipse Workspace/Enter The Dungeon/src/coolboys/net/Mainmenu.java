package coolboys.net;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mainmenu extends JFrame implements ActionListener {

	public static int screenWidth = 0;
	public static int screenHeigth = 0;

	private JButton startButton;
	private JButton closeButton;
	private JButton optionsButton;
	private JButton creditsButton;
	private JButton soundButton;
	private JButton soundAnButton;
	private JButton backButton;
	private JPanel options;
	private static JFrame gui;
	private JPanel mainmenu;
	boolean hintergrundmusik = false;

	public Mainmenu() {
		String soundPath = "Sound\\Mainmenu.wav";
		Sound sound = new Sound();
		Sound.playSound(soundPath);
		Sound.clip.loop(Clip.LOOP_CONTINUOUSLY);

		screenWidth = 800;
		screenHeigth = 600;

		gui = new JFrame("Enter the Dungeon");

		mainmenu = new JPanel();
		mainmenu.setLayout(null);

		createMainMenuButtons();

		mainmenu.add(startButton);
		mainmenu.add(closeButton);
		mainmenu.add(optionsButton);
		mainmenu.add(creditsButton);
		

		gui.setSize(screenWidth, screenHeigth);

		gui.add(mainmenu);

		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		gui.setVisible(true);

	}
	
	public void Einstellung(){
		
		options = new JPanel();
		options.setLayout(null);
		gui = new JFrame("Enter the Dungeon Einstellung");
		gui.setSize(screenWidth, screenHeigth);

		gui.add(options);

		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);
		options.add(soundButton);
		options.add(backButton);
		
	}

	public void Credits(){
		
		options = new JPanel();
		options.setLayout(null);
		gui = new JFrame("Enter the Dungeon Credits");
		gui.setSize(screenWidth, screenHeigth);

		gui.add(options);

		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);
		options.add(soundButton);
		options.add(backButton);
		
	}


	private void gameWindow() {
		JFrame gamewindow = new JFrame();
		gamewindow.add(new GUI());

	}

	private void createMainMenuButtons() {

		startButton = new JButton("Spielen");
		startButton.setBounds(320, 40, 160, 40);
		startButton.addActionListener(this);

		closeButton = new JButton("Beenden");
		closeButton.setBounds(320, 280, 160, 40);
		closeButton.addActionListener(this);

		optionsButton = new JButton("Einstellungen");
		optionsButton.setBounds(320, 200, 160, 40);
		optionsButton.addActionListener(this);

		creditsButton = new JButton("Credits");
		creditsButton.setBounds(320, 120, 160, 40);
		creditsButton.addActionListener(this);

		soundButton = new JButton("Ton aus");
		soundButton.setBounds(320, 40, 160, 40);
		soundButton.addActionListener(this);

		soundAnButton = new JButton("Ton An");
		soundAnButton.setBounds(320, 40, 160, 40);
		soundAnButton.addActionListener(this);
		
		backButton = new JButton("Zurück");
		backButton.setBounds(320, 280, 160, 40);
		backButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == startButton) {
			screenWidth = 1920;
			screenHeigth = 1080;
			gameWindow();
			Sound.clip.stop();

			hintergrundmusik = true;

			if (hintergrundmusik == true) {
				String soundPath2 = "Sound\\background.wav";
				Sound sound = new Sound();
				Sound.playSound(soundPath2);
				Sound.clip.loop(Clip.LOOP_CONTINUOUSLY);
				Sound.clip.start();
			}
		}

		if (e.getSource() == closeButton) {
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
			Sound.clip.stop();
			options.add(soundAnButton);
			options.remove(soundButton);
		}
		if (e.getSource() == soundAnButton) {
			Sound.clip.start();
			options.remove(soundAnButton);
			options.add(soundButton);

		}

	}

}
