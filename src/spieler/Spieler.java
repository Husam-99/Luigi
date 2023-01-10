package spieler;

import Main.SpielPanel;

import java.awt.*;

public class Spieler {
    SpielPanel sp;
    int positionX ;
    int positionY ;
    public int bildschirmX;
    public int bildschirmY;
    public int weltX, weltY;
    public int geschwindigkeit;
    Konto konto;
    public Inventar inventar;

    Spielfigur taha = new Taha(this);

    public Spieler(SpielPanel sp) {
        this.sp = sp;
        konto = new Konto();
        inventar = new Inventar(this);
        foo();
    }

    public void foo() {
        bildschirmX = sp.bildschirmBreite/2 - (sp.doppelteFliesenGroesse/2);
        bildschirmY = sp.bildschirmHoehe/2 - (sp.doppelteFliesenGroesse/2) ;
        sp.mapManager.hinzufuegeSpieler(this);
        setzeWerte();
    }

    public void setzeWerte(){
        positionX = 688;
        positionY = 368;
        weltX = sp.doppelteFliesenGroesse * 11;
        weltY = sp.doppelteFliesenGroesse * 21;
        geschwindigkeit = 4;
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
        taha.malen(g2);
    }



}