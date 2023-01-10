package Spielablauf;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class RotesFeld extends Feld{
    private int muenzen;


    public RotesFeld(int weltY, int weltX) {
        super(weltY, weltX);
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Red_field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void muenzeVerlieren(){

    }
}
