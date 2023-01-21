package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SternTaxi extends Gegenstand{


    public SternTaxi(Spieler spieler) {
        super(spieler);
        preis = 15;
        nummer = 6;
        getGegenstandBilder();
    }

    @Override
    public void getGegenstandBilder(){
        try {
            icon = ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Sterntaxi.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void effeckteAnwenden(){
        spieler.zuStern = true;
        spieler.naechstesFeld = spieler.spielablaufManager.mapManager.mapFliesen[spieler.spielablaufManager.mapManager.stern.sternFeldZeile][spieler.spielablaufManager.mapManager.stern.sternFeldSpalte].feld;
        spieler.aktuellesFeld = null;
        spieler.aktuellFeld = spieler.spielablaufManager.mapManager.mapFliesen[spieler.spielablaufManager.mapManager.stern.sternFeldZeile][spieler.spielablaufManager.mapManager.stern.sternFeldSpalte].feld;
        spieler.tempFeld = null;
        spieler.bewegung = true;
        spieler.inventarZustand = false;
        spieler.spielablaufManager.mapManager.mapEingabeManager.iGedrueckt = false;
    }

}
