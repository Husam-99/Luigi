package Minispiele;

import Main.SpielPanel;
import Spielablauf.Fliese;

import java.awt.*;
import java.util.ArrayList;

public abstract class Minispiel {
    SpielPanel sp;
    Fliese[] minispielFliesen;
    int[][] minispielMap;
    ArrayList<MinispielSpieler> alleMinispielSpieler;


    public Minispiel(SpielPanel sp, ArrayList<MinispielSpieler> alleMinispielSpieler){
        this.sp = sp;
        this.alleMinispielSpieler = alleMinispielSpieler;


    }
    public abstract void getFlieseImage();
    public abstract void mapLaden();
    public abstract void malen(Graphics2D g2);

}
