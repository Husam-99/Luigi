package Spielablauf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GruenesFeld extends Feld{
    private int erhalteneMuenzen;
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
        erhalteneMuenzen = random.nextInt(1,3);
        mapManager.sp.spieler.konto.muenzenErhalten(erhalteneMuenzen);
    }
}
