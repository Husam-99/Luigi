package Spielablauf;

import java.awt.image.BufferedImage;

public class GruenesFeld extends Feld{
    private int muenzen;

    public GruenesFeld(int x, int y, Feld naechsteFeld, BufferedImage Bild, int muenzen) {
        super(x, y, naechsteFeld, Bild);
        this.muenzen = muenzen;
    }
    public void muenzeErhalten(){

    }
}
