package Minispiele;

import spieler.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MinispielSpieler {
    MinispielManager minispielManager;
    Spieler minispielSpieler;
    SammlerEingabeManager sammlerEingabeManager;
    int minispielXPosition;
    int minispielYPosition;
    String richtung = "up";
 
    int geschwindigkeit;
    Rectangle minispielSpielerRechteck;
    int punktzahl;
    public boolean obenGedrueckt, untenGedrueckt, linksGedrueckt, rechtsGedrueckt;


    public MinispielSpieler(MinispielManager minispielManager, Spieler spieler){

        this.minispielManager = minispielManager;

        if(spieler.spielfigur instanceof Abdo){
            minispielSpieler = new Spieler();
            minispielSpieler.spielfigur = new Abdo();
            minispielXPosition = 645;
            minispielYPosition = 167;
            richtung = "up";

        } else if(spieler.spielfigur instanceof Husam){
            minispielSpieler = new Spieler();
            minispielSpieler.spielfigur = new Husam();
            minispielXPosition = 422;
            minispielYPosition = 334;
            richtung = "left";

        } else if(spieler.spielfigur instanceof Taha){
            minispielSpieler = new Spieler();
            minispielSpieler.spielfigur = new Taha();
            minispielXPosition = 867;
            minispielYPosition = 334;
            richtung = "right";

        } else if(spieler.spielfigur instanceof Yousef){
            minispielSpieler = new Spieler();
            minispielSpieler.spielfigur = new Yousef();
            minispielXPosition = 645;
            minispielYPosition = 501;
            richtung = "down";
        }
        geschwindigkeit = 5;
        minispielSpielerRechteck = new Rectangle(minispielXPosition+30,
                minispielYPosition+50, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse-60, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse-50);

    }
    public void update() {
        if (obenGedrueckt || untenGedrueckt || linksGedrueckt || rechtsGedrueckt) {

            if (obenGedrueckt) {

                richtung = "up";
                if (!this.minispielSpielerRechteck.intersects(minispielManager.sammler.wandRechteck[0])) {
                    minispielYPosition -= geschwindigkeit;
                    minispielSpielerRechteck.y -= geschwindigkeit;
                }
                minispielManager.sammler.kollisionChecken(this);

            } else if (untenGedrueckt) {

                richtung = "down";
                if (!this.minispielSpielerRechteck.intersects(minispielManager.sammler.wandRechteck[1])) {
                    minispielYPosition += geschwindigkeit;
                    minispielSpielerRechteck.y += geschwindigkeit;
                }
                minispielManager.sammler.kollisionChecken(this);

            } else if (linksGedrueckt) {

                richtung = "left";
                if (!this.minispielSpielerRechteck.intersects(minispielManager.sammler.wandRechteck[2])) {
                    minispielXPosition -= geschwindigkeit;
                    minispielSpielerRechteck.x -= geschwindigkeit;
                }
                minispielManager.sammler.kollisionChecken(this);

            } else {

                richtung = "right";
                if (!this.minispielSpielerRechteck.intersects(minispielManager.sammler.wandRechteck[3])) {
                    minispielXPosition += geschwindigkeit;
                    minispielSpielerRechteck.x += geschwindigkeit;
                }
                minispielManager.sammler.kollisionChecken(this);
            }

            minispielSpieler.spielfigur.spriteZaehler++;
            if (minispielSpieler.spielfigur.spriteZaehler > 3) {
                if (minispielSpieler.spielfigur.spriteNum == 1) {
                    minispielSpieler.spielfigur.spriteNum = 2;
                } else if (minispielSpieler.spielfigur.spriteNum == 2) {
                    minispielSpieler.spielfigur.spriteNum = 3;
                } else if (minispielSpieler.spielfigur.spriteNum == 3) {
                    minispielSpieler.spielfigur.spriteNum = 4;
                } else if (minispielSpieler.spielfigur.spriteNum == 4) {
                    minispielSpieler.spielfigur.spriteNum = 1;
                }
                minispielSpieler.spielfigur.spriteZaehler = 0;
            }
        }
    }
    

    public void malen(Graphics2D g2, int width) {


        BufferedImage image = null;

        switch (richtung) {
            case "up" -> {
                if (minispielSpieler.spielfigur.spriteNum == 1 || minispielSpieler.spielfigur.spriteNum == 3) {
                    image = minispielSpieler.spielfigur.up1;
                }
                if (minispielSpieler.spielfigur.spriteNum == 2) {
                    image = minispielSpieler.spielfigur.up2;
                }
                if (minispielSpieler.spielfigur.spriteNum == 4) {
                    image = minispielSpieler.spielfigur.up3;
                }
            }
            case "down" -> {
                if (minispielSpieler.spielfigur.spriteNum == 1 || minispielSpieler.spielfigur.spriteNum == 3) {
                    image = minispielSpieler.spielfigur.down1;
                }
                if (minispielSpieler.spielfigur.spriteNum == 2) {
                    image = minispielSpieler.spielfigur.down2;
                }
                if (minispielSpieler.spielfigur.spriteNum == 4) {
                    image = minispielSpieler.spielfigur.down3;
                }
            }
            case "left" -> {
                if (minispielSpieler.spielfigur.spriteNum == 1 || minispielSpieler.spielfigur.spriteNum == 3) {
                    image = minispielSpieler.spielfigur.left1;
                }
                if (minispielSpieler.spielfigur.spriteNum == 2) {
                    image = minispielSpieler.spielfigur.left2;
                }
                if (minispielSpieler.spielfigur.spriteNum == 4) {
                    image = minispielSpieler.spielfigur.left3;
                }
            }
            case "right" -> {
                if (minispielSpieler.spielfigur.spriteNum == 1 || minispielSpieler.spielfigur.spriteNum == 3) {
                    image = minispielSpieler.spielfigur.right1;
                }
                if (minispielSpieler.spielfigur.spriteNum == 2) {
                    image = minispielSpieler.spielfigur.right2;
                }
                if (minispielSpieler.spielfigur.spriteNum == 4) {
                    image = minispielSpieler.spielfigur.right3;
                }
            }
        }
        g2.drawImage(image, minispielXPosition, minispielYPosition, this.minispielManager.sp.vergroesserteFliesenGroesse, this.minispielManager.sp.vergroesserteFliesenGroesse, null);
        minispielerBoxMalen(g2, width);
        minispielerStatusMalen(g2, width);
    }

    private void minispielerBoxMalen(Graphics2D g2, int boxWidth) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g2.setColor(new Color(20, 9, 54));
        g2.fillRoundRect(boxWidth,5, 210, 90, 25, 25);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(boxWidth + 5,10,200, 80, 15, 15);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));


    }

    private void minispielerStatusMalen(Graphics2D g2, int width) {
        g2.drawImage(minispielSpieler.spielfigur.profile, width + 8, 8, minispielManager.sp.vergroesserteFliesenGroesse-10, minispielManager.sp.vergroesserteFliesenGroesse-10, null);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        g2.setColor(new Color(196, 29, 29, 203));
        g2.drawString("" + punktzahl, width + 110, 70);

    }

}
