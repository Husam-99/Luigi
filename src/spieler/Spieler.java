package spieler;

import Main.SpielPanel;
import Spielablauf.Feld;

import java.awt.*;

public class Spieler {
    SpielPanel sp;
    public final int bildschirmX, bildschirmY;
    public int weltX, weltY;
    public int geschwindigkeit;
    public Feld naechstesFeld;
    public Feld aktuellesFeld;
    public String richtung;
    Konto konto;
    public Inventar inventar;

    Spielfigur taha = new Taha(this);

    public Spieler(SpielPanel sp) {
        this.sp = sp;
        konto = new Konto();
        inventar = new Inventar(this);
        bildschirmX = sp.bildschirmBreite/2 - (sp.vergroesserteFliesenGroesse/2);
        bildschirmY = sp.bildschirmHoehe/2 - (sp.vergroesserteFliesenGroesse/2) ;
        sp.mapManager.hinzufuegeSpieler(this);
        setzeWerte();
    }



    public void setzeWerte(){
        weltX = sp.vergroesserteFliesenGroesse * 11;
        weltY = sp.vergroesserteFliesenGroesse * 21;
        geschwindigkeit = 4;
        richtung = "nord";
        naechstesFeld = sp.mapManager.mapFliesen[19][11].feld;
        aktuellesFeld = null;
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