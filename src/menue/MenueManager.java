package menue;

import Main.SpielPanel;

import java.awt.*;

public class MenueManager {

    SpielPanel sp;

    public MenueHintergrund menueHintergrund;
    public MenueEingabeManager menueEingabeManager;
    public SpielfigurAuswahl spielfigurAuswahl;
    Hauptmenue hauptmenue;
    public int spielerAnzahl;

    //Das gesamte Menü Zustande
    public int menueZustand;
    public int hauptmenueZustand;
    public int SpielerUndRundenAnzahlZustand;
    public int spielfigurAuswahlZustand;

    public MenueManager(SpielPanel sp){
        this.sp = sp;
        menueHintergrund = new MenueHintergrund(sp);
        menueEingabeManager = new MenueEingabeManager(this);
        hauptmenue = new Hauptmenue(this);
        spielfigurAuswahl = new SpielfigurAuswahl(this);
        hauptmenueZustand = 0;
        SpielerUndRundenAnzahlZustand = 1;
        spielfigurAuswahlZustand = 2;
    }

    public void update(){
        spielfigurAuswahl.update();
    }

    public void malen(Graphics2D g2){
        menueHintergrund.malen(g2);
        if(!sp.client.istDran()){
            spielfigurAuswahl.wartenBoxMalen(g2);
            spielfigurAuswahl.wartenMalen(g2);
        }else if (menueZustand == hauptmenueZustand || menueZustand == SpielerUndRundenAnzahlZustand) {
            hauptmenue.malen(g2);
        } else if (menueZustand == spielfigurAuswahlZustand) {
            spielfigurAuswahl.malen(g2);
        }
    }

}
