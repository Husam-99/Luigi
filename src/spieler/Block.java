package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Block extends Gegenstand{

    public BufferedImage block;
    public Block(Spieler s) {
        super(s);
    }

    @Override
    public void getGegenstandBilder(){
        try {
            block= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Block.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
