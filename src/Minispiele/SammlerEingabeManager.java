package Minispiele;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SammlerEingabeManager implements KeyListener {
    public boolean obenGedrueckt, untenGedrueckt, linksGedrueckt, rechtsGedrueckt, dicePressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            obenGedrueckt = true;
        }
        if (code == KeyEvent.VK_S) {
            untenGedrueckt = true;
        }
        if (code == KeyEvent.VK_A) {
            linksGedrueckt = true;
        }
        if (code == KeyEvent.VK_D) {
            rechtsGedrueckt = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            obenGedrueckt = false;
        }
        if (code == KeyEvent.VK_S) {
            untenGedrueckt = false;
        }
        if (code == KeyEvent.VK_A) {
            linksGedrueckt = false;
        }
        if (code == KeyEvent.VK_D) {
            rechtsGedrueckt = false;
        }
    }
}
