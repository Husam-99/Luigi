package Spielablauf;

import Networking.Pakete.SternKaufen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Stern {

    SpielMapManager mapManager;
    Graphics2D g2;

    private int spriteZaehler;
    public int feldNum, sternFeldZeile, sternFeldSpalte, spriteNum;
    public BufferedImage stern, stern1, stern2, stern3, stern4, stern5;
    public String sternPostition;
    public boolean sternKaufen;

    public Stern(SpielMapManager mapManager) {
        this.mapManager = mapManager;
        getSternBilder();
        spriteZaehler = 0;
        spriteNum = 0;
        sternKaufen = false;
    }

    private void getSternBilder(){
        try {
            stern= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/stern/Stern.png")));
            stern1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/stern/Stern1.png")));
            stern2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/stern/Stern2.png")));
            stern3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/stern/Stern3.png")));
            stern4= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/stern/Stern4.png")));
            stern5= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/stern/Stern5.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setFeldNum(int feldNum) {
        for(int zeile = 0; zeile < 25; zeile++){
            for(int spalte = 0; spalte < 30; spalte++) {
                if (mapManager.mapFliesen[zeile][spalte] != null) {
                    if (mapManager.mapFliesen[zeile][spalte].feld != null) {
                        if (mapManager.mapFliesen[zeile][spalte].feld.feldNum == this.feldNum) {
                            mapManager.mapFliesen[zeile][spalte].feld.hatStern = false;
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

        //festlegen, ob der Spieler genug Münzen für das Kaufen des Sterns hat
        if(mapManager.spielablaufManager.mainSpieler.konto.muenzen >= 10){
            mapManager.spielablaufManager.mainSpieler.konto.muenzenVerlieren(10);
            mapManager.spielablaufManager.mainSpieler.konto.sterneErhalten(1);
            sternKaufen = false;

            //sende an die Server, dass der Stern gekauft ist
            SternKaufen sternKaufen = new SternKaufen();
            sternKaufen.sternGekauft = true;
            mapManager.spielablaufManager.sp.client.send(sternKaufen);
            mapManager.spielablaufManager.mainSpieler.amSpiel = false;
        }else{
            mapManager.spielablaufManager.mainSpieler.konto.genugMuenzen = false;
        }
    }

    private void setSternPosition(){
        for(int zeile = 0; zeile < 25; zeile++){
            for(int spalte = 0; spalte < 30; spalte++) {
                if (mapManager.mapFliesen[zeile][spalte] != null) {
                    if (mapManager.mapFliesen[zeile][spalte].feld != null) {
                        if (mapManager.mapFliesen[zeile][spalte].feld.feldNum == feldNum) {
                            if(feldNum == 2 || feldNum == 3 || feldNum == 4 || feldNum == 7 || feldNum == 11 || feldNum == 12 || feldNum == 19 || feldNum == 20 || feldNum == 22 || feldNum == 23 || feldNum == 24 || feldNum == 34 || feldNum == 35){
                                sternPostition = "unten";
                            }else if(feldNum == 13 || feldNum == 21 || feldNum == 25 || feldNum == 27 || feldNum == 33 || feldNum == 36){
                                sternPostition = "oben";
                            }else if(feldNum == 1 || feldNum == 6 || feldNum == 10 || feldNum == 29 || feldNum == 32){
                                sternPostition = "links";
                            }else{
                                sternPostition = "rechts";
                            }
                            mapManager.mapFliesen[zeile][spalte].feld.hatStern = true;
                            sternFeldSpalte = spalte;
                            sternFeldZeile = zeile;
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
            mapManager.spielablaufManager.mainSpieler.konto.muenzeRueckMeldungMalen(10 - mapManager.spielablaufManager.mainSpieler.konto.muenzen);
        }
    }

    private void sternKaufenBox(){

        //hinter bildschirm dunkler malen
        Color c = new Color(0,0,0,100);
        g2.setColor(c);
        g2.fillRect(0, 0, 1440, 864);

        c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(330, 300, 780, 200, 35, 35);
        c = new Color(255,255,255,200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(335,305,770, 190, 25, 25);

        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,65F));
        g2.drawString("Willst du einen Stern ",410, 380);
        g2.drawString("für 10 Muenzen Kaufen?",370, 450);
        g2.drawImage(stern,250,220, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse*2, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse*2, null);
    }

}
