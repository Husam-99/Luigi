package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Husam extends Spielfigur {

    public Husam(Spieler s){
        super(s);
        getSpielFigurBilder();
    }
    @Override
    public void getSpielFigurBilder() {
        try {
            up1= ImageIO.read(getClass().getResourceAsStream("/husam/up1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/husam/up2.png"));
            up3= ImageIO.read(getClass().getResourceAsStream("/husam/up3.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/husam/down1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/husam/down2.png"));
            down3= ImageIO.read(getClass().getResourceAsStream("/husam/down3.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/husam/right1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/husam/right2.png"));
            right3= ImageIO.read(getClass().getResourceAsStream("/husam/right3.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/husam/left1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/husam/left2.png"));
            left3= ImageIO.read(getClass().getResourceAsStream("/husam/left3.png"));
            profile= ImageIO.read(getClass().getResourceAsStream("/husam/profile.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}
