package Spielablauf;

import Networking.Pakete.Bescheid;
import Networking.Pakete.Bewegung;
import Networking.Pakete.SpielerPosition;
import spieler.Spieler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class MapEingabeManager implements KeyListener {
    SpielMapManager spielMapManager;
    Spieler spieler;
    public Boolean obenGedrueckt = false, untenGedrueckt = false, rechtsGedrueckt = false, linksGedrueckt = false,
            spaceGedrueckt = false, iGedrueckt = false, bewegungOben = false, bewegungUnten = false, bewegungRechts = false, bewegungLinks = false, falscheRichtung = false;
    public MapEingabeManager(SpielMapManager spielMapManager) {
        this.spielMapManager = spielMapManager;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        spieler = this.spielMapManager.sp.mainSpieler;
        int code = e.getKeyCode();
        if(spielMapManager.sp.client.istDran()) {
            if (!spieler.bewegung && !iGedrueckt) {
                if (code == KeyEvent.VK_SPACE) {
                    spaceGedrueckt = true;
                    spieler.wuerfelZustand = true;
                }
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        spieler = this.spielMapManager.sp.mainSpieler;
        if(spielMapManager.sp.client.istDran()) {
            if (!spieler.bewegung) {
                switch (e.getKeyChar()) {
                    case 'w':
                        obenGedrueckt = true;
                        if (iGedrueckt) {
                            if (spieler.inventar.befehlNum == 0) {
                                spieler.inventar.befehlNum = 1;
                            } else if (spieler.inventar.befehlNum == 1) {
                                spieler.inventar.befehlNum = 0;
                            } else if (spieler.inventar.befehlNum == 2) {
                                spieler.inventar.befehlNum = 3;
                            } else if (spieler.inventar.befehlNum == 3) {
                                spieler.inventar.befehlNum = 2;
                            }
                        } else {
                            checkNaechstesFeld();
                            if (!falscheRichtung) {
                                if (spieler.naechstesFeld != null && spieler.naechstesFeld.nordFeld != null) {
                                    spieler.schritteAnzahl--;
                                    spieler.bewegung = true;
                                    bewegungOben = true;
                                    Bewegung bewegung = new Bewegung();
                                    bewegung.bewegungOben = true;
                                    spielMapManager.sp.client.send(bewegung);
                                    if (spielMapManager.sp.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spielMapManager.sp.vergroesserteFliesenGroesse / 2)) {
                                        spieler.spielfigur.richtung = "stehen";
                                        spieler.aktuellesFeld = spieler.naechstesFeld;
                                        spieler.naechstesFeld = null;
                                        if (spieler.schritteAnzahl == 0) {
                                            spieler.aktuellFeld.effeckteAnwenden();
                                            Bescheid bescheid = new Bescheid();
                                            bescheid.fertig = true;
                                            spielMapManager.sp.client.send(bescheid);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 's':
                        untenGedrueckt = true;
                        if (iGedrueckt) {
                            if (spieler.inventar.befehlNum == 0) {
                                spieler.inventar.befehlNum = 1;
                            } else if (spieler.inventar.befehlNum == 1) {
                                spieler.inventar.befehlNum = 0;
                            } else if (spieler.inventar.befehlNum == 2) {
                                spieler.inventar.befehlNum = 3;
                            } else if (spieler.inventar.befehlNum == 3) {
                                spieler.inventar.befehlNum = 2;
                            }
                        } else {
                            checkNaechstesFeld();
                            if (!falscheRichtung) {
                                if (spieler.naechstesFeld != null && spieler.naechstesFeld.suedFeld != null) {
                                    spieler.schritteAnzahl--;
                                    spieler.bewegung = true;
                                    bewegungUnten = true;
                                    Bewegung bewegung = new Bewegung();
                                    bewegung.bewegungUnten = true;
                                    spielMapManager.sp.client.send(bewegung);
                                    if (spielMapManager.sp.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spielMapManager.sp.vergroesserteFliesenGroesse / 2)) {
                                        spieler.spielfigur.richtung = "stehen";
                                        spieler.aktuellesFeld = spieler.naechstesFeld;
                                        spieler.naechstesFeld = null;
                                        if (spieler.schritteAnzahl == 0) {
                                            spieler.aktuellFeld.effeckteAnwenden();
                                            Bescheid bescheid = new Bescheid();
                                            bescheid.fertig = true;
                                            spielMapManager.sp.client.send(bescheid);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 'd':
                        rechtsGedrueckt = true;
                        if (iGedrueckt) {
                            if (spieler.inventar.befehlNum == 0) {
                                spieler.inventar.befehlNum = 2;
                            } else if (spieler.inventar.befehlNum == 2) {
                                spieler.inventar.befehlNum = 0;
                            } else if (spieler.inventar.befehlNum == 1) {
                                spieler.inventar.befehlNum = 3;
                            } else if (spieler.inventar.befehlNum == 3) {
                                spieler.inventar.befehlNum = 1;
                            }
                        } else {
                            checkNaechstesFeld();
                            if (!falscheRichtung) {
                                if (spieler.naechstesFeld != null && spieler.naechstesFeld.ostFeld != null) {
                                    spieler.schritteAnzahl--;
                                    spieler.bewegung = true;
                                    bewegungRechts = true;
                                    Bewegung bewegung = new Bewegung();
                                    bewegung.bewegungRechts = true;
                                    spielMapManager.sp.client.send(bewegung);
                                    if (spielMapManager.sp.mainSpieler.weltX == (spieler.naechstesFeld.weltX)) {
                                        spieler.spielfigur.richtung = "stehen";
                                        spieler.aktuellesFeld = spieler.naechstesFeld;
                                        spieler.naechstesFeld = null;
                                        if (spieler.schritteAnzahl == 0) {
                                            spieler.aktuellFeld.effeckteAnwenden();
                                            Bescheid bescheid = new Bescheid();
                                            bescheid.fertig = true;
                                            spielMapManager.sp.client.send(bescheid);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 'a':
                        linksGedrueckt = true;
                        if (iGedrueckt) {
                            if (spieler.inventar.befehlNum == 0) {
                                spieler.inventar.befehlNum = 2;
                            } else if (spieler.inventar.befehlNum == 2) {
                                spieler.inventar.befehlNum = 0;
                            } else if (spieler.inventar.befehlNum == 1) {
                                spieler.inventar.befehlNum = 3;
                            } else if (spieler.inventar.befehlNum == 3) {
                                spieler.inventar.befehlNum = 1;
                            }
                        } else {
                            checkNaechstesFeld();
                            if (!falscheRichtung) {
                                if (spieler.naechstesFeld != null && spieler.naechstesFeld.westFeld != null) {
                                    spieler.schritteAnzahl--;
                                    spieler.bewegung = true;
                                    bewegungLinks = true;
                                    Bewegung bewegung = new Bewegung();
                                    bewegung.bewegungLinks = true;
                                    spielMapManager.sp.client.send(bewegung);
                                    if (spielMapManager.sp.mainSpieler.weltX == (spieler.naechstesFeld.weltX)) {
                                        spieler.spielfigur.richtung = "stehen";
                                        spieler.aktuellesFeld = spieler.naechstesFeld;
                                        spieler.naechstesFeld = null;
                                        if (spieler.schritteAnzahl == 0) {
                                            spieler.aktuellFeld.effeckteAnwenden();
                                            Bescheid bescheid = new Bescheid();
                                            bescheid.fertig = true;
                                            spielMapManager.sp.client.send(bescheid);
                                        }
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
                }
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        spieler = this.spielMapManager.sp.mainSpieler;
        int code = e.getKeyCode();
        if(spielMapManager.sp.client.istDran()) {
            if (!spieler.bewegung && !iGedrueckt) {
                if (code == KeyEvent.VK_SPACE) {
                    spaceGedrueckt = false;
                    spieler.wuerfel.schritteAnzahlBestimmen();
                    spieler.wuerfelZustand = false;
                }
            }
        }
    }

    public void checkNaechstesFeld(){
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
            spieler.aktuellFeld = spieler.tempFeld;
            falscheRichtung = false;
        }else if(spieler.tempFeld.equals(spieler.aktuellesFeld)){
            falscheRichtung = true;
        }else if(!spieler.tempFeld.equals(spieler.aktuellesFeld)){
            spieler.aktuellFeld = spieler.tempFeld;
            falscheRichtung = false;
        }
        System.out.println(spieler.aktuellesFeld);
        System.out.println(spieler.naechstesFeld);
        System.out.println(spieler.aktuellFeld);

    }
}
