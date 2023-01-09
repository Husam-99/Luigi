package Spielablauf;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BlauesFeld extends Feld{
//    private Gegenstand gegenstand;

    public BlauesFeld(int x, int y) {
        super(x, y);
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Blue_Field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


// public Gegenstand getGegenstand() {
   //     return gegenstand;
   // }
//
   // public void setGegenstand(Gegenstand gegenstand) {
   //     this.gegenstand = gegenstand;
   // }
    //public void gegenstandWaehlen(){
//
    //}
    //public void gegenstandKlauen(){
//
    //}
}
