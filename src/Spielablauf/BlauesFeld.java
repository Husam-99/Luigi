package Spielablauf;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BlauesFeld extends Feld{


    public BlauesFeld(SpielMapManager mapManager, int weltY, int weltX, int feldNum) {
        super(mapManager, weltY, weltX, feldNum);
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Blue_Field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
