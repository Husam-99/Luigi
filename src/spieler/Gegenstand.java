package spieler;

import java.awt.image.BufferedImage;

public abstract class Gegenstand {

    Spieler spieler;
    public BufferedImage icon;

    public int preis;
    public int nummer;

    public Gegenstand(Spieler spieler){
        this.spieler = spieler;
    }

    public void getGegenstandBilder(){}

    public void effeckteAnwenden(){}

}
