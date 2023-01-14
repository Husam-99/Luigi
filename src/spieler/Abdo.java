package spieler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Abdo extends Spielfigur {

    public Abdo(Spieler s){
        super(s);
        getSpielFigurBilder();
    }
    @Override
    public void getSpielFigurBilder() {
        try {
            up1= ImageIO.read(getClass().getResourceAsStream("/abdo/up1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/abdo/up2.png"));
            up3= ImageIO.read(getClass().getResourceAsStream("/abdo/up3.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/abdo/down1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/abdo/down2.png"));
            down3= ImageIO.read(getClass().getResourceAsStream("/abdo/down3.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/abdo/right1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/abdo/right2.png"));
            right3= ImageIO.read(getClass().getResourceAsStream("/abdo/right3.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/abdo/left1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/abdo/left2.png"));
            left3= ImageIO.read(getClass().getResourceAsStream("/abdo/left3.png"));
            profile=ImageIO.read(getClass().getResourceAsStream("/abdo/profile.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}