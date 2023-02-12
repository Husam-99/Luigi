package Minispiele;
import Networking.Pakete.SquidGamePosition;
import Networking.Pakete.SquidGamePunkte;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SquidGameEingabeManger implements KeyListener {
    boolean tippenErlaubt = true;
    MinispielSpieler mainMinispielSpieler;
    MinispielManager minispielManager;

    public SquidGameEingabeManger(MinispielManager m, MinispielSpieler mainSpieler) {
        this.minispielManager = m;
        this.mainMinispielSpieler = mainSpieler;
    }
    //Bewegung nach links oder rechts funktionalitaet
    @Override
    public void keyTyped(KeyEvent e) {
        if (mainMinispielSpieler.amSpielen) {
            if (this.mainMinispielSpieler == minispielManager.mainMinispielSpieler) {
                if (tippenErlaubt && mainMinispielSpieler.aktuellerZustand.equals("stehen")) {
                    if (e.getKeyChar() == 'd') {
                        if (mainMinispielSpieler.aktuellePalette == null) {
                            mainMinispielSpieler.aktuellePalette = minispielManager.squidGame.paletten[1];
                            if (!mainMinispielSpieler.aktuellePalette.hatFalle) {
                                mainMinispielSpieler.punktzahl++;
                            }
                        } else if (mainMinispielSpieler.aktuellePalette.paletteNummer < 12) {
                            mainMinispielSpieler.aktuellePalette = mainMinispielSpieler.aktuellePalette.naechsteRechts;
                            if (!mainMinispielSpieler.aktuellePalette.hatFalle) {
                                mainMinispielSpieler.punktzahl++;
                            }
                            if (mainMinispielSpieler.aktuellePalette.paletteNummer >= 8) {
                                if (mainMinispielSpieler.aktuellePalette.paletteNummer >= 10) {
                                    mainMinispielSpieler.bildschirmY -= minispielManager.sp.vergroesserteFliesenGroesse;
                                }
                                mainMinispielSpieler.bildschirmY -= minispielManager.sp.vergroesserteFliesenGroesse;
                            }
                        }
                        mainMinispielSpieler.minispielXPosition = mainMinispielSpieler.aktuellePalette.weltX;
                        mainMinispielSpieler.minispielYPosition = mainMinispielSpieler.aktuellePalette.weltY;
                        for (MinispielSpieler spieler : minispielManager.alleMinispielSpieler) {
                            if (spieler != null) {
                                spieler.bildschirmX = spieler.minispielXPosition - minispielManager.mainMinispielSpieler.minispielXPosition + minispielManager.mainMinispielSpieler.bildschirmX;
                                spieler.bildschirmY = spieler.minispielYPosition - minispielManager.mainMinispielSpieler.minispielYPosition + minispielManager.mainMinispielSpieler.bildschirmY;
                            }
                        }
                        SquidGamePosition squidGamePosition = new SquidGamePosition();
                        squidGamePosition.minispielXPosition = mainMinispielSpieler.minispielXPosition;
                        squidGamePosition.minispielYPosition = mainMinispielSpieler.minispielYPosition;
                        squidGamePosition.aktuellePalettenNr = mainMinispielSpieler.aktuellePalette.paletteNummer;
                        minispielManager.sp.client.send(squidGamePosition);

                        SquidGamePunkte squidGamePunkte = new SquidGamePunkte();
                        squidGamePunkte.punktZahl = mainMinispielSpieler.punktzahl;
                        minispielManager.sp.client.send(squidGamePunkte);

                    } else if (e.getKeyChar() == 'a') {
                        if (mainMinispielSpieler.aktuellePalette == null) {
                            mainMinispielSpieler.aktuellePalette = minispielManager.squidGame.paletten[0];
                            if (!mainMinispielSpieler.aktuellePalette.hatFalle) {
                                mainMinispielSpieler.punktzahl++;
                            }
                        } else if (mainMinispielSpieler.aktuellePalette.paletteNummer < 12) {
                            mainMinispielSpieler.aktuellePalette = mainMinispielSpieler.aktuellePalette.naechsteLinks;
                            if (!mainMinispielSpieler.aktuellePalette.hatFalle) {
                                mainMinispielSpieler.punktzahl++;
                            }
                            if (mainMinispielSpieler.aktuellePalette.paletteNummer >= 8) {
                                if (mainMinispielSpieler.aktuellePalette.paletteNummer >= 10) {
                                    mainMinispielSpieler.bildschirmY -= minispielManager.sp.vergroesserteFliesenGroesse;
                                }
                                mainMinispielSpieler.bildschirmY -= minispielManager.sp.vergroesserteFliesenGroesse;

                            }
                        }
                        mainMinispielSpieler.minispielXPosition = mainMinispielSpieler.aktuellePalette.weltX;
                        mainMinispielSpieler.minispielYPosition = mainMinispielSpieler.aktuellePalette.weltY;
                        for (MinispielSpieler spieler : minispielManager.alleMinispielSpieler) {
                            if (spieler != null) {
                                spieler.bildschirmX = spieler.minispielXPosition - minispielManager.mainMinispielSpieler.minispielXPosition + minispielManager.mainMinispielSpieler.bildschirmX;
                                spieler.bildschirmY = spieler.minispielYPosition - minispielManager.mainMinispielSpieler.minispielYPosition + minispielManager.mainMinispielSpieler.bildschirmY;
                            }
                        }
                        SquidGamePosition squidGamePosition = new SquidGamePosition();
                        squidGamePosition.minispielXPosition = mainMinispielSpieler.minispielXPosition;
                        squidGamePosition.minispielYPosition = mainMinispielSpieler.minispielYPosition;
                        squidGamePosition.aktuellePalettenNr = mainMinispielSpieler.aktuellePalette.paletteNummer;
                        minispielManager.sp.client.send(squidGamePosition);

                        SquidGamePunkte squidGamePunkte = new SquidGamePunkte();
                        squidGamePunkte.punktZahl = mainMinispielSpieler.punktzahl;
                        minispielManager.sp.client.send(squidGamePunkte);
                    } else if (e.getKeyChar() == 'w') {
                        if (mainMinispielSpieler.aktuellePalette!=null&&mainMinispielSpieler.aktuellePalette.paletteNummer == 13 || mainMinispielSpieler.aktuellePalette.paletteNummer == 12) {
                            minispielManager.squidGame.endeErreichen();
                            mainMinispielSpieler.aktuellerZustand="gewonnen";
                        }
                    }
                    tippenErlaubt = false;
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (mainMinispielSpieler.amSpielen) {
            if (this.mainMinispielSpieler == minispielManager.mainMinispielSpieler) {
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    tippenErlaubt = true;

                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    tippenErlaubt = true;
                }
            }
        }
    }
}
