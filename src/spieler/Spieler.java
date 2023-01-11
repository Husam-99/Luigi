package spieler;

import Main.SpielPanel;
import Spielablauf.Feld;

import java.awt.*;

public class Spieler {
    SpielPanel sp;
    Spielfigur spieler;
    Wuerfel wuerfel;
    public final int bildschirmX, bildschirmY;
    public int weltX, weltY;
    public int geschwindigkeit;
    public Feld naechstesFeld;
    public Feld aktuellesFeld;
    public String richtung;
    Konto konto;
    public Inventar inventar;
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

    public void spielfigurAuswaehlen() {
        if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 0){
            spieler = new Abdo(this);
            wuerfel = new AbdoWuerfel(this);
        } else if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 1){
            spieler = new Husam(this);
            wuerfel = new HusamWuerfel(this);
        } else if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 2){
            spieler = new Taha(this);
            wuerfel = new TahaWuerfel(this);
        } else if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 3){
            spieler = new Yousef(this);
            wuerfel = new YousefWuerfel(this);
        }
    }

    public void KontoAktualisieren() {}
    public void getSpielerPositionX() {}
    public void getSpielerPositionY() {}
    public void malen(Graphics2D g2){
        spieler.malen(g2);
    }



}