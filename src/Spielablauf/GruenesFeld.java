package Spielablauf;

import spieler.Spieler;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GruenesFeld extends Feld{
    Random random = new Random();


    public GruenesFeld(SpielMapManager mapManager, int weltY, int weltX, int feldNum) {
        super(mapManager, weltY, weltX, feldNum);
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Green_Field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void effeckteAnwenden(){
        int erhalteneMuenzen = random.nextInt(1, 3);
        mapManager.spielablaufManager.mainSpieler.konto.muenzenErhalten(erhalteneMuenzen);
        if(!mapManager.spielablaufManager.sp.alleSpieler.isEmpty())
            for(Spieler spieler : mapManager.spielablaufManager.sp.alleSpieler) {
                if (spieler.spielfigur != null) {
                    spieler.amSpiel = false;

                }
            }
        if(!mapManager.spielablaufManager.mainSpieler.aktuellesFeld.hatStern){
            mapManager.spielablaufManager.mainSpieler.amSpiel = false;
        }else{
            mapManager.stern.sternKaufen = true;
        }
    }
}
