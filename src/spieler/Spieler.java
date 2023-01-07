package spieler;

import Main.SpielPanel;

import java.awt.*;

public class Spieler {
    SpielPanel sp;
    Spielfigur spielfigur;
    String name ;
    int PositionX ;
    int PositionY ;

    public Taha taha = new Taha(this);
    public Husam husam = new Husam(this);
    public Abdo abdo = new Abdo(this);
    public Yousef yousef = new Yousef(this);
    public TahaWuerfel twuerfel = new TahaWuerfel(this);
    public HusamWuerfel hwuerfel = new HusamWuerfel(this);
    public AbdoWuerfel awuerfel = new AbdoWuerfel(this);
    public YousefWuerfel ywuerfel = new YousefWuerfel(this);

    public Spieler(SpielPanel sp) {
        this.sp = sp;
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



}