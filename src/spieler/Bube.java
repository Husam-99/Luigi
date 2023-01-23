package spieler;

import Networking.Pakete.Bewegung;
import Spielablauf.Feld;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bube extends Gegenstand{

    public int befehlNum = 0;
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

    public void positionWechsel(){
        Bewegung bewegung = new Bewegung();
        bewegung.feldNum = spieler.aktuellFeld.feldNum;
        bewegung.bubeClientIndex = spieler.spielablaufManager.ausgewaehlteClientInex;
        spieler.spielablaufManager.sp.client.send(bewegung);
        Feld tempfled = spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).aktuellFeld;
        spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).aktuellFeld = spieler.spielablaufManager.mainSpieler.aktuellFeld;
        spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).naechstesFeld = spieler.spielablaufManager.mainSpieler.aktuellFeld;
        spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).aktuellesFeld = null;
        spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).tempFeld = null;
        spieler.spielablaufManager.mainSpieler.aktuellFeld = tempfled;
        spieler.spielablaufManager.mainSpieler.naechstesFeld = tempfled;
        spieler.spielablaufManager.mainSpieler.aktuellesFeld = null;
        spieler.spielablaufManager.mainSpieler.tempFeld = null;

        int weltXtemp = spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).weltX;
        int weltYtemp = spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).weltY;
        spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).weltX = spieler.spielablaufManager.mainSpieler.weltX;
        spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).weltY = spieler.spielablaufManager.mainSpieler.weltY;
        spieler.spielablaufManager.mainSpieler.weltX = weltXtemp;
        spieler.spielablaufManager.mainSpieler.weltY = weltYtemp;
        for (Spieler spieler : spieler.spielablaufManager.sp.alleSpieler) {
            spieler.bildschirmX = spieler.weltX - spieler.spielablaufManager.mainSpieler.weltX + spieler.spielablaufManager.mainSpieler.bildschirmX;
            spieler.bildschirmY = spieler.weltY - spieler.spielablaufManager.mainSpieler.weltY + spieler.spielablaufManager.mainSpieler.bildschirmY;
        }

        System.out.println("bube");
        System.out.println("aktuelles: " + spieler.aktuellesFeld);
        System.out.println("naechstes: " + spieler.naechstesFeld);
        System.out.println("aktuell: " + spieler.aktuellFeld);
        System.out.println("temp: " + spieler.tempFeld);
    }
    @Override
    public void effeckteAnwenden() {
        spieler.inventarZustand = false;
        spieler.spielablaufManager.mapManager.mapEingabeManager.iGedrueckt = false;
        spieler.spielablaufManager.clientAuswaehlen = true;
    }




}
