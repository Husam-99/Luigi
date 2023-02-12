package Spielablauf;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Muenze {

    public BufferedImage muenze;

    public Muenze(){

        //Münze Bild
        try {
            muenze = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/muenze/Coin1.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
