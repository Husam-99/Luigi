package spieler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Gegenstand {
    Spieler s;
    int positionX, positionY ;
    public BufferedImage icon;

    public Gegenstand(Spieler s, int x, int y){
        this.s = s;
        this.positionX = 20;
        this.positionY = 20;
    }

    public void effeckteAnwenden(){}

    public void getGegenstandBilder(){}

    public void malen(Graphics2D g2){
        g2.drawImage(icon, positionX, positionY, s.sp.flieseGroesse, s.sp.flieseGroesse, null);
    }
}
