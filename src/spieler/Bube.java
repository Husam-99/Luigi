package spieler;

import Networking.Pakete.Bewegung;
import Spielablauf.Feld;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bube extends Gegenstand{
    int bubeClientIndex = -1;
    public Bube(Spieler spieler) {
        super(spieler);
        preis = 11;
        nummer = 2;
        getGegenstandBilder();
    }

    @Override
    public void getGegenstandBilder(){
        try {
            icon = ImageIO.read(getClass().getResourceAsStream("/gegenstaende/Bube.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void effeckteAnwenden(){
        bubeClientIndex = 1;
        Bewegung bewegung = new Bewegung();
        bewegung.feldNum = spieler.aktuellFeld.feldNum;
        bewegung.bubeClientIndex = bubeClientIndex;
        spieler.spielablaufManager.sp.client.send(bewegung);
        Feld tempfled = spieler.spielablaufManager.sp.alleSpieler.get(bubeClientIndex).aktuellFeld;
        spieler.spielablaufManager.sp.alleSpieler.get(bubeClientIndex).aktuellFeld = spieler.spielablaufManager.mainSpieler.aktuellFeld;
        spieler.spielablaufManager.sp.alleSpieler.get(bubeClientIndex).naechstesFeld = spieler.spielablaufManager.mainSpieler.aktuellFeld;
        spieler.spielablaufManager.sp.alleSpieler.get(bubeClientIndex).aktuellesFeld = null;
        spieler.spielablaufManager.sp.alleSpieler.get(bubeClientIndex).tempFeld = null;
        spieler.spielablaufManager.mainSpieler.aktuellFeld = tempfled;
        spieler.spielablaufManager.mainSpieler.naechstesFeld = tempfled;
        spieler.spielablaufManager.mainSpieler.aktuellesFeld = null;
        spieler.spielablaufManager.mainSpieler.tempFeld = null;

        int weltXtemp = spieler.spielablaufManager.sp.alleSpieler.get(bubeClientIndex).weltX;
        int weltYtemp = spieler.spielablaufManager.sp.alleSpieler.get(bubeClientIndex).weltY;
        spieler.spielablaufManager.sp.alleSpieler.get(bubeClientIndex).weltX = spieler.spielablaufManager.mainSpieler.weltX;
        spieler.spielablaufManager.sp.alleSpieler.get(bubeClientIndex).weltY = spieler.spielablaufManager.mainSpieler.weltY;
        spieler.spielablaufManager.mainSpieler.weltX = weltXtemp;
        spieler.spielablaufManager.mainSpieler.weltY = weltYtemp;
        spieler.spielablaufManager.sp.alleSpieler.get(bubeClientIndex).bildschirmX = spieler.spielablaufManager.sp.alleSpieler.get(bubeClientIndex).weltX - spieler.spielablaufManager.mainSpieler.weltX + spieler.spielablaufManager.mainSpieler.bildschirmX;
        spieler.spielablaufManager.sp.alleSpieler.get(bubeClientIndex).bildschirmY = spieler.spielablaufManager.sp.alleSpieler.get(bubeClientIndex).weltY - spieler.spielablaufManager.mainSpieler.weltY + spieler.spielablaufManager.mainSpieler.bildschirmY;

        spieler.inventarZustand = false;
        spieler.spielablaufManager.mapManager.mapEingabeManager.iGedrueckt = false;
    }



}
