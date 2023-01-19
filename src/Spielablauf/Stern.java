package Spielablauf;

import Networking.Pakete.SternKaufen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Stern {
    SpielMapManager mapManager;
    private int feldNum;
    public int spriteNum = 0;
    private int spriteZaehler = 0;
    public String sternPostition;
    public BufferedImage stern, stern1, stern2, stern3, stern4, stern5;
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
        SternKaufen sternKaufen = new SternKaufen();
        sternKaufen.sternGekauft = true;
        mapManager.sp.client.send(sternKaufen);

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

}
