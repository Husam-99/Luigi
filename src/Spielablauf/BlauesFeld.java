package Spielablauf;

import Networking.Pakete.GegenstandInfo;
import Networking.Pakete.Muenzenzahl;
import Networking.Pakete.Sternzahl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BlauesFeld extends Feld{
    Graphics2D g2;
    public int befehlNum1 = 0, befehlNum2 = 0, befehlNum3 = 0, slotNum;
    public boolean inventarLeer = false, inventarKlauen = false, klauenMoeglichkeiten = false;

    public BlauesFeld(SpielMapManager mapManager, int weltY, int weltX, int feldNum) {
        super(mapManager, weltY, weltX, feldNum);
        try {
            super.feldImage = ImageIO.read(new File("src/source/felder/Blue_Field.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void muenzeKlauen(){
        if(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).konto.muenzen >= 5) {
            mapManager.spielablaufManager.mainSpieler.konto.muenzenErhalten(5);
            Muenzenzahl muenzenzahl = new Muenzenzahl();
            muenzenzahl.blauesFeldClientIndex = mapManager.spielablaufManager.ausgewaehlteClientInex;
            mapManager.spielablaufManager.sp.client.send(muenzenzahl);
            mapManager.spielablaufManager.tempBlauesFeld.befehlNum2 = 0;
            mapManager.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten = false;
            mapManager.spielablaufManager.blauesFeldZustand = false;
            if(!mapManager.spielablaufManager.mainSpieler.aktuellesFeld.hatStern) {
                mapManager.spielablaufManager.mainSpieler.amSpiel = false;
            }else{
                mapManager.spielablaufManager.mapManager.stern.sternKaufen = true;
            }
        }else{
            mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).konto.genugMuenzen = false;
        }
    }

    public void sternKlauen(){
        if(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).konto.sterne >= 1) {
            mapManager.spielablaufManager.mainSpieler.konto.sterneErhalten(1);
            mapManager.spielablaufManager.tempBlauesFeld.befehlNum2 = 0;
            mapManager.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten = false;
            mapManager.spielablaufManager.blauesFeldZustand = false;
            if(!mapManager.spielablaufManager.mainSpieler.aktuellesFeld.hatStern) {
                mapManager.spielablaufManager.mainSpieler.amSpiel = false;
            }else{
                mapManager.spielablaufManager.mapManager.stern.sternKaufen = true;
            }            Sternzahl sternzahl = new Sternzahl();
            sternzahl.blauesFeldClientIndex = mapManager.spielablaufManager.ausgewaehlteClientInex;
            mapManager.spielablaufManager.sp.client.send(sternzahl);
        }else {
            mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).konto.genugSterne = false;
        }
    }

    public void gegenstandKlauen(){
        mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.anzahlGegenstaendeInInventar();
        if(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl > 0) {
            mapManager.spielablaufManager.mainSpieler.inventar.gegenstandBekommen(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.inventar[mapManager.spielablaufManager.tempBlauesFeld.slotNum].nummer);
            GegenstandInfo gegenstandInfo = new GegenstandInfo();
            gegenstandInfo.gegenstandClientIndex = mapManager.spielablaufManager.ausgewaehlteClientInex;
            gegenstandInfo.gestohleneGegenstandNum = mapManager.spielablaufManager.tempBlauesFeld.slotNum;
            mapManager.spielablaufManager.sp.client.send(gegenstandInfo);
            mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.inventar[mapManager.spielablaufManager.tempBlauesFeld.slotNum] = null;
            mapManager.spielablaufManager.tempBlauesFeld.befehlNum3 = 0;
            mapManager.spielablaufManager.blauesFeldZustand = false;
            mapManager.spielablaufManager.tempBlauesFeld.inventarKlauen = false;
            if(!mapManager.spielablaufManager.mainSpieler.aktuellesFeld.hatStern) {
                mapManager.spielablaufManager.mainSpieler.amSpiel = false;
            }else{
                mapManager.spielablaufManager.mapManager.stern.sternKaufen = true;
            }
        }else{
            inventarLeer = true;
        }
    }

    @Override
    public void effeckteAnwenden(){
        mapManager.spielablaufManager.blauesFeldZustand = true;
        mapManager.spielablaufManager.clientAuswaehlen = true;
        mapManager.spielablaufManager.miniMapZustand = false;
    }

    public void malen(Graphics2D g2){
        this.g2 = g2;
        if(klauenMoeglichkeiten){
            mapManager.spielablaufManager.hinterBoxMalen();
            klauenMoeglichkeitenMalen();
            if(!mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).konto.genugSterne){
                mapManager.spielablaufManager.mainSpieler.konto.baluesFeldRueckMeldungMalen();
            }else if(!mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).konto.genugMuenzen){
                mapManager.spielablaufManager.mainSpieler.konto.baluesFeldRueckMeldungMalen();
            }else if(inventarLeer){
                mapManager.spielablaufManager.mainSpieler.konto.baluesFeldRueckMeldungMalen();
            }else if(mapManager.spielablaufManager.inventarVoll){
                mapManager.spielablaufManager.mainSpieler.inventar.inventarVollRueckMeldungMalen(g2);
            }
        }else if(inventarKlauen){
            inventarKlauenBoxMalen();
            gegenstaendeKlauenMalen();
        }
    }

    private void klauenMoeglichkeitenMalen(){
        if(befehlNum2 == 0) {
            g2.drawImage(mapManager.muenze.muenze1, 400, 325, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse * 3 + 30, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse * 3 + 30, null);
            g2.drawImage(mapManager.stern.stern, 600, 265, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse * 3, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse * 3, null);
            g2.drawImage(mapManager.spielablaufManager.mainSpieler.inventar.icon, 905, 335, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse + 40, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse + 40, null);
        }else if(befehlNum2 == 1){
            g2.drawImage(mapManager.muenze.muenze1, 400, 325, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse * 3, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse * 3, null);
            g2.drawImage(mapManager.stern.stern, 600, 265, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse * 3 + 30, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse * 3 + 30, null);
            g2.drawImage(mapManager.spielablaufManager.mainSpieler.inventar.icon, 905, 335, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse + 40 , mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse + 40 , null);
        }else if(befehlNum2 == 2){
            g2.drawImage(mapManager.muenze.muenze1, 400, 325, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse * 3, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse * 3, null);
            g2.drawImage(mapManager.stern.stern, 600, 265, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse * 3, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse * 3, null);
            g2.drawImage(mapManager.spielablaufManager.mainSpieler.inventar.icon, 905, 335, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse + 40 + 30, mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse + 40 + 30, null);
        }
    }

    private void inventarKlauenBoxMalen(){
        int x = 620, width = 200;
        mapManager.spielablaufManager.mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.anzahlGegenstaendeInInventar();
        if(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 2){
            x = 545;
            width = 350;
        }else if(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 3){
            x = 470;
            width = 500;
        }else if(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 4){
            x = 395;
            width = 650;
        }
        Color c = new Color(0,0,0,100);
        g2.setColor(c);
        g2.fillRect(0, 0, 1440, 864);
        c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, 300, width, 170, 35, 35);
        c = new Color(255,255,255,200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,305,width-10, 160, 25, 25);
    }

    private void gegenstaendeKlauenMalen(){
        int x = 615;
        int messung = mapManager.spielablaufManager.sp.vergroesserteFliesenGroesse * 2;
        int temp1= 0;
        mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.anzahlGegenstaendeInInventar();
        if(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 2){
            x = 545;
        }else if(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 3){
            x = 470;
        }else if(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 4){
            x = 395;
        }
        for(int temp2 = 0; temp2 < 4; temp2++){
            if(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.inventar[temp2] != null){
                temp1++;
                if(temp1 == 1 && befehlNum3 == 0) {
                    g2.drawImage(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.inventar[temp2].icon, x, 280, messung + 20, messung + 20, null);
                    slotNum = temp2;
                }else if(temp1 == 2 && befehlNum3 == 1){
                    g2.drawImage(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.inventar[temp2].icon, x, 280, messung + 20, messung + 20, null);
                    slotNum = temp2;
                }else if(temp1 == 3 && befehlNum3 == 2){
                    g2.drawImage(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.inventar[temp2].icon, x, 280, messung + 20, messung + 20, null);
                    slotNum = temp2;
                }else if(temp1 == 4 && befehlNum3 == 3){
                    g2.drawImage(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.inventar[temp2].icon, x, 280, messung + 20, messung + 20, null);
                    slotNum = temp2;
                }else{
                    g2.drawImage(mapManager.spielablaufManager.sp.alleSpieler.get(mapManager.spielablaufManager.ausgewaehlteClientInex).inventar.inventar[temp2].icon, x, 280, messung, messung, null);
                }
                x += 150;
            }
        }
    }

}
