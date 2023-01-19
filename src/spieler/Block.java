package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Block extends Gegenstand{

    public Block(Spieler spieler) {
        super(spieler);
        preis = 7;
        nummer = 1;
        getGegenstandBilder();
    }

    @Override
    public void getGegenstandBilder(){
        try {
            icon= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Block.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
