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
    public Sammler sammler;
    public MinispielSpieler mainMinispielSpieler;
    public ArrayList<MinispielSpieler> alleMinispielSpieler;

    public SammlerEingabeManager sammlerEingabeManager;

    public int gesamtSekundenAnzahl = 64;
    public float size = 700F;
    int xPosition;
    public int yPosition = 432;
    String go = "GO";
    public MinispielManager(SpielPanel sp, int minispielIndex) {
        this.sp = sp;
        alleMinispielSpieler = new ArrayList<>();

        if(minispielIndex == SAMMLER_INDEX){
            minispielWahl = SAMMLER_INDEX;
        }
        setzeWerte();
    }
    public void setzeWerte(){
        mainMinispielSpieler = new MinispielSpieler(this, sp.spielablaufManager.mainSpieler);
        for(Spieler spieler: sp.alleSpieler){
            if(spieler.spielfigur!=null){
                alleMinispielSpieler.add(new MinispielSpieler(this, spieler));
            }
            else{
                alleMinispielSpieler.add(null);
            }
        }
        sammlerEingabeManager = new SammlerEingabeManager(this, mainMinispielSpieler);
        sammler = new Sammler(this.sp, mainMinispielSpieler, alleMinispielSpieler);

    }

    public void update(){
        if(minispielWahl == SAMMLER_INDEX) {
            sammler.update();
        }

    }
    public void malen(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(sp.marioPartyFont);


        if(minispielWahl == SAMMLER_INDEX){
            sammler.malen(g2);
            mainMinispielSpieler.malen(g2);
            for(MinispielSpieler spieler: alleMinispielSpieler) {
                if (spieler != null) {
                    spieler.malen(g2);
                }
            }
            if(sammler.spider1 != null){
                sammler.spider1.spiderMalen(g2);
            }
            if(sammler.spider2 != null){
                sammler.spider2.spiderMalen(g2);
            }
            if(sammler.spider3 != null){
                sammler.spider3.spiderMalen(g2);
            }

            int width = 25;
            mainMinispielSpieler.minispielerBoxMalen(g2, width);
            mainMinispielSpieler.minispielerStatusMalen(g2, width);

            width += 230;
            int spielerIndex = 0;
            for(MinispielSpieler spieler: alleMinispielSpieler){
                if(spieler!= null){
                    spieler.minispielerBoxMalen(g2, width);
                    spieler.minispielerStatusMalen(g2, width);
                    if(spielerIndex == 0){
                        width = 1205;
                    } else{
                        width -= 230;
                    }
                    spielerIndex++;

                }
            }
        }
        zeitBoxmalen(g2);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));

        g2.setColor(Color.YELLOW);

        if(gesamtSekundenAnzahl <= 63 && gesamtSekundenAnzahl > 60){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, size));
            xPosition = getXfuerCenter(Integer.toString(gesamtSekundenAnzahl-60));
            g2.drawString(Integer.toString(gesamtSekundenAnzahl-60), xPosition, yPosition);
            yPosition += 15;
            size += 50F;
        }
        else if(gesamtSekundenAnzahl == 60){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, size));
            xPosition = getXfuerCenter(go);
            yPosition += 15;
            g2.drawString(go, xPosition, yPosition);
            size += 50F;
        }
    }
    private void zeitBoxmalen(Graphics2D g2){
        if(gesamtSekundenAnzahl <= 60) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
            g2.setColor(new Color(20, 9, 54));
            g2.fillRoundRect(620, 5, 200, 90, 25, 25);
            if(gesamtSekundenAnzahl >= 30){
                g2.setColor(new Color(93, 206, 31, 203));
            } else if(gesamtSekundenAnzahl >= 10){
                g2.setColor(new Color(255, 243, 0, 255));
            } else{
                g2.setColor(new Color(196, 29, 29, 203));
            }
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(620 + 5, 10, 190, 80, 15, 15);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
            if(gesamtSekundenAnzahl < 10){
                g2.drawString("" + gesamtSekundenAnzahl, 620 + 70, 70);
            } else{
                g2.drawString("" + gesamtSekundenAnzahl, 620 + 50, 70);
            }
        }

    }
    public int getXfuerCenter(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = this.sp.bildschirmBreiteMenue / 2 - length / 2;
        return x;
    }

}
