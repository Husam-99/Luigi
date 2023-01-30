package Spielablauf;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class GelbesFeld extends Feld{
    public Stern stern;

    public GelbesFeld(SpielMapManager mapManager, int weltY, int weltX, int feldNum) {
        super(mapManager, weltY, weltX, feldNum);
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Yellow_Field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void effeckteAnwenden(){
        if(mapManager.spielablaufManager.mainSpieler.aktuellesFeld.hatStern){
            mapManager.stern.sternKaufen = true;
        }else{
            mapManager.spielablaufManager.mainSpieler.zuStern = true;
            mapManager.spielablaufManager.mainSpieler.vorherigesFeld = null;
            mapManager.spielablaufManager.mainSpieler.aktuellesFeld = mapManager.mapFliesen[mapManager.stern.sternFeldZeile][mapManager.stern.sternFeldSpalte].feld;
            mapManager.spielablaufManager.mainSpieler.bewegung = true;
        }
    }
}
