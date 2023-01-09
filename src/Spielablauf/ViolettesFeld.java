package Spielablauf;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ViolettesFeld extends Feld{
    //private Gegenstand gegenstand;

    public ViolettesFeld(int weltY, int weltX) {
        super(weltY, weltX);
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Purple_Field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void zufaelligenGegenstandErhalten(){

    }


}
