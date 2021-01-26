package enterTheDungeon.api;

import java.awt.Dimension;

import javax.swing.JFrame;

import enterTheDungeon.game.Mainmenu;

public class Register extends javax.swing.JPanel {

	
	private JFrame f;
	private Mainmenu menu;
	
	public Register(Mainmenu menu) {
		this.menu = menu;
		initComponents();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		f = new JFrame("Registrierung");
		f.setVisible(true);
		jLabel1 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		email = new javax.swing.JTextField();
		passwort = new javax.swing.JTextField();
		username = new javax.swing.JTextField();
		passwort2 = new javax.swing.JTextField();

		jLabel1.setText("Geben sie Ihre Daten ein");

		jButton1.setText("Send");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		email.setText("Email");

		passwort.setText("Passwort");

		username.setText("Username");

		passwort2.setText("Passwort wiederholen");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(69, 69, 69).addComponent(jButton1))
						.addGroup(layout.createSequentialGroup().addGap(38, 38, 38)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(jLabel1)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(passwort, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(passwort2, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
														javax.swing.GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap(24, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(28, 28, 28).addComponent(jLabel1).addGap(18, 18, 18)
						.addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(4, 4, 4)
						.addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(passwort, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(passwort2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButton1)
						.addContainerGap(23, Short.MAX_VALUE)));
		f.add(this);
		f.setSize(new Dimension(250, 250));
	}// </editor-fold>

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		if (!email.getText().isEmpty() && !passwort.getText().isEmpty() && !passwort2.getText().isEmpty() && !username.getText().isEmpty()) {
			if (passwort.getText().equals(passwort2.getText())) {
				menu.setRegisterOpen(false);
				f.dispose();
				Networking.addUser(email.getText(), username.getText(), passwort.getText());
			} else {
				Popup.error("Ihre Passwörter Stimmen nicht überin", "Error");
			}
		} else {
			Popup.error("Eine Ihrer Eingaben ist ungültig", "Error");
		}
	}

	// Variables declaration - do not modify
	private javax.swing.JTextField email;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JTextField passwort;
	private javax.swing.JTextField passwort2;
	private javax.swing.JTextField username;
	// End of variables declaration
}
