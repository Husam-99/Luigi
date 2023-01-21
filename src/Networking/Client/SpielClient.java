package Networking.Client;

import Main.SpielPanel;
import Networking.Pakete.*;
import Spielablauf.Feld;
import Spielablauf.Fliese;
import Spielablauf.Stern;
import Spielablauf.ViolettesFeld;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import spieler.Spieler;

import java.io.IOException;
import java.util.Random;

public class SpielClient {
    private boolean istDran = false;
    private boolean istHost = false;
    static int tcp_Port = 54777;
    static int udp_Port = 54555;
    private static String ipAdresse = "127.0.0.1";
    private final Client client;
    private SpielPanel sp;
    public int anzahlSpieler;
    public int clientIndex;
    public boolean wartung = false;
    public SpielClient(SpielPanel sp){
        this.sp = sp;
        client = new Client();

    }

    public void start(){
        try {
            Register.register(client.getKryo());
            client.start();
            client.connect(5000, ipAdresse, tcp_Port, udp_Port);
            client.addListener(new Listener(){
                @Override
                public void received(Connection connection, Object object) {

                    if (object instanceof ClientsZug zug) {
                        if(zug.dispose){
                            sp.window.dispose();
                            System.exit(0);
                        } else if(zug.wartung){
                            wartung = true;
                            istDran = zug.istDran;
                        }else{
                            wartung = false;
                            istDran = zug.istDran;

                        }
                        System.out.println("Clinet: dran " + istDran);
                        System.out.println("Client: wartung " + wartung);
                    } else if (object instanceof HostClient hostAuskuefte) {
                        istHost = hostAuskuefte.istHost;
                        clientIndex = hostAuskuefte.clientIndex;
                        System.out.println("Client: ich bin der Host " + istHost);
                        System.out.println("Client: mein Index " + clientIndex);
                    } else if (object instanceof AnzahlClients anzahl) {
                        anzahlSpieler = anzahl.anzahlVerbundeneClients;

                    } else if (object instanceof Rundenzahl rundenzahl) {
                        sp.menueManager.rundenAnzahl = rundenzahl.anzahlDerRunden;

                    } else if (object instanceof SpielfigurAuswahl spielfigurAuswahl) {
                        Spieler andererSpieler = new Spieler(sp.spielablaufManager);
                        if(spielfigurAuswahl.spielfigurMenueIndex == -1) {
                            if (spielfigurAuswahl.spielfigurIndex == 0) {
                                andererSpieler.spielfigurAuswaehlen(0);
                                System.out.println("at recieve spielfigur the client index " + spielfigurAuswahl.clientIndex);
                                sp.hinzufuegeSpieler(andererSpieler, spielfigurAuswahl.clientIndex);
                            } else if (spielfigurAuswahl.spielfigurIndex == 1) {
                                andererSpieler.spielfigurAuswaehlen(1);
                                System.out.println("at recieve spielfigur the client index " + spielfigurAuswahl.clientIndex);
                                sp.hinzufuegeSpieler(andererSpieler, spielfigurAuswahl.clientIndex);
                            } else if (spielfigurAuswahl.spielfigurIndex == 2) {
                                andererSpieler.spielfigurAuswaehlen(2);
                                System.out.println("at recieve spielfigur the client index " + spielfigurAuswahl.clientIndex);
                                sp.hinzufuegeSpieler(andererSpieler, spielfigurAuswahl.clientIndex);
                            } else if (spielfigurAuswahl.spielfigurIndex == 3) {
                                andererSpieler.spielfigurAuswaehlen(3);
                                System.out.println("at recieve spielfigur the client index " + spielfigurAuswahl.clientIndex);
                                sp.hinzufuegeSpieler(andererSpieler, spielfigurAuswahl.clientIndex);
                            }
                        } else{
                            sp.menueManager.menueEingabeManager.spielfigurMenueIndex = spielfigurAuswahl.spielfigurMenueIndex;

                        }
                    } else if(object instanceof SternPosition sternPosition){
                        sp.spielablaufManager.mapManager.stern.setFeldNum(sternPosition.sternFeldnummer);
                        System.out.println("Client: ich habe die feldnummer fuer stern erhalten.");


                    } else if(object instanceof SpielerPosition spielerPosition){
                        sp.alleSpieler.get(spielerPosition.clientIndex).weltX = spielerPosition.weltX;
                        sp.alleSpieler.get(spielerPosition.clientIndex).weltY = spielerPosition.weltY;
                        sp.alleSpieler.get(spielerPosition.clientIndex).bildschirmX = spielerPosition.weltX - sp.spielablaufManager.mainSpieler.weltX + sp.spielablaufManager.mainSpieler.bildschirmX;
                        sp.alleSpieler.get(spielerPosition.clientIndex).bildschirmY = spielerPosition.weltY - sp.spielablaufManager.mainSpieler.weltY + sp.spielablaufManager.mainSpieler.bildschirmY;

                    } else if(object instanceof Muenzenzahl muenzenzahl){
                        if(muenzenzahl.anzahlDerMuenzen > 0){
                            sp.alleSpieler.get(muenzenzahl.clientIndex).konto.muenzen += muenzenzahl.anzahlDerMuenzen;
                            System.out.println("spieler " + muenzenzahl.clientIndex + " hat " + muenzenzahl.anzahlDerMuenzen + " muenze erhalten.");
                        }else if(muenzenzahl.anzahlDerMuenzen < 0){
                            sp.alleSpieler.get(muenzenzahl.clientIndex).konto.muenzen += muenzenzahl.anzahlDerMuenzen;
                            System.out.println("spieler " + muenzenzahl.clientIndex + " hat " + muenzenzahl.anzahlDerMuenzen + " muenze verloren.");
                        }
                    } else if(object instanceof Sternzahl sternzahl){
                        if(sternzahl.anzahlDerSterne > 0){
                            sp.alleSpieler.get(sternzahl.clientIndex).konto.sterne += sternzahl.anzahlDerSterne;
                            System.out.println("spieler " + sternzahl.clientIndex + " hat " + sternzahl.anzahlDerSterne + " sterne erhalten.");

                        }else if(sternzahl.anzahlDerSterne < 0){
                            sp.alleSpieler.get(sternzahl.clientIndex).konto.sterne += sternzahl.anzahlDerSterne;
                            System.out.println("spieler " + sternzahl.clientIndex + " hat " + sternzahl.anzahlDerSterne + "sterne verloren.");
                        }
                    } else if(object instanceof Schritte schritte){
                        sp.alleSpieler.get(schritte.clientIndex).schritteAnzahl = schritte.schritteAnzahl;
                    } else if(object instanceof Bewegung bewegung){
                        if(bewegung.richtung.equals("oben")){
                            sp.alleSpieler.get(bewegung.clientIndex).spielfigur.richtung = "oben";
                        }else if(bewegung.richtung.equals("unten")){
                            sp.alleSpieler.get(bewegung.clientIndex).spielfigur.richtung = "unten";
                        }else if(bewegung.richtung.equals("rechts")){
                            sp.alleSpieler.get(bewegung.clientIndex).spielfigur.richtung = "rechts";
                        }else if(bewegung.richtung.equals("links")){
                            sp.alleSpieler.get(bewegung.clientIndex).spielfigur.richtung = "links";
                        }else if(bewegung.richtung.equals("stehen")){
                            sp.alleSpieler.get(bewegung.clientIndex).spielfigur.richtung = "stehen";
                        }
                        if(bewegung.zustern){
                            sp.alleSpieler.get(bewegung.clientIndex).zuStern = true;
                        }else if(!bewegung.zustern){
                            sp.alleSpieler.get(bewegung.clientIndex).zuStern = false;
                        }
                        if(bewegung.feldNum!=0){
                            for(int zeile = 0; zeile < 25; zeile++) {
                                for (int spalte = 0; spalte < 30; spalte++) {
                                    if (sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte] != null) {
                                        if (sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld != null) {
                                            if (sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld.feldNum == bewegung.feldNum) {
                                                if(bewegung.bubeClientIndex != -1) {
                                                    sp.alleSpieler.get(bewegung.clientIndex).aktuellFeld = sp.spielablaufManager.mainSpieler.aktuellFeld;
                                                    sp.alleSpieler.get(bewegung.clientIndex).naechstesFeld = sp.spielablaufManager.mainSpieler.aktuellFeld;
                                                    sp.alleSpieler.get(bewegung.clientIndex).aktuellesFeld = null;
                                                    sp.alleSpieler.get(bewegung.clientIndex).tempFeld = null;
                                                    sp.spielablaufManager.mainSpieler.aktuellFeld = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld;
                                                    sp.spielablaufManager.mainSpieler.naechstesFeld = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld;
                                                    sp.spielablaufManager.mainSpieler.aktuellesFeld = null;
                                                    sp.spielablaufManager.mainSpieler.tempFeld = null;
                                                    int weltXtemp = sp.spielablaufManager.mainSpieler.weltX;
                                                    int weltYtemp = sp.spielablaufManager.mainSpieler.weltY;
                                                    sp.alleSpieler.get(bewegung.clientIndex).weltX = weltXtemp;
                                                    sp.alleSpieler.get(bewegung.clientIndex).weltY = weltYtemp;
                                                    sp.spielablaufManager.mainSpieler.weltX = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld.weltX;
                                                    sp.spielablaufManager.mainSpieler.weltY = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld.weltY - sp.vergroesserteFliesenGroesse / 2;
                                                    sp.alleSpieler.get(bewegung.clientIndex).bildschirmX = weltXtemp - sp.spielablaufManager.mainSpieler.weltX + sp.spielablaufManager.mainSpieler.bildschirmX;
                                                    sp.alleSpieler.get(bewegung.clientIndex).bildschirmY = weltYtemp - sp.spielablaufManager.mainSpieler.weltY + sp.spielablaufManager.mainSpieler.bildschirmY;
                                                }else{
                                                    sp.alleSpieler.get(bewegung.clientIndex).aktuellFeld = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld;
                                                    sp.alleSpieler.get(bewegung.clientIndex).naechstesFeld = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld;
                                                    sp.alleSpieler.get(bewegung.clientIndex).aktuellesFeld = null;
                                                    sp.alleSpieler.get(bewegung.clientIndex).tempFeld = null;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else if(object instanceof GegenstandInfo gegenstandInfo){
                        if(gegenstandInfo.gegenstandNum != -1){
                            sp.alleSpieler.get(gegenstandInfo.clientIndex).inventar.gegenstandBekommen(gegenstandInfo.gegenstandNum);
                        } else {
                            sp.alleSpieler.get(gegenstandInfo.clientIndex).inventar.gegenstandVerwenden(gegenstandInfo.befehlNum);

                        }
                    }


                }
            });
            System.out.println("ich bin der Client und bin am Start");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isIstHost() {
        return istHost;
    }

    public boolean istDran() {
        return istDran;
    }

    public void close(){
        client.close();
    }

    public void send(Object object){
        if(object instanceof Bescheid bescheid){
            client.sendTCP(bescheid);
        } else if(object instanceof AnzahlMitspieler anzahlMitspieler){
            client.sendTCP(anzahlMitspieler);
        } else if (object instanceof  Rundenzahl rundenzahl) {
            client.sendTCP(rundenzahl);
        } else if(object instanceof SpielfigurAuswahl spielfigurAuswahl){
            client.sendTCP(spielfigurAuswahl);
        } else if(object instanceof SpielerPosition spielerPosition){
            client.sendTCP(spielerPosition);
        } else if(object instanceof Muenzenzahl muenzenzahl){
            muenzenzahl.clientIndex = this.clientIndex;
            client.sendTCP(muenzenzahl);
        } else if(object instanceof Sternzahl sternzahl){
            sternzahl.clientIndex = this.clientIndex;
            client.sendTCP(sternzahl);
        } else if(object instanceof SternKaufen sternKaufen){
            client.sendTCP(sternKaufen);
        } else if(object instanceof Schritte schritte){
            schritte.clientIndex = this.clientIndex;
            client.sendTCP(schritte);
        } else if(object instanceof Bewegung bewegung){
            bewegung.clientIndex = this.clientIndex;
            client.sendTCP(bewegung);
        } else if(object instanceof GegenstandInfo gegenstandInfo){
            gegenstandInfo.clientIndex = this.clientIndex;
            client.sendTCP(gegenstandInfo);
        } else if(object instanceof Blocken block){
            client.sendTCP(block);
        }

    }


}
