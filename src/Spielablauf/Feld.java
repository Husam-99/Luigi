package Spielablauf;

import java.awt.image.BufferedImage;

public abstract class Feld {

    public int weltX, weltY;
    public int feldNum;
    SpielMapManager mapManager;
    public Feld suedFeld, nordFeld, ostFeld, westFeld;
    protected BufferedImage feldImage;
    public boolean hatStern;

    public Feld(SpielMapManager mapManager, int weltY, int weltX, int feldNum){
        this.mapManager = mapManager;
        this.weltY = weltY;
        this.weltX = weltX;
        this.feldNum = feldNum;
        hatStern = false;
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
    public void effeckteAnwenden(){}

}
