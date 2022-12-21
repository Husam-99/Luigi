package Spielablauf;

import java.awt.image.BufferedImage;

public class GelbesFeld extends Feld{
    public Stern stern;

    public GelbesFeld(int x, int y, Feld naechsteFeld, BufferedImage Bild, Stern stern) {
        super(x, y, naechsteFeld, Bild);
        this.stern = stern;
    }
    public void zurSternPositionFuehren(){

    }
}
