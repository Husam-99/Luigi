package Spielablauf;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OrangesFeld extends Feld{

    public OrangesFeld(SpielMapManager mapManager, int weltY, int weltX, int feldNum) {
        super(mapManager, weltY, weltX, feldNum);
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Orange_Field_.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void effeckteAnwenden(){
        mapManager.spielablaufManager.shopGeoeffnet = true;
    }
}
