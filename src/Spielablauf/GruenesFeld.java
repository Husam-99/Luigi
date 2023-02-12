package Spielablauf;

import spieler.Spieler;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GruenesFeld extends Feld{

    Random random;

    public GruenesFeld(SpielMapManager mapManager, int weltY, int weltX, int feldNum) {
        super(mapManager, weltY, weltX, feldNum);
        random = new Random();

        //Fled Bild
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Green_Field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void effeckteAnwenden(){

        //Random Nummer zwischen 1 und 3
        int erhalteneMuenzen = random.nextInt(1, 4);

        //Random Nummer wird zum Spieler Münzen Anzahl addiert
        mapManager.spielablaufManager.mainSpieler.konto.muenzenErhalten(erhalteneMuenzen);
        if(!mapManager.spielablaufManager.sp.alleSpieler.isEmpty())
            for(Spieler spieler : mapManager.spielablaufManager.sp.alleSpieler) {
                if (spieler.spielfigur != null) {
                    spieler.amSpiel = false;

                }
            }

        //wenn das Feld ein Stern hat, dann kann man der Stern kaufen
        //andersrum wird der Spieler zug beendet
        if(!mapManager.spielablaufManager.mainSpieler.aktuellesFeld.hatStern){
            mapManager.spielablaufManager.mainSpieler.amSpiel = false;
        }else{
            mapManager.stern.sternKaufen = true;
            mapManager.spielablaufManager.miniMapZustand = false;
        }
    }

}
