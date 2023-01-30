package Spielablauf;

import spieler.Spieler;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RotesFeld extends Feld{
    Random random = new Random();


    public RotesFeld(SpielMapManager mapManager, int weltY, int weltX, int feldNum) {
        super(mapManager, weltY, weltX, feldNum);
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Red_field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void effeckteAnwenden(){
        int verlierneMuenzen = random.nextInt(1, 3);
        mapManager.spielablaufManager.mainSpieler.konto.muenzenVerlieren(verlierneMuenzen);
        if(!mapManager.spielablaufManager.sp.alleSpieler.isEmpty())
            for(Spieler spieler : mapManager.spielablaufManager.sp.alleSpieler) {
                if (spieler.spielfigur != null) {
                    spieler.amSpiel = false;
                }
            }
        if(!mapManager.spielablaufManager.mainSpieler.aktuellesFeld.hatStern) {
            mapManager.spielablaufManager.mainSpieler.amSpiel = false;
        }else{
            mapManager.stern.sternKaufen = true;
        }
    }
}
