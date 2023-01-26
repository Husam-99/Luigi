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
            daimond1, daimond2, daimond3, mushroom, spider;

    public Rectangle gegenstandRechteck;
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
        this.gegenstandRechteck = new Rectangle(this.elementXPosition, this.elementYPosition, sp.fliesenGroesse * 2, sp.fliesenGroesse * 2);
        getSammlerBilder();
    }

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
                muenze7 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/coin8.png")));

            }
            else if (elementIndex == 2) {
                spider = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/SpiderWeb.png")));
            }
            else if (elementIndex == 3) {
                daimond1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/Diamond1.png")));
                daimond2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/Diamond2.png")));
                daimond3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/SammlerBilder/Diamond3.png")));
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
                spriteNum = 0;
            }
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

        g2.drawImage(image2, elementXPosition, elementYPosition, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);


    }

    public void muenzeMalen(Graphics2D g2) {
        BufferedImage image = null;
        if (this.spriteNum == 0) {
            image = this.muenze1;
        } else if (this.spriteNum == 1) {
            image = this.muenze2;
        } else if (this.spriteNum == 2) {
            image = this.muenze3;
        } else if (this.spriteNum == 3) {
            image = this.muenze4;
        } else if (this.spriteNum == 4) {
            image = this.muenze5;
        } else if (this.spriteNum == 5) {
            image = this.muenze6;
        } else if (this.spriteNum == 6) {
            image = this.muenze7;
        } else if(this.spriteNum == 7) {
            image = this.muenze8;
        }

        g2.drawImage(image, elementXPosition, elementYPosition, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);

    }

    public void spiderMalen(Graphics2D g2) {
        g2.drawImage(spider, elementXPosition, elementYPosition, sp.vergroesserteFliesenGroesse * 2, sp.vergroesserteFliesenGroesse * 2, null);

    }

    public void mushroomMalen(Graphics2D g2) {
        g2.drawImage(mushroom, elementXPosition, elementYPosition, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);

    }


}
