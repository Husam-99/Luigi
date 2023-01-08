package Spielablauf;

import java.awt.image.BufferedImage;
import java.sql.Array;
import java.util.ArrayList;

public abstract class Feld {
    private int weltX, weltY;
    private ArrayList<Feld> naechstesFeld ;
    private ArrayList<Feld> vorherigesFeld;
    protected BufferedImage feldImage;


    public Feld(){}

    public Feld(int weltX, int weltY){
        this.weltX = weltX;
        this.weltY = weltY;
        naechstesFeld = new ArrayList<>();
        vorherigesFeld = new ArrayList<>();


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

    public ArrayList<Feld> getVorherigesFeld() {
        return vorherigesFeld;
    }

    public void addVorherigesFeld(Feld vorherigesFeld) {
        this.vorherigesFeld.add(vorherigesFeld);
    }

    public ArrayList<Feld> getNaechstesFeld() {
        return naechstesFeld;
    }
    public void addNaechstesFeld(Feld naechsteFeld){
        this.naechstesFeld.add(naechsteFeld);
    }
}
