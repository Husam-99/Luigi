package spieler;

import java.awt.image.BufferedImage;

public abstract class GegenstandWuerfel extends Gegenstand{
    int spriteNum;
    public GegenstandWuerfel(Spieler s) {
        super(s, s.positionX, s.positionY);
    }
    @Override
    public void getGegenstandBilder(){}

}