package Spielablauf;

import java.awt.image.BufferedImage;

public class RotesFeld extends Feld{
    private int muenzen;

    public RotesFeld(int x, int y, Feld naechsteFeld, BufferedImage Bild, int muenzen) {
        super(x, y, naechsteFeld, Bild);
        this.muenzen = muenzen;
    }
    public void muenzeVerlieren(){

    }
}
