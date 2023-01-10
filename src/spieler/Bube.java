package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bube extends Gegenstand{


    public Bube(Spieler s) {
        super(s);
        getGegenstandBilder();
    }

    @Override
    public void getGegenstandBilder(){
        try {
            icon= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Bube.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}
