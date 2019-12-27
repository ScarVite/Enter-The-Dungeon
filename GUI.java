import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame 
{
        JPanel jButtonPanel = new JPanel();
        JButton jStartButton = new JButton();
        JPanel jOptionsPanel = new JPanel();
        JButton jOptionsButton = new JButton();
        
      public GUI()        
      {
             setTitle("Enter The Dungeon");
             setSize(800,500);
             setVisible(true);
             setDefaultCloseOperation(EXIT_ON_CLOSE);
             
             //Start Button
             jStartButton.setIcon(new ImageIcon("start.png"));
             jButtonPanel.add(jStartButton);
             add(jButtonPanel);
             jStartButton.setPreferredSize(new Dimension(500,200));
             jButtonPanel.setPreferredSize(new Dimension(500,200));
             add(jOptionsPanel,BorderLayout.SOUTH);
             
             //Options Button
             jOptionsButton.setIcon(new ImageIcon("Option.png"));
             jOptionsPanel.add(jOptionsButton);
             add(jOptionsPanel , BorderLayout.NORTH);
             jOptionsButton.setPreferredSize(new Dimension(500,200));
             jOptionsPanel.setPreferredSize(new Dimension(500,200)); 
            validate();
      }

       public static void main(String[] args)
       {
             GUI GUI = new GUI();
       }
}