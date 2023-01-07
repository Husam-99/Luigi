package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Repos extends Gegenstand{

    public BufferedImage repos;

    public Repos(Spieler s) {
        super(s);
    }

    @Override
    public void getGegenstandBilder(){
        try {
            repos= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Repos.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
