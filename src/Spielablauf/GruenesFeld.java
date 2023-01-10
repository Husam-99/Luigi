package Spielablauf;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class GruenesFeld extends Feld{
    private int muenzen;

    public GruenesFeld(int weltY, int weltX) {
        super(weltY, weltX);
        this.muenzen = muenzen;
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Green_Field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void muenzeErhalten(){

    }
}
