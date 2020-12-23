package enterTheDungeon.api;

import java.awt.*; 
import javax.swing.*; 

public class ProgressBar {
	
	private JFrame f; 
	private JTextArea taskOutput;
    private JProgressBar b;
    private JScrollPane output;
  
	@SuppressWarnings("deprecation")
	public ProgressBar(int length) {
        // create a frame 
        f = new JFrame("Enter The Dungeon Update"); 
        // create a panel 
        JPanel p = new JPanel(); 
        // create a progressbar 
        b = new JProgressBar(0, length); 
        // create Output
        taskOutput = new JTextArea();
        // set initial value 
        b.setValue(0); 
        b.setStringPainted(true); 
        b.setVisible(true);
        //b.setIndeterminate(true);
        taskOutput.setMargin(new Insets(5,5,5,5));
        taskOutput.setEditable(false);
        // add progressbar 
        p.add(b); 
        // add panel 
        f.add(p); 
        // add taskoutput
        output = new JScrollPane(taskOutput);
        f.add(output, BorderLayout.CENTER);
        // set the size of the frame 
        f.setCursor(Cursor.WAIT_CURSOR);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.setSize(400, 250); 
        f.setLocationRelativeTo(null);
        f.setVisible(true); 
  
	}
	
	public void update(String status, int progress) {
		taskOutput.append(status);
		if(progress != -1) {
			b.setValue(progress);
		}
		JScrollBar vertical = output.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
	}
	
	public void yeet() {
		f.dispose();
	}
	
}
