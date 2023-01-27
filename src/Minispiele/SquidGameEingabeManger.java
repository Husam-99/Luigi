package Minispiele;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SquidGameEingabeManger implements KeyListener {
    boolean tippenErlaubt =true;
    MinispielSpieler mainMinispielSpieler;
    MinispielManager minispielManager;

    public SquidGameEingabeManger(MinispielManager m, MinispielSpieler mainSpieler) {
       this.minispielManager=m;
       this.mainMinispielSpieler=mainSpieler;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if (tippenErlaubt) {
            if (e.getKeyChar() == 'd'){
                if (mainMinispielSpieler.aktuellePalette == null) {
                    mainMinispielSpieler.aktuellePalette = minispielManager.squidGame.paletten[1];
                } else {
                    mainMinispielSpieler.aktuellePalette = mainMinispielSpieler.aktuellePalette.naechsteRechts;
                }
                mainMinispielSpieler.minispielXPosition = mainMinispielSpieler.aktuellePalette.weltX;
                mainMinispielSpieler.minispielYPosition = mainMinispielSpieler.aktuellePalette.weltY;
            } else if (e.getKeyChar() == 'a') {
                if (mainMinispielSpieler.aktuellePalette == null) {
                    mainMinispielSpieler.aktuellePalette = minispielManager.squidGame.paletten[0];
                } else {
                    mainMinispielSpieler.aktuellePalette = mainMinispielSpieler.aktuellePalette.naechsteLinks;
                }
                mainMinispielSpieler.minispielXPosition = mainMinispielSpieler.aktuellePalette.weltX;
                mainMinispielSpieler.minispielYPosition = mainMinispielSpieler.aktuellePalette.weltY;
            }
            tippenErlaubt = false;
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if(mainMinispielSpieler.aktuellePalette.hatFalle){
                mainMinispielSpieler.respawn();
                mainMinispielSpieler.aktuellePalette = null;
            }
            else if (mainMinispielSpieler.aktuellePalette.paletteNummer == 13 && mainMinispielSpieler.aktuellePalette.hatFalle==false) {
                minispielManager.squidGame.siegerKueren();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            if(mainMinispielSpieler.aktuellePalette.hatFalle){
                mainMinispielSpieler.respawn();
                mainMinispielSpieler.aktuellePalette = null;
            }
            else if (mainMinispielSpieler.aktuellePalette.paletteNummer == 12 && mainMinispielSpieler.aktuellePalette.hatFalle==false) {
                minispielManager.squidGame.siegerKueren();
            }
        }
        tippenErlaubt =true;
    }
}