package spieler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Gegenstand {
    Spieler s;
    int positionX, positionY ;
    public BufferedImage icon;

    public Gegenstand(Spieler s){
        this.s = s;

    }

    public void effeckteAnwenden(){}

    public void getGegenstandBilder(){}

    public void malen(Graphics2D g2){
        g2.drawImage(icon, positionX, positionY, s.sp.vergroesserteFliesenGroesse, s.sp.vergroesserteFliesenGroesse, null);
    }
}
