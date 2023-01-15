package Spielablauf;

import Networking.Pakete.Bescheid;
import Networking.Pakete.SpielerPosition;
import spieler.Spieler;

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
        spieler = this.spielMapManager.sp.mainSpieler;
        if(spielMapManager.sp.client.istDran()){
            if (e.getKeyCode() == KeyEvent.VK_W) {
                if (spieler.aktuellesFeld == null) {
                    spieler.aktuellesFeld = this.spielMapManager.mapFliesen[19][11].feld;

                    spieler.weltY -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                    spieler.weltX += spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                    for(Spieler andererSpieler : spielMapManager.sp.alleSpieler){
                        andererSpieler.bildschirmY += spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                        andererSpieler.bildschirmX -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;

                    }
                }
                if (spieler.naechstesFeld == null) {
                    spieler.naechstesFeld = spieler.aktuellesFeld.nordFeld;
                }
                if (spieler.naechstesFeld != null) {
                    if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[6][14].feld)) {
                        while (spieler.weltX > spieler.naechstesFeld.weltX) {
                            spieler.weltX -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                        }
                    } else if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[6][26].feld)) {
                        while (spieler.weltX < spieler.naechstesFeld.weltX) {
                            spieler.weltX += spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                        }
                    }

                    while (spieler.weltY > (spieler.naechstesFeld.weltY - spielMapManager.sp.vergroesserteFliesenGroesse / 2)) {
                        spieler.weltY -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                        if(!spielMapManager.sp.alleSpieler.isEmpty())
                            for(Spieler andererSpieler : spielMapManager.sp.alleSpieler){
                                andererSpieler.bildschirmY += spielMapManager.sp.vergroesserteFliesenGroesse / 2;

                            }
                    }

                    if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[6][9].feld) || spieler.naechstesFeld.equals(spielMapManager.mapFliesen[3][17].feld)) {
                        while (spieler.weltX < (spieler.naechstesFeld.weltX)) {
                            spieler.weltX += spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                        }
                    } else if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[3][23].feld)) {
                        while (spieler.weltX > spieler.naechstesFeld.weltX) {
                            spieler.weltX -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                        }
                    }
                    SpielerPosition spielerPosition = new SpielerPosition();
                    spielerPosition.weltX = spieler.naechstesFeld.weltX;
                    spielerPosition.weltY = spieler.naechstesFeld.weltY - spielMapManager.sp.vergroesserteFliesenGroesse/2;
                    spieler.aktuellesFeld = spieler.naechstesFeld;
                    spieler.naechstesFeld = null;
                    Bescheid bescheid = new Bescheid();
                    bescheid.fertig = true;

                    spielMapManager.sp.client.send(spielerPosition);
                    spielMapManager.sp.client.send(bescheid);

                }

                //SpielerPosition spielerPosition = new SpielerPosition(spieler.bildschirmX, spieler.bildschirmY);
                //spielMapManager.sp.client.send(spielerPosition);


            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                if (spieler.naechstesFeld == null) {
                    spieler.naechstesFeld = spieler.aktuellesFeld.suedFeld;
                }
                if (spieler.naechstesFeld != null) {
                    if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[7][15].feld) || spieler.naechstesFeld.equals(spielMapManager.mapFliesen[4][24].feld)) {
                        while (spieler.weltX < (spieler.naechstesFeld.weltX)) {
                            spieler.weltX += spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                        }
                    }
                    while (spieler.weltY < (spieler.naechstesFeld.weltY - spielMapManager.sp.vergroesserteFliesenGroesse / 2)) {
                        spieler.weltY += spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                        if(!spielMapManager.sp.alleSpieler.isEmpty())
                            for(Spieler andererSpieler : spielMapManager.sp.alleSpieler){
                                andererSpieler.bildschirmY -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;

                            }
                    }
                    if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[4][16].feld) || spieler.naechstesFeld.equals(spielMapManager.mapFliesen[7][8].feld)) {
                        while (spieler.weltX > (spieler.naechstesFeld.weltX)) {
                            spieler.weltX -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                        }
                    } else if (spieler.naechstesFeld.equals(spielMapManager.mapFliesen[7][25].feld)) {
                        while (spieler.weltX > (spieler.naechstesFeld.weltX)) {
                            spieler.weltX -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                        }
                    }
                    SpielerPosition spielerPosition = new SpielerPosition();
                    spielerPosition.weltX = spieler.naechstesFeld.weltX;
                    spielerPosition.weltY = spieler.naechstesFeld.weltY - spielMapManager.sp.vergroesserteFliesenGroesse/2;
                    spieler.aktuellesFeld = spieler.naechstesFeld;
                    spieler.naechstesFeld = null;
                    Bescheid bescheid = new Bescheid();
                    bescheid.fertig = true;

                    spielMapManager.sp.client.send(spielerPosition);
                    spielMapManager.sp.client.send(bescheid);


                }
                //SpielerPosition spielerPosition = new SpielerPosition(spieler.bildschirmX, spieler.bildschirmY);
               // spielMapManager.sp.client.send(spielerPosition);

            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                if (spieler.naechstesFeld == null) {
                    spieler.naechstesFeld = spieler.aktuellesFeld.ostFeld;
                }
                if (spieler.naechstesFeld != null) {
                    while (spieler.weltX < (spieler.naechstesFeld.weltX)) {
                        spieler.weltX += spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                        if(!spielMapManager.sp.alleSpieler.isEmpty())
                            for(Spieler andererSpieler:spielMapManager.sp.alleSpieler){
                                andererSpieler.bildschirmX -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;

                            }
                    }
                    SpielerPosition spielerPosition = new SpielerPosition();
                    spielerPosition.weltX = spieler.naechstesFeld.weltX;
                    spielerPosition.weltY = spieler.naechstesFeld.weltY - spielMapManager.sp.vergroesserteFliesenGroesse/2;
                    spieler.aktuellesFeld = spieler.naechstesFeld;
                    spieler.naechstesFeld = null;
                    Bescheid bescheid = new Bescheid();
                    bescheid.fertig = true;

                    spielMapManager.sp.client.send(spielerPosition);
                    spielMapManager.sp.client.send(bescheid);

                }


            } else if (e.getKeyCode() == KeyEvent.VK_A) {
                if (spieler.naechstesFeld == null) {
                    spieler.naechstesFeld = spieler.aktuellesFeld.westFeld;
                }
                if (spieler.naechstesFeld != null) {
                    while (spieler.weltX > (spieler.naechstesFeld.weltX)) {
                        spieler.weltX -= spielMapManager.sp.vergroesserteFliesenGroesse / 2;
                        if(!spielMapManager.sp.alleSpieler.isEmpty())
                            for(Spieler andererSpieler : spielMapManager.sp.alleSpieler){
                                andererSpieler.bildschirmX += spielMapManager.sp.vergroesserteFliesenGroesse / 2;

                            }
                    }


                    SpielerPosition spielerPosition = new SpielerPosition();
                    spielerPosition.weltX = spieler.naechstesFeld.weltX;
                    spielerPosition.weltY = spieler.naechstesFeld.weltY - spielMapManager.sp.vergroesserteFliesenGroesse/2;
                    spieler.aktuellesFeld = spieler.naechstesFeld;
                    spieler.naechstesFeld = null;
                    Bescheid bescheid = new Bescheid();
                    bescheid.fertig = true;

                    spielMapManager.sp.client.send(spielerPosition);
                    spielMapManager.sp.client.send(bescheid);

                }


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
