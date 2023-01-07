package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SternTaxi extends Gegenstand{

    public BufferedImage sternTaxi;

    public SternTaxi(Spieler s) {
        super(s);
    }

    @Override
    public void getGegenstandBilder(){
        try {
            sternTaxi = ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Sterntaxi.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
