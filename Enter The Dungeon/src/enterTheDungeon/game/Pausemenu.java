package enterTheDungeon.game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import enterTheDungeon.input.TastaturInput;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Pausemenu extends JFrame implements ActionListener {
	private JFrame gui;
	private JPanel pausemenuw;
	private Mainmenu main;
	private int screenwidth;
	private int screenheight;
	private JButton zurueckSpiel;
	private Game game;
	private TastaturInput tastaturinput;

	public Pausemenu(Mainmenu mainmenu, Game pGame) {
		this.game = pGame;
		this.main = mainmenu;
		screenwidth = 800;
		screenheight = 600;
		gui = new JFrame("Enter the Dungeon");
		pausemenuw = new JPanel();
		pausemenuw.setLayout(null);
		tastaturinput = new TastaturInput(pGame, this);
		zurueckSpiel = new JButton("Zurück zum Spiel");
		zurueckSpiel.setBounds(310, 295, 160, 40);
		zurueckSpiel.addActionListener(this);
		pausemenuw.add(zurueckSpiel);
		pausemenuw.add(main.getCloseButton());
		pausemenuw.add(main.getOptionsButton());
		pausemenuw.add(main.getCreditsButton());
		gui.addKeyListener(tastaturinput);
		pausemenuw.addKeyListener(tastaturinput);
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
			gui.setVisible(false);
			game.setPause(false);
			game.setPausemenuOpen(true);
		}
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("Taste gedrückt");
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE) {
			if (!game.isPausemenuOpen()) {
				gui.setVisible(false);
				game.setPause(false);
				game.setPausemenuOpen(true);
			}

		}
	}
}

