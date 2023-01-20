package Minispiele;

import Main.SpielPanel;
import spieler.Spieler;

import java.awt.*;
import java.util.ArrayList;

public class MinispielManager {
    SpielPanel sp;
    Graphics2D g2;
    private int minispielWahl;
    private final int SAMMLER_INDEX = 0;
    Sammler sammler;
    ArrayList<MinispielSpieler> alleMinispielSpieler;

    public SammlerEingabeManager sammlerEingabeManager;
    //minispiel jo
    //runden
    public int gesamtSekundenAnzahl = 63;
    float size =400F;
    int xPosition;
    int yPosition;
    String go = "GO";
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
        alleMinispielSpieler.add(new MinispielSpieler(this, sp.spielablaufManager.mainSpieler, sammlerEingabeManager));
        for(Spieler spieler: sp.alleSpieler){
            if(spieler.spielfigur!=null){
                alleMinispielSpieler.add(new MinispielSpieler(this, spieler, sammlerEingabeManager));
            } else{
                alleMinispielSpieler.add(null);
            }
        }
        sammler = new Sammler(this.sp, alleMinispielSpieler);

    }

    public void update(){
        if(minispielWahl == SAMMLER_INDEX){
            sammler.update();
        }

    }
    //get eingabeManager for Spielpanel
    public void malen(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(sp.marioPartyFont);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        g2.setColor(Color.YELLOW);

        if(minispielWahl == SAMMLER_INDEX){
            sammler.malen(g2);
            for(MinispielSpieler spieler: alleMinispielSpieler){
                if(spieler!= null){
                    spieler.malen(g2);
                }
            }
        }
        for(MinispielSpieler spieler1: alleMinispielSpieler){
            int xPosition = 50;
            if(spieler1!=null){
                g2.drawString("Punktzahl: " + spieler1.punktzahl, xPosition, 50);
                g2.drawString("verbleibend: " + gesamtSekundenAnzahl, xPosition, 80);
            }

        }
        if(gesamtSekundenAnzahl < 60){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, size));
            xPosition = getXfuerCenter(go);
            yPosition = getYfuerCenter(go);
            g2.drawString(go, xPosition, yPosition);
            size += 20;

        }

    }
    public int getXfuerCenter(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = this.sp.bildschirmBreiteMenue/2 - length/2;
        return x;
    }
    public int getYfuerCenter(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
        int y = (this.sp.bildschirmHoeheMenue-96)/2 + length/2;
        return y;
    }

}
