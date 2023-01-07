package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Taha extends Spielfigur{

    public BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3;
    Spieler s;
    public Taha(Spieler s){
        super(s);
        getSpielFigurBilder();
    }
    @Override
    public void getSpielFigurBilder() {
        try {
            up1= ImageIO.read(getClass().getResourceAsStream("/taha/up1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/taha/up2.png"));
            up3= ImageIO.read(getClass().getResourceAsStream("/taha/up3.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/taha/down1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/taha/down2.png"));
            down3= ImageIO.read(getClass().getResourceAsStream("/taha/down3.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/taha/right1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/taha/right2.png"));
            right3= ImageIO.read(getClass().getResourceAsStream("/taha/right3.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/taha/left1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/taha/left2.png"));
            left3= ImageIO.read(getClass().getResourceAsStream("/taha/left3.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}

