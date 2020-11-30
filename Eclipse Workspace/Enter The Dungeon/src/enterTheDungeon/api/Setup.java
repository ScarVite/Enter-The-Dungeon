package enterTheDungeon.api;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import enterTheDungeon.game.Mainmenu;

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
		// Networking.GenerateToken(420);
		// Networking.updateLeaderboard("ScarVite", 220);
		if (jTextField1.getText().isEmpty() == false) {
			if (Networking.validatekey(jTextField1.getText())) {
				File mainFile = new File(System.getProperty("user.home"));
				try {
					mainFile = new File(Setup.class.getProtectionDomain().getCodeSource().getLocation().toURI());
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
				try {
					File myObj = new File(mainFile, "/EnterTheDungeon/KeyValid");
					if (myObj.createNewFile()) {
						System.out.println("File created: " + myObj.getName());
					} else {
						System.out.println("File already exists.");
					}
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
				new Mainmenu();
				this.dispose();
				// System.out.println("True");
				// jLabel2 = new JLabel("Das Spiel Startet nun");
				// jLabel2.setBounds(65, 10, 200, 24);
				// add(jLabel2);
				// remove(jLabel1);
				// remove(jButton1);
				// remove(jTextField1);
				// repaint();
				// return;

			}
		} else {
			Popup.error("Bitte Geben sie einen Key ein", "Error");
		}
	}

	public static void main(String args[]) {
		File mainFile = new File(System.getProperty("user.home"));
		try {
			mainFile = new File(Setup.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		File Key = new File(mainFile, "/EnterTheDungeon");
		if (!Key.exists()) {
			Key.mkdirs();
		}
		File Key2 = new File(mainFile, "/EnterTheDungeon/KeyValid");
		System.out.println(Key.getAbsolutePath());
		if (Key2.exists() && !Key2.isDirectory()) {
			new Mainmenu();
		} else {
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					new Setup().setVisible(true);
				}
			});
		}
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

	// idk why this has to be here, since its initialized a few lines higher, but it
	// doesn't work without it
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