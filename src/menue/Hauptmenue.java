package menue;


import java.awt.*;

public class Hauptmenue {

    MenueManager mn;
    Graphics2D g2;
    public int befehlNum1 = 0;
    public int befehlNum2 = -1;
    public int befehlNum3 = 0;
    public int enterZustand = 0;


    public Hauptmenue(MenueManager mn){
        this.mn = mn;
    }
    public void update(){

    }
    public void malen(Graphics2D g2){

        this.g2 = g2;
        g2.setFont(mn.sp.marioPartyFont);
        if(mn.menueZustand == 0){
            titelMalen();
            optionenMalen();
            bilderMalen();
        }else if(mn.menueZustand == 1){
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
        g2.drawString(text2,x2+5,y2+5);
        g2.setColor(Color.red);
        g2.drawString("L",x1,y1);
        g2.drawString("P",x2,y2);
        Color c = new Color(50,205,50);
        g2.setColor(c);
        g2.drawString("U",601,y1);
        g2.drawString("A",542,y2);
        g2.setColor(Color.yellow);
        g2.drawString("I",711,y1);
        g2.drawString("R",661,y2);
        c = new Color(31, 81, 255);
        g2.setColor(c);
        g2.drawString("G",768,y1);
        g2.drawString("T",772,y2);
        c = new Color(255,95,31);
        g2.setColor(c);
        g2.drawString("I",879,y1);
        g2.drawString("Y",884,y2);
    }
    public void optionenMalen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,75F));
        g2.setColor(Color.white);
        String text = "Spiel erstellen";
        int x = getXfuerCenter(text);
        int y = 570;
        g2.drawString(text,x,y);
        if(befehlNum1 == 0){
            g2.setColor(Color.gray);
            g2.drawString(text,x,y);
            g2.setColor(Color.white);
        }
        text = "Spiel beitreten";
        x = getXfuerCenter(text);
        y = 630;
        g2.drawString(text,x,y);
        if(befehlNum1 == 1){
            g2.setColor(Color.gray);
            g2.drawString(text,x,y);
            g2.setColor(Color.white);
        }
        text = "Spiel verlassen";
        x = getXfuerCenter(text);
        y = 690;
        g2.drawString(text,x,y);
        if(befehlNum1 == 2){
            g2.setColor(Color.gray);
            g2.drawString(text,x,y);
            g2.setColor(Color.white);
        }
    }
    public void bilderMalen(){
    }
    public void spielErstellenBoxMalen(){
        g2.setColor(Color.black);
        g2.fillRoundRect(150, 100, 1140, 600, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(155,105,1130, 590, 25, 25);

    }
    public void rundenAnzahlBoxMalen(){
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,75F));
        String text = "Runden Anzahl";
        g2.drawString(text, 500, 500);
        g2.setColor(Color.white);
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
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,75F));
        String text = "Spieler Anzahl";
        g2.drawString(text, 500, 250);
        g2.setColor(Color.white);
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
