package coolboys.net;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Setup extends JFrame {

	private static final long serialVersionUID = 1L;

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		if (license.validatekey(jTextField1.getText()) == true) {
			System.out.println("True");

		} else {
			System.out.println("false");
		}
	}

	public static void main(String args[]) {
	/*if(datei valid auf true){
		Spiel Starten
	}else{
	datei erstellen und abfrage starten
	}*/
		createJFrame();
	}

	private static void createJFrame() {
		JFrame Keyeingabe = new JFrame("Enter The Dungeon Setup");
		Keyeingabe.setSize(new Dimension(300,150));
		JTextField jTextField1 = new JTextField();
//		jTextField1.setSize(new Dimension(10,10));
		jTextField1.setBounds(50,100, 50, 10);
		JLabel jLabel1 = new JLabel("Bitte geben sie ihren Key ein");
		jLabel1.setSize(200,24);
		Keyeingabe.add(jLabel1);
		Keyeingabe.add(jTextField1);
		Keyeingabe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Keyeingabe.setLocationRelativeTo(null);
		Keyeingabe.setLayout(null);
		Keyeingabe.validate();
		Keyeingabe.setVisible(true);
	}
}