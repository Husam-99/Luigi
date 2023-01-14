package Spielablauf;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RotesFeld extends Feld{
    private int verlierneMuenzen;
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
        verlierneMuenzen = random.nextInt(1,3);
        mapManager.sp.spieler.konto.muenzenVerlieren(verlierneMuenzen);
    }
}
