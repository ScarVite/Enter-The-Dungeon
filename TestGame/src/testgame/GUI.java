/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import java.awt.Label;
import javax.swing.JFrame;

/**
 *
 * @author major
 */
public class GUI {
   
    
    public GUI(){
        
        VarKlasse.JFrame1 = new JFrame();  
        VarKlasse.JFrame1.setSize(VarKlasse.screenwidth,VarKlasse.screenheight);
        VarKlasse.JFrame1.setLocationRelativeTo(null);
        VarKlasse.JFrame1.setVisible(true);
        VarKlasse.JFrame1.setLayout(null);
        VarKlasse.JFrame1.setTitle("Enter the Dungeon");
        VarKlasse.JFrame1.setResizable(false);
        VarKlasse.JFrame1.requestFocus();
        
        VarKlasse.Label1 = new Label();
        VarKlasse.Label1.setBounds(0, 0, VarKlasse.screenwidth,VarKlasse.screenheight);
        VarKlasse.JFrame1.add(VarKlasse.Label1);
        VarKlasse.Label1.setVisible(true);
       
        
        
    }
    
}
