package Minispiele;

import Main.SpielPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SammlerElement {
    SpielPanel sp;
    public BufferedImage  muenze7, muenze8, muenze9, muenze10, muenze11,muenze12,muenze13,daimond1,daimond2,daimond3,mushroom,spider;

    public Rectangle gegenstandRechteck;
    public int gegenstandX;
    public int gegenstandY;

    public int spriteNum;
    public int Imageindex;
    private int spriteZaehler;

    public SammlerElement(SpielPanel sp, int gegenstandX, int gegenstandY, int Imageindex){
        this.sp = sp;
        this.gegenstandX = gegenstandX;
        this.gegenstandY = gegenstandY;
        this.Imageindex=Imageindex;
        spriteNum = 0;
        spriteZaehler = 0;
        this.gegenstandRechteck = new Rectangle(this.gegenstandX, this.gegenstandY, sp.fliesenGroesse*2, sp.fliesenGroesse*2);
        getSammlerBilder();
    }

    public void getSammlerBilder(){
        try {
            if(Imageindex==0){
            muenze7= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele.sammlerMapBilder/SammlerBilder/coin7.png")));
            muenze8= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele.sammlerMapBilder/SammlerBilder/coin8.png")));
            muenze9= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele.sammlerMapBilder/SammlerBilder/coin9.png")));
            muenze10= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele.sammlerMapBilder/SammlerBilder/coin10.png")));
            muenze11= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele.sammlerMapBilder/SammlerBilder/coin11.png")));
            muenze12= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele.sammlerMapBilder/SammlerBilder/coin12.png")));
            muenze13= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele.sammlerMapBilder/SammlerBilder/coin13.png")));
            }
            if(Imageindex==1){
                spider= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/SpiderWeb.png")));
            }
            if(Imageindex==2){
                daimond1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/Diamond1.png")));
                daimond2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/Diamond2.png")));
                daimond3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/Diamond3.png")));
            }
            if(Imageindex==3){
                mushroom= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/Mushroom.png")));
            }

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void muenzeupdate() {
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
            } else if (spriteNum == 5) {
                spriteNum = 6;
            }
        } else if (spriteNum == 6) {
            spriteNum = 0;
        }
            spriteZaehler = 0;
        }
    public void daimondupdate() {
        spriteZaehler++;
        if (spriteZaehler > 15) {
            if (spriteNum == 0) {
                spriteNum = 1;
            } else if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            } else if (spriteNum == 1) {
                spriteNum = 0;
            }
            spriteZaehler = 0;
        }
    }
    public void daimondmalen(Graphics2D g2) {
        BufferedImage image2 = null;
        if (this.spriteNum == 0) {
            image2 = this.daimond1;
        } else if (this.spriteNum == 1) {
            image2 = this.daimond2;
        } else if (this.spriteNum == 2) {
            image2 = this.daimond3;
        }

            g2.drawImage(image2, gegenstandX, gegenstandY, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);


    }

    public void muenzemalen(Graphics2D g2) {
        BufferedImage image = null;
        if (this.spriteNum == 0) {
            image = this.muenze7;
        } else if (this.spriteNum == 1) {
            image = this.muenze8;
        } else if (this.spriteNum == 2) {
            image = this.muenze9;
        } else if (this.spriteNum == 3) {
            image = this.muenze10;
        } else if (this.spriteNum == 4) {
            image = this.muenze11;
        } else if (this.spriteNum == 5) {
            image = this.muenze12;
        }
        else if (this.spriteNum == 6) {
            image = this.muenze13;
        }

            g2.drawImage(image, gegenstandX, gegenstandY, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);


    }
    public void spidermalen(Graphics2D g2) {
        g2.drawImage(spider, gegenstandX, gegenstandY, sp.vergroesserteFliesenGroesse*2, sp.vergroesserteFliesenGroesse*2, null);

    }
    public void mushroommalen(Graphics2D g2) {
        g2.drawImage(mushroom, gegenstandX, gegenstandY, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);

    }


}
