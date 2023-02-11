package Minispiele;

import Main.SpielPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SammlerElement {
    SpielPanel sp;
    public BufferedImage muenze1, muenze2, muenze3, muenze4, muenze5, muenze6, muenze7, muenze8,
            diamond1, diamond2, diamond3, mushroom, spider;

    public Rectangle elementRechteck;
    public int elementXPosition;
    public int elementYPosition;

    public int spriteNum;
    public int elementIndex;
    private int spriteZaehler;

    public SammlerElement(SpielPanel sp, int elementXPosition, int elementYPosition, int elementIndex) {
        this.sp = sp;
        this.elementXPosition = elementXPosition;
        this.elementYPosition = elementYPosition;
        this.elementIndex = elementIndex;
        spriteNum = 0;
        spriteZaehler = 0;
        if(elementIndex == 2){
            this.elementRechteck = new Rectangle(this.elementXPosition, this.elementYPosition, sp.vergroesserteFliesenGroesse * 2, sp.vergroesserteFliesenGroesse * 2);
        } else if(elementIndex == 3){
            this.elementRechteck = new Rectangle(this.elementXPosition + 10, this.elementYPosition + 10, ((sp.vergroesserteFliesenGroesse/3) * 4) -10, ((sp.vergroesserteFliesenGroesse/3) * 4) -10);
        } else {
            this.elementRechteck = new Rectangle(this.elementXPosition + 10, this.elementYPosition + 10,  sp.vergroesserteFliesenGroesse -10, sp.vergroesserteFliesenGroesse -10);
        }
        getSammlerBilder();
    }

    // hier wird das Element Fotos festgelegt
    public void getSammlerBilder() {
        try {
            if (elementIndex == 1) {
                muenze1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/coin1.png")));
                muenze2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/coin2.png")));
                muenze3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/coin3.png")));
                muenze4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/coin4.png")));
                muenze5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/coin5.png")));
                muenze6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/coin6.png")));
                muenze7 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/coin7.png")));
                muenze8 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/coin8.png")));

            }
            else if (elementIndex == 2) {
                spider = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/SpiderWeb.png")));
            }
            else if (elementIndex == 3) {
                diamond1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/Diamond1.png")));
                diamond2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/Diamond2.png")));
                diamond3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/Diamond3.png")));
            }
            else if (elementIndex == 4) {
                mushroom = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/Mushroom.png")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void muenzeUpdate() {
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
            } else if (spriteNum == 6) {
                spriteNum = 7;
            } else if(spriteNum == 7) {
                spriteNum = 8;
            } else if(spriteNum == 8){
                spriteNum = 9;
            } else if(spriteNum == 9){
                spriteNum = 10;
            } else if(spriteNum == 10){
                spriteNum = 11;
            } else if(spriteNum == 11){
                spriteNum = 12;
            } else if(spriteNum == 12){
                spriteNum = 13;
            } else if(spriteNum == 13){
                spriteNum = 0;
            }
            spriteZaehler = 0;
        }
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

    //Hier wird daimond gemalt
    public void daimondmalen(Graphics2D g2) {
        BufferedImage image2 = null;
        if (this.spriteNum == 0) {
            image2 = this.diamond1;
        } else if (this.spriteNum == 1) {
            image2 = this.diamond2;
        } else if (this.spriteNum == 2) {
            image2 = this.diamond3;
        }

        g2.drawImage(image2, elementXPosition, elementYPosition, (sp.vergroesserteFliesenGroesse/3) * 4, (sp.vergroesserteFliesenGroesse/3) * 4, null);


    }


    //Hier wird Meunzen gemalt
    public void muenzeMalen(Graphics2D g2) {
        BufferedImage image = null;
        if (this.spriteNum == 0 ) {image = this.muenze1;
        } else if (this.spriteNum == 1 || this.spriteNum == 13) {image = this.muenze2;
        } else if (this.spriteNum == 2 || this.spriteNum == 12) {image = this.muenze3;
        } else if (this.spriteNum == 3 || this.spriteNum == 11) {image = this.muenze4;
        } else if (this.spriteNum == 4 || this.spriteNum == 10) {image = this.muenze5;
        } else if (this.spriteNum == 5 || this.spriteNum == 9) {image = this.muenze6;
        } else if (this.spriteNum == 6 || this.spriteNum == 8) {image = this.muenze7;
        } else if (this.spriteNum == 7) {image = this.muenze8;
        }

        g2.drawImage(image, elementXPosition, elementYPosition, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);

    }

    //Hier wird Spidernetz gemalt
    public void spiderMalen(Graphics2D g2) {
        g2.drawImage(spider, elementXPosition, elementYPosition, sp.vergroesserteFliesenGroesse * 2, sp.vergroesserteFliesenGroesse * 2, null);

    }

    //Hier wird Mushroom gemalt
    public void mushroomMalen(Graphics2D g2) {
        g2.drawImage(mushroom, elementXPosition, elementYPosition, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);

    }


}
