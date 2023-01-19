package Spielablauf;

import Main.SpielPanel;
import spieler.Spieler;

import java.awt.*;

public class SpielablaufManager {
    public SpielPanel sp;
    Graphics2D g2;

    public SpielMapManager mapManager;
    public Spieler mainSpieler;
    public int spielerNum = 0;
    public Shop shop;
    public boolean shopGeoeffnet = false;

    public SpielablaufManager(SpielPanel sp) {
        this.sp = sp;
        mapManager = new SpielMapManager(this);
        mainSpieler = new Spieler(this);
        shop = new Shop(this);


    }

    public void update() {
        mapManager.update();
        if (mainSpieler.spielfigur != null) {
            if (!sp.alleSpieler.isEmpty())
                for (Spieler spieler : sp.alleSpieler) {
                    if (spieler.spielfigur != null) {
                        spieler.update();
                    }
                }
            mainSpieler.update();
        }
    }

    public void malen(Graphics2D g2){
        this.g2 = g2;
        mapManager.malen(g2);
        if(mainSpieler.spielfigur!=null){
            if(!sp.alleSpieler.isEmpty())
                for(Spieler spieler : sp.alleSpieler) {
                    if (spieler.spielfigur != null) {
                        spieler.malen(g2);
                    }
                }

            mainSpieler.malen(g2);
        }
        if(shopGeoeffnet){
            shop.malen(g2);
        }
        mainSpieler.konto.malen(g2);
        if(mainSpieler.spielfigur!=null) {
            if (!sp.alleSpieler.isEmpty())
                for (Spieler spieler : sp.alleSpieler) {
                    if (spieler.spielfigur != null) {
                        spielerNum++;
                        spieler.konto.malen(g2);

                    }
                }
        }
        spielerNum = 0;
    }
}
