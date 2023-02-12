package spieler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MegaWuerfel extends GegenstandWuerfel{

    public BufferedImage wuerfel7, wuerfel8, wuerfel9, wuerfel10, wuerfel11, wuerfel12;

    public MegaWuerfel(Spieler spieler) {
        super(spieler);
        preis = 5;
        nummer = 3;
        getGegenstandBilder();
    }

    @Override
    public void getGegenstandBilder(){
        try {
            icon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gegenstaende/Megawuerfel.png")));
            wuerfel7= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gegenstaende/megaWuerfel/wuerfel7.png")));
            wuerfel8= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gegenstaende/megaWuerfel/wuerfel8.png")));
            wuerfel9= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gegenstaende/megaWuerfel/wuerfel9.png")));
            wuerfel10= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gegenstaende/megaWuerfel/wuerfel10.png")));
            wuerfel11= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gegenstaende/megaWuerfel/wuerfel11.png")));
            wuerfel12= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gegenstaende/megaWuerfel/wuerfel12.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void effeckteAnwenden(){
        spieler.normaleWuerfelZustand = false;
        spieler.megaWuerfelZustand = true;
        spieler.inventarZustand = false;
        spieler.spielablaufManager.mapManager.mapEingabeManager.iGedrueckt = false;
    }
    @Override

    public void schritteAnzahlBestimmen(){
        if (spriteNum == 0) {
            spieler.schritteAnzahl = 7;
        } else if (spriteNum == 1) {
            spieler.schritteAnzahl = 8;
        } else if (spriteNum == 2) {
            spieler.schritteAnzahl = 9;
        } else if (spriteNum == 3) {
            spieler.schritteAnzahl = 10;
        } else if (spriteNum == 4) {
            spieler.schritteAnzahl = 11;
        }else if (spriteNum == 5) {
            spieler.schritteAnzahl = 12;
        }
    }

    @Override
    public void update() {
        if(spieler.spielablaufManager.mapManager.mapEingabeManager.spaceGedrueckt) {
            spriteZaehler++;
            if (spriteZaehler > 3) {
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
                }else if (spriteNum == 5) {
                    spriteNum = 0;
                }
                spriteZaehler = 0;
            }
        }
    }

    @Override
    public void malen(Graphics2D g2){
        BufferedImage image = null;
        if(spriteNum == 0){
            image = wuerfel12;
        }else if(spriteNum == 1){
            image = wuerfel7;
        }else if(spriteNum == 2){
            image = wuerfel10;
        }else if(spriteNum == 3){
            image = wuerfel11;
        }else if(spriteNum == 4){
            image = wuerfel8;
        }else if(spriteNum == 5){
            image = wuerfel9;
        }
        g2.drawImage(image, 620 , 65, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse*2, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse*2, null);
    }

}
