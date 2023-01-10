package spieler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Spielfigur {
    Spieler s;
    public BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3;
    int spriteNum = 1;
    int spriteZaehler = 0;
    String richtung = "unten";

    public Spielfigur(Spieler s){
        this.s = s;
    }
    public void getSpielFigurBilder() {}

    public void update(){
       /* if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true){

            if(keyH.upPressed == true){
                richtung = "oben";
                s.positionY -= s.geschwindigkeit;
            }else if (keyH.downPressed == true){
                richtung = "unten";
                s.positionY += s.geschwindigkeit;
            }else if (keyH.leftPressed == true){
                richtung = "links";
                s.positionX -= s.geschwindigkeit;
            }else if(keyH.rightPressed == true){
                richtung = "rechts";
                s.positionX += s.geschwindigkeit;
            }
            spriteZaehler++;
            if(spriteZaehler > 3){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 1;
                }
                spriteZaehler = 0;
            }
        }*/
    }
    public void malen(Graphics2D g2){
        BufferedImage image = null;
        switch(richtung) {
            case "oben":
                if(spriteNum == 1 ||spriteNum ==3) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                if(spriteNum == 4) {
                    image = up3;
                }
                break;
            case "unten":
                if(spriteNum == 1 || spriteNum == 3) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                if(spriteNum == 4) {
                    image = down3;
                }
                break;
            case "links":
                if(spriteNum == 1 || spriteNum == 3) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                if(spriteNum == 4) {
                    image = left3;
                }
                break;
            case "rechts":
                if(spriteNum == 1 || spriteNum == 3) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                if(spriteNum == 4) {
                    image = right3;
                }
                break;
        }
        g2.drawImage(image, s.bildschirmX, s.bildschirmY, s.sp.vergroesserteFliesenGroesse, s.sp.vergroesserteFliesenGroesse, null);
    }
}
