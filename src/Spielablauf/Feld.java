package Spielablauf;

import java.awt.image.BufferedImage;
import java.sql.Array;
import java.util.ArrayList;

public abstract class Feld {
    private int weltX, weltY;
    Feld suedFeld;
    Feld nordFeld;
    Feld ostFeld;
    Feld westFeld;

    protected BufferedImage feldImage;


    public Feld(){}

    public Feld(int weltX, int weltY){
        this.weltX = weltX;
        this.weltY = weltY;



    }

    public BufferedImage getFeldImage() {
        return feldImage;
    }

    public int getWeltX() {
        return weltX;
    }

    public void setWeltX(int weltX) {
        this.weltX = weltX;
    }

    public int getWeltY() {
        return weltY;
    }

    public void setWeltY(int weltY) {
        this.weltY = weltY;
    }


}
