package Minispiele;

import Main.SpielPanel;
import spieler.Spieler;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MinispielManager {
    SpielPanel sp;
    Graphics2D g2;
    private int minispielWahl;
    private final int SAMMLER_INDEX = 0;
    Sammler sammler;
    ArrayList<MinispielSpieler> alleMinispielSpieler;

    public SammlerEingabeManager sammlerEingabeManager;

    public MinispielManager(SpielPanel sp, int minispielIndex) {
        this.sp = sp;
        alleMinispielSpieler = new ArrayList<>();

        if(minispielIndex == SAMMLER_INDEX){
            minispielWahl = SAMMLER_INDEX;
            sammlerEingabeManager = new SammlerEingabeManager();

            }
        setzeWerte();

    }
    public void setzeWerte(){
        alleMinispielSpieler.add(new MinispielSpieler(this, sp.mainSpieler, sammlerEingabeManager));
        // for(Spieler spieler: sp.alleSpieler){
        //     if(spieler.spielfigur!=null){
        //         alleMinispielSpieler.add(new MinispielSpieler(this, spieler, sammlerEingabeManager));
        //     } else{
        //         alleMinispielSpieler.add(null);
        //     }
        // }
        sammler = new Sammler(this.sp, alleMinispielSpieler);

    }

    public void update(){
        if(minispielWahl == SAMMLER_INDEX){
            sammler.update();
        }

    }
    //get eingabeManager for Spielpanel
    public void malen(Graphics2D g2){
        if(minispielWahl == SAMMLER_INDEX){
            sammler.malen(g2);
            for(MinispielSpieler spieler: alleMinispielSpieler){
                if(spieler!= null){
                    spieler.malen(g2);
                }
            }
        }

    }
}
