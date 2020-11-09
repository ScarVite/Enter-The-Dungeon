package etd.dungeon.api;


import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import etd.dungeon.game.Mainmenu;

//import org.omg.CORBA.TIMEOUT;

public class Setup extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	JTextField jTextField1;
	JButton jButton1;
	JLabel jLabel1;
	JLabel jLabel2;

	public Setup() {
		createJFrame();
	}


	
	private void ButtonPressed(java.awt.event.ActionEvent evt) {
		System.out.println("Hier");
		Networking.GenerateToken(420);
		Networking.updateLeaderboard("ScarVite", 220 );
		if (jTextField1.getText().isEmpty() == false) {
			if (Networking.validatekey(jTextField1.getText()) == true) {
				new Mainmenu();
				this.dispose();
//				System.out.println("True");
//				jLabel2 = new JLabel("Das Spiel Startet nun");
//				jLabel2.setBounds(65, 10, 200, 24);
//				add(jLabel2);
//				remove(jLabel1);
//				remove(jButton1);
//				remove(jTextField1);
//				repaint();
//				return;

			} else {
				Popup.error("Ihr Key war leider Falsch", "Error");
			}
		} else {
			Popup.error("Bitte Geben sie einen Key ein", "Error");
		}
	}

	public static void main(String args[]) {
		/*
		 * if(datei valid auf true){ Spiel Starten }else{ datei erstellen und abfrage
		 * starten }
		 */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Setup().setVisible(true);
			}
		});
	}

	private void createJFrame() {
		setTitle("Enter The Dungeon Setup");
		setSize(new Dimension(300, 175));
		jTextField1 = new JTextField();
		jTextField1.setBounds(75, 50, 150, 20);
		jLabel1 = new JLabel("Bitte geben sie ihren Key ein");
		jLabel1.setBounds(65, 10, 200, 24);
		jButton1 = new JButton("Eingeben");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				ButtonPressed(e);
			}
		});
		jTextField1.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {


			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ButtonPressed(null);
				}

			}
		});
		jButton1.setBounds(90, 90, 100, 24);
		add(jButton1);
		add(jLabel1);
		add(jTextField1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		validate();
		setVisible(true);
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (!jTextField1.getText().contains("\n")) {
					try {
						Thread.sleep(20);
					} catch (Exception e) {
					}
				}
				jTextField1.setText(jTextField1.getText().replaceAll("\n", ""));
				ButtonPressed(null);
			}
		}).start();

	}


// 	idk why this has to be here, since its initialized a few lines higher, but it doesn't work without it
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}