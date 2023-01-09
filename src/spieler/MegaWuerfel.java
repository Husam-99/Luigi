package spieler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MegaWuerfel extends GegenstandWuerfel{

    public BufferedImage wuerfel7, wuerfel8, wuerfel9, wuerfel10, wuerfel11, wuerfel12;

    public MegaWuerfel(Spieler s) {
        super(s);
        getGegenstandBilder();
    }

    @Override
    public void getGegenstandBilder(){
        try {
            icon = ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Megawuerfel.png"));
            wuerfel7= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/megaWuerfel/wuerfel7.png"));
            wuerfel8= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/megaWuerfel/wuerfel8.png"));
            wuerfel9= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/megaWuerfel/wuerfel9.png"));
            wuerfel10= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/megaWuerfel/wuerfel10.png"));
            wuerfel11= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/megaWuerfel/wuerfel11.png"));
            wuerfel12= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/megaWuerfel/wuerfel12.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void wuerfelmalen(Graphics2D g2){

    }
}
