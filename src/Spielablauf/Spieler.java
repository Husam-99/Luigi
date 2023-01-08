package Spielablauf;

import Main.SpielPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Spieler {
    SpielPanel sp;

    SpielMapManager mapManager;
    public final int bildschirmX;
    public final int bildschirmY;
    public int weltX, weltY;
    public int geschwindigkeit;
    public BufferedImage myImage;


    public Spieler(SpielPanel sp, SpielMapManager mapManager){
        this.sp = sp;
        this.mapManager = mapManager;
        try {
            myImage = ImageIO.read(new File("src/source/husam/down1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bildschirmX = sp.bildschirmBreite/2 - (sp.doppelteFliesenGroesse/2);
        bildschirmY = sp.bildschirmHoehe/2 - (sp.doppelteFliesenGroesse/2) ;

        mapManager.hinzufuegeSpieler(this);
        setzeWerte();

    }
    public void update(){

    }
    public void setzeWerte(){
        weltX = sp.doppelteFliesenGroesse * 11;
        weltY = sp.doppelteFliesenGroesse * 21;
        geschwindigkeit = 4;

    }
    public void malen(Graphics2D g2){
        g2.drawImage(myImage, bildschirmX, bildschirmY, sp.doppelteFliesenGroesse, sp.doppelteFliesenGroesse, null);

    }
}
