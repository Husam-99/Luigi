package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MiniWuerfel extends GegenstandWuerfel{

    public BufferedImage miniWuerfel, wuerfel1, wuerfel2, wuerfel3;

    public MiniWuerfel(Spieler s) {
        super(s);
    }

    @Override
    public void getGegenstandBilder(){
        try {
            miniWuerfel= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Miniwuerfel.png"));
            wuerfel1= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/miniWuerfel/wuerfel1.png"));
            wuerfel2= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/miniWuerfel/wuerfel2.png"));
            wuerfel3= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/miniWuerfel/wuerfel3.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
