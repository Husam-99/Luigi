package Spielablauf;


import Networking.Pakete.Bewegung;
import spieler.Bube;
import spieler.Spieler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MapEingabeManager implements KeyListener {
    SpielMapManager spielMapManager;
    Spieler spieler;
    int round= 0;
    public Boolean obenGedrueckt = false, untenGedrueckt = false, rechtsGedrueckt = false, linksGedrueckt = false,
            spaceGedrueckt = false, iGedrueckt = false, bewegungOben = false, bewegungUnten = false, bewegungRechts = false,
            bewegungLinks = false, falscheRichtung = false;
    public MapEingabeManager(SpielMapManager spielMapManager) {
        this.spielMapManager = spielMapManager;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        spieler = this.spielMapManager.spielablaufManager.mainSpieler;
        int code = e.getKeyCode();
        if(spielMapManager.spielablaufManager.sp.client.istDran()) {
            if(iGedrueckt){
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
            }else if(spieler.spielablaufManager.shopGeoeffnet){
                if(code == KeyEvent.VK_ENTER){
                    if(spieler.konto.genugMuenzen) {
                        spieler.spielablaufManager.shop.gegenstandKaufen();
                    }else if(!spieler.konto.genugMuenzen){
                        spieler.konto.genugMuenzen = true;
                    }
                }else if(code == KeyEvent.VK_ESCAPE){
                    spieler.spielablaufManager.shopGeoeffnet = false;
                    if(!spielMapManager.spielablaufManager.mainSpieler.aktuellFeld.hatStern) {
                        spielMapManager.spielablaufManager.mainSpieler.amSpiel = false;
                    }else{
                        spielMapManager.stern.sternKaufen = true;
                    }
                }
            }else if(spielMapManager.stern.sternKaufen){
                if (code == KeyEvent.VK_ENTER) {
                    if (spieler.konto.genugMuenzen) {
                        spielMapManager.stern.sternKaufen();
                    } else if (!spieler.konto.genugMuenzen) {
                        spieler.konto.genugMuenzen = true;
                    }
                }else if(code == KeyEvent.VK_ESCAPE){
                    spielMapManager.stern.sternKaufen = false;
                    spielMapManager.spielablaufManager.mainSpieler.amSpiel = false;
                }
            }else if(spieler.spielablaufManager.clientAuswaehlen){
                if (code == KeyEvent.VK_ENTER) {
                    spieler.spielablaufManager.clientAuswaehlen = false;
                    spieler.spielablaufManager.bube.positionWechsel();
                }
            } else if (!spieler.bewegung) {
                if (code == KeyEvent.VK_SPACE) {
                    spaceGedrueckt = true;
                    spieler.wuerfelZustand = true;
                }
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        Bewegung bewegung = new Bewegung();
        spieler = this.spielMapManager.spielablaufManager.mainSpieler;
        if(spielMapManager.spielablaufManager.sp.client.istDran()) {
            if (!spieler.bewegung && !spielMapManager.stern.sternKaufen) {
                switch (e.getKeyChar()) {
                    case 'w':
                        obenGedrueckt = true;
                        if (iGedrueckt) {
                          untenUndObenInventar();
                        } else if(spieler.spielablaufManager.shopGeoeffnet) {
                           untenUndObenShop();
                        }else if(!spieler.spielablaufManager.clientAuswaehlen){
                            checkNaechstesFeld();
                            if (!falscheRichtung) {
                                if (spieler.naechstesFeld != null && spieler.naechstesFeld.nordFeld != null) {
                                    spieler.schritteAnzahl--;
                                    spieler.bewegung = true;
                                    bewegungOben = true;
                                    if (spielMapManager.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spielMapManager.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                        spieler.spielfigur.richtung = "stehen";
                                        spieler.aktuellesFeld = spieler.naechstesFeld;
                                        spieler.naechstesFeld = null;
                                        System.out.println("afeter map");
                                        System.out.println("aktuelles: " +spieler.aktuellesFeld);
                                        System.out.println("naechstes: " +spieler.naechstesFeld);
                                        System.out.println("aktuell: "+ spieler.aktuellFeld);
                                        System.out.println("temp: " + spieler.tempFeld);
                                    }
                                }
                            }
                        }
                        break;
                    case 's':
                        untenGedrueckt = true;
                        if (iGedrueckt) {
                            untenUndObenInventar();
                        }else if(spieler.spielablaufManager.shopGeoeffnet){
                            untenUndObenShop();
                        }else if(!spieler.spielablaufManager.clientAuswaehlen){
                            checkNaechstesFeld();
                            if (!falscheRichtung) {
                                if (spieler.naechstesFeld != null && spieler.naechstesFeld.suedFeld != null) {
                                    spieler.schritteAnzahl--;
                                    spieler.bewegung = true;
                                    bewegungUnten = true;
                                    if (spielMapManager.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spielMapManager.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                        spieler.spielfigur.richtung = "stehen";
                                        spieler.aktuellesFeld = spieler.naechstesFeld;
                                        spieler.naechstesFeld = null;
                                        System.out.println("afeter map");
                                        System.out.println("aktuelles: " +spieler.aktuellesFeld);
                                        System.out.println("naechstes: " +spieler.naechstesFeld);
                                        System.out.println("aktuell: "+ spieler.aktuellFeld);
                                        System.out.println("temp: " + spieler.tempFeld);

                                    }
                                }
                            }
                        }
                        break;
                    case 'd':
                        rechtsGedrueckt = true;
                        if (iGedrueckt) {
                          rechtsUndLinksInventar();
                        } else if(spieler.spielablaufManager.shopGeoeffnet){
                            rechtsShop();
                        }else if(spieler.spielablaufManager.clientAuswaehlen){
                            if(spieler.spielablaufManager.sp.client.anzahlSpieler == 3){
                                if(spieler.spielablaufManager.bube.befehlNum == 0 || spieler.spielablaufManager.blauesFeldBefehlNum1 == 0){
                                    spieler.spielablaufManager.bube.befehlNum = 1;
                                    spieler.spielablaufManager.blauesFeldBefehlNum1 = 1;
                                }else if(spieler.spielablaufManager.bube.befehlNum == 1 || spieler.spielablaufManager.blauesFeldBefehlNum1 == 1){
                                    spieler.spielablaufManager.bube.befehlNum = 0;
                                    spieler.spielablaufManager.blauesFeldBefehlNum1 = 0;
                                }
                            }else if(spieler.spielablaufManager.sp.client.anzahlSpieler == 4){
                                if(spieler.spielablaufManager.bube.befehlNum == 0 || spieler.spielablaufManager.blauesFeldBefehlNum1 == 0){
                                    spieler.spielablaufManager.bube.befehlNum = 1;
                                    spieler.spielablaufManager.blauesFeldBefehlNum1 = 1;
                                }else if(spieler.spielablaufManager.bube.befehlNum == 1 || spieler.spielablaufManager.blauesFeldBefehlNum1 == 1){
                                    spieler.spielablaufManager.bube.befehlNum = 2;
                                    spieler.spielablaufManager.blauesFeldBefehlNum1 = 2;
                                }else if(spieler.spielablaufManager.bube.befehlNum == 2 || spieler.spielablaufManager.blauesFeldBefehlNum1 == 2){
                                    spieler.spielablaufManager.bube.befehlNum = 0;
                                    spieler.spielablaufManager.blauesFeldBefehlNum1 = 0;
                                }
                            }
                        } else{
                            checkNaechstesFeld();
                            if (!falscheRichtung) {
                                if (spieler.naechstesFeld != null && spieler.naechstesFeld.ostFeld != null) {
                                    spieler.schritteAnzahl--;
                                    spieler.bewegung = true;
                                    bewegungRechts = true;
                                    if (spielMapManager.spielablaufManager.mainSpieler.weltX == (spieler.naechstesFeld.weltX)) {
                                        spieler.spielfigur.richtung = "stehen";
                                        spieler.aktuellesFeld = spieler.naechstesFeld;
                                        spieler.naechstesFeld = null;
                                        System.out.println("afeter map");
                                        System.out.println("aktuelles: " + spieler.aktuellesFeld);
                                        System.out.println("naechstes: " + spieler.naechstesFeld);
                                        System.out.println("aktuell: " + spieler.aktuellFeld);
                                        System.out.println("temp: " + spieler.tempFeld);

                                    }
                                }
                            }
                        }

                        break;
                    case 'a':
                        linksGedrueckt = true;
                        if (iGedrueckt) {
                            rechtsUndLinksInventar();
                        }else if(spieler.spielablaufManager.shopGeoeffnet){
                            linksShop();
                        }else if(spieler.spielablaufManager.clientAuswaehlen) {
                            if(spieler.spielablaufManager.sp.client.anzahlSpieler == 3){
                                if(spieler.spielablaufManager.bube.befehlNum == 0 || spieler.spielablaufManager.blauesFeldBefehlNum1 == 0){
                                    spieler.spielablaufManager.bube.befehlNum = 1;
                                    spieler.spielablaufManager.blauesFeldBefehlNum1 = 1;
                                }else if(spieler.spielablaufManager.bube.befehlNum == 1 || spieler.spielablaufManager.blauesFeldBefehlNum1 == 1){
                                    spieler.spielablaufManager.bube.befehlNum = 0;
                                    spieler.spielablaufManager.blauesFeldBefehlNum1 = 0;
                                }
                            }else if(spieler.spielablaufManager.sp.client.anzahlSpieler == 4){
                                if(spieler.spielablaufManager.bube.befehlNum == 0 || spieler.spielablaufManager.blauesFeldBefehlNum1 == 0){
                                    spieler.spielablaufManager.bube.befehlNum = 2;
                                    spieler.spielablaufManager.blauesFeldBefehlNum1 = 2;
                                }else if(spieler.spielablaufManager.bube.befehlNum == 1|| spieler.spielablaufManager.blauesFeldBefehlNum1 == 1){
                                    spieler.spielablaufManager.bube.befehlNum = 0;
                                    spieler.spielablaufManager.blauesFeldBefehlNum1 = 0;
                                }else if(spieler.spielablaufManager.bube.befehlNum == 2 || spieler.spielablaufManager.blauesFeldBefehlNum1 == 2){
                                    spieler.spielablaufManager.bube.befehlNum = 1;
                                    spieler.spielablaufManager.blauesFeldBefehlNum1 = 1;                                }
                            }
                        }else {
                            checkNaechstesFeld();
                            if (!falscheRichtung) {
                                if (spieler.naechstesFeld != null && spieler.naechstesFeld.westFeld != null) {
                                    spieler.schritteAnzahl--;
                                    spieler.bewegung = true;
                                    bewegungLinks = true;
                                    if (spielMapManager.spielablaufManager.mainSpieler.weltX == (spieler.naechstesFeld.weltX)) {
                                        spieler.spielfigur.richtung = "stehen";
                                        spieler.aktuellesFeld = spieler.naechstesFeld;
                                        spieler.naechstesFeld = null;
                                        System.out.println("afeter map");
                                        System.out.println("aktuelles: " +spieler.aktuellesFeld);
                                        System.out.println("naechstes: " +spieler.naechstesFeld);
                                        System.out.println("aktuell: "+ spieler.aktuellFeld);
                                        System.out.println("temp: " + spieler.tempFeld);
                                    }
                                }
                            }
                        }
                        break;
                    case 'i':
                        if (!iGedrueckt) {
                            spieler.inventarZustand = true;
                            iGedrueckt = true;
                        } else if (iGedrueckt) {
                            spieler.inventarZustand = false;
                            iGedrueckt = false;
                        }
                        break;
                }
                bewegung.feldNum  =  spieler.aktuellFeld.feldNum;
                spieler.spielablaufManager.sp.client.send(bewegung);
            }

        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        spieler = this.spielMapManager.spielablaufManager.mainSpieler;
        int code = e.getKeyCode();
        if(spielMapManager.spielablaufManager.sp.client.istDran()) {
            if (!spieler.bewegung && !iGedrueckt) {
                if (code == KeyEvent.VK_SPACE) {
                    spaceGedrueckt = false;
                    if(spieler.normaleWuerfelZustand) {
                        spieler.wuerfel.schritteAnzahlBestimmen();
                    }else if(spieler.megaWuerfelZustand){
                        spieler.megaWuerfel.schritteAnzahlBestimmen();
                        spieler.megaWuerfelZustand = false;
                        spieler.normaleWuerfelZustand = true;
                    }else if(spieler.miniWuerfelZustand){
                        spieler.miniWuerfel.schritteAnzahlBestimmen();
                        spieler.miniWuerfelZustand = false;
                        spieler.normaleWuerfelZustand = true;
                    }
                    spieler.wuerfelZustand = false;
                }
            }
        }
    }
    private void checkNaechstesFeld(){
        if(spieler.aktuellesFeld == null){
        }else if(obenGedrueckt) {
            spieler.tempFeld = spieler.naechstesFeld.nordFeld;
             obenGedrueckt = false;
        }else if(untenGedrueckt) {
            spieler.tempFeld = spieler.naechstesFeld.suedFeld;
            untenGedrueckt = false;
        }else if(rechtsGedrueckt) {
            spieler.tempFeld = spieler.naechstesFeld.ostFeld;
            rechtsGedrueckt = false;
        }else if(linksGedrueckt) {
            spieler.tempFeld = spieler.naechstesFeld.westFeld;
            linksGedrueckt = false;
        }
        if(spieler.naechstesFeld == null || spieler.tempFeld == null){
            falscheRichtung = false;
        }else if(spieler.tempFeld.equals(spieler.aktuellesFeld)){
            falscheRichtung = true;
        }else if(!spieler.tempFeld.equals(spieler.aktuellesFeld)){
            spieler.aktuellFeld = spieler.tempFeld;
            falscheRichtung = false;
        }
        System.out.println("afeter test");
        System.out.println("aktuelles: " +spieler.aktuellesFeld);
        System.out.println("naechstes: " +spieler.naechstesFeld);
        System.out.println("aktuell: "+ spieler.aktuellFeld);
        System.out.println("temp: " + spieler.tempFeld);

    }
    private void untenUndObenInventar(){
        spieler.inventar.anzahlGegenstaendeInInventar();
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
        spieler.inventar.anzahlGegenstaendeInInventar();
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
