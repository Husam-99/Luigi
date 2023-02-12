package Spielablauf;


import Networking.Client.SpielClient;
import spieler.Spieler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MapEingabeManager implements KeyListener {

    SpielMapManager spielMapManager;
    Spieler spieler;

    //booleans für die Kontrolle auf dem Spielfeld
    public Boolean obenGedrueckt, untenGedrueckt, rechtsGedrueckt, linksGedrueckt;

    public Boolean spaceGedrueckt, iGedrueckt, bewegungOben , bewegungUnten, bewegungRechts, bewegungLinks, falscheRichtung;

    public MapEingabeManager(SpielMapManager spielMapManager) {
        this.spielMapManager = spielMapManager;

        obenGedrueckt = false;
        untenGedrueckt = false;
        rechtsGedrueckt = false;
        linksGedrueckt = false;

        spaceGedrueckt = false;
        iGedrueckt = false;
        bewegungOben = false;
        bewegungUnten = false;
        bewegungRechts = false;
        bewegungLinks = false;
        falscheRichtung = false;

    }

    @Override
    public void keyPressed(KeyEvent e) {
        spieler = this.spielMapManager.spielablaufManager.mainSpieler;
        int code = e.getKeyCode();

        //Minimap kann jederzeit gezeigt wird durch das Taste M und durch ESC verstecken
        if(code == KeyEvent.VK_M){
            spielMapManager.spielablaufManager.miniMapZustand = true;
        }else if(code == KeyEvent.VK_ESCAPE){
            spielMapManager.spielablaufManager.miniMapZustand = false;
        }

        //Speziell fall für beginn des Spiels (nur Würfel kann gespielt wird)
        if(spielMapManager.spielablaufManager.sp.client.istDran() && spielMapManager.spielablaufManager.sp.aktuelleRundenAnzahl == 0){
            if (code == KeyEvent.VK_SPACE) {
                spaceGedrueckt = true;
            }
        }

        //alle Taste, die gedruckt können, wenn der Spieler dran ist
        else if(spielMapManager.spielablaufManager.sp.client.istDran()) {

            //Inventar
            if(iGedrueckt){

                //benutzen der ausgewählte Gegenstand
                if(code == KeyEvent.VK_ENTER){
                    if(spieler.inventar.befehlNum == 0){
                        if(spieler.inventar.inventar[0] != null) {
                            spieler.inventar.gegenstandVerwenden(0);
                        }
                        if(spieler.inventar.inventar[1] != null) {
                            spieler.inventar.befehlNum = 2;
                        }else if(spieler.inventar.inventar[2] != null) {
                            spieler.inventar.befehlNum = 1;
                        }else if(spieler.inventar.inventar[3] != null) {
                            spieler.inventar.befehlNum = 3;
                        }else{
                            spieler.inventar.befehlNum = 0;
                        }
                    }else if(spieler.inventar.befehlNum == 1){
                        if(spieler.inventar.inventar[2] != null) {
                            spieler.inventar.gegenstandVerwenden(2);
                        }
                        if(spieler.inventar.inventar[0] != null) {
                            spieler.inventar.befehlNum = 0;
                        }else if(spieler.inventar.inventar[1] != null) {
                            spieler.inventar.befehlNum = 2;
                        }else if(spieler.inventar.inventar[3] != null) {
                            spieler.inventar.befehlNum = 3;
                        }else{
                            spieler.inventar.befehlNum = 0;
                        }
                    }else if(spieler.inventar.befehlNum == 2){
                        if(spieler.inventar.inventar[1] != null) {
                            spieler.inventar.gegenstandVerwenden(1);
                        }
                        if(spieler.inventar.inventar[0] != null) {
                            spieler.inventar.befehlNum = 0;
                        }else if(spieler.inventar.inventar[2] != null) {
                            spieler.inventar.befehlNum = 1;
                        }else if(spieler.inventar.inventar[3] != null) {
                            spieler.inventar.befehlNum = 3;
                        }else{
                            spieler.inventar.befehlNum = 0;
                        }
                    }else if(spieler.inventar.befehlNum == 3){
                        if(spieler.inventar.inventar[3] != null) {
                            spieler.inventar.gegenstandVerwenden(3);
                        }
                        if(spieler.inventar.inventar[0] != null) {
                            spieler.inventar.befehlNum = 0;
                        }else if(spieler.inventar.inventar[2] != null) {
                            spieler.inventar.befehlNum = 1;
                        }else if(spieler.inventar.inventar[1] != null) {
                            spieler.inventar.befehlNum = 2;
                        }else{
                            spieler.inventar.befehlNum = 0;
                        }
                    }
                }

                //Inventar schließen
                else if(code == KeyEvent.VK_ESCAPE){
                    spieler.inventarZustand = false;
                    spieler.inventar.befehlNum = 0;
                    iGedrueckt = false;
                }
            }

            //Shop
            else if(spieler.spielablaufManager.shopGeoeffnet){

                //kaufe der Gegenstand, wenn der Spieler genug Münzen hat
                if(code == KeyEvent.VK_ENTER){
                    if(spieler.konto.genugMuenzen && !spieler.inventar.inventarVoll) {
                        spieler.spielablaufManager.shop.gegenstandKaufen();
                    }else if(!spieler.konto.genugMuenzen){
                        spieler.konto.genugMuenzen = true;
                    }else if(spieler.spielablaufManager.mainSpieler.inventar.inventarVoll){
                        spieler.spielablaufManager.inventarVoll = !spieler.spielablaufManager.inventarVoll;
                    }
                }

                //Shop schließen
                else if(code == KeyEvent.VK_ESCAPE){
                    spieler.spielablaufManager.shopGeoeffnet = false;
                    spieler.spielablaufManager.shop.befehlNum = 0;
                    spieler.konto.genugMuenzen = true;
                    if(!spielMapManager.spielablaufManager.mainSpieler.aktuellesFeld.hatStern) {
                        spielMapManager.spielablaufManager.mainSpieler.amSpiel = false;
                    }else{
                        spielMapManager.stern.sternKaufen = true;
                    }
                }
            }

            //Stern
            else if(spielMapManager.stern.sternKaufen){

                //Stern kaufen, wenn der Spieler genug Münzen hat
                if (code == KeyEvent.VK_ENTER) {
                    if (spieler.konto.genugMuenzen) {
                        spielMapManager.stern.sternKaufen();
                    } else {
                        spieler.konto.genugMuenzen = true;
                    }
                }

                //Stern schließen
                else if(code == KeyEvent.VK_ESCAPE){
                    spielMapManager.stern.sternKaufen = false;
                    spieler.konto.genugMuenzen = true;
                    spielMapManager.spielablaufManager.mainSpieler.amSpiel = false;
                }
            }

            //für bube/blauesFeld Client
            else if(spieler.spielablaufManager.clientAuswaehlen){

                //Client auswählen
                if (code == KeyEvent.VK_ENTER) {
                    if(spieler.spielablaufManager.bubeZustand) {
                        spieler.spielablaufManager.clientAuswaehlen = false;
                        spieler.spielablaufManager.bube.befehlNum = 0;
                        spieler.spielablaufManager.bube.positionWechsel();
                        spieler.spielablaufManager.bubeZustand = false;
                    }else if(spieler.spielablaufManager.blauesFeldZustand){
                        spieler.spielablaufManager.clientAuswaehlen = false;
                        spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 0;
                        spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten = true;
                    }
                }
            }

            //klauen Möglichkeit
            else if(spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten){

                //stern/Münze klauen, wenn der ausgewählte Spieler genug Münzen/sterne oder nicht leere Inventar hat
                if(code == KeyEvent.VK_ENTER) {
                    if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 0) {
                        if(spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugMuenzen){
                            spieler.spielablaufManager.tempBlauesFeld.muenzeKlauen();
                        }else{
                            spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugMuenzen = true;
                        }
                    } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 1) {
                        if(spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugSterne){
                            spieler.spielablaufManager.tempBlauesFeld.sternKlauen();
                        }else{
                            spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugSterne = true;
                        }
                    } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 2) {
                        spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.inventarLeerOderVoll();
                        if(!spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.inventarLeer && !spieler.inventar.inventarVoll){
                            spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten = false;
                            spieler.spielablaufManager.tempBlauesFeld.inventarKlauen = true;
                        }else if(spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.inventarLeer){
                            spieler.spielablaufManager.tempBlauesFeld.inventarLeer = !spieler.spielablaufManager.tempBlauesFeld.inventarLeer;
                        }else if(spieler.spielablaufManager.mainSpieler.inventar.inventarVoll){
                            spieler.spielablaufManager.inventarVoll = !spieler.spielablaufManager.inventarVoll;
                        }
                    }
                }

                //klauen Möglichkeiten schließen
                else if(code == KeyEvent.VK_ESCAPE){
                    spieler.spielablaufManager.tempBlauesFeld.befehlNum2 = 0;
                    spieler.spielablaufManager.inventarVoll = false;
                    spieler.spielablaufManager.tempBlauesFeld.inventarLeer = false;
                    spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugSterne = true;
                    spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugMuenzen = true;
                    spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten = false;
                    spieler.spielablaufManager.blauesFeldZustand = false;
                    if(!spielMapManager.spielablaufManager.mainSpieler.aktuellesFeld.hatStern) {
                        spielMapManager.spielablaufManager.mainSpieler.amSpiel = false;
                    }else{
                        spielMapManager.stern.sternKaufen = true;
                    }
                }
            }

            //Gegenstand kaluen
            else if(spieler.spielablaufManager.tempBlauesFeld.inventarKlauen){
                if(code == KeyEvent.VK_ENTER) {
                    spieler.spielablaufManager.tempBlauesFeld.gegenstandKlauen();
                }
            }

            //anfang des Rounds bevor das Würfeln
            else if (!spieler.bewegung && spieler.wuerfelZustand) {

                //würfeln
                if (code == KeyEvent.VK_SPACE) {
                    spaceGedrueckt = true;
                }

                //inventar öffnen
                else if(code == KeyEvent.VK_I) {
                    spieler.inventarZustand = true;
                    iGedrueckt = true;
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        spieler = this.spielMapManager.spielablaufManager.mainSpieler;

        //alle Taste, die gedruckt können, wenn der Spieler dran ist
        if (spielMapManager.spielablaufManager.sp.client.istDran()) {

            //alle Taste, die gedruckt können, wenn der Spieler nicht am Bewegung zwischen zwei felder ist
            if (!spieler.bewegung && !spielMapManager.stern.sternKaufen) {
                switch (e.getKeyChar()) {
                    case 'w' -> {
                        obenGedrueckt = true;

                        //Bewegung oben in Inventar
                        if (iGedrueckt) {
                            untenUndObenInventar();
                        }

                        //Bewegung oben in Shop
                        else if (spieler.spielablaufManager.shopGeoeffnet && spieler.konto.genugMuenzen) {
                            untenUndObenShop();
                        }

                        //Bewegung Nord nach das nächste Feld
                        else if (!spieler.spielablaufManager.clientAuswaehlen && spieler.konto.genugMuenzen && !spieler.wuerfelZustand
                                && !spieler.spielablaufManager.tempBlauesFeld.inventarKlauen && !spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten) {
                            checkNaechstesFeld();
                            if (!falscheRichtung) {
                                if (spieler.aktuellesFeld != null && spieler.aktuellesFeld.nordFeld != null) {
                                    spieler.schritteAnzahl--;
                                    spieler.bewegung = true;
                                    bewegungOben = true;
                                    if (spielMapManager.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spielMapManager.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                        spieler.spielfigur.richtung = "stehen";
                                        spieler.vorherigesFeld = spieler.aktuellesFeld;
                                        spieler.aktuellesFeld = null;
                                    }
                                }
                            }
                        }
                    }
                    case 's' -> {
                        untenGedrueckt = true;

                        //Bewegung unten in Inventar
                        if (iGedrueckt) {
                            untenUndObenInventar();
                        }

                        //Bewegung unten in Shop
                        else if (spieler.spielablaufManager.shopGeoeffnet && spieler.konto.genugMuenzen) {
                            untenUndObenShop();
                        }

                        //Bewegung Süd nach das nächste Feld
                        else if (!spieler.spielablaufManager.clientAuswaehlen && spieler.konto.genugMuenzen && !spieler.wuerfelZustand
                                && !spieler.spielablaufManager.tempBlauesFeld.inventarKlauen && !spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten) {
                            checkNaechstesFeld();
                            if (!falscheRichtung) {
                                if (spieler.aktuellesFeld != null && spieler.aktuellesFeld.suedFeld != null) {
                                    spieler.schritteAnzahl--;
                                    spieler.bewegung = true;
                                    bewegungUnten = true;
                                    if (spielMapManager.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spielMapManager.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                        spieler.spielfigur.richtung = "stehen";
                                        spieler.vorherigesFeld = spieler.aktuellesFeld;
                                        spieler.aktuellesFeld = null;
                                    }
                                }
                            }
                        }
                    }
                    case 'd' -> {
                        rechtsGedrueckt = true;

                        //Bewegung rechts in Inventar
                        if (iGedrueckt) {
                            rechtsUndLinksInventar();
                        }

                        //Bewegung rechts in Shop
                        else if (spieler.spielablaufManager.shopGeoeffnet && spieler.konto.genugMuenzen) {
                            rechtsShop();
                        }

                        //Bewegung rechts in Clients Auswahl
                        else if (spieler.spielablaufManager.clientAuswaehlen) {
                            if (SpielClient.anzahlSpieler == 3) {
                                if (spieler.spielablaufManager.bube.befehlNum == 0 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 0) {
                                    spieler.spielablaufManager.bube.befehlNum = 1;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 1;
                                } else if (spieler.spielablaufManager.bube.befehlNum == 1 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 1) {
                                    spieler.spielablaufManager.bube.befehlNum = 0;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 0;
                                }
                            } else if (SpielClient.anzahlSpieler == 4) {
                                if (spieler.spielablaufManager.bube.befehlNum == 0 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 0) {
                                    spieler.spielablaufManager.bube.befehlNum = 1;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 1;
                                } else if (spieler.spielablaufManager.bube.befehlNum == 1 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 1) {
                                    spieler.spielablaufManager.bube.befehlNum = 2;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 2;
                                } else if (spieler.spielablaufManager.bube.befehlNum == 2 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 2) {
                                    spieler.spielablaufManager.bube.befehlNum = 0;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 0;
                                }
                            }
                        }

                        //Bewegung rechts in Klauen Möglichkeit
                        else if (spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten && spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugMuenzen
                                && spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugSterne && !spieler.spielablaufManager.tempBlauesFeld.inventarLeer) {
                            if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 0) {
                                spieler.spielablaufManager.tempBlauesFeld.befehlNum2 = 1;
                            } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 1) {
                                spieler.spielablaufManager.tempBlauesFeld.befehlNum2 = 2;
                            } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 2) {
                                spieler.spielablaufManager.tempBlauesFeld.befehlNum2 = 0;
                            }
                        }

                        //Bewegung rechts in Gegenstand Klauen
                        else if (spieler.spielablaufManager.tempBlauesFeld.inventarKlauen) {
                            if (spielMapManager.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 2) {
                                if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 0) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 1;
                                } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 1) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 0;
                                }
                            } else if (spielMapManager.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 3) {
                                if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 0) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 1;
                                } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 1) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 2;
                                } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 2) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 0;
                                }
                            } else if (spielMapManager.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 4) {
                                if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 0) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 1;
                                } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 1) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 2;
                                } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 2) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 3;
                                } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 3) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 0;
                                }
                            }
                        }

                        //Bewegung Ost nach das nächste Feld
                        else if (spieler.konto.genugMuenzen && !spieler.wuerfelZustand && !spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten) {
                            checkNaechstesFeld();
                            if (!falscheRichtung) {
                                if (spieler.aktuellesFeld != null && spieler.aktuellesFeld.ostFeld != null) {
                                    spieler.schritteAnzahl--;
                                    spieler.bewegung = true;
                                    bewegungRechts = true;
                                    if (spielMapManager.spielablaufManager.mainSpieler.weltX == (spieler.aktuellesFeld.weltX)) {
                                        spieler.spielfigur.richtung = "stehen";
                                        spieler.vorherigesFeld = spieler.aktuellesFeld;
                                        spieler.aktuellesFeld = null;
                                    }
                                }
                            }
                        }
                    }
                    case 'a' -> {
                        linksGedrueckt = true;

                        //Bewegung links in Inventar
                        if (iGedrueckt) {
                            rechtsUndLinksInventar();
                        }

                        //Bewgung links in Shop
                        else if (spieler.spielablaufManager.shopGeoeffnet && spieler.konto.genugMuenzen) {
                            linksShop();
                        }

                        //Bewegung links in Clients Auswahl
                        else if (spieler.spielablaufManager.clientAuswaehlen) {
                            if (SpielClient.anzahlSpieler == 3) {
                                if (spieler.spielablaufManager.bube.befehlNum == 0 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 0) {
                                    spieler.spielablaufManager.bube.befehlNum = 1;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 1;
                                } else if (spieler.spielablaufManager.bube.befehlNum == 1 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 1) {
                                    spieler.spielablaufManager.bube.befehlNum = 0;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 0;
                                }
                            } else if (SpielClient.anzahlSpieler == 4) {
                                if (spieler.spielablaufManager.bube.befehlNum == 0 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 0) {
                                    spieler.spielablaufManager.bube.befehlNum = 2;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 2;
                                } else if (spieler.spielablaufManager.bube.befehlNum == 1 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 1) {
                                    spieler.spielablaufManager.bube.befehlNum = 0;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 0;
                                } else if (spieler.spielablaufManager.bube.befehlNum == 2 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 2) {
                                    spieler.spielablaufManager.bube.befehlNum = 1;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 1;
                                }
                            }
                        }

                        //Bewegung links in Klauen Möglichkeit
                        else if (spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten && spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugMuenzen
                                && spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugSterne && !spieler.spielablaufManager.tempBlauesFeld.inventarLeer) {
                            if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 0) {
                                spieler.spielablaufManager.tempBlauesFeld.befehlNum2 = 2;
                            } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 2) {
                                spieler.spielablaufManager.tempBlauesFeld.befehlNum2 = 1;
                            } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 1) {
                                spieler.spielablaufManager.tempBlauesFeld.befehlNum2 = 0;
                            }
                        }

                        //Bewegung links in Gegenstand Klauen
                        else if (spieler.spielablaufManager.tempBlauesFeld.inventarKlauen) {
                            if (spielMapManager.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 2) {
                                if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 0) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 1;
                                } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 1) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 0;
                                }
                            } else if (spielMapManager.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 3) {
                                if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 0) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 2;
                                } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 2) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 1;
                                } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 1) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 0;
                                }
                            } else if (spielMapManager.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 4) {
                                if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 0) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 3;
                                } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 3) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 2;
                                } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 2) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 1;
                                } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 1) {
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 0;
                                }
                            }
                        }

                        //Bewegung West nach das nächste Feld
                        else if (spieler.konto.genugMuenzen && !spieler.wuerfelZustand && !spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten) {
                            checkNaechstesFeld();
                            if (!falscheRichtung) {
                                if (spieler.aktuellesFeld != null && spieler.aktuellesFeld.westFeld != null) {
                                    spieler.schritteAnzahl--;
                                    spieler.bewegung = true;
                                    bewegungLinks = true;
                                    if (spielMapManager.spielablaufManager.mainSpieler.weltX == (spieler.aktuellesFeld.weltX)) {
                                        spieler.spielfigur.richtung = "stehen";
                                        spieler.vorherigesFeld = spieler.aktuellesFeld;
                                        spieler.aktuellesFeld = null;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        spieler = this.spielMapManager.spielablaufManager.mainSpieler;
        int code = e.getKeyCode();

        //Speziell fall für beginn des Spiels (nur Würfel kann gespielt wird)
        if(spielMapManager.spielablaufManager.sp.client.istDran() && spielMapManager.spielablaufManager.sp.aktuelleRundenAnzahl == 0){
            if (code == KeyEvent.VK_SPACE) {
                spaceGedrueckt = false;
                spieler.wuerfel.schritteAnzahlBestimmen();
            }
        }

        //alle Taste, die freigegeben können, wenn der Spieler dran ist
        else if(spielMapManager.spielablaufManager.sp.client.istDran()) {

            //alle Taste, die freigegeben können, wenn der Spieler nicht am Bewegung zwischen zwei felder ist
            if (!spieler.bewegung && !iGedrueckt) {
                if(spieler.wuerfelZustand) {

                    //Schritte Anzahl bestimmen, abhängig von dem Typ des Würfels
                    if (code == KeyEvent.VK_SPACE) {
                        spaceGedrueckt = false;
                        if (spieler.normaleWuerfelZustand) {
                            spieler.wuerfel.schritteAnzahlBestimmen();
                        } else if (spieler.megaWuerfelZustand) {
                            spieler.megaWuerfel.schritteAnzahlBestimmen();
                            spieler.megaWuerfelZustand = false;
                            spieler.normaleWuerfelZustand = true;
                        } else if (spieler.miniWuerfelZustand) {
                            spieler.miniWuerfel.schritteAnzahlBestimmen();
                            spieler.miniWuerfelZustand = false;
                            spieler.normaleWuerfelZustand = true;
                        }
                        spieler.wuerfelZustand = false;
                    }
                }
            }
        }
    }

    //festlegen, ob das nächste Feld die gleiche wie das vorherige Feld ist
    private void checkNaechstesFeld(){
        Feld tempFeld = null;
        if(obenGedrueckt && spieler.vorherigesFeld != null) {
            tempFeld = spieler.aktuellesFeld.nordFeld;
             obenGedrueckt = false;
        }else if(untenGedrueckt && spieler.vorherigesFeld != null) {
            tempFeld = spieler.aktuellesFeld.suedFeld;
            untenGedrueckt = false;
        }else if(rechtsGedrueckt && spieler.vorherigesFeld != null) {
            tempFeld = spieler.aktuellesFeld.ostFeld;
            rechtsGedrueckt = false;
        }else if(linksGedrueckt && spieler.vorherigesFeld != null) {
            tempFeld = spieler.aktuellesFeld.westFeld;
            linksGedrueckt = false;
        }
        if(spieler.aktuellesFeld == null || tempFeld == null){
            falscheRichtung = false;
        }else falscheRichtung = tempFeld.equals(spieler.vorherigesFeld);
    }

    private void untenUndObenInventar(){
        spieler.inventar.inventarLeerOderVoll();
        if(spieler.inventar.gegenstaendeAnzahl == 2 || spieler.inventar.gegenstaendeAnzahl == 3){
            if (spieler.inventar.inventar[0] != null && spieler.inventar.inventar[2] != null) {
                if (spieler.inventar.befehlNum == 0) {
                    spieler.inventar.befehlNum = 1;
                } else if (spieler.inventar.befehlNum == 1) {
                    spieler.inventar.befehlNum = 0;
                }
            }else if(spieler.inventar.inventar[2] != null && spieler.inventar.inventar[3] != null){
                if (spieler.inventar.befehlNum == 2) {
                    spieler.inventar.befehlNum = 3;
                } else if (spieler.inventar.befehlNum == 3) {
                    spieler.inventar.befehlNum = 2;
                }
            }
        }else if(spieler.inventar.gegenstaendeAnzahl == 4) {
            if (spieler.inventar.befehlNum == 0) {
                spieler.inventar.befehlNum = 1;
            } else if (spieler.inventar.befehlNum == 1) {
                spieler.inventar.befehlNum = 0;
            } else if (spieler.inventar.befehlNum == 2) {
                spieler.inventar.befehlNum = 3;
            } else if (spieler.inventar.befehlNum == 3) {
                spieler.inventar.befehlNum = 2;
            }
        }
    }

    private void rechtsUndLinksInventar(){
        spieler.inventar.inventarLeerOderVoll();
        if(spieler.inventar.gegenstaendeAnzahl == 2 || spieler.inventar.gegenstaendeAnzahl == 3){
            if (spieler.inventar.inventar[0] != null && spieler.inventar.inventar[1] != null) {
                if (spieler.inventar.befehlNum == 0) {
                    spieler.inventar.befehlNum = 2;
                } else if (spieler.inventar.befehlNum == 2) {
                    spieler.inventar.befehlNum = 0;
                }
            }else if(spieler.inventar.inventar[2] != null && spieler.inventar.inventar[3] != null){
                if (spieler.inventar.befehlNum == 1) {
                    spieler.inventar.befehlNum = 3;
                } else if (spieler.inventar.befehlNum == 3) {
                    spieler.inventar.befehlNum = 1;
                }
            }else if(spieler.inventar.inventar[0] != null && spieler.inventar.inventar[3] != null) {
                if (spieler.inventar.befehlNum == 0) {
                    spieler.inventar.befehlNum = 3;
                } else if (spieler.inventar.befehlNum == 3) {
                    spieler.inventar.befehlNum = 0;
                }
            }else if(spieler.inventar.inventar[1] != null && spieler.inventar.inventar[2] != null) {
                if (spieler.inventar.befehlNum == 1) {
                    spieler.inventar.befehlNum = 2;
                } else if (spieler.inventar.befehlNum == 2) {
                    spieler.inventar.befehlNum = 1;
                }
            }
        }else if(spieler.inventar.gegenstaendeAnzahl == 4) {
            if (spieler.inventar.befehlNum == 0) {
                spieler.inventar.befehlNum = 2;
            } else if (spieler.inventar.befehlNum == 2) {
                spieler.inventar.befehlNum = 0;
            } else if (spieler.inventar.befehlNum == 1) {
                spieler.inventar.befehlNum = 3;
            } else if (spieler.inventar.befehlNum == 3) {
                spieler.inventar.befehlNum = 1;
            }
        }
    }

    private void untenUndObenShop(){
        if(spieler.spielablaufManager.shop.befehlNum == 0){
            spieler.spielablaufManager.shop.befehlNum = 3;
        }else if(spieler.spielablaufManager.shop.befehlNum == 3){
            spieler.spielablaufManager.shop.befehlNum = 0;
        }else if(spieler.spielablaufManager.shop.befehlNum == 1){
            spieler.spielablaufManager.shop.befehlNum = 4;
        }else if(spieler.spielablaufManager.shop.befehlNum == 4){
            spieler.spielablaufManager.shop.befehlNum = 1;
        }else if(spieler.spielablaufManager.shop.befehlNum == 2){
            spieler.spielablaufManager.shop.befehlNum = 5;
        }else if(spieler.spielablaufManager.shop.befehlNum == 5){
            spieler.spielablaufManager.shop.befehlNum = 2;
        }
    }

    private void rechtsShop(){
        if(spieler.spielablaufManager.shop.befehlNum == 0){
            spieler.spielablaufManager.shop.befehlNum = 1;
        }else if(spieler.spielablaufManager.shop.befehlNum == 1){
            spieler.spielablaufManager.shop.befehlNum = 2;
        }else if(spieler.spielablaufManager.shop.befehlNum == 2){
            spieler.spielablaufManager.shop.befehlNum = 0;
        }else if(spieler.spielablaufManager.shop.befehlNum == 3){
            spieler.spielablaufManager.shop.befehlNum = 4;
        }else if(spieler.spielablaufManager.shop.befehlNum == 4){
            spieler.spielablaufManager.shop.befehlNum = 5;
        }else if(spieler.spielablaufManager.shop.befehlNum == 5){
            spieler.spielablaufManager.shop.befehlNum = 3;
        }
    }

    private void linksShop(){
        if(spieler.spielablaufManager.shop.befehlNum == 0){
            spieler.spielablaufManager.shop.befehlNum = 2;
        }else if(spieler.spielablaufManager.shop.befehlNum == 2){
            spieler.spielablaufManager.shop.befehlNum = 1;
        }else if(spieler.spielablaufManager.shop.befehlNum == 1){
            spieler.spielablaufManager.shop.befehlNum = 0;
        }else if(spieler.spielablaufManager.shop.befehlNum == 3){
            spieler.spielablaufManager.shop.befehlNum = 5;
        }else if(spieler.spielablaufManager.shop.befehlNum == 5){
            spieler.spielablaufManager.shop.befehlNum = 4;
        }else if(spieler.spielablaufManager.shop.befehlNum == 4){
            spieler.spielablaufManager.shop.befehlNum = 3;
        }
    }

}
