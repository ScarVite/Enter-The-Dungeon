package enterTheDungeon.game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pausemenu implements ActionListener {
	private JFrame gui;
	private JPanel pausemenu;
	private Mainmenu main;
	private int screenwidth;
	private int screenheight;
	private JButton zurueckSpiel;
	private Game game;
	
	public Pausemenu(Mainmenu mainmenu,Game pgame){
		this.game = pgame;
		screenwidth = 800;
		screenheight = 600;
		gui = new JFrame("Enter the Dungeon");
		this.main = mainmenu;
		pausemenu = new JPanel();
		pausemenu.setLayout(null);
		zurueckSpiel = new JButton("Zurück zum Spiel");
		zurueckSpiel.setBounds(310, 295, 160, 40);
		zurueckSpiel.addActionListener(this);
		pausemenu.add(zurueckSpiel);
		pausemenu.add(main.getCloseButton());
		pausemenu.add(main.getOptionsButton());
		pausemenu.add(main.getCreditsButton());
		gui.setSize(screenwidth, screenheight);
		gui.add(pausemenu);
		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.setDefaultCloseOperation(gui.HIDE_ON_CLOSE);		
		gui.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == zurueckSpiel) {
			game.setPause(false);
			gui.setVisible(false);
		}
		
	}
	

}
