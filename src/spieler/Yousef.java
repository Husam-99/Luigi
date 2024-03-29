package spieler;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Yousef extends Spielfigur {

    public Yousef(){
        super();
        getSpielFigurBilder();
    }

    public Yousef(Spieler spieler){
        super(spieler);
        getSpielFigurBilder();
    }

    @Override
    public void getSpielFigurBilder() {
        try {
            up1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/up1.png")));
            up2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/up2.png")));
            up3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/up3.png")));
            down1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/down1.png")));
            down2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/down2.png")));
            down3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/down3.png")));
            right1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/right1.png")));
            right2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/right2.png")));
            right3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/right3.png")));
            left1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/left1.png")));
            left2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/left2.png")));
            left3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/left3.png")));
            profile= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/profile.png")));
            fallen1=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/Yousef0.png")));
            fallen2=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/Yousef1.png")));
            fallen3=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/Yousef2.png")));
            fallen4=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/Yousef3.png")));
            fallen5=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/Yousef4.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}