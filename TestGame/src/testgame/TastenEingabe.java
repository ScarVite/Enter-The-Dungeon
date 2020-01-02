/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author major
 */
public class TastenEingabe implements KeyListener {
    

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_UP){
            VarKlasse.moveup = true;
        }
         if (e.getKeyCode()==KeyEvent.VK_DOWN){
            VarKlasse.movedown = true;
        }
          if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            VarKlasse.moveright = true;
        }
           if (e.getKeyCode()==KeyEvent.VK_LEFT){
            VarKlasse.moveleft = true;
        }
               
    }
       
    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode()==KeyEvent.VK_UP){
            VarKlasse.moveup = false;
        }
          if (e.getKeyCode()==KeyEvent.VK_DOWN){
            VarKlasse.movedown = false;
        }
          if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            VarKlasse.moveright = false;
        }
           if (e.getKeyCode()==KeyEvent.VK_LEFT){
            VarKlasse.moveleft = false;
        }
        
        
        
    }
    
    
    
}
