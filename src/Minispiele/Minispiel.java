package Minispiele;

import Main.SpielPanel;
import Spielablauf.Fliese;

import java.awt.*;
import java.util.ArrayList;

public abstract class Minispiel {
    SpielPanel sp;
    Fliese[] minispielFliesen;
    int[][] minispielMap;

    MinispielSpieler mainMinispielSpieler;
    ArrayList<MinispielSpieler> alleMinispielSpieler;


    public Minispiel(SpielPanel sp, MinispielSpieler mainMinispielSpieler, ArrayList<MinispielSpieler> alleMinispielSpieler){
        this.sp = sp;
        this.mainMinispielSpieler = mainMinispielSpieler;
        this.alleMinispielSpieler = alleMinispielSpieler;


    }
    public abstract void mapLaden();

    public abstract void siegerFestlegen();

    public abstract void siegerKuerenMalen(Graphics2D g2);

    public abstract void malen(Graphics2D g2);

}
