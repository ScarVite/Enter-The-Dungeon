package enterTheDungeon.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import enterTheDungeon.input.TastaturInput;

public class Pausemenu extends JFrame implements ActionListener {
	private JFrame gui;
	private JPanel pausemenuw;
	private Mainmenu main;
	private int screenwidth;
	private int screenheight;
	private JButton zurueckSpiel;
	private JPanel options;
	private Game game;
	private TastaturInput tastaturinput;
	private boolean sound;

	public Pausemenu(Mainmenu mainmenu, Game pGame) {
		//Erzeugung
		this.game = pGame;
		this.main = mainmenu;
		
		screenwidth = 800;
		screenheight = 600;
		gui = new JFrame("Enter the Dungeon");
		pausemenuw = new JPanel();
		pausemenuw.setLayout(null);
		tastaturinput = new TastaturInput(pGame, this);
		//Buttons
		zurueckSpiel = new JButton("Zurueck zum Spiel");
		zurueckSpiel.setBounds(310, 295, 160, 40);
		zurueckSpiel.addActionListener(this);
		zurueckSpiel.setBorderPainted(false);
		mainmenu.getCloseButton();

		
		//JPanel
		pausemenuw.add(zurueckSpiel);
		pausemenuw.add(main.getCloseButton());
		gui.addKeyListener(tastaturinput);
		pausemenuw.addKeyListener(tastaturinput);
		//JFrame
		gui.setSize(screenwidth, screenheight);
		gui.add(pausemenuw);
		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.setDefaultCloseOperation(gui.HIDE_ON_CLOSE);
		gui.setVisible(true);
		gui.setFocusable(true);
		pausemenuw.setFocusable(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == zurueckSpiel) {
			setSound(true);
			gui.setVisible(false);
			game.setPause(false);
			game.setPausemenuOpen(true);
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE) {
			System.out.println("ESC im Pausemenu gedrueckt");
			if (!game.isPausemenuOpen()) {
				gui.setVisible(false);
				game.setPause(false);
				game.setPausemenuOpen(true);
				setSound(true);
			}

		}
	}
	

	public boolean isSound() {
		return sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}
}
