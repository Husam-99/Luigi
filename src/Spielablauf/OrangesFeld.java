package Spielablauf;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OrangesFeld extends Feld{

    public OrangesFeld(int x, int y) {
        super(x, y);

        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Orange_Field_.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
