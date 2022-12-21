package menue;

import main.SpielPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenueManager {

    SpielPanel sp;
    public int spielerAnzahl;
    public int rundenAnzahl;
    public MenueEingabeManager menueEingabeManager = new MenueEingabeManager(this);
    Hauptmenue hauptmenue = new Hauptmenue(this);
    public int menueZustand;
    public int hauptmenueZustand1 = 0;
    public int hauptmenueZustand2 = 1;
    public int spielfigurAuswaehlenZustand = 2;
    SpielfigurAuswaehlen spielfigurAuswaehlen = new SpielfigurAuswaehlen(this);
    BufferedImage hup1, hup2, hup3, hdown1, hdown2, hdown3, hright1, hright2, hright3, hleft1, hleft2, hleft3;
    BufferedImage hwuerfel1, hwuerfel2, hwuerfel3, hwuerfel4, hwuerfel5, hwuerfel6;
    BufferedImage tup1, tup2, tup3, tdown1, tdown2, tdown3, tright1, tright2, tright3, tleft1, tleft2, tleft3;
    BufferedImage twuerfel1, twuerfel2, twuerfel3, twuerfel4, twuerfel5, twuerfel6;
    BufferedImage muenzen, stern, mark1,mark2;
    public MenueManager(SpielPanel sp){
        this.sp = sp;
        getbilder();
        getTahaBilder();
        getHusamBilder();
    }
    public int spielerAnzahlFestlegen(){
        return 1;
    }
    public int rundenAnzahlFestlegen(){
        return 1;
    }
    public void update(){
        if(menueZustand == hauptmenueZustand1 || menueZustand == hauptmenueZustand2){
            hauptmenue.update();
        }else if(menueZustand == spielfigurAuswaehlenZustand){
            spielfigurAuswaehlen.update();
        }    }
    public void malen(Graphics2D g2){
        if(menueZustand == hauptmenueZustand1 || menueZustand == hauptmenueZustand2){
            hauptmenue.malen(g2);
        }else if(menueZustand == spielfigurAuswaehlenZustand){
            spielfigurAuswaehlen.malen(g2);
        }
    }
    public void getbilder(){
        try {
            muenzen = ImageIO.read(getClass().getResourceAsStream("/bilder/muenzen.png"));
            stern = ImageIO.read(getClass().getResourceAsStream("/bilder/stern.png"));
            mark1 = ImageIO.read(getClass().getResourceAsStream("/bilder/mark1.png"));
            mark2 = ImageIO.read(getClass().getResourceAsStream("/bilder/mark2.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void getTahaBilder(){
        try {
            tup1= ImageIO.read(getClass().getResourceAsStream("/taha/up1.png"));
            tup2= ImageIO.read(getClass().getResourceAsStream("/taha/up2.png"));
            tup3= ImageIO.read(getClass().getResourceAsStream("/taha/up3.png"));
            tdown1= ImageIO.read(getClass().getResourceAsStream("/taha/down1.png"));
            tdown2= ImageIO.read(getClass().getResourceAsStream("/taha/down2.png"));
            tdown3= ImageIO.read(getClass().getResourceAsStream("/taha/down3.png"));
            tright1= ImageIO.read(getClass().getResourceAsStream("/taha/right1.png"));
            tright2= ImageIO.read(getClass().getResourceAsStream("/taha/right2.png"));
            tright3= ImageIO.read(getClass().getResourceAsStream("/taha/right3.png"));
            tleft1= ImageIO.read(getClass().getResourceAsStream("/taha/left1.png"));
            tleft2= ImageIO.read(getClass().getResourceAsStream("/taha/left2.png"));
            tleft3= ImageIO.read(getClass().getResourceAsStream("/taha/left3.png"));
            twuerfel1= ImageIO.read(getClass().getResourceAsStream("/taha/wuerfel1.png"));
            twuerfel2=ImageIO.read(getClass().getResourceAsStream("/taha/wuerfel2.png"));
            twuerfel3= ImageIO.read(getClass().getResourceAsStream("/taha/wuerfel3.png"));
            twuerfel4= ImageIO.read(getClass().getResourceAsStream("/taha/wuerfel4.png"));
            twuerfel5= ImageIO.read(getClass().getResourceAsStream("/taha/wuerfel5.png"));
            twuerfel6= ImageIO.read(getClass().getResourceAsStream("/taha/wuerfel6.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void getHusamBilder(){
        try {
            hup1= ImageIO.read(getClass().getResourceAsStream("/husam/up1.png"));
            hup2= ImageIO.read(getClass().getResourceAsStream("/husam/up2.png"));
            hup3= ImageIO.read(getClass().getResourceAsStream("/husam/up3.png"));
            hdown1= ImageIO.read(getClass().getResourceAsStream("/husam/down1.png"));
            hdown2= ImageIO.read(getClass().getResourceAsStream("/husam/down2.png"));
            hdown3= ImageIO.read(getClass().getResourceAsStream("/husam/down3.png"));
            hright1= ImageIO.read(getClass().getResourceAsStream("/husam/right1.png"));
            hright2= ImageIO.read(getClass().getResourceAsStream("/husam/right2.png"));
            hright3= ImageIO.read(getClass().getResourceAsStream("/husam/right3.png"));
            hleft1= ImageIO.read(getClass().getResourceAsStream("/husam/left1.png"));
            hleft2= ImageIO.read(getClass().getResourceAsStream("/husam/left2.png"));
            hleft3= ImageIO.read(getClass().getResourceAsStream("/husam/left3.png"));
            hwuerfel1= ImageIO.read(getClass().getResourceAsStream("/husam/wuerfel1.png"));
            hwuerfel2=ImageIO.read(getClass().getResourceAsStream("/husam/wuerfel2.png"));
            hwuerfel3= ImageIO.read(getClass().getResourceAsStream("/husam/wuerfel3.png"));
            hwuerfel4= ImageIO.read(getClass().getResourceAsStream("/husam/wuerfel4.png"));
            hwuerfel5= ImageIO.read(getClass().getResourceAsStream("/husam/wuerfel5.png"));
            hwuerfel6= ImageIO.read(getClass().getResourceAsStream("/husam/wuerfel6.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
