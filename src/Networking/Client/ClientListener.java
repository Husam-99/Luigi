package Networking.Client;

import Minispiele.SammlerElement;
import Networking.Pakete.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import spieler.Spieler;

import java.util.ArrayList;

import static Networking.Client.SpielClient.*;

public class ClientListener extends Listener {
    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof ClientsZug zug) {
            if(zug.dispose){
                sp.window.dispose();
                System.exit(0);
            }else{
                istDran = zug.istDran;
                if(istDran && sp.zustand == sp.spielBrettZustand){
                    sp.spielablaufManager.mainSpieler.wuerfelZustand = true;
                }
            }
            if(zug.ersteRunde){
                sp.aktuelleRundenAnzahl++;
            }
            if(zug.zustand == sp.minispielZustand){
                sp.setzeZustand(sp.minispielZustand, zug.minispielIndex);
            } else if(zug.zustand == sp.spielBrettZustand){
                sp.wurfelzustand = false;
                sp.setzeZustand(sp.spielBrettZustand, -1);
            } else if(zug.zustand == sp.zugFestlegenZustand){
                sp.wurfelzustand = true;
                sp.setzeZustand(sp.spielBrettZustand, -1);
            } else if(zug.zustand == sp.siegerKuerenZustand){
                sp.setzeZustand(sp.siegerKuerenZustand, -1);
            }
            System.out.println("Clinet: dran " + istDran);
        } else if (object instanceof HostClient hostAuskuefte) {
            if(hostAuskuefte.istHost){
                istHost = true;
            }
            clientIndex = hostAuskuefte.clientIndex;
            System.out.println("Client: ich bin der Host " + istHost);
            System.out.println("Client: mein Index " + clientIndex);
        } else if (object instanceof AnzahlClients anzahl) {
            anzahlSpieler = anzahl.anzahlVerbundeneClients;

        } else if(object instanceof SpielerConnection spielerConnection){
            sp.alleSpieler.set(spielerConnection.clientIndex, new Spieler());


        } else if (object instanceof Rundenzahl rundenzahl) {
            sp.ausgewaehlteRundenAnzahl = rundenzahl.anzahlDerRunden;

        } else if (object instanceof SpielfigurAuswahl spielfigurAuswahl) {
            if(spielfigurAuswahl.spielfigurMenueIndex == -1) {
                Spieler andererSpieler = new Spieler(sp.spielablaufManager);
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
                sp.menueManager.menueEingabeManager.ausgewaehlteSpielfiguren.set(spielfigurAuswahl.clientIndex,spielfigurAuswahl.spielfigurMenueIndex);
                sp.menueManager.spielfigurAuswaehlen.befehlNum1Bestimmen();
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
            } else if(sp.zustand == sp.spielBrettZustand){
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
                                                sp.alleSpieler.get(bewegung.clientIndex).aktuellesFeld = sp.spielablaufManager.mainSpieler.aktuellesFeld;
                                                sp.alleSpieler.get(bewegung.clientIndex).vorherigesFeld = null;
                                                sp.spielablaufManager.mainSpieler.aktuellesFeld = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld;
                                                sp.spielablaufManager.mainSpieler.vorherigesFeld = null;
                                                int weltXtemp = sp.spielablaufManager.mainSpieler.weltX;
                                                int weltYtemp = sp.spielablaufManager.mainSpieler.weltY;
                                                sp.alleSpieler.get(bewegung.clientIndex).weltX = weltXtemp;
                                                sp.alleSpieler.get(bewegung.clientIndex).weltY = weltYtemp;
                                                sp.spielablaufManager.mainSpieler.weltX = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld.weltX;
                                                sp.spielablaufManager.mainSpieler.weltY = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld.weltY - sp.vergroesserteFliesenGroesse / 2;
                                            } else {
                                                sp.alleSpieler.get(bewegung.clientIndex).aktuellesFeld = sp.alleSpieler.get(bewegung.bubeClientIndex).aktuellesFeld;
                                                sp.alleSpieler.get(bewegung.clientIndex).vorherigesFeld = null;
                                                sp.alleSpieler.get(bewegung.bubeClientIndex).aktuellesFeld = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld;
                                                sp.alleSpieler.get(bewegung.bubeClientIndex).vorherigesFeld = null;
                                                int weltXtemp = sp.alleSpieler.get(bewegung.bubeClientIndex).weltX;
                                                int weltYtemp = sp.alleSpieler.get(bewegung.bubeClientIndex).weltY;
                                                sp.alleSpieler.get(bewegung.clientIndex).weltX = weltXtemp;
                                                sp.alleSpieler.get(bewegung.clientIndex).weltY = weltYtemp;
                                                sp.alleSpieler.get(bewegung.bubeClientIndex).weltX = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld.weltX;
                                                sp.alleSpieler.get(bewegung.bubeClientIndex).weltY = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld.weltY - sp.vergroesserteFliesenGroesse / 2;
                                            }
                                            for (Spieler spieler : sp.alleSpieler) {
                                                if(spieler.spielfigur != null){
                                                    spieler.bildschirmX = spieler.weltX - spieler.spielablaufManager.mainSpieler.weltX + spieler.spielablaufManager.mainSpieler.bildschirmX;
                                                    spieler.bildschirmY = spieler.weltY - spieler.spielablaufManager.mainSpieler.weltY + spieler.spielablaufManager.mainSpieler.bildschirmY;

                                                }
                                            }
                                        } else {
                                            sp.alleSpieler.get(bewegung.clientIndex).aktuellesFeld = sp.spielablaufManager.mapManager.mapFliesen[zeile][spalte].feld;
                                            sp.alleSpieler.get(bewegung.clientIndex).vorherigesFeld = null;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if(object instanceof GegenstandInfo gegenstandInfo){
            if(clientIndex == gegenstandInfo.gegenstandClientIndex){
                sp.spielablaufManager.mainSpieler.inventar.gegenstandVerwenden(gegenstandInfo.gestohleneGegenstandNum);
            }else if(gegenstandInfo.gegenstandNum != -1 || gegenstandInfo.befehlNum != -1){
                if(gegenstandInfo.gegenstandNum != -1){
                    sp.alleSpieler.get(gegenstandInfo.clientIndex).inventar.gegenstandBekommen(gegenstandInfo.gegenstandNum);
                } else {
                    sp.alleSpieler.get(gegenstandInfo.clientIndex).inventar.gegenstandVerwenden(gegenstandInfo.befehlNum);
                }
            }else{
                sp.spielablaufManager.sp.alleSpieler.get(gegenstandInfo.gegenstandClientIndex).inventar.gegenstandVerwenden(gegenstandInfo.gestohleneGegenstandNum);
            }

        } else if(object instanceof SammlerGegenstaende sammlerGegenstaende){
            if(sp.minispielManager.minispielWahl == 0) {
                if (sammlerGegenstaende.elementIndex == 1) {
                    SammlerElement muenze1 = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 1);
                    sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex, muenze1);
                    System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                } else if (sammlerGegenstaende.elementIndex == 2) {
                    SammlerElement muenze2 = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 1);
                    sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex, muenze2);
                    System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                } else if (sammlerGegenstaende.elementIndex == 3) {
                    SammlerElement spider1 = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 2);
                    sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex, spider1);
                    System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                } else if (sammlerGegenstaende.elementIndex == 4) {
                    SammlerElement spider2 = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 2);
                    sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex, spider2);
                    System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                } else if (sammlerGegenstaende.elementIndex == 5) {
                    SammlerElement spider3 = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 2);
                    sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex, spider3);
                    System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                } else if (sammlerGegenstaende.elementIndex == 6) {
                    SammlerElement diamond = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 3);
                    sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex, diamond);
                    System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                } else if (sammlerGegenstaende.elementIndex == 7) {
                    SammlerElement mushroom = new SammlerElement(sp, sammlerGegenstaende.elementX, sammlerGegenstaende.elementY, 4);
                    sp.minispielManager.sammler.setzeElement(sammlerGegenstaende.elementIndex, mushroom);
                    System.out.println("i've got muenze " + sammlerGegenstaende.elementIndex + " " + sammlerGegenstaende.elementX + sammlerGegenstaende.elementY);

                }
            }
        } else if(object instanceof SammlerPunkte sammlerPunkte){
            if(sammlerPunkte.diamond){
                sp.minispielManager.alleMinispielSpieler.get(sammlerPunkte.clientIndex).punktzahl += 3;
            } else {
                sp.minispielManager.alleMinispielSpieler.get(sammlerPunkte.clientIndex).punktzahl++;
            }
        } else if(object instanceof SquidGamePunkte squidGamePunkte){
            if(squidGamePunkte.endeErreicht){
                sp.minispielManager.alleMinispielSpieler.get(squidGamePunkte.clientIndex).endeErreicht = true;
            }
            if(squidGamePunkte.sekundenAnzahl!=-1){
                sp.minispielManager.alleMinispielSpieler.get(squidGamePunkte.clientIndex).endeErreichtSekunde=squidGamePunkte.sekundenAnzahl;

            }
            sp.minispielManager.alleMinispielSpieler.get(squidGamePunkte.clientIndex).punktzahl = squidGamePunkte.punktZahl;

        } else if(object instanceof SquidGamePosition squidGamePosition){
            sp.minispielManager.alleMinispielSpieler.get(squidGamePosition.clientIndex).minispielXPosition = squidGamePosition.minispielXPosition;
            sp.minispielManager.alleMinispielSpieler.get(squidGamePosition.clientIndex).minispielYPosition = squidGamePosition.minispielYPosition;
            sp.minispielManager.alleMinispielSpieler.get(squidGamePosition.clientIndex).bildschirmX = sp.minispielManager.alleMinispielSpieler.get(squidGamePosition.clientIndex).minispielXPosition - sp.minispielManager.mainMinispielSpieler.minispielXPosition + sp.minispielManager.mainMinispielSpieler.bildschirmX;
            sp.minispielManager.alleMinispielSpieler.get(squidGamePosition.clientIndex).bildschirmY = sp.minispielManager.alleMinispielSpieler.get(squidGamePosition.clientIndex).minispielYPosition - sp.minispielManager.mainMinispielSpieler.minispielYPosition + sp.minispielManager.mainMinispielSpieler.bildschirmY;
            if(squidGamePosition.aktuellePalettenNr != -1){
                sp.minispielManager.alleMinispielSpieler.get(squidGamePosition.clientIndex).aktuellePalette = sp.minispielManager.squidGame.paletten[squidGamePosition.aktuellePalettenNr];

            }

        } else if(object instanceof PalettenFalle palettenFalle){
            ArrayList<Boolean> allePalletenFalle = new ArrayList<>();
            allePalletenFalle.add(palettenFalle.pallete0);
            allePalletenFalle.add(palettenFalle.pallete2);
            allePalletenFalle.add(palettenFalle.pallete4);
            allePalletenFalle.add(palettenFalle.pallete6);
            allePalletenFalle.add(palettenFalle.pallete8);
            allePalletenFalle.add(palettenFalle.pallete10);
            allePalletenFalle.add(palettenFalle.pallete12);
            sp.minispielManager.squidGame.falleFestlegen(allePalletenFalle);
        } else if(object instanceof Blocken){
            Bescheid bescheid = new Bescheid();
            bescheid.fertig = true;
           // client.send(bescheid);
        }
    }
}
