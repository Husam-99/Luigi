package menue;

import Networking.Client.SpielClient;
import spieler.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpielfigurAuswahl {

    MenueManager menueManager;
    Graphics2D g2;

    public int befehlNum1, enterZustand;
    int spriteNum, spriteZaehler, richtungNum;
    String richtung;
    BufferedImage image1, image2;

    //die folgenden Objekte sind nur für Bilder Zugriff
    public Spielfigur taha, husam, abdo, yousef;
    public Wuerfel tahaWuerfel, husamWuerfel, abdoWuerfel, yousefWuerfel;

    public SpielfigurAuswahl(MenueManager menueManager){
        this.menueManager = menueManager;
        taha = new Taha(null);
        husam = new Husam(null);
        abdo = new Abdo(null);
        yousef = new Yousef(null);
        tahaWuerfel = new TahaWuerfel(null);
        husamWuerfel = new HusamWuerfel(null);
        abdoWuerfel = new AbdoWuerfel(null);
        yousefWuerfel = new YousefWuerfel(null);
        enterZustand = 0;
        spriteNum = 1;
        spriteZaehler = 0;
        richtungNum = 0;
        richtung = "unten";
        image1 = null;
        image2 = null;
    }

    //in dieser Methode wird bestimmt, an welche Stelle die Spielfigur Auswahl Menü anfangen soll
    public void befehlNum1Bestimmen(){
        if(menueManager.sp.client.isIstHost()){
            befehlNum1 = 0;
        }else{
            if(SpielClient.clientIndex == 1) {
                if(menueManager.menueEingabeManager.ausgewaehlteSpielfiguren != null) {
                    if (menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.get(0) == 0) {
                        befehlNum1 = 1;
                    } else {
                        befehlNum1 = 0;
                    }
                }
            }else if(SpielClient.clientIndex == 2){
                if(menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(0) && menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(1)){
                    befehlNum1 = 2;
                }else if(menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(0)){
                    befehlNum1 = 1;
                }else{
                    befehlNum1 = 0;
                }
            }else if(SpielClient.clientIndex == 3){
                if(menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(0) && menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(1) && menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(2)){
                    befehlNum1 = 3;
                }else if(menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(0) && menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(1) && menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(3)){
                    befehlNum1 = 2;
                }else if(menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(0) && menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(2) && menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(3)){
                    befehlNum1 = 1;
                }else{
                    befehlNum1 = 0;
                }
            }
        }
    }

    public void update(){
        if(menueManager.menueZustand == menueManager.spielfigurAuswahlZustand){
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
        g2.setFont(menueManager.sp.marioPartyFont);
        //wenn der Client dran ist, wird Spielfigur Auswahl Menü gemalt
        if(enterZustand == 0){
            namenBoxMalen();
            bilderBoxMalen();
            namenMalen();
            bilderMalen();
        }

        //wenn der Client nicht dran ist (nach der Spielfigur Festlegen), wird Warte Box gemalt
        else if (enterZustand == 1) {
            wartenBoxMalen(g2);
            wartenMalen(g2);
        }
    }

    private void namenBoxMalen(){
        g2.setColor(Color.black);
        g2.fillRoundRect(110, 60, 500, 680, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(115,65,490, 670, 25, 25);
    }

    private void bilderBoxMalen(){
        g2.setColor(Color.black);
        g2.fillRoundRect(700, 60, 630, 680, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(705,65,620, 670, 25, 25);
    }

    private void namenMalen(){
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        String text = "Spieler Auswaehlen";
        g2.drawString(text, 150, 160);

        g2.setColor(Color.gray);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));

        //es wird nur die ungewählte Spielfiguren gemalt werden
        if(!menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(0)) {
            g2.drawString("Abdo", 300, 300);
            if (befehlNum1 == 0) {
                g2.setColor(Color.white);
                g2.drawString("Abdo", 300, 300);
            }
        }
        if(!menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(1)) {
            g2.setColor(Color.gray);
            g2.drawString("Husam", 290, 400);
            if (befehlNum1 == 1) {
                g2.setColor(Color.white);
                g2.drawString("Husam", 290, 400);
            }
        }
        if(!menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(2)) {
            g2.setColor(Color.gray);
            g2.drawString("Taha", 300, 500);
            if (befehlNum1 == 2) {
                g2.setColor(Color.white);
                g2.drawString("Taha", 300, 500);
            }
        }
        if(!menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.contains(3)) {
            g2.setColor(Color.gray);
            g2.drawString("Yousef", 280, 600);
            if (befehlNum1 == 3) {
                g2.setColor(Color.white);
                g2.drawString("Yousef", 280, 600);
            }
        }
    }

    private void bilderMalen(){
        switch (richtung) {
            case "oben" -> spielerObenBilderMalen();
            case "unten" -> spielerUntenBilderMalen();
            case "links" -> spielerLinksBilderMalen();
            case "rechts" -> spielerRechtsBilderMalen();
        }
        g2.drawImage(image1, 850, 350, menueManager.sp.fliesenGroesse*10, menueManager.sp.fliesenGroesse*10, null);
        g2.drawImage(image2, 850, 60, menueManager.sp.fliesenGroesse*10, menueManager.sp.fliesenGroesse*10, null);
    }

    private void spielerObenBilderMalen(){
        if(befehlNum1 == 0){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = abdo.up1;
                image2 = abdoWuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = abdo.up2;
                image2 = abdoWuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = abdo.up3;
                image2 = abdoWuerfel.wuerfel3;
            }
        }
        if(befehlNum1 == 1){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = husam.up1;
                image2 = husamWuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = husam.up2;
                image2 = husamWuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = husam.up3;
                image2 = husamWuerfel.wuerfel3;
            }
        }
        if(befehlNum1 == 2){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = taha.up1;
                image2 = tahaWuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = taha.up2;
                image2 = tahaWuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = taha.up3;
                image2 = tahaWuerfel.wuerfel3;
            }
        }
        if(befehlNum1 == 3){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = yousef.up1;
                image2 = yousefWuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = yousef.up2;
                image2 = yousefWuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = yousef.up3;
                image2 = yousefWuerfel.wuerfel3;
            }
        }
    }

    private void spielerUntenBilderMalen(){
        if(befehlNum1 == 0){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = abdo.down1;
                image2 = abdoWuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = abdo.down2;
                image2 = abdoWuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = abdo.down3;
                image2 = abdoWuerfel.wuerfel3;
            }
        }
        if(befehlNum1 == 1){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = husam.down1;
                image2 = husamWuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = husam.down2;
                image2 = husamWuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = husam.down3;
                image2 = husamWuerfel.wuerfel3;
            }
        }
        if(befehlNum1 == 2){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = taha.down1;
                image2 = tahaWuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = taha.down2;
                image2 = tahaWuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = taha.down3;
                image2 = tahaWuerfel.wuerfel3;
            }
        }
        if(befehlNum1 == 3){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = yousef.down1;
                image2 = yousefWuerfel.wuerfel1;
            }
            if(spriteNum == 2) {
                image1 = yousef.down2;
                image2 = yousefWuerfel.wuerfel2;
            }
            if(spriteNum == 4) {
                image1 = yousef.down3;
                image2 = yousefWuerfel.wuerfel3;
            }
        }
    }

    private void spielerRechtsBilderMalen(){
        if(befehlNum1 == 0){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = abdo.right1;
                image2 = abdoWuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = abdo.right2;
                image2 = abdoWuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = abdo.right3;
                image2 = abdoWuerfel.wuerfel6;
            }
        }
        if(befehlNum1 == 1){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = husam.right1;
                image2 = husamWuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = husam.right2;
                image2 = husamWuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = husam.right3;
                image2 = husamWuerfel.wuerfel6;
            }
        }
        if(befehlNum1 == 2){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = taha.right1;
                image2 = tahaWuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = taha.right2;
                image2 = tahaWuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = taha.right3;
                image2 = tahaWuerfel.wuerfel6;
            }
        }
        if(befehlNum1 == 3){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = yousef.right1;
                image2 = yousefWuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = yousef.right2;
                image2 = yousefWuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = yousef.right3;
                image2 = yousefWuerfel.wuerfel6;
            }
        }
    }

    private void spielerLinksBilderMalen(){
        if(befehlNum1 == 0){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = abdo.left1;
                image2 = abdoWuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = abdo.left2;
                image2 = abdoWuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = abdo.left3;
                image2 = abdoWuerfel.wuerfel6;
            }
        }
        if(befehlNum1 == 1){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = husam.left1;
                image2 = husamWuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = husam.left2;
                image2 = husamWuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = husam.left3;
                image2 = husamWuerfel.wuerfel6;
            }
        }
        if(befehlNum1 == 2){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = taha.left1;
                image2 = tahaWuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = taha.left2;
                image2 = tahaWuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = taha.left3;
                image2 = tahaWuerfel.wuerfel6;
            }
        }
        if(befehlNum1 == 3){
            if(spriteNum == 1 ||spriteNum ==3) {
                image1 = yousef.left1;
                image2 = yousefWuerfel.wuerfel4;
            }
            if(spriteNum == 2) {
                image1 = yousef.left2;
                image2 = yousefWuerfel.wuerfel5;
            }
            if(spriteNum == 4) {
                image1 = yousef.left3;
                image2 = yousefWuerfel.wuerfel6;
            }
        }
        g2.drawImage(image1, 850, 350, menueManager.sp.fliesenGroesse*10, menueManager.sp.fliesenGroesse*10, null);
        g2.drawImage(image2, 850, 60, menueManager.sp.fliesenGroesse*10, menueManager.sp.fliesenGroesse*10, null);

    }

    public void wartenMalen(Graphics2D g2){
        g2.setFont(menueManager.sp.marioPartyFont);
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

    public void wartenBoxMalen(Graphics2D g2){
        Color c = new Color(0,0,0,220);
        g2.setColor(c);
        g2.fillRoundRect(325, 230, 800, 300, 35, 35);
        c = new Color(255,255,255,220);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(330,235,790, 290, 25, 25);
    }

}


