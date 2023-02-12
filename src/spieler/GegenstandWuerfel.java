package spieler;

import java.awt.*;

public abstract class GegenstandWuerfel extends Gegenstand{

    int spriteNum;
    int spriteZaehler;

    public GegenstandWuerfel(Spieler spieler) {
        super(spieler);
    }

    @Override
    public void getGegenstandBilder(){}

    @Override
    public void effeckteAnwenden(){}

    public void schritteAnzahlBestimmen(){}

    public void update(){}

    public void malen(Graphics2D g2){}


}