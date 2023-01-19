package spieler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MiniWuerfel extends GegenstandWuerfel{

    public BufferedImage wuerfel1, wuerfel2, wuerfel3;

    public MiniWuerfel(Spieler spieler) {
        super(spieler);
        preis = 3;
        nummer = 4;
        getGegenstandBilder();
    }

    @Override
    public void getGegenstandBilder(){
        try {
            icon= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Miniwuerfel.png"));
            wuerfel1= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/miniWuerfel/wuerfel1.png"));
            wuerfel2= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/miniWuerfel/wuerfel2.png"));
            wuerfel3= ImageIO.read(getClass().getResourceAsStream("/gegenstaende/miniWuerfel/wuerfel3.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void effeckteAnwenden(){
        spieler.normaleWuerfelZustand = false;
        spieler.miniWuerfelZustand = true;
    }
    @Override
    public void schritteAnzahlBestimmen(){
        if (spriteNum == 0) {
            spieler.schritteAnzahl = 1;
        } else if (spriteNum == 1) {
            spieler.schritteAnzahl = 2;
        } else if (spriteNum == 2) {
            spieler.schritteAnzahl = 3;
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
            image = wuerfel2;
        }else if(spriteNum == 1){
            image = wuerfel3;
        }else if(spriteNum == 2){
            image = wuerfel1;
        }
        g2.drawImage(image, 620 , 65, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse*2, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse*2, null);
    }
}
