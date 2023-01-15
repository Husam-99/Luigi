package spieler;

import Main.SpielPanel;
import Spielablauf.Feld;

import java.awt.*;

public class Spieler {
    SpielPanel sp;
    public Spielfigur spielfigur;
    Wuerfel wuerfel;
    public int bildschirmX;
    public int bildschirmY;
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

        setzeWerte();
    }

    public void setzeWerte(){
        this.bildschirmX = sp.bildschirmBreite / 2 - (sp.vergroesserteFliesenGroesse / 2);
        this.bildschirmY = sp.bildschirmHoehe / 2 - (sp.vergroesserteFliesenGroesse / 2);
        this.weltY = (int) (sp.vergroesserteFliesenGroesse * 21.5);

       //if(this == sp.mainSpieler) {
       //    if(this.spielfigur instanceof Abdo){
       //        this.weltX = (int) (sp.vergroesserteFliesenGroesse * 9.5);
       //    }else if(this.spielfigur instanceof Husam){
       //        this.weltX = (int) (sp.vergroesserteFliesenGroesse * 10.5);
       //    }else if(this.spielfigur instanceof Taha){
       //        this.weltX = (int) (sp.vergroesserteFliesenGroesse * 11.5);
       //    }else if(this.spielfigur instanceof Yousef){
       //        this.weltX = (int) (sp.vergroesserteFliesenGroesse * 12.5);
       //    }
       //}
        geschwindigkeit = 4;
        richtung = "nord";
        naechstesFeld = sp.mapManager.mapFliesen[19][11].feld;
        aktuellesFeld = null;

    }

    public void spielfigurAuswaehlen() {
        if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 0){
            spielfigur = new Abdo(this);
            wuerfel = new AbdoWuerfel(this);
            this.weltX = (int) (sp.vergroesserteFliesenGroesse * 9.5);

        } else if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 1){
            spielfigur = new Husam(this);
            wuerfel = new HusamWuerfel(this);
            this.weltX = (int) (sp.vergroesserteFliesenGroesse * 10.5);

        } else if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 2){
            spielfigur = new Taha(this);
            wuerfel = new TahaWuerfel(this);
            this.weltX = (int) (sp.vergroesserteFliesenGroesse * 11.5);
        } else if(sp.menueManager.spielfigurAuswaehlen.befehlNum1 == 3){
            spielfigur = new Yousef(this);
            wuerfel = new YousefWuerfel(this);
            this.weltX = (int) (sp.vergroesserteFliesenGroesse * 12.5);

        }
    }
    public void spielfigurAuswaehlen(int spielfigurIndex) {
        if(spielfigurIndex == 0){
            spielfigur = new Abdo(this);
            wuerfel = new AbdoWuerfel(this);
        } else if(spielfigurIndex == 1){
            spielfigur = new Husam(this);
            wuerfel = new HusamWuerfel(this);
        } else if(spielfigurIndex == 2){
            spielfigur = new Taha(this);
            wuerfel = new TahaWuerfel(this);
        } else if(spielfigurIndex == 3){
            spielfigur = new Yousef(this);
            wuerfel = new YousefWuerfel(this);
        }
    }

    public void KontoAktualisieren() {}
    public void getSpielerPositionX() {}
    public void getSpielerPositionY() {}
    public void malen(Graphics2D g2){
        if(sp.mainSpieler.spielfigur!=null){
            spielfigur.malen(g2);

        }

    }



}