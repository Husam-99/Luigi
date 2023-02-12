package Spielablauf;

import spieler.Spieler;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ViolettesFeld extends Feld{

    Random random = new Random();

    public ViolettesFeld(SpielMapManager mapManager, int weltY, int weltX, int feldNum) {
        super(mapManager, weltY, weltX, feldNum);

        //Feld Bild
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Purple_Field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void effeckteAnwenden(){

        //wenn das Inventar voll ist, passiert nichts
        if(!mapManager.spielablaufManager.mainSpieler.inventar.inventarVoll) {

            //Random Nummer zwischen 1 und 6
            int gegenstandNum = random.nextInt(1, 7);

            //der Gegenstand mit dem Random Nummer wird zum Spieler Inventar addiert
            mapManager.spielablaufManager.mainSpieler.inventar.gegenstandBekommen(gegenstandNum);
            if (!mapManager.spielablaufManager.sp.alleSpieler.isEmpty())
                for (Spieler spieler : mapManager.spielablaufManager.sp.alleSpieler) {
                    if (spieler.spielfigur != null) {
                        spieler.amSpiel = false;
                    }
                }
        }

        //wenn das Feld ein Stern hat, dann kann man der Stern kaufen
        //andersrum wird der Spieler zug beendet
        if(!mapManager.spielablaufManager.mainSpieler.aktuellesFeld.hatStern) {
            mapManager.spielablaufManager.mainSpieler.amSpiel = false;
        }else{
            mapManager.stern.sternKaufen = true;
            mapManager.spielablaufManager.miniMapZustand = false;
        }
    }

}
