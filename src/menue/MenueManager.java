package menue;

import Main.SpielPanel;

import java.awt.*;

public class MenueManager {

    SpielPanel sp;
    public int spielerAnzahl;
    public int rundenAnzahl;
    public MenueHintergrund menueHintergrund;
    public MenueEingabeManager menueEingabeManager;
    Hauptmenue hauptmenue;
    public int menueZustand;
    public int hauptmenueZustand1 = 0;
    public int hauptmenueZustand2 = 1;
    public int spielfigurAuswaehlenZustand = 2;
    public SpielfigurAuswaehlen spielfigurAuswaehlen ;

    public MenueManager(SpielPanel sp){
        this.sp = sp;
        menueHintergrund = new MenueHintergrund(this);
        menueEingabeManager = new MenueEingabeManager(this);
        hauptmenue = new Hauptmenue(this);
        spielfigurAuswaehlen = new SpielfigurAuswaehlen(this);

    }
    public int spielerAnzahlFestlegen(){
        return spielerAnzahl;
    }
    public int rundenAnzahlFestlegen(){
        return rundenAnzahl;
    }
    public void update(){
        spielfigurAuswaehlen.update();
    }
    public void malen(Graphics2D g2){
        if(!sp.client.istDran()){
            spielfigurAuswaehlen.enterZustand = 1;
            spielfigurAuswaehlen.malen(g2);
        }else{
            spielfigurAuswaehlen.enterZustand = 0;
            if(menueZustand == hauptmenueZustand1 || menueZustand == hauptmenueZustand2){
                hauptmenue.malen(g2);
            }else if(menueZustand == spielfigurAuswaehlenZustand){
                spielfigurAuswaehlen.malen(g2);
            }
        }
    }


}
