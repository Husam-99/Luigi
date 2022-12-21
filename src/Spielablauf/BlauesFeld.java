package Spielablauf;

import java.awt.image.BufferedImage;

public class BlauesFeld extends Feld{
    private Gegenstand gegenstand;

    public BlauesFeld(int x, int y, Feld naechsteFeld, BufferedImage Bild, Gegenstand gegenstand) {
        super(x, y, naechsteFeld, Bild);
        this.gegenstand = gegenstand;
    }

    public Gegenstand getGegenstand() {
        return gegenstand;
    }

    public void setGegenstand(Gegenstand gegenstand) {
        this.gegenstand = gegenstand;
    }
    public void gegenstandWaehlen(){

    }
    public void gegenstandKlauen(){

    }
}
