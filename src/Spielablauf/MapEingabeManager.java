package Spielablauf;


import spieler.Spieler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MapEingabeManager implements KeyListener {
    SpielMapManager spielMapManager;
    Spieler spieler;
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
                else if(code == KeyEvent.VK_ESCAPE){
                    spieler.inventarZustand = false;
                    spieler.inventar.befehlNum = 0;
                    iGedrueckt = false;
                }
            }else if(spieler.spielablaufManager.shopGeoeffnet){
                if(code == KeyEvent.VK_ENTER){
                    if(spieler.konto.genugMuenzen && !spieler.inventar.inventarVoll) {
                        spieler.spielablaufManager.shop.gegenstandKaufen();
                    }else if(!spieler.konto.genugMuenzen){
                        spieler.konto.genugMuenzen = true;
                    }else if(spieler.spielablaufManager.mainSpieler.inventar.inventarVoll){
                        if(spieler.spielablaufManager.inventarVoll){
                            spieler.spielablaufManager.inventarVoll = false;
                        }else if(!spieler.spielablaufManager.inventarVoll){
                            spieler.spielablaufManager.inventarVoll = true;
                        }
                    }
                }else if(code == KeyEvent.VK_ESCAPE){
                    spieler.spielablaufManager.shopGeoeffnet = false;
                    spieler.spielablaufManager.shop.befehlNum = 0;
                    spieler.konto.genugMuenzen = true;
                    if(!spielMapManager.spielablaufManager.mainSpieler.aktuellesFeld.hatStern) {
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
                    spieler.konto.genugMuenzen = true;
                    spielMapManager.spielablaufManager.mainSpieler.amSpiel = false;
                }
            }else if(spieler.spielablaufManager.clientAuswaehlen){
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
            }else if(spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten){
                if(code == KeyEvent.VK_ENTER) {
                    if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 0) {
                        if(spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugMuenzen){
                            spieler.spielablaufManager.tempBlauesFeld.muenzeKlauen();
                        }else if(!spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugMuenzen){
                            spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugMuenzen = true;
                        }
                    } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 1) {
                        if(spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugSterne){
                            spieler.spielablaufManager.tempBlauesFeld.sternKlauen();
                        }else if(!spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugSterne){
                            spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugSterne = true;
                        }
                    } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 2) {
                        spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.anzahlGegenstaendeInInventar();
                        if(!spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.inventarLeer && !spieler.inventar.inventarVoll){
                            spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten = false;
                            spieler.spielablaufManager.tempBlauesFeld.inventarKlauen = true;
                        }else if(spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.inventarLeer){
                            if(spieler.spielablaufManager.tempBlauesFeld.inventarLeer) {
                                spieler.spielablaufManager.tempBlauesFeld.inventarLeer = false;
                            }else if(!spieler.spielablaufManager.tempBlauesFeld.inventarLeer){
                                spieler.spielablaufManager.tempBlauesFeld.inventarLeer = true;
                            }
                        }else if(spieler.spielablaufManager.mainSpieler.inventar.inventarVoll){
                            if(spieler.spielablaufManager.inventarVoll){
                                spieler.spielablaufManager.inventarVoll = false;
                            }else if(!spieler.spielablaufManager.inventarVoll){
                                spieler.spielablaufManager.inventarVoll = true;
                            }
                        }
                    }
                }else if(code == KeyEvent.VK_ESCAPE){
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
            }else if(spieler.spielablaufManager.tempBlauesFeld.inventarKlauen){
                if(code == KeyEvent.VK_ENTER) {
                    spieler.spielablaufManager.tempBlauesFeld.gegenstandKlauen();
                }
            }else if (!spieler.bewegung && spieler.wuerfelZustand) {
                if (code == KeyEvent.VK_SPACE) {
                    spaceGedrueckt = true;
                }else if(code == KeyEvent.VK_I) {
                    spieler.inventarZustand = true;
                    iGedrueckt = true;
                }
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        spieler = this.spielMapManager.spielablaufManager.mainSpieler;
        if (spielMapManager.spielablaufManager.sp.client.istDran()) {
            if (!spieler.bewegung && !spielMapManager.stern.sternKaufen) {
                switch (e.getKeyChar()) {
                    case 'w':
                        obenGedrueckt = true;
                        if (iGedrueckt) {
                            untenUndObenInventar();
                        } else if (spieler.spielablaufManager.shopGeoeffnet && spieler.konto.genugMuenzen) {
                            untenUndObenShop();
                        } else if (!spieler.spielablaufManager.clientAuswaehlen && spieler.konto.genugMuenzen && !spieler.wuerfelZustand
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
                        break;
                    case 's':
                        untenGedrueckt = true;
                        if (iGedrueckt) {
                            untenUndObenInventar();
                        } else if (spieler.spielablaufManager.shopGeoeffnet && spieler.konto.genugMuenzen) {
                            untenUndObenShop();
                        } else if (!spieler.spielablaufManager.clientAuswaehlen && spieler.konto.genugMuenzen && !spieler.wuerfelZustand
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
                        break;
                    case 'd':
                        rechtsGedrueckt = true;
                        if (iGedrueckt) {
                            rechtsUndLinksInventar();
                        }else if (spieler.spielablaufManager.shopGeoeffnet && spieler.konto.genugMuenzen) {
                            rechtsShop();
                        }else if (spieler.spielablaufManager.clientAuswaehlen) {
                            if (spieler.spielablaufManager.sp.client.anzahlSpieler == 3) {
                                if (spieler.spielablaufManager.bube.befehlNum == 0 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 0) {
                                    spieler.spielablaufManager.bube.befehlNum = 1;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 1;
                                } else if (spieler.spielablaufManager.bube.befehlNum == 1 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 1) {
                                    spieler.spielablaufManager.bube.befehlNum = 0;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 0;
                                }
                            } else if (spieler.spielablaufManager.sp.client.anzahlSpieler == 4) {
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
                        }else if (spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten && spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugMuenzen
                                && spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugSterne && !spieler.spielablaufManager.tempBlauesFeld.inventarLeer) {
                            if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 0) {
                                spieler.spielablaufManager.tempBlauesFeld.befehlNum2 = 1;
                            } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 1) {
                                spieler.spielablaufManager.tempBlauesFeld.befehlNum2 = 2;
                            } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 2) {
                                spieler.spielablaufManager.tempBlauesFeld.befehlNum2 = 0;
                            }
                        }else if(spieler.spielablaufManager.tempBlauesFeld.inventarKlauen){
                            if(spielMapManager.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 2){
                                if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 0){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 1;
                                }else if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 1){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 0;
                                }
                            }else if(spielMapManager.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 3){
                                if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 0){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 1;
                                }else if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 1){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 2;
                                }else if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 2){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 0;
                                }
                            }else if(spielMapManager.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 4){
                                if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 0){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 1;
                                }else if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 1){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 2;
                                }else if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 2){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 3;
                                }else if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 3){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 0;
                                }
                            }
                        } else if(spieler.konto.genugMuenzen && !spieler.wuerfelZustand && !spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten){
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

                        break;
                    case 'a':
                        linksGedrueckt = true;
                        if (iGedrueckt) {
                            rechtsUndLinksInventar();
                        } else if (spieler.spielablaufManager.shopGeoeffnet && spieler.konto.genugMuenzen) {
                            linksShop();
                        } else if (spieler.spielablaufManager.clientAuswaehlen) {
                            if (spieler.spielablaufManager.sp.client.anzahlSpieler == 3) {
                                if (spieler.spielablaufManager.bube.befehlNum == 0 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 0) {
                                    spieler.spielablaufManager.bube.befehlNum = 1;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 1;
                                } else if (spieler.spielablaufManager.bube.befehlNum == 1 || spieler.spielablaufManager.tempBlauesFeld.befehlNum1 == 1) {
                                    spieler.spielablaufManager.bube.befehlNum = 0;
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum1 = 0;
                                }
                            } else if (spieler.spielablaufManager.sp.client.anzahlSpieler == 4) {
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
                        } else if (spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten && spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugMuenzen
                                && spieler.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).konto.genugSterne && !spieler.spielablaufManager.tempBlauesFeld.inventarLeer) {
                            if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 0) {
                                spieler.spielablaufManager.tempBlauesFeld.befehlNum2 = 2;
                            } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 2) {
                                spieler.spielablaufManager.tempBlauesFeld.befehlNum2 = 1;
                            } else if (spieler.spielablaufManager.tempBlauesFeld.befehlNum2 == 1) {
                                spieler.spielablaufManager.tempBlauesFeld.befehlNum2 = 0;
                            }
                        } else if(spieler.spielablaufManager.tempBlauesFeld.inventarKlauen){
                            if(spielMapManager.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 2){
                                if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 0){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 1;
                                }else if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 1){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 0;
                                }
                            }else if(spielMapManager.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 3){
                                if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 0){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 2;
                                }else if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 2){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 1;
                                }else if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 1){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 0;
                                }
                            }else if(spielMapManager.spielablaufManager.sp.alleSpieler.get(spieler.spielablaufManager.ausgewaehlteClientInex).inventar.gegenstaendeAnzahl == 4){
                                if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 0){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 3;
                                }else if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 3){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 2;
                                }else if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 2){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 1;
                                }else if(spieler.spielablaufManager.tempBlauesFeld.befehlNum3 == 1){
                                    spieler.spielablaufManager.tempBlauesFeld.befehlNum3 = 0;
                                }
                            }
                        }else if(spieler.konto.genugMuenzen && !spieler.wuerfelZustand && !spieler.spielablaufManager.tempBlauesFeld.klauenMoeglichkeiten){
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
                        break;
                }
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        spieler = this.spielMapManager.spielablaufManager.mainSpieler;
        int code = e.getKeyCode();
        if(spielMapManager.spielablaufManager.sp.client.istDran()) {
            if (!spieler.bewegung && !iGedrueckt) {
                if(spieler.wuerfelZustand) {
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
    private void checkNaechstesFeld(){
        Feld tempFeld = null;
        if(spieler.vorherigesFeld == null){
        }else if(obenGedrueckt) {
            tempFeld = spieler.aktuellesFeld.nordFeld;
             obenGedrueckt = false;
        }else if(untenGedrueckt) {
            tempFeld = spieler.aktuellesFeld.suedFeld;
            untenGedrueckt = false;
        }else if(rechtsGedrueckt) {
            tempFeld = spieler.aktuellesFeld.ostFeld;
            rechtsGedrueckt = false;
        }else if(linksGedrueckt) {
            tempFeld = spieler.aktuellesFeld.westFeld;
            linksGedrueckt = false;
        }
        if(spieler.aktuellesFeld == null || tempFeld == null){
            falscheRichtung = false;
        }else if(tempFeld.equals(spieler.vorherigesFeld)){
            falscheRichtung = true;
        }else if(!tempFeld.equals(spieler.vorherigesFeld)){
            falscheRichtung = false;
        }
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
