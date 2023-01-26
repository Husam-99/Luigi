package Minispiele;

import Networking.Pakete.SpielerPosition;
import spieler.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MinispielSpieler {
    MinispielManager minispielManager;
    private int miniSpielIndex=-1;
    Spieler minispielSpieler;
    public int minispielXPosition;
    public int minispielYPosition;
    int minispielXPosition;
    int minispielYPosition;
    public int bildschirmX=-1;
    public int bildschirmY=-1;
    public Palette aktuellePalette=null;

    String richtung = "up";
 
    int geschwindigkeit;
    public Rectangle minispielSpielerRechteck;
    public int punktzahl;
    public boolean obenGedrueckt, untenGedrueckt, linksGedrueckt, rechtsGedrueckt;
    boolean hatMushroom = false;
    boolean unterSpider = false;
    public boolean amSpielen = false;
    int mushroomZeit;


    public MinispielSpieler(MinispielManager minispielManager, Spieler spieler, int miniSpielIndex){

        this.minispielManager = minispielManager;
        this.miniSpielIndex=miniSpielIndex;

        if(spieler.spielfigur instanceof Abdo){
            minispielSpieler = new Spieler();
            minispielSpieler.spielfigur = new Abdo();
            if(this.miniSpielIndex==0) {
                minispielXPosition = 645;
                minispielYPosition = 167;
                richtung = "up";
            }
            else{
                respawn();
                bildschirmX = minispielManager.sp.bildschirmBreite / 2 - (minispielManager.sp.vergroesserteFliesenGroesse / 2)*3;
                bildschirmY = minispielManager.sp.bildschirmHoehe / 2 - (minispielManager.sp.vergroesserteFliesenGroesse / 2)*4;

            }

        } else if(spieler.spielfigur instanceof Husam){
            minispielSpieler = new Spieler();
            minispielSpieler.spielfigur = new Husam();
            if(this.miniSpielIndex==0) {
                minispielXPosition = 422;
                minispielYPosition = 334;
                richtung = "left";
            }
            else{
                respawn();
                bildschirmX = minispielManager.sp.bildschirmBreite / 2 - (minispielManager.sp.vergroesserteFliesenGroesse / 2)*3;
                bildschirmY = minispielManager.sp.bildschirmHoehe / 2 - (minispielManager.sp.vergroesserteFliesenGroesse / 2)*4;
            }

        } else if(spieler.spielfigur instanceof Taha){
            minispielSpieler = new Spieler();
            minispielSpieler.spielfigur = new Taha();
            if(this.miniSpielIndex==0) {
                minispielXPosition = 867;
                minispielYPosition = 334;
                richtung = "right";
            }
            else{
                respawn();
                bildschirmX = minispielManager.sp.bildschirmBreite / 2 - (minispielManager.sp.vergroesserteFliesenGroesse / 2)*3;
                bildschirmY = minispielManager.sp.bildschirmHoehe / 2 - (minispielManager.sp.vergroesserteFliesenGroesse / 2)*4;

            }

        } else if(spieler.spielfigur instanceof Yousef){
            minispielSpieler = new Spieler();
            minispielSpieler.spielfigur = new Yousef();
            if(this.miniSpielIndex==0) {
                minispielXPosition = 645;
                minispielYPosition = 501;
                richtung = "down";
            }
            else{
                respawn();
                bildschirmX = minispielManager.sp.bildschirmBreite / 2 - (minispielManager.sp.vergroesserteFliesenGroesse / 2)*3;
                bildschirmY = minispielManager.sp.bildschirmHoehe / 2 - (minispielManager.sp.vergroesserteFliesenGroesse / 2)*4;

            }
        }
        geschwindigkeit = 6;
        minispielSpielerRechteck = new Rectangle(minispielXPosition+30,
                minispielYPosition+50, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse-60, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse-50);


    }
    public void respawn() {
        if(this.minispielSpieler.spielfigur instanceof Abdo) {
            minispielXPosition = 5 * minispielManager.sp.vergroesserteFliesenGroesse;
            minispielYPosition = 17 * minispielManager.sp.vergroesserteFliesenGroesse - minispielManager.sp.vergroesserteFliesenGroesse / 3;
        }
       else if(this.minispielSpieler.spielfigur instanceof Husam) {
            minispielXPosition = 6 * minispielManager.sp.vergroesserteFliesenGroesse;
            minispielYPosition = 17 * minispielManager.sp.vergroesserteFliesenGroesse - minispielManager.sp.vergroesserteFliesenGroesse / 3;
        }
        else if(this.minispielSpieler.spielfigur instanceof Taha) {
            minispielXPosition = 7 * minispielManager.sp.vergroesserteFliesenGroesse;
            minispielYPosition = 17 * minispielManager.sp.vergroesserteFliesenGroesse - minispielManager.sp.vergroesserteFliesenGroesse / 3;
        }
        else if(this.minispielSpieler.spielfigur instanceof Yousef) {
            minispielXPosition = 8 * minispielManager.sp.vergroesserteFliesenGroesse;
            minispielYPosition = 17 * minispielManager.sp.vergroesserteFliesenGroesse - minispielManager.sp.vergroesserteFliesenGroesse / 3;
        }
    }
    public void update() {
        if(hatMushroom && !unterSpider){
            geschwindigkeit = 10;
        }
        else if(unterSpider && !hatMushroom){
            geschwindigkeit = 2;
        }
        else {
            geschwindigkeit = 6;
        }
        if (mushroomZeit - 5 == minispielManager.gesamtSekundenAnzahl) {
            hatMushroom = false;
        }
        if (obenGedrueckt || untenGedrueckt || linksGedrueckt || rechtsGedrueckt) {
            if (obenGedrueckt) {

                richtung = "up";
                if(this == minispielManager.mainMinispielSpieler) {
                    if (!this.minispielSpielerRechteck.intersects(minispielManager.sammler.wandRechteck[0])) {
                        minispielYPosition -= geschwindigkeit;
                        minispielSpielerRechteck.y -= geschwindigkeit;
                        SpielerPosition spielerPosition = new SpielerPosition();
                        spielerPosition.clientIndex = this.minispielManager.sp.client.clientIndex;
                        spielerPosition.weltX = minispielXPosition;
                        spielerPosition.weltY = minispielYPosition;
                        minispielManager.sp.client.send(spielerPosition);
                    }
                }
                //minispielManager.sammler.kollisionChecken(this);

            } else if (untenGedrueckt) {

                richtung = "down";
                if(this == minispielManager.mainMinispielSpieler) {

                    if (!this.minispielSpielerRechteck.intersects(minispielManager.sammler.wandRechteck[1])) {
                        minispielYPosition += geschwindigkeit;
                        minispielSpielerRechteck.y += geschwindigkeit;
                        SpielerPosition spielerPosition = new SpielerPosition();
                        spielerPosition.clientIndex = this.minispielManager.sp.client.clientIndex;
                        spielerPosition.weltX = minispielXPosition;
                        spielerPosition.weltY = minispielYPosition;
                        minispielManager.sp.client.send(spielerPosition);
                    }
                }
                //minispielManager.sammler.kollisionChecken(this);

            } else if (linksGedrueckt) {

                richtung = "left";
                if(this == minispielManager.mainMinispielSpieler) {

                    if (!this.minispielSpielerRechteck.intersects(minispielManager.sammler.wandRechteck[2])) {
                        minispielXPosition -= geschwindigkeit;
                        minispielSpielerRechteck.x -= geschwindigkeit;
                        SpielerPosition spielerPosition = new SpielerPosition();
                        spielerPosition.clientIndex = this.minispielManager.sp.client.clientIndex;
                        spielerPosition.weltX = minispielXPosition;
                        spielerPosition.weltY = minispielYPosition;
                        minispielManager.sp.client.send(spielerPosition);
                    }
                }
                //minispielManager.sammler.kollisionChecken(this);

            } else {

                richtung = "right";
                if (this == minispielManager.mainMinispielSpieler) {

                    if (!this.minispielSpielerRechteck.intersects(minispielManager.sammler.wandRechteck[3])) {
                        minispielXPosition += geschwindigkeit;
                        minispielSpielerRechteck.x += geschwindigkeit;
                        SpielerPosition spielerPosition = new SpielerPosition();
                        spielerPosition.clientIndex = this.minispielManager.sp.client.clientIndex;
                        spielerPosition.weltX = minispielXPosition;
                        spielerPosition.weltY = minispielYPosition;
                        minispielManager.sp.client.send(spielerPosition);
                    }
                }
            }
            minispielManager.sammler.kollisionChecken(this);

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
        //System.out.println(this.minispielXPosition + " " + this.minispielYPosition);
    }
    

    public void malen(Graphics2D g2) {
        if(this.miniSpielIndex==0) {
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
        }
        else{
            g2.drawImage(minispielSpieler.spielfigur.down1, bildschirmX, bildschirmY, this.minispielManager.sp.vergroesserteFliesenGroesse,  this.minispielManager.sp.vergroesserteFliesenGroesse, null);
        }
    }

    public void minispielerBoxMalen(Graphics2D g2, int boxWidth) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g2.setColor(new Color(20, 9, 54));
        g2.fillRoundRect(boxWidth,5, 230, 90, 25, 25);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(boxWidth + 5,10,220, 80, 15, 15);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));


    }

    public void minispielerStatusMalen(Graphics2D g2, int width) {
        g2.drawImage(minispielSpieler.spielfigur.profile, width + 8, 8, minispielManager.sp.vergroesserteFliesenGroesse-10, minispielManager.sp.vergroesserteFliesenGroesse-10, null);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        g2.setColor(new Color(196, 29, 29, 203));
        g2.drawString("" + punktzahl, width + 110, 70);

    }

}
