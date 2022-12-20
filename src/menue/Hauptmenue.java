package menue;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Hauptmenue {

    MenueManager mn;
    Font marioPartyFont;
    Graphics2D g2;

    public int befehlNum1 = 0;
    public int befehlNum2 = -1;
    public int befehlNum3 = 0;
    public int enterZustand = 0;

    public Hauptmenue(MenueManager mn){
        this.mn = mn;

        try {
            InputStream is = getClass().getResourceAsStream("/font/Mario-Party-Hudson-Font.ttf");
            marioPartyFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException| IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){

    }
    public void malen(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(marioPartyFont);
        g2.setColor(Color.white);
        if(mn.hauptmenueZustand == 0){
            titelMalen();
            optionenMalen();
        }else if(mn.hauptmenueZustand == 1){
            spielErstellenBoxMalen();
            spielerAnzahlBoxMalen();
            rundenAnzahlBoxMalen();
        }
    }
    public void titelMalen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,170F));
        String text1 = "LUIGI";
        String text2 = "PARTY";
        int x1 = getXfuerCenter(text1);
        int x2 = getXfuerCenter(text2);
        int y1 = 220;
        int y2 = 350;

        g2.setColor(Color.gray);
        g2.drawString(text1,x1+5,y1+5);
        g2.setColor(Color.gray);
        g2.drawString(text2,x2+5,y2+5);

        g2.setColor(Color.yellow);
        g2.drawString(text1,x1,y1);
        g2.setColor(Color.yellow);
        g2.drawString(text2,x2,y2);
    }
    public void optionenMalen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,75F));
        g2.setColor(Color.white);
        String text = "Spiel erstellen";
        int x = getXfuerCenter(text);
        int y = 570;
        g2.drawString(text,x,y);
        if(befehlNum1 == 0){
            g2.drawString(">", x-55, y);
        }
        text = "Spiel beitreten";
        x = getXfuerCenter(text);
        y = 630;
        g2.drawString(text,x,y);
        if(befehlNum1 == 1){
            g2.drawString(">", x-55, y);
        }
        text = "Spiel verlassen";
        x = getXfuerCenter(text);
        y = 690;
        g2.drawString(text,x,y);
        if(befehlNum1 == 2){
            g2.drawString(">", x-55, y);
        }
    }
    public void spielErstellenBoxMalen(){
        Color c = new Color(0,0,0);
        g2.setColor(c);
        g2.fillRoundRect(150, 100, 1140, 600, 35, 35);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(155,105,1130, 590, 25, 25);

    }
    public void rundenAnzahlBoxMalen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,75F));
        String text = "Runden Anzahl";
        g2.drawString(text, 500, 500);
        g2.drawString("6", 485, 600);
        if(befehlNum2 == 0){
            g2.setColor(Color.gray);
            g2.drawString("6", 485, 600);
            g2.setColor(Color.white);
        }
        g2.drawString("7", 585, 600);
        if(befehlNum2 == 1){
            g2.setColor(Color.gray);
            g2.drawString("7", 585, 600);
            g2.setColor(Color.white);
        }
        g2.drawString("8", 685, 600);
        if(befehlNum2 == 2){
            g2.setColor(Color.gray);
            g2.drawString("8", 685, 600);
            g2.setColor(Color.white);
        }
        g2.drawString("9", 785, 600);
        if(befehlNum2 == 3){
            g2.setColor(Color.gray);
            g2.drawString("9", 785, 600);
            g2.setColor(Color.white);
        }
        g2.drawString("10", 885, 600);
        if(befehlNum2 == 4){
            g2.setColor(Color.gray);
            g2.drawString("10", 885, 600);
            g2.setColor(Color.white);
        }
    }
    public void spielerAnzahlBoxMalen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,75F));
        String text = "Spieler Anzahl";
        g2.drawString(text, 500, 250);
        g2.drawString("2", 585, 350);
        if(befehlNum3 == 0) {
            g2.setColor(Color.gray);
            g2.drawString("2", 585, 350);
            g2.setColor(Color.white);
        }
        g2.drawString("3", 685, 350);
        if(befehlNum3 == 1) {
            g2.setColor(Color.gray);
            g2.drawString("3", 685, 350);
            g2.setColor(Color.white);
        }
        g2.drawString("4", 785, 350);
        if(befehlNum3 == 2) {
            g2.setColor(Color.gray);
            g2.drawString("4", 785, 350);
            g2.setColor(Color.white);
        }
    }
    public int getXfuerCenter(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = mn.sp.bildschirmBreite/2 - length/2;
        return x;
    }

}
