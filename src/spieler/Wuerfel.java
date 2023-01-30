package spieler;

import Networking.Pakete.Bescheid;
import Networking.Pakete.Schritte;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Wuerfel {
    public BufferedImage wuerfel1, wuerfel2, wuerfel3, wuerfel4, wuerfel5, wuerfel6;
    Spieler spieler;
    int spriteNum = 0, spriteZaehler = 0;
    public Wuerfel(Spieler spieler){
        this.spieler = spieler;
    }
    public void schritteAnzahlBestimmen(){
        if (spriteNum == 0) {
            spieler.schritteAnzahl = 1;
        } else if (spriteNum == 1) {
            spieler.schritteAnzahl = 2;
        } else if (spriteNum == 2) {
            spieler.schritteAnzahl = 3;
        } else if (spriteNum == 3) {
            spieler.schritteAnzahl = 4;
        } else if (spriteNum == 4) {
            spieler.schritteAnzahl = 5;
        }else if (spriteNum == 5) {
            spieler.schritteAnzahl = 6;
        }
        if(spieler.spielablaufManager.sp.wurfelzustand){
            Bescheid bescheid = new Bescheid();
            bescheid.fertig = true;
            spieler.spielablaufManager.sp.client.send(bescheid);
            Schritte schritte = new Schritte();
            schritte.schritteAnzahl = spieler.schritteAnzahl;
            spieler.spielablaufManager.sp.client.send(schritte);
            spieler.spielablaufManager.sp.aktuelleRundenAnzahl++;
        }
    }
    public void getWuerfelBilder() {}
    public void update(){
        if(spieler.spielablaufManager.mapManager.mapEingabeManager.spaceGedrueckt) {
            spriteZaehler++;
            if (spriteZaehler > 1) {
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
            image = wuerfel6;
        }else if(spriteNum == 1){
            image = wuerfel1;
        }else if(spriteNum == 2){
            image = wuerfel4;
        }else if(spriteNum == 3){
            image = wuerfel3;
        }else if(spriteNum == 4){
            image = wuerfel5;
        }else if(spriteNum == 5){
            image = wuerfel2;
        }
        g2.drawImage(image, 620 , 65, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse*2, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse*2, null);
    }
}
