package coolboys.net;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	private static JFrame gui;
	private JPanel mainmenu;

	public Mainmenu() {

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
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);

		gui.setVisible(true);

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

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == startButton) {
			screenWidth = 1920;
			screenHeigth = 1080;
			gameWindow();
		}

		if (e.getSource() == closeButton) {
			System.exit(0);
		}

		if (e.getSource() == optionsButton) {
			// Einstellung aufrufen
		}

		if (e.getSource() == creditsButton) {
			// Credits aufrufen
		}

	}

}
