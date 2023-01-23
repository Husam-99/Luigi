package Minispiele;

import Networking.Pakete.Bewegung;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SammlerEingabeManager implements KeyListener {
    MinispielManager minispielManager;
    MinispielSpieler mainMinispielSpieler;
    Bewegung bewegung;


    public SammlerEingabeManager(MinispielManager minispielManager, MinispielSpieler mainMinispielSpieler){
        this.minispielManager = minispielManager;
        this.mainMinispielSpieler = mainMinispielSpieler;
        bewegung = new Bewegung();
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            mainMinispielSpieler.obenGedrueckt = true;
            bewegung.obenGedrueckt = true;
        }
        else if (code == KeyEvent.VK_S) {
            mainMinispielSpieler.untenGedrueckt = true;
            bewegung.untenGedrueckt = true;
        }
        else if (code == KeyEvent.VK_A) {
            mainMinispielSpieler.linksGedrueckt = true;
            bewegung.linksGedrueckt = true;
        }
        else if (code == KeyEvent.VK_D) {
            mainMinispielSpieler.rechtsGedrueckt = true;
            bewegung.rechtsGedrueckt = true;
        }
        minispielManager.sp.client.send(bewegung);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            mainMinispielSpieler.obenGedrueckt = false;
            bewegung.obenGedrueckt = false;
        }
        else if (code == KeyEvent.VK_S) {
            mainMinispielSpieler.untenGedrueckt = false;
            bewegung.untenGedrueckt = false;
        }
        else if (code == KeyEvent.VK_A) {
            mainMinispielSpieler.linksGedrueckt = false;
            bewegung.linksGedrueckt = false;
        }
        else if (code == KeyEvent.VK_D) {
            mainMinispielSpieler.rechtsGedrueckt = false;
            bewegung.rechtsGedrueckt = false;
        }
        minispielManager.sp.client.send(bewegung);

    }
}
