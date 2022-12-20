package menue;

import main.SpielPanel;

import java.awt.*;

public class MenueManager {

    SpielPanel sp;
    public int spielerAnzahl;
    public int rundenAnzahl;

    public MenueEingabeManager menueEingabeManager = new MenueEingabeManager(this);
    Graphics2D g2;

    public Hauptmenue hauptmenue = new Hauptmenue(this);

    public int hauptmenueZustand = 0;

    public MenueManager(SpielPanel sp){
        this.sp = sp;
    }
    public int spielerAnzahlFestlegen(){
        return 1;
    }

    public int rundenAnzahlFestlegen(){
        return 1;
    }

    public void update(){
        hauptmenue.update();
    }
    public void malen(Graphics2D g2){
        hauptmenue.malen(g2);
    }
}
