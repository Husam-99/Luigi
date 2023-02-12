package spieler;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class YousefWuerfel extends Wuerfel{

    public YousefWuerfel(Spieler spieler){
        super(spieler);
        getWuerfelBilder();
    }

    @Override
    public void getWuerfelBilder() {
        try {
            wuerfel1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/wuerfel1.png")));
            wuerfel2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/wuerfel2.png")));
            wuerfel3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/wuerfel3.png")));
            wuerfel4= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/wuerfel4.png")));
            wuerfel5= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/wuerfel5.png")));
            wuerfel6= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/yousef/wuerfel6.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
