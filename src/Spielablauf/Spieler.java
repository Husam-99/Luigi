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
    public Feld naechstesFeld;
    public Feld aktuellesFeld;
    public String richtung;


    public Spieler(SpielPanel sp, SpielMapManager mapManager){
        this.sp = sp;
        this.mapManager = mapManager;
        try {
            myImage = ImageIO.read(new File("src/source/husam/down1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bildschirmX = sp.bildschirmBreite/2 - (sp.vergroesserteFliesenGroesse /2);
        bildschirmY = sp.bildschirmHoehe/2 - (sp.vergroesserteFliesenGroesse /2) ;

        mapManager.hinzufuegeSpieler(this);
        setzeWerte();

    }
    public void update(){

    }
    public void setzeWerte(){
        weltX = sp.vergroesserteFliesenGroesse * 11;
        weltY = sp.vergroesserteFliesenGroesse * 21;
        geschwindigkeit = 4;
        richtung = "nord";
        naechstesFeld = mapManager.mapFliesen[19][11].feld;
        aktuellesFeld = null;

    }
    public void malen(Graphics2D g2){
        g2.drawImage(myImage, bildschirmX, bildschirmY, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);

    }
}
