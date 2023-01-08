package Spielablauf;

import java.awt.image.BufferedImage;

public class Fliese {
    public BufferedImage flieseImage;
    public int weltX, weltY;
    public boolean kollision = false;
    public Feld feld;

    public Fliese(){

    }
    public BufferedImage getFlieseImage(){
        return flieseImage;
    }
}
