package Spielablauf;

import spieler.Spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ViolettesFeld extends Feld{
    Random random = new Random();
    int gegenstandNum;
    public ViolettesFeld(SpielMapManager mapManager, int weltY, int weltX, int feldNum) {
        super(mapManager, weltY, weltX, feldNum);
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Purple_Field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void effeckteAnwenden(){
        gegenstandNum = random.nextInt(1,6);
        mapManager.sp.mainSpieler.inventar.gegenstandBekommen(gegenstandNum);
        if(!mapManager.sp.alleSpieler.isEmpty())
            for(Spieler spieler : mapManager.sp.alleSpieler) {
                if (spieler.spielfigur != null) {
                    spieler.amSpiel = false;

                }
            }
        mapManager.sp.mainSpieler.amSpiel = false;

    }


}
