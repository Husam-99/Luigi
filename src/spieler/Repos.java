package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Repos extends Gegenstand{


    public Repos(Spieler s) {
        super(s);
        getGegenstandBilder();
    }

    @Override
    public void getGegenstandBilder(){
        try {
            icon= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Repos.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
