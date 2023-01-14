package Spielablauf;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Muenze {

    public BufferedImage muenze1, muenze2, muenze3, muenze4, muenze5, muenze6;

    public Muenze(){
        getMuenzeBilder();
    }

    public void getMuenzeBilder(){
        try {
            muenze1= ImageIO.read(getClass().getResourceAsStream("/bestandteile/muenze/Coin1.png"));
            muenze2= ImageIO.read(getClass().getResourceAsStream("/bestandteile/muenze/Coin2.png"));
            muenze3= ImageIO.read(getClass().getResourceAsStream("/bestandteile/muenze/Coin3.png"));
            muenze4= ImageIO.read(getClass().getResourceAsStream("/bestandteile/muenze/Coin4.png"));
            muenze5= ImageIO.read(getClass().getResourceAsStream("/bestandteile/muenze/Coin5.png"));
            muenze6= ImageIO.read(getClass().getResourceAsStream("/bestandteile/muenze/Coin6.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
