package spieler;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Abdo extends Spielfigur {

    public Abdo(){
        super();
        getSpielFigurBilder();
    }

    public Abdo(Spieler spieler){
        super(spieler);
        getSpielFigurBilder();
    }

    @Override
    public void getSpielFigurBilder() {
        try {
            up1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/up1.png")));
            up2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/up2.png")));
            up3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/up3.png")));
            down1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/down1.png")));
            down2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/down2.png")));
            down3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/down3.png")));
            right1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/right1.png")));
            right2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/right2.png")));
            right3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/right3.png")));
            left1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/left1.png")));
            left2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/left2.png")));
            left3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/left3.png")));
            profile=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/profile.png")));
            fallen1=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/Abdo0.png")));
            fallen2=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/Abdo1.png")));
            fallen3=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/Abdo2.png")));
            fallen4=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/Abdo3.png")));
            fallen5=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/abdo/Abdo4.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}