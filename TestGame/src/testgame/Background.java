/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;
        
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

public class Background extends JLabel {
 private static final long serialVersionUID = 1L;

 protected void paintComponent(Graphics g) {
  
  super.paintComponent(g);

  Graphics2D g2d = (Graphics2D) g;

  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
RenderingHints.VALUE_ANTIALIAS_ON);

  g.drawImage(VarKlasse.bi1, 0, VarKlasse.backgroundy1, 800, 600, null);
  g.drawImage(VarKlasse.bi2, 0, VarKlasse.backgroundy2, 800, 600, null);
  
  g.drawImage(VarKlasse.charakter, VarKlasse.x, VarKlasse.y, 50, 70, null);
  
  repaint();

 }

}
    
        

