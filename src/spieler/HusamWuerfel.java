package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HusamWuerfel extends Wuerfel{

    public HusamWuerfel(Spieler spieler){
        super(spieler);
        getWuerfelBilder();
    }
    @Override
    public void getWuerfelBilder() {
        try {
            wuerfel1= ImageIO.read(getClass().getResourceAsStream("/husam/wuerfel1.png"));
            wuerfel2= ImageIO.read(getClass().getResourceAsStream("/husam/wuerfel2.png"));
            wuerfel3= ImageIO.read(getClass().getResourceAsStream("/husam/wuerfel3.png"));
            wuerfel4= ImageIO.read(getClass().getResourceAsStream("/husam/wuerfel4.png"));
            wuerfel5= ImageIO.read(getClass().getResourceAsStream("/husam/wuerfel5.png"));
            wuerfel6= ImageIO.read(getClass().getResourceAsStream("/husam/wuerfel6.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
