package menue;

import spieler.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpielfigurAuswaehlen{

    MenueManager mn;
    Graphics2D g2;
    public int befehlNum1 = 0, enterZustand = 0;
    int spriteNum = 1, spriteZaehler = 0, richtungNum = 0;
    String richtung = "unten";
    BufferedImage image1 = null, image2 = null;
    Spieler s;
    public Taha taha = new Taha(s);
    public Husam husam = new Husam(s);
    public Abdo abdo = new Abdo(s);
    public Yousef yousef = new Yousef(s);
    public TahaWuerfel twuerfel = new TahaWuerfel(s);
    public HusamWuerfel hwuerfel = new HusamWuerfel(s);
    public AbdoWuerfel awuerfel = new AbdoWuerfel(s);
    public YousefWuerfel ywuerfel = new YousefWuerfel(s);
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
            wartenBoxMalen();
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
        g2.setColor(Color.gray);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        g2.drawString("Abdo", 300, 300);
        if(befehlNum1 == 0){
            g2.setColor(Color.white);
            g2.drawString("Abdo", 300, 300);
            g2.setColor(Color.gray);
        }
        g2.drawString("Husam", 290, 400);
        if(befehlNum1 == 1){
            g2.setColor(Color.white);
            g2.drawString("Husam", 290, 400);
            g2.setColor(Color.gray);
        }
        g2.drawString("Taha", 300, 500);
        if(befehlNum1 == 2){
            g2.setColor(Color.white);
            g2.drawString("Taha", 300, 500);
            g2.setColor(Color.gray);
        }
        g2.drawString("Yousef", 280, 600);
        if(befehlNum1 == 3){
            g2.setColor(Color.white);
            g2.drawString("Yousef", 280, 600);
            g2.setColor(Color.gray);
        }
    }
    public void bilderMalen(){
        switch(richtung) {
            case "oben":
                spielerObenBilderMalen();
                break;
            case "unten":
                spielerUntenBilderMalen();
                break;
            case "links":
                spielerLinksBilderMalen();
                break;
            case "rechts":
                spielerRechtsBilderMalen();
                break;
        }
        g2.drawImage(image1, 850, 350, mn.sp.fliesenGroesse*10, mn.sp.fliesenGroesse*10, null);
        g2.drawImage(image2, 850, 60, mn.sp.fliesenGroesse*10, mn.sp.fliesenGroesse*10, null);
    }
    public void spielerObenBilderMalen(){
        if(befehlNum1 == 0){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = abdo.up1;
                image2 = awuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = abdo.up2;
                image2 = awuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = abdo.up3;
                image2 = awuerfel.wuerfel3;
            }
        }
        if(befehlNum1 == 1){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = husam.up1;
                image2 = hwuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = husam.up2;
                image2 = hwuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = husam.up3;
                image2 = hwuerfel.wuerfel3;
            }
        }
        if(befehlNum1 == 2){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = taha.up1;
                image2 = twuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = taha.up2;
                image2 = twuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = taha.up3;
                image2 = twuerfel.wuerfel3;
            }
        }
        if(befehlNum1 == 3){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = yousef.up1;
                image2 = ywuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = yousef.up2;
                image2 = ywuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = yousef.up3;
                image2 = ywuerfel.wuerfel3;
            }
        }
    }
    public void spielerUntenBilderMalen(){
        if(befehlNum1 == 0){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = abdo.down1;
                image2 = awuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = abdo.down2;
                image2 = awuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = abdo.down3;
                image2 = awuerfel.wuerfel3;
            }
        }
        if(befehlNum1 == 1){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = husam.down1;
                image2 = hwuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = husam.down2;
                image2 = hwuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = husam.down3;
                image2 = hwuerfel.wuerfel3;
            }
        }
        if(befehlNum1 == 2){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = taha.down1;
                image2 = twuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = taha.down2;
                image2 = twuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = taha.down3;
                image2 = twuerfel.wuerfel3;
            }
        }
        if(befehlNum1 == 3){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = yousef.down1;
                image2 = ywuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = yousef.down2;
                image2 = ywuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = yousef.down3;
                image2 = ywuerfel.wuerfel3;
            }
        }
    }
    public void spielerRechtsBilderMalen(){
        if(befehlNum1 == 0){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = abdo.right1;
                image2 = awuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = abdo.right2;
                image2 = awuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = abdo.right3;
                image2 = awuerfel.wuerfel6;
            }
        }
        if(befehlNum1 == 1){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = husam.right1;
                image2 = hwuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = husam.right2;
                image2 = hwuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = husam.right3;
                image2 = hwuerfel.wuerfel6;
            }
        }
        if(befehlNum1 == 2){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = taha.right1;
                image2 = twuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = taha.right2;
                image2 = twuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = taha.right3;
                image2 = twuerfel.wuerfel6;
            }
        }
        if(befehlNum1 == 3){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = yousef.right1;
                image2 = ywuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = yousef.right2;
                image2 = ywuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = yousef.right3;
                image2 = ywuerfel.wuerfel6;
            }
        }
    }
    public void spielerLinksBilderMalen(){
        if(befehlNum1 == 0){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = abdo.left1;
                image2 = awuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = abdo.left2;
                image2 = awuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = abdo.left3;
                image2 = awuerfel.wuerfel6;
            }
        }
        if(befehlNum1 == 1){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = husam.left1;
                image2 = hwuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = husam.left2;
                image2 = hwuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = husam.left3;
                image2 = hwuerfel.wuerfel6;
            }
        }
        if(befehlNum1 == 2){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = taha.left1;
                image2 = twuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = taha.left2;
                image2 = twuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = taha.left3;
                image2 = twuerfel.wuerfel6;
            }
        }
        if(befehlNum1 == 3){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = yousef.left1;
                image2 = ywuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = yousef.left2;
                image2 = ywuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = yousef.left3;
                image2 = ywuerfel.wuerfel6;
            }
        }
        g2.drawImage(image1, 850, 350, mn.sp.fliesenGroesse*10, mn.sp.fliesenGroesse*10, null);
        g2.drawImage(image2, 850, 60, mn.sp.fliesenGroesse*10, mn.sp.fliesenGroesse*10, null);

    }
    public void wartenMalen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,100F));
        String text1 = "Warten auf ";
        String text2 = "andere Spieler...";
        g2.setColor(Color.gray);
        g2.drawString(text1,465,355);
        g2.drawString(text2,375,455);
        g2.setColor(Color.yellow);
        g2.drawString(text1,460, 350);
        g2.drawString(text2,370, 450);
    }
    public void wartenBoxMalen(){
        Color c = new Color(0,0,0,220);
        g2.setColor(c);
        g2.fillRoundRect(325, 230, 800, 300, 35, 35);
        c = new Color(255,255,255,220);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(330,235,790, 290, 25, 25);
    }

}


