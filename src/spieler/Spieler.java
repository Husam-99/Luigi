package spieler;

import Main.SpielPanel;

import java.awt.*;

public class Spieler {
    SpielPanel sp;
    int positionX ;
    int positionY ;
    int geschwindigkeit;
    Konto konto;
    public Inventar inventar;

    public Spieler(SpielPanel sp) {
        this.sp = sp;
        konto = new Konto();
        inventar = new Inventar(this);
    }

    public void KontoAktualisieren() {


    }

    public void spielfigurAuswaehlen() {

    }


    public void getSpielerPositionX() {

    }

    public void getSpielerPositionY() {

    }

    public void  wuerfelAuswaehlen() {

    }
    public void malen(Graphics2D g2){
    }



}