package Spielablauf;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GelbesFeld extends Feld{
    public Stern stern;

    public GelbesFeld(int weltY, int weltX) {
        super(weltY, weltX);
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Yellow_Field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void zurSternPositionFuehren(){

    }
}
