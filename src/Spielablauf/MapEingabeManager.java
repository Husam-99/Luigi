package Spielablauf;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MapEingabeManager implements KeyListener {
    SpielMapManager spielMapManager;
    Spieler spieler;


    public MapEingabeManager(SpielMapManager spielMapManager) {
        this.spielMapManager = spielMapManager;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        spieler = this.spielMapManager.sp.husam;
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (spieler.aktuellesFeld == null) {
                spieler.aktuellesFeld = this.spielMapManager.mapFliesen[19][11].feld;
            }
            if (spieler.naechstesFeld == null) {
                spieler.naechstesFeld = spieler.aktuellesFeld.nordFeld;
            }
            if (spieler.naechstesFeld != null) {
                if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[6][14].feld)) {
                    while (spielMapManager.alleSpieler.get(0).weltX > spieler.naechstesFeld.weltX) {
                        spielMapManager.alleSpieler.get(0).weltX -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                    }
                } else if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[6][26].feld)) {
                    while (spielMapManager.alleSpieler.get(0).weltX < spieler.naechstesFeld.weltX) {
                        spielMapManager.alleSpieler.get(0).weltX += spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                    }
                }

                while (spielMapManager.alleSpieler.get(0).weltY > (spieler.naechstesFeld.weltY - spielMapManager.sp.vergroesserteFliesenGroesse / 2)) {
                    spielMapManager.alleSpieler.get(0).weltY -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                }
                if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[6][9].feld) || spieler.naechstesFeld.equals(spielMapManager.mapFliesen[3][17].feld)) {
                    while (spielMapManager.alleSpieler.get(0).weltX < (spieler.naechstesFeld.weltX)) {
                        spielMapManager.alleSpieler.get(0).weltX += spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                    }
                } else if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[3][23].feld)) {
                    while (spielMapManager.alleSpieler.get(0).weltX > spieler.naechstesFeld.weltX) {
                        spielMapManager.alleSpieler.get(0).weltX -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                    }
                }

                spieler.aktuellesFeld = spieler.naechstesFeld;
                spieler.naechstesFeld = null;

            }

        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            if (spieler.naechstesFeld == null) {
                spieler.naechstesFeld = spieler.aktuellesFeld.suedFeld;
            }
            if (spieler.naechstesFeld != null) {
                if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[7][15].feld) || spieler.naechstesFeld.equals(spielMapManager.mapFliesen[4][24].feld)) {
                    while (spielMapManager.alleSpieler.get(0).weltX < (spieler.naechstesFeld.weltX)) {
                        spielMapManager.alleSpieler.get(0).weltX += spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                    }
                }
                while (spielMapManager.alleSpieler.get(0).weltY < (spieler.naechstesFeld.weltY - spielMapManager.sp.vergroesserteFliesenGroesse / 2)) {
                    spielMapManager.alleSpieler.get(0).weltY += spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                }
                if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[4][16].feld) || spieler.naechstesFeld.equals(spielMapManager.mapFliesen[7][8].feld)) {
                    while (spielMapManager.alleSpieler.get(0).weltX > (spieler.naechstesFeld.weltX)) {
                        spielMapManager.alleSpieler.get(0).weltX -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                    }
                } else if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[7][25].feld)) {
                    while (spielMapManager.alleSpieler.get(0).weltX > (spieler.naechstesFeld.weltX)) {
                        spielMapManager.alleSpieler.get(0).weltX -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                    }
                }

                spieler.aktuellesFeld = spieler.naechstesFeld;
                spieler.naechstesFeld = null;

            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            if (spieler.naechstesFeld == null) {
                spieler.naechstesFeld = spieler.aktuellesFeld.ostFeld;
            }
            if (spieler.naechstesFeld != null) {
                while (spielMapManager.alleSpieler.get(0).weltX < (spieler.naechstesFeld.weltX)) {
                    spielMapManager.alleSpieler.get(0).weltX += spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                }

                spieler.aktuellesFeld = spieler.naechstesFeld;
                spieler.naechstesFeld = null;

            }

        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            if (spieler.naechstesFeld == null) {
                spieler.naechstesFeld = spieler.aktuellesFeld.westFeld;
            }
            if (spieler.naechstesFeld != null) {
                while (spielMapManager.alleSpieler.get(0).weltX > (spieler.naechstesFeld.weltX)) {
                    spielMapManager.alleSpieler.get(0).weltX -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                }

                spieler.aktuellesFeld = spieler.naechstesFeld;
                spieler.naechstesFeld = null;

            }


        }


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
