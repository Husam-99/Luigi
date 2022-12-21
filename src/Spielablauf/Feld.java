package Spielablauf;

import java.awt.image.BufferedImage;

public abstract class Feld {
    private int xPosition;
    private int yPosition;
    private Feld naechsteFeld;
    private BufferedImage feldBild;


    public Feld(){}

    public Feld(int x, int y, Feld naechsteFeld, BufferedImage Bild){
        xPosition = x;
        yPosition = y;
        this.naechsteFeld = naechsteFeld;
        this.feldBild = Bild;
    }

    public Feld getNaechsteFeld() {
        return naechsteFeld;
    }
    public void setNaechsteFeld(Feld naechsteFeld){
        this.naechsteFeld = naechsteFeld;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public BufferedImage getFeldBild() {
        return feldBild;
    }

    public void setFeldBild(BufferedImage feldBild) {
        this.feldBild = feldBild;
    }
    public void maleFeld(){

    }
}
