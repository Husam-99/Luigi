package menue;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpielfigurAuswaehlen{

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
                        image1 = mn.sp.spieler.abdo.up1;
                        image2 = mn.sp.spieler.awuerfel.wuerfel1;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.abdo.up2;
                        image2 = mn.sp.spieler.awuerfel.wuerfel2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.abdo.up3;
                        image2 = mn.sp.spieler.awuerfel.wuerfel3;
                    }
                }
                if(befehlNum1 == 1){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.husam.up1;
                        image2 = mn.sp.spieler.hwuerfel.wuerfel1;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.husam.up2;
                        image2 = mn.sp.spieler.hwuerfel.wuerfel2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.husam.up3;
                        image2 = mn.sp.spieler.hwuerfel.wuerfel3;
                    }
                }
                if(befehlNum1 == 2){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.taha.up1;
                        image2 = mn.sp.spieler.twuerfel.wuerfel1;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.taha.up2;
                        image2 = mn.sp.spieler.twuerfel.wuerfel2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.taha.up3;
                        image2 = mn.sp.spieler.twuerfel.wuerfel3;
                    }
                }
                if(befehlNum1 == 3){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.yousef.up1;
                        image2 = mn.sp.spieler.ywuerfel.wuerfel1;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.yousef.up2;
                        image2 = mn.sp.spieler.ywuerfel.wuerfel2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.yousef.up3;
                        image2 = mn.sp.spieler.ywuerfel.wuerfel3;
                    }
                }
                break;
            case "unten":
                if(befehlNum1 == 0){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.abdo.down1;
                        image2 = mn.sp.spieler.awuerfel.wuerfel1;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.abdo.down2;
                        image2 = mn.sp.spieler.awuerfel.wuerfel2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.abdo.down3;
                        image2 = mn.sp.spieler.awuerfel.wuerfel3;
                    }
                }
                if(befehlNum1 == 1){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.husam.down1;
                        image2 = mn.sp.spieler.hwuerfel.wuerfel1;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.husam.down2;
                        image2 = mn.sp.spieler.hwuerfel.wuerfel2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.husam.down3;
                        image2 = mn.sp.spieler.hwuerfel.wuerfel3;
                    }
                }
                if(befehlNum1 == 2){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.taha.down1;
                        image2 = mn.sp.spieler.twuerfel.wuerfel1;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.taha.down2;
                        image2 = mn.sp.spieler.twuerfel.wuerfel2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.taha.down3;
                        image2 = mn.sp.spieler.twuerfel.wuerfel3;
                    }
                }
                if(befehlNum1 == 3){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.yousef.down1;
                        image2 = mn.sp.spieler.ywuerfel.wuerfel1;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.yousef.down2;
                        image2 = mn.sp.spieler.ywuerfel.wuerfel2;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.yousef.down3;
                        image2 = mn.sp.spieler.ywuerfel.wuerfel3;
                    }
                }
                break;
            case "links":
                if(befehlNum1 == 0){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.abdo.left1;
                        image2 = mn.sp.spieler.awuerfel.wuerfel4;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.abdo.left2;
                        image2 = mn.sp.spieler.awuerfel.wuerfel5;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.abdo.left3;
                        image2 = mn.sp.spieler.awuerfel.wuerfel6;
                    }
                }
                if(befehlNum1 == 1){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.husam.left1;
                        image2 = mn.sp.spieler.hwuerfel.wuerfel4;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.husam.left2;
                        image2 = mn.sp.spieler.hwuerfel.wuerfel5;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.husam.left3;
                        image2 = mn.sp.spieler.hwuerfel.wuerfel6;
                    }
                }
                if(befehlNum1 == 2){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.taha.left1;
                        image2 = mn.sp.spieler.twuerfel.wuerfel4;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.taha.left2;
                        image2 = mn.sp.spieler.twuerfel.wuerfel5;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.taha.left3;
                        image2 = mn.sp.spieler.twuerfel.wuerfel6;
                    }
                }
                if(befehlNum1 == 3){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.yousef.left1;
                        image2 = mn.sp.spieler.ywuerfel.wuerfel4;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.yousef.left2;
                        image2 = mn.sp.spieler.ywuerfel.wuerfel5;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.yousef.left3;
                        image2 = mn.sp.spieler.ywuerfel.wuerfel6;
                    }
                }
                break;
            case "rechts":
                if(befehlNum1 == 0){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.abdo.right1;
                        image2 = mn.sp.spieler.awuerfel.wuerfel4;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.abdo.right2;
                        image2 = mn.sp.spieler.awuerfel.wuerfel5;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.abdo.right3;
                        image2 = mn.sp.spieler.awuerfel.wuerfel6;
                    }
                }
                if(befehlNum1 == 1){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.husam.right1;
                        image2 = mn.sp.spieler.hwuerfel.wuerfel4;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.husam.right2;
                        image2 = mn.sp.spieler.hwuerfel.wuerfel5;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.husam.right3;
                        image2 = mn.sp.spieler.hwuerfel.wuerfel6;
                    }
                }
                if(befehlNum1 == 2){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.taha.right1;
                        image2 = mn.sp.spieler.twuerfel.wuerfel4;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.taha.right2;
                        image2 = mn.sp.spieler.twuerfel.wuerfel5;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.taha.right3;
                        image2 = mn.sp.spieler.twuerfel.wuerfel6;
                    }
                }
                if(befehlNum1 == 3){
                    if(spriteNum == 1 ||spriteNum ==3) {
                        image1 = mn.sp.spieler.yousef.right1;
                        image2 = mn.sp.spieler.ywuerfel.wuerfel4;
                    }
                    if(spriteNum == 2) {
                        image1 = mn.sp.spieler.yousef.right2;
                        image2 = mn.sp.spieler.ywuerfel.wuerfel5;
                    }
                    if(spriteNum == 4) {
                        image1 = mn.sp.spieler.yousef.right3;
                        image2 = mn.sp.spieler.ywuerfel.wuerfel6;
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
