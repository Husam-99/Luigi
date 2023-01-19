package Spielablauf;

import Networking.Pakete.SternKaufen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Stern {
    SpielMapManager mapManager;
    Graphics2D g2;
    private int feldNum, spriteZaehler = 0;
    public int spriteNum = 0;
    public String sternPostition;
    public BufferedImage stern, stern1, stern2, stern3, stern4, stern5;
    public boolean sternKaufen = false;

    public Stern(SpielMapManager mapManager) {
        this.mapManager = mapManager;
        getSternBilder();
    }
    public void getSternBilder(){
        try {
            stern= ImageIO.read(getClass().getResourceAsStream("/bestandteile/stern/Stern.png"));
            stern1= ImageIO.read(getClass().getResourceAsStream("/bestandteile/stern/Stern1.png"));
            stern2= ImageIO.read(getClass().getResourceAsStream("/bestandteile/stern/Stern2.png"));
            stern3= ImageIO.read(getClass().getResourceAsStream("/bestandteile/stern/Stern3.png"));
            stern4= ImageIO.read(getClass().getResourceAsStream("/bestandteile/stern/Stern4.png"));
            stern5= ImageIO.read(getClass().getResourceAsStream("/bestandteile/stern/Stern5.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setFeldNum(int feldNum) {
        for(int spalte = 0; spalte < 25; spalte++){
            for(int zeile = 0; zeile < 30; zeile++) {
                if (mapManager.mapFliesen[spalte][zeile] != null) {
                    if (mapManager.mapFliesen[spalte][zeile].feld != null) {
                        if (mapManager.mapFliesen[spalte][zeile].feld.feldNum == this.feldNum) {
                            mapManager.mapFliesen[spalte][zeile].feld.hatStern = false;
                        }
                    }
                }
            }
        }
        this.feldNum = feldNum;
        setSternPosition();
        System.out.println("setSternposition " + this.feldNum);
    }

    public void sternKaufen(){
        if(mapManager.spielablaufManager.mainSpieler.konto.muenzen >= 10){
            mapManager.spielablaufManager.mainSpieler.konto.muenzenVerlieren(10);
            mapManager.spielablaufManager.mainSpieler.konto.sterneErhalten(1);
            sternKaufen = false;
            SternKaufen sternKaufen = new SternKaufen();
            sternKaufen.sternGekauft = true;
            mapManager.spielablaufManager.sp.client.send(sternKaufen);
            mapManager.spielablaufManager.mainSpieler.amSpiel = false;
        }else{
            mapManager.spielablaufManager.mainSpieler.konto.genugMuenzen = false;
        }
    }
    private void setSternPosition(){
        for(int spalte = 0; spalte < 25; spalte++){
            for(int zeile = 0; zeile < 30; zeile++) {
                if (mapManager.mapFliesen[spalte][zeile] != null) {
                    if (mapManager.mapFliesen[spalte][zeile].feld != null) {
                        if (mapManager.mapFliesen[spalte][zeile].feld.feldNum == feldNum) {
                            if(feldNum == 2 || feldNum == 3 || feldNum == 4 || feldNum == 7 || feldNum == 11 || feldNum == 12 || feldNum == 19 || feldNum == 20 || feldNum == 22 || feldNum == 23 || feldNum == 24 || feldNum == 34 || feldNum == 35){
                                sternPostition = "unten";
                            }else if(feldNum == 13 || feldNum == 21 || feldNum == 25 || feldNum == 27 || feldNum == 33 || feldNum == 36){
                                sternPostition = "oben";
                            }else if(feldNum == 1 || feldNum == 6 || feldNum == 10 || feldNum == 29 || feldNum == 32){
                                sternPostition = "links";
                            }else{
                                sternPostition = "rechts";
                            }
                            mapManager.mapFliesen[spalte][zeile].feld.hatStern = true;
                        }
                    }
                }
            }
        }
    }
    public void update(){
        spriteZaehler++;
        if (spriteZaehler > 15) {
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
            } else if(spriteNum == 5) {
                spriteNum = 6;
            }else if(spriteNum == 6){
                spriteNum = 7;
            }else if(spriteNum == 7){
                spriteNum = 0;
            }
            spriteZaehler = 0;
        }
    }

    public void malen(Graphics2D g2){
        this.g2 = g2;
        sternKaufenBox();
        if(!mapManager.spielablaufManager.mainSpieler.konto.genugMuenzen){
            mapManager.spielablaufManager.mainSpieler.konto.rueckMeldungMalen(10 - mapManager.spielablaufManager.mainSpieler.konto.muenzen);
        }
    }

    private void sternKaufenBox(){
        Color c = new Color(0,0,0,100);
        g2.setColor(c);
        g2.fillRect(0, 0, 1440, 864);
        c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(330, 350, 780, 200, 35, 35);
        c = new Color(255,255,255,200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(335,355,770, 190, 25, 25);
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,65F));
        g2.drawString("Willst du einen Stern ",410, 430);
        g2.drawString("für 10 Muenzen Kaufen?",370, 500);
        g2.drawImage(stern,250,270, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse*2, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse*2, null);

    }

}
