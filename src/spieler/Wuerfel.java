package spieler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Wuerfel {
    int positionX;
    int positionY;
    public BufferedImage wuerfel1, wuerfel2, wuerfel3, wuerfel4, wuerfel5, wuerfel6;
    Spieler s;
    int spriteNum = 0;
    public Wuerfel(Spieler s){
        this.s = s;
    }
    public void getWuerfelBilder() {}
    public void update(){

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
        g2.drawImage(image, positionX, positionY, s.sp.doppelteFliesenGroesse, s.sp.doppelteFliesenGroesse, null);
    }
}
