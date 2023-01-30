package spieler;

import Networking.Pakete.Bewegung;
import Spielablauf.Feld;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

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
            icon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gegenstaende/Bube.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void positionWechsel(){
        Bewegung bewegung = new Bewegung();
        bewegung.feldNum = spieler.aktuellesFeld.feldNum;
        bewegung.bubeClientIndex = spieler.spielablaufManager.ausgewaehlteClientInex;
        spieler.spielablaufManager.sp.client.send(bewegung);
        Feld tempfled = spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).aktuellesFeld;
        spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).aktuellesFeld = spieler.spielablaufManager.mainSpieler.aktuellesFeld;
        spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).vorherigesFeld = null;
        spieler.spielablaufManager.mainSpieler.aktuellesFeld = tempfled;
        spieler.spielablaufManager.mainSpieler.vorherigesFeld = null;

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
    }
    @Override
    public void effeckteAnwenden() {
        spieler.inventarZustand = false;
        spieler.spielablaufManager.mapManager.mapEingabeManager.iGedrueckt = false;
        spieler.spielablaufManager.clientAuswaehlen = true;
        spieler.spielablaufManager.bubeZustand = true;
    }




}
