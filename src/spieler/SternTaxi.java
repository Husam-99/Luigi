package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SternTaxi extends Gegenstand{


    public SternTaxi(Spieler s) {
        super(s);
        preis = 15;
        nummer = 6;
        getGegenstandBilder();
    }

    @Override
    public void getGegenstandBilder(){
        try {
            icon = ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Sterntaxi.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
