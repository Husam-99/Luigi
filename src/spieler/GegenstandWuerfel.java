package spieler;

import java.awt.image.BufferedImage;

public abstract class GegenstandWuerfel extends Gegenstand{
    int spriteNum;
    public GegenstandWuerfel(Spieler s) {
        super(s);
    }
    @Override
    public void getGegenstandBilder(){}

}