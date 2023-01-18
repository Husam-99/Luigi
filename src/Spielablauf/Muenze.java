package Spielablauf;

import Main.SpielPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Muenze {

    SpielPanel sp;
    public BufferedImage muenze1, muenze2, muenze3, muenze4, muenze5, muenze6;

    public Rectangle muenzeRechteck;
    private int muenzeX;
    private int muenzeY;
    public int spriteNum = 0;
    private int spriteZaehler = 0;
    public Muenze(SpielPanel sp){
        this.sp = sp;
        Random random = new Random();
        this.muenzeX = random.nextInt(100, 1290);
        this.muenzeY = random.nextInt(100, 668);
        this.muenzeRechteck = new Rectangle(this.muenzeX, this.muenzeY, sp.fliesenGroesse*2, sp.fliesenGroesse*2);
        getMuenzeBilder();
    }

    public void getMuenzeBilder(){
        try {
            muenze1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/muenze/Coin1.png")));
            muenze2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/muenze/Coin2.png")));
            muenze3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/muenze/Coin3.png")));
            muenze4= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/muenze/Coin4.png")));
            muenze5= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/muenze/Coin5.png")));
            muenze6= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/muenze/Coin6.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
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
                spriteNum = 0;
            }
            spriteZaehler = 0;
        }

    }
    public void malen(Graphics2D g2) {
        BufferedImage image = null;
        if (this.spriteNum == 0) {
            image = this.muenze1;
        } else if (this.spriteNum == 1) {
            image = this.muenze3;
        } else if (this.spriteNum == 2) {
            image = this.muenze4;
        } else if (this.spriteNum == 3) {
            image = this.muenze5;
        } else if (this.spriteNum == 4) {
            image = this.muenze6;
        } else if (this.spriteNum == 5) {
            image = this.muenze2;
        }
        g2.drawImage(image, muenzeX, muenzeY, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
    }



}
