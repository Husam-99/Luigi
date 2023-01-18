package spieler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Yousef extends Spielfigur {

    public Yousef(){
        super();
        getSpielFigurBilder();

    }
    public Yousef(Spieler s){
        super(s);
        getSpielFigurBilder();
    }
    @Override
    public void getSpielFigurBilder() {
        try {
            up1= ImageIO.read(getClass().getResourceAsStream("/yousef/up1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/yousef/up2.png"));
            up3= ImageIO.read(getClass().getResourceAsStream("/yousef/up3.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/yousef/down1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/yousef/down2.png"));
            down3= ImageIO.read(getClass().getResourceAsStream("/yousef/down3.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/yousef/right1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/yousef/right2.png"));
            right3= ImageIO.read(getClass().getResourceAsStream("/yousef/right3.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/yousef/left1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/yousef/left2.png"));
            left3= ImageIO.read(getClass().getResourceAsStream("/yousef/left3.png"));
            profile= ImageIO.read(getClass().getResourceAsStream("/yousef/profile.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}