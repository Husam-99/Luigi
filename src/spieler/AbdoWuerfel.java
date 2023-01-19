package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AbdoWuerfel extends Wuerfel{


    public AbdoWuerfel(Spieler spieler){
        super(spieler);
        getWuerfelBilder();
    }
    @Override
    public void getWuerfelBilder() {
        try {
            wuerfel1= ImageIO.read(getClass().getResourceAsStream("/abdo/wuerfel1.png"));
            wuerfel2= ImageIO.read(getClass().getResourceAsStream("/abdo/wuerfel2.png"));
            wuerfel3= ImageIO.read(getClass().getResourceAsStream("/abdo/wuerfel3.png"));
            wuerfel4= ImageIO.read(getClass().getResourceAsStream("/abdo/wuerfel4.png"));
            wuerfel5= ImageIO.read(getClass().getResourceAsStream("/abdo/wuerfel5.png"));
            wuerfel6= ImageIO.read(getClass().getResourceAsStream("/abdo/wuerfel6.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
