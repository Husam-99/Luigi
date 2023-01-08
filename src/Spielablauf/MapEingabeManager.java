package Spielablauf;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MapEingabeManager implements KeyListener {
    SpielMapManager spielMapManager;


    public MapEingabeManager(SpielMapManager spielMapManager){
        this.spielMapManager = spielMapManager;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            spielMapManager.alleSpieler.get(0).weltY -= spielMapManager.sp.doppelteFliesenGroesse/2;

        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            spielMapManager.alleSpieler.get(0).weltY += spielMapManager.sp.doppelteFliesenGroesse/2;

        }else if(e.getKeyCode() == KeyEvent.VK_D){
            spielMapManager.alleSpieler.get(0).weltX += spielMapManager.sp.doppelteFliesenGroesse/2;

        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            spielMapManager.alleSpieler.get(0).weltX -= spielMapManager.sp.doppelteFliesenGroesse/2;

        }


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }



    @Override
    public void keyReleased(KeyEvent e) {

    }
}
