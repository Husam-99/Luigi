package menue;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpielfigurAuswaehlen {

    MenueManager mn;
    Graphics2D g2;
    public int befehlNum1 = 0;
    public int enterZustand = 0;
    int spriteNum = 1;
    int spriteZaehler = 0;
    int richtungNum = 0;
    String richtung = "unten";
    public SpielfigurAuswaehlen(MenueManager mn){
        this.mn = mn;
    }
    public void update(){
        if(mn.menueZustand == mn.spielfigurAuswaehlenZustand){
            if(enterZustand == 0){
                spriteZaehler++;
                if (spriteZaehler > 12) {
                    if(richtungNum == 0){
                        richtung = "unten";
                        if(spriteNum == 1){
                            spriteNum = 2;
                        } else if (spriteNum == 2) {
                            spriteNum = 3;
                        } else if (spriteNum == 3) {
                            spriteNum = 4;
                        } else if (spriteNum == 4) {
                            spriteNum = 1;
                            richtungNum = 1;
                        }
                    } else if (richtungNum == 1) {
                        richtung = "rechts";
                        if(spriteNum == 1){
                            spriteNum = 2;
                        } else if (spriteNum == 2) {
                            spriteNum = 3;
                        } else if (spriteNum == 3) {
                            spriteNum = 4;
                        } else if (spriteNum == 4) {
                            spriteNum = 1;
                            richtungNum = 2;
                        }
                    } else if (richtungNum == 2) {
                        richtung = "oben";
                        if(spriteNum == 1){
                            spriteNum = 2;
                        } else if (spriteNum == 2) {
                            spriteNum = 3;
                        } else if (spriteNum == 3) {
                            spriteNum = 4;
                        } else if (spriteNum == 4) {
                            spriteNum = 1;
                            richtungNum = 3;
                        }
                    } else if (richtungNum == 3) {
                        richtung = "links";
                        if(spriteNum == 1){
                            spriteNum = 2;
                        } else if (spriteNum == 2) {
                            spriteNum = 3;
                        } else if (spriteNum == 3) {
                            spriteNum = 4;
                        } else if (spriteNum == 4) {
                            spriteNum = 1;
                            richtungNum = 0;
                        }
                    }
                    spriteZaehler = 0;
                }
            }
        }
    }
    public void malen(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(mn.sp.marioPartyFont);
        if(enterZustand == 0){
            namenBoxMalen();
            bilderBoxMalen();
            namenMalen();
            bilderMalen();
        } else if (enterZustand == 1) {
            wartenMalen();
        }
    }
    public void namenBoxMalen(){
        g2.setColor(Color.black);
        g2.fillRoundRect(110, 60, 500, 680, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(115,65,490, 670, 25, 25);
    }
    public void bilderBoxMalen(){
        g2.setColor(Color.black);
        g2.fillRoundRect(700, 60, 630, 680, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(705,65,620, 670, 25, 25);
    }
    public void namenMalen(){
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        String text = "Spieler Auswaehlen";
        g2.drawString(text, 150, 160);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        g2.drawString("Abdo", 300, 300);
        if(befehlNum1 == 0){
            g2.setColor(Color.gray);
            g2.drawString("Abdo", 300, 300);
            g2.setColor(Color.white);
        }
        g2.drawString("Husam", 290, 400);
        if(befehlNum1 == 1){
            g2.setColor(Color.gray);
            g2.drawString("Husam", 290, 400);
            g2.setColor(Color.white);
        }
        g2.drawString("Taha", 300, 500);
        if(befehlNum1 == 2){
            g2.setColor(Color.gray);
            g2.drawString("Taha", 300, 500);
            g2.setColor(Color.white);
        }
        g2.drawString("Yousef", 280, 600);
        if(befehlNum1 == 3){
            g2.setColor(Color.gray);
            g2.drawString("Yousef", 280, 600);
            g2.setColor(Color.white);
        }
    }
    public void bilderMalen(){
        BufferedImage image1 = null;
        BufferedImage image2 = null;

        switch(richtung) {
            case "oben":
                if(befehlNum1 == 0){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                }
                if(befehlNum1 == 1){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.hup1;
                        image2 = mn.hwuerfel1;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.hup2;
                        image2 = mn.hwuerfel2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.hup3;
                        image2 = mn.hwuerfel3;
                    }
                }
                if(befehlNum1 == 2){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.tup1;
                        image2 = mn.twuerfel1;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.tup2;
                        image2 = mn.twuerfel2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.tup3;
                        image2 = mn.twuerfel3;
                    }
                }
                if(befehlNum1 == 3){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                }
                break;
            case "unten":
                if(befehlNum1 == 0){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                }
                if(befehlNum1 == 1){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.hdown1;
                        image2 = mn.hwuerfel1;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.hdown2;
                        image2 = mn.hwuerfel2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.hdown3;
                        image2 = mn.hwuerfel3;
                    }
                }
                if(befehlNum1 == 2){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.tdown1;
                        image2 = mn.twuerfel1;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.tdown2;
                        image2 = mn.twuerfel2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.tdown3;
                        image2 = mn.twuerfel3;
                    }
                }
                if(befehlNum1 == 3){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                }
                break;
            case "links":
                if(befehlNum1 == 0){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                }
                if(befehlNum1 == 1){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.hleft1;
                        image2 = mn.hwuerfel4;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.hleft2;
                        image2 = mn.hwuerfel5;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.hleft3;
                        image2 = mn.hwuerfel6;
                    }
                }
                if(befehlNum1 == 2){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.tleft1;
                        image2 = mn.twuerfel4;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.tleft2;
                        image2 = mn.twuerfel5;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.tleft3;
                        image2 = mn.twuerfel6;
                    }
                }
                if(befehlNum1 == 3){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                }
                break;
            case "rechts":
                if(befehlNum1 == 0){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                }
                if(befehlNum1 == 1){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.hright1;
                        image2 = mn.hwuerfel4;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.hright2;
                        image2 = mn.hwuerfel5;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.hright3;
                        image2 = mn.hwuerfel6;
                    }
                }
                if(befehlNum1 == 2){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.tright1;
                        image2 = mn.twuerfel4;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.tright2;
                        image2 = mn.twuerfel5;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.tright3;
                        image2 = mn.twuerfel6;
                    }
                }
                if(befehlNum1 == 3){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.mark1;
                        image2 = mn.mark2;
                    }
                }
                break;
        }
        g2.drawImage(image1, 850, 350, mn.sp.flieseGroesse*2, mn.sp.flieseGroesse*2, null);
        g2.drawImage(image2, 850, 60, mn.sp.flieseGroesse*2, mn.sp.flieseGroesse*2, null);

    }
    public void wartenMalen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,100F));
        String text1 = "Warten auf ";
        String text2 = "andere Spieler...";
        g2.setColor(Color.gray);
        g2.drawString(text1,455,355);
        g2.drawString(text2,365,455);
        g2.setColor(Color.yellow);
        g2.drawString(text1,460, 350);
        g2.drawString(text2,370, 450);

    }

}
