package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bube extends Gegenstand{

    public BufferedImage bube;

    public Bube(Spieler s) {
        super(s);
    }

    @Override
    public void getGegenstandBilder(){
        try {
            bube= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Bube.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
