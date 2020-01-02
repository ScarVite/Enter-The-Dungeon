/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import java.awt.Label;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author major
 */
public class VarKlasse {
    
    // Alle Variablen werden in dieser Klassen deklariert und auf static gesetzt das wir drauf zu greifen können
    static JFrame JFrame1;
    static Label Label1;
    static int screenwidth = 800, screenheight = 600;
    static boolean moveup = false ,movedown = false ,moveleft = false ,moveright = false;
    static BufferedImage bi1,bi2,charakter;
    static int backgroundy1 = 0, backgroundy2 = -600;
    static int x = 400,y= 400;
    
    
    
    
    public void Var(){
        
        try{
            bi1 = ImageIO.read(new File("TestGame\\Bilder\\Background.png"));
            bi2 = ImageIO.read(new File("TestGame\\Bilder\\Background.png"));
            charakter = ImageIO.read(new File ("Bilder/pikachu.png")); 
            
        }catch(IOException IO){
            IO.printStackTrace();
            System.out.println("Bild konnte nicht geladen werden");
        }
        
    }
    
    
}
