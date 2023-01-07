package spieler;

import java.awt.image.BufferedImage;

public abstract class GegenstandWuerfel extends Gegenstand{
    public GegenstandWuerfel(Spieler s) {
        super(s);
    }
    @Override
    public void getGegenstandBilder(){}
}