package Networking.Client;

import Main.SpielPanel;
import Minispiele.SammlerElement;
import Networking.Pakete.*;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import spieler.Spieler;

import java.io.IOException;

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
                        if(zug.zustand == sp.minispielZustand){
                            sp.setzeZustand(sp.minispielZustand);
                        } else if(zug.zustand == sp.spielBrettZustand){
                            sp.setzeZustand(sp.spielBrettZustand);
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
                            sp.menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.add(spielfigurAuswahl.spielfigurMenueIndex);
                        }
                    } else if(object instanceof SternPosition sternPosition){
                        sp.spielablaufManager.mapManager.stern.setFeldNum(sternPosition.sternFeldnummer);
                        System.out.println("Client: ich habe die feldnummer fuer stern erhalten.");


                    } else if(object instanceof SpielerPosition spielerPosition){
                        if(sp.zustand == sp.minispielZustand) {
                            sp.minispielManager.alleMinispielSpieler.get(spielerPosition.clientIndex).minispielXPosition = spielerPosition.weltX;
                            sp.minispielManager.alleMinispielSpieler.get(spielerPosition.clientIndex).minispielYPosition = spielerPosition.weltY;
                            sp.minispielManager.alleMinispielSpieler.get(spielerPosition.clientIndex).minispielSpielerRechteck.y = spielerPosition.weltY+50;
                            sp.minispielManager.alleMinispielSpieler.get(spielerPosition.clientIndex).minispielSpielerRechteck.x = spielerPosition.weltX+30;


                        } else{
                            sp.alleSpieler.get(spielerPosition.clientIndex).weltX = spielerPosition.weltX;
                            sp.alleSpieler.get(spielerPosition.clientIndex).weltY = spielerPosition.weltY;
                            sp.alleSpieler.get(spielerPosition.clientIndex).bildschirmX = spielerPosition.weltX - sp.spielablaufManager.mainSpieler.weltX + sp.spielablaufManager.mainSpieler.bildschirmX;
                            sp.alleSpieler.get(spielerPosition.clientIndex).bildschirmY = spielerPosition.weltY - sp.spielablaufManager.mainSpieler.weltY + sp.spielablaufManager.mainSpieler.bildschirmY;

                        }
                    } else if(object instanceof Muenzenzahl muenzenzahl){
                        if(clientIndex == muenzenzahl.blauesFeldClientIndex) {
                            sp.spielablaufManager.mainSpieler.konto.muenzenVerlieren(5);
                        }else {
                            sp.alleSpieler.get(muenzenzahl.clientIndex).konto.muenzen += muenzenzahl.anzahlDerMuenzen;
                            System.out.println("spieler " + muenzenzahl.clientIndex + " hat " + muenzenzahl.anzahlDerMuenzen + " muenze erhalten.");
                        }
                    } else if(object instanceof Sternzahl sternzahl) {
                        if(clientIndex == sternzahl.blauesFeldClientIndex) {
                            sp.spielablaufManager.mainSpieler.konto.sterneVerlieren(1);
                        }else {
                            sp.alleSpieler.get(sternzahl.clientIndex).konto.sterne += sternzahl.anzahlDerSterne;
                            System.out.println("spieler " + sternzahl.clientIndex + " hat " + sternzahl.anzahlDerSterne + " sterne erhalten.");
                        }
                    }else if(object instanceof Schritte schritte){
                        sp.alleSpieler.get(schritte.clientIndex).schritteAnzahl = schritte.schritteAnzahl;
                    } else if(object instanceof Bewegung bewegung){
                        if(sp.zustand == sp.minispielZustand){
                            //System.out.println("ich habe die bewegung von " + bewegung.clientIndex);
                            sp.minispielManager.alleMinispielSpieler.get(bewegung.clientIndex).obenGedrueckt = bewegung.obenGedrueckt;
                            sp.minispielManager.alleMinispielSpieler.get(bewegung.clientIndex).untenGedrueckt = bewegung.untenGedrueckt;
                            sp.minispielManager.alleMinispielSpieler.get(bewegung.clientIndex).linksGedrueckt = bewegung.linksGedrueckt;
                            sp.minispielManager.alleMinispielSpieler.get(bewegung.clientIndex).rechtsGedrueckt = bewegung.rechtsGedrueckt;
                        } else {

                            if (bewegung.richtung.equals("oben")) {
                                sp.alleSpieler.get(bewegung.clientIndex).spielfigur.richtung = "oben";
                            } else if (bewegung.richtung.equals("unten")) {
                                sp.alleSpieler.get(bewegung.clientIndex).spielfigur.richtung = "unten";
                            } else if (bewegung.richtung.equals("rechts")) {
                                sp.alleSpieler.get(bewegung.clientIndex).spielfigur.richtung = "rechts";
                            } else if (bewegung.richtung.equals("links")) {
                                sp.alleSpieler.get(bewegung.clientIndex).spielfigur.richtung = "links";
                            } else if (bewegung.richtung.equals("stehen")) {
                                sp.alleSpieler.get(bewegung.clientIndex).spielfigur.richtung = "stehen";
                            }
                            if (bewegung.zustern) {
                                sp.alleSpieler.get(bewegung.clientIndex).zuStern = true;
                            } else if (!bewegung.zustern) {
                                sp.alleSpieler.get(bewegung.clientIndex).zuStern = false;
                            }
                            if (bewegung.feldNum != 0) {
                                for (int zeile = 0; zeile < 25; zeile++) {
                                    for (int spalte = 0; spalte < 30; spalte++) {
                                        if (sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte] != null) {
                                            if (sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld != null) {
                                                if (sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld.feldNum == bewegung.feldNum) {
                                                    if (bewegung.bubeClientIndex != -1) {
                                                        if (clientIndex == bewegung.bubeClientIndex) {
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
                                                        } else {
                                                            sp.alleSpieler.get(bewegung.clientIndex).aktuellFeld = sp.alleSpieler.get(bewegung.bubeClientIndex).aktuellFeld;
                                                            sp.alleSpieler.get(bewegung.clientIndex).naechstesFeld = sp.alleSpieler.get(bewegung.bubeClientIndex).aktuellFeld;
                                                            sp.alleSpieler.get(bewegung.clientIndex).aktuellesFeld = null;
                                                            sp.alleSpieler.get(bewegung.clientIndex).tempFeld = null;
                                                            sp.alleSpieler.get(bewegung.bubeClientIndex).aktuellFeld = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld;
                                                            sp.alleSpieler.get(bewegung.bubeClientIndex).naechstesFeld = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld;
                                                            sp.alleSpieler.get(bewegung.bubeClientIndex).aktuellesFeld = null;
                                                            sp.alleSpieler.get(bewegung.bubeClientIndex).tempFeld = null;
                                                            int weltXtemp = sp.alleSpieler.get(bewegung.bubeClientIndex).weltX;
                                                            int weltYtemp = sp.alleSpieler.get(bewegung.bubeClientIndex).weltY;
                                                            sp.alleSpieler.get(bewegung.clientIndex).weltX = weltXtemp;
                                                            sp.alleSpieler.get(bewegung.clientIndex).weltY = weltYtemp;
                                                            sp.alleSpieler.get(bewegung.bubeClientIndex).weltX = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld.weltX;
                                                            sp.alleSpieler.get(bewegung.bubeClientIndex).weltY = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld.weltY - sp.vergroesserteFliesenGroesse / 2;
                                                        }
                                                        for (Spieler spieler : sp.alleSpieler) {
                                                            spieler.bildschirmX = spieler.weltX - spieler.spielablaufManager.mainSpieler.weltX + spieler.spielablaufManager.mainSpieler.bildschirmX;
                                                            spieler.bildschirmY = spieler.weltY - spieler.spielablaufManager.mainSpieler.weltY + spieler.spielablaufManager.mainSpieler.bildschirmY;
                                                        }
                                                    } else {
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
                        }
                    } else if(object instanceof GegenstandInfo gegenstandInfo){
                        if(gegenstandInfo.gegenstandNum != -1){
                            sp.alleSpieler.get(gegenstandInfo.clientIndex).inventar.gegenstandBekommen(gegenstandInfo.gegenstandNum);
                        } else {
                            sp.alleSpieler.get(gegenstandInfo.clientIndex).inventar.gegenstandVerwenden(gegenstandInfo.befehlNum);

                        }
                    } else if(object instanceof SammlerGegenstaende sammlerGegenstaende){
                        if(sammlerGegenstaende.elementIndex == 1){
                            SammlerElement muenze1 = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 1);
                            sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex,  muenze1);
                            System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                        } else if(sammlerGegenstaende.elementIndex == 2){
                            SammlerElement muenze2 = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 1);
                            sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex,  muenze2);
                            System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                        } else if(sammlerGegenstaende.elementIndex == 3){
                            SammlerElement spider1 = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 2);
                            sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex,  spider1);
                            System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                        } else if(sammlerGegenstaende.elementIndex == 4){
                            SammlerElement spider2 = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 2);
                            sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex,  spider2);
                            System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                        } else if(sammlerGegenstaende.elementIndex == 5){
                            SammlerElement spider3 = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 2);
                            sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex,  spider3);
                            System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                        } else if(sammlerGegenstaende.elementIndex == 6){
                            SammlerElement diamond = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 3);
                            sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex,  diamond);
                            System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                        } else if(sammlerGegenstaende.elementIndex == 7){
                            SammlerElement mushroom = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 4);
                            sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex,  mushroom);
                            System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                        }
                    } else if(object instanceof SammlerPunkte sammlerPunkte){
                        if(sammlerPunkte.diamond){
                            sp.minispielManager.alleMinispielSpieler.get(sammlerPunkte.clientIndex).punktzahl += 3;
                        } else {
                            sp.minispielManager.alleMinispielSpieler.get(sammlerPunkte.clientIndex).punktzahl++;
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
        } else if(object instanceof SammlerGegenstaende sammlerGegenstaende){
            client.sendTCP(sammlerGegenstaende);
        } else if(object instanceof SammlerPunkte sammlerPunkte){
            client.sendTCP(sammlerPunkte);
        }


    }


}
