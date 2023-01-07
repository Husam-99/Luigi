package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TahaWuerfel extends Wuerfel{
    public BufferedImage wuerfel1, wuerfel2, wuerfel3, wuerfel4, wuerfel5, wuerfel6;
    Spieler s;
    public TahaWuerfel(Spieler s){
        this.s = s;
        getWuerfelBilder();
    }

    @Override
    public void getWuerfelBilder() {
        try {
            wuerfel1= ImageIO.read(getClass().getResourceAsStream("/taha/wuerfel1.png"));
            wuerfel2= ImageIO.read(getClass().getResourceAsStream("/taha/wuerfel2.png"));
            wuerfel3= ImageIO.read(getClass().getResourceAsStream("/taha/wuerfel3.png"));
            wuerfel4= ImageIO.read(getClass().getResourceAsStream("/taha/wuerfel4.png"));
            wuerfel5= ImageIO.read(getClass().getResourceAsStream("/taha/wuerfel5.png"));
            wuerfel6= ImageIO.read(getClass().getResourceAsStream("/taha/wuerfel6.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }

    }
}
