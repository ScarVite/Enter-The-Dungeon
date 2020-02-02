package EnterTheDungeon;

import java.awt.Label;

import javax.swing.JFrame;

public class Gui {

	static JFrame jf;
	static Draw draw;
	static int screenwidth = 1920, screenheight = 1080;

	public Gui() {

		jf = new JFrame();
		jf.setSize(screenwidth, screenheight);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		draw = new Draw();
		draw.setBounds(0, 0, screenwidth, screenheight);
		draw.setVisible(true);
		jf.add(draw);
		Var.Label1 = new Label();
		Var.Label1.setBounds(0, 0, screenwidth, screenheight);
		jf.add(Var.Label1);
		Var.Label1.setVisible(true);
		jf.addKeyListener(new TastenEingabe());
		jf.setVisible(true);

	}

}
