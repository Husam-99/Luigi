package spieler;

import Networking.Pakete.Schritte;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Wuerfel {
    public BufferedImage wuerfel1, wuerfel2, wuerfel3, wuerfel4, wuerfel5, wuerfel6;
    Spieler s;
    int spriteNum = 0, spriteZaehler = 0;
    public Wuerfel(Spieler s){
        this.s = s;
    }
    public void schritteAnzahlBestimmen(){
        if (spriteNum == 0) {
            s.schritteAnzahl = 1;
        } else if (spriteNum == 1) {
            s.schritteAnzahl = 2;
        } else if (spriteNum == 2) {
            s.schritteAnzahl = 3;
        } else if (spriteNum == 3) {
            s.schritteAnzahl = 4;
        } else if (spriteNum == 4) {
            s.schritteAnzahl = 5;
        }else if (spriteNum == 5) {
            s.schritteAnzahl = 6;
        }
        Schritte schritte = new Schritte();
        schritte.schritteAnzahl = s.schritteAnzahl;
        //s.sp.client.send(schritte);
    }
    public void getWuerfelBilder() {}
    public void update(){
        if(s.sp.mapManager.mapEingabeManager.spaceGedrueckt) {
            spriteZaehler++;
            if (spriteZaehler > 3) {
                if (spriteNum == 0) {
                    spriteNum = 1;
                } else if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 5;
                }else if (spriteNum == 5) {
                    spriteNum = 0;
                }
                spriteZaehler = 0;
            }
        }
    }
    public void malen(Graphics2D g2){
        BufferedImage image = null;
        if(spriteNum == 0){
            image = wuerfel1;
        }else if(spriteNum == 1){
            image = wuerfel2;
        }else if(spriteNum == 2){
            image = wuerfel3;
        }else if(spriteNum == 3){
            image = wuerfel4;
        }else if(spriteNum == 4){
            image = wuerfel5;
        }else if(spriteNum == 5){
            image = wuerfel6;
        }
        g2.drawImage(image, 620 , 65, s.sp.vergroesserteFliesenGroesse*2, s.sp.vergroesserteFliesenGroesse*2, null);
    }
}
