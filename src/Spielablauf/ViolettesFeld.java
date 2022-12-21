package Spielablauf;

import java.awt.image.BufferedImage;

public class ViolettesFeld extends Feld{
    private Gegenstand gegenstand;

    public ViolettesFeld(int x, int y, Feld naechsteFeld, BufferedImage Bild) {
        super(x, y, naechsteFeld, Bild);

    }
    public void zufaelligenGegenstandErhalten(){

    }
}
