package spieler;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Taha extends Spielfigur{

    public Taha(){
        super();
        getSpielFigurBilder();

    }
    public Taha(Spieler spieler){
        super(spieler);
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
            profile= ImageIO.read(getClass().getResourceAsStream("/taha/profile.png"));
            fallen1=ImageIO.read(getClass().getResourceAsStream("/taha/Taha0.png"));
            fallen2=ImageIO.read(getClass().getResourceAsStream("/taha/Taha1.png"));
            fallen3=ImageIO.read(getClass().getResourceAsStream("/taha/Taha2.png"));
            fallen4=ImageIO.read(getClass().getResourceAsStream("/taha/Taha3.png"));
            fallen5=ImageIO.read(getClass().getResourceAsStream("/taha/Taha4.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}

