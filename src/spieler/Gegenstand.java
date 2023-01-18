package spieler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Gegenstand {
    Spieler s;
    public BufferedImage icon;
    public int preis;
    public  int nummer;
    public Gegenstand(Spieler s){
        this.s = s;

    }
    public void effeckteAnwenden(){}

    public void getGegenstandBilder(){}

}
