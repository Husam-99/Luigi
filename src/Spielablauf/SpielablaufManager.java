package Spielablauf;

import Main.SpielPanel;
import Networking.Client.SpielClient;
import spieler.Bube;
import spieler.Spieler;

import java.awt.*;

public class SpielablaufManager {

    public SpielPanel sp;
    Graphics2D g2;

    public SpielMapManager mapManager;
    public Spieler mainSpieler;
    public Shop shop;
    MiniMap miniMap;

    public int spielerNum, ausgewaehlteClientInex;

    //temporäre Variablen für den Zugriff auf Variablen und Methoden der Variablen
    public Bube bube;
    public BlauesFeld tempBlauesFeld;

    //booleans für den Spielablauf
    public boolean shopGeoeffnet, clientAuswaehlen, bubeZustand, blauesFeldZustand, inventarVoll, miniMapZustand;

    public SpielablaufManager(SpielPanel sp) {
        this.sp = sp;
        mapManager = new SpielMapManager(this);
        mainSpieler = new Spieler(this);
        shop = new Shop(this);
        bube = new Bube(mainSpieler);

        spielerNum = 0;
        ausgewaehlteClientInex = -1;

        shopGeoeffnet = false;
        clientAuswaehlen = false;
        bubeZustand = false;
        blauesFeldZustand = false;
        inventarVoll = false;
        miniMapZustand = false;

        //das Objekt ist nur fuer den Zugriff auf die Methoden und dei Variablen
        tempBlauesFeld = new BlauesFeld(mapManager, -1, -1, -1);
    }

    public void update() {
        mapManager.update();
        if (!sp.alleSpieler.isEmpty())
            for (Spieler spieler : sp.alleSpieler) {
                spieler.update();
            }
        mainSpieler.update();
    }

    public void malen(Graphics2D g2){
        this.g2 = g2;
        mapManager.malen(g2);
        if(mainSpieler.spielfigur!=null){
            if(!sp.alleSpieler.isEmpty())
                for(Spieler spieler : sp.alleSpieler) {
                    if (spieler.spielfigur != null) {
                        spieler.malen(g2);
                    }
                }
            mainSpieler.malen(g2);
        }
        mainSpieler.konto.malen(g2);
        if(mainSpieler.spielfigur!=null) {
            if (!sp.alleSpieler.isEmpty()) {
                for (Spieler spieler : sp.alleSpieler) {
                    if (spieler.spielfigur != null) {

                        //Spieler Nummer festlegen, für die Spieler Status Boxes
                        spielerNum++;
                        spieler.konto.malen(g2);
                    }
                }
            }
        }
        roundAnzahlMalen();

        if(mainSpieler.inventarZustand){
            mainSpieler.inventar.malen(g2);
        }else if(shopGeoeffnet){
            shop.malen(g2);
        }else if(mapManager.stern.sternKaufen){
            mapManager.stern.malen(g2);
        }else if(clientAuswaehlen){
            hinterBoxMalen();
            clientMalen();
        }else if(blauesFeldZustand) {
            tempBlauesFeld.malen(g2);
        }

        //Minispiel kann jederzeit gemalt werden
        if(miniMapZustand){
            miniMap = new MiniMap(this);
            miniMap.malen(g2);
        }
        spielerNum = 0;
    }

    private void clientMalen(){

        //die Position festlegen, abhängig von Spieler Anzahl
        int x = 605;
        int profileMessung = sp.vergroesserteFliesenGroesse * 2;
        int temp1 = 0;
        if(SpielClient.anzahlSpieler == 3) {
            x = 495;
        }else if(SpielClient.anzahlSpieler == 4){
            x = 370;
        }

        for(int temp2 = 0; temp2 < 4; temp2++){
            if(sp.alleSpieler.get(temp2).spielfigur != null) {
                temp1++;
                if (temp1 == 1 && (bube.befehlNum == 0 || tempBlauesFeld.befehlNum1 == 0)){
                    g2.drawImage(sp.alleSpieler.get(temp2).spielfigur.profile, x, 300, profileMessung + 40, profileMessung + 40, null);
                    ausgewaehlteClientInex = temp2;
                }else if(temp1 == 2 && (bube.befehlNum == 1 || tempBlauesFeld.befehlNum1 == 1)) {
                    g2.drawImage(sp.alleSpieler.get(temp2).spielfigur.profile, x, 300, profileMessung + 40, profileMessung + 40, null);
                    ausgewaehlteClientInex = temp2;
                }else if(temp1 == 3 && (bube.befehlNum == 2 || tempBlauesFeld.befehlNum1 == 2)) {
                    g2.drawImage(sp.alleSpieler.get(temp2).spielfigur.profile, x, 300, profileMessung + 40, profileMessung + 40, null);
                    ausgewaehlteClientInex = temp2;
                }else{
                    g2.drawImage(sp.alleSpieler.get(temp2).spielfigur.profile, x, 310, profileMessung, profileMessung, null);
                }
                x += 250;
            }
        }
    }

    public void hinterBoxMalen(){

        //Die Boxmessungen und die position des Boxes anpassen für die Clients
        int x, width;
        if(tempBlauesFeld.klauenMoeglichkeiten ||  SpielClient.anzahlSpieler == 4){
            x = 350;
            width = 750;
        }else if(SpielClient.anzahlSpieler == 3) {
            x = 475;
            width = 500;
        }else{
            x = 600;
            width = 250;
        }

        //hinter bildschirm dunkler malen
        Color c = new Color(0,0,0,100);
        g2.setColor(c);
        g2.fillRect(0, 0, 1440, 864);

        c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, 300, width, 210, 35, 35);
        c = new Color(255,255,255,200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,305,width-10, 200, 25, 25);
    }

    //Runde Box und Runde Nummer malen
    private void roundAnzahlMalen(){

        //Der Box hinter malen
        Color c = new Color(0,0,0,150);
        g2.setColor(c);
        g2.fillRoundRect(0, 764, 290, 100, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(5,769,280, 90, 25, 25);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,52F));
        g2.setColor(Color.yellow);

        //Die Position "Runde X" mit der Rundnummer anpassen, abhängig von der Runde Nummer
        if(sp.ausgewaehlteRundenAnzahl > 9) {
            g2.drawString("Runde", 15, 827);
        }else{
            g2.drawString("Runde", 25, 827);
        }
        c = new Color(255,255,255,200);
        g2.setColor(c);
        if(sp.ausgewaehlteRundenAnzahl > 9) {
            g2.drawString("X", 162, 827);
        }else{
            g2.drawString("X", 172, 827);
        }
        g2.setColor(Color.yellow);
        String rundenAnzahl = Integer.toString(sp.aktuelleRundenAnzahl);
        if(sp.aktuelleRundenAnzahl > 9) {
            g2.drawString(rundenAnzahl, 210, 827);
        }else{
            g2.drawString(rundenAnzahl, 220, 827);
        }
    }

}
