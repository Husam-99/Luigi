package Minispiele;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SquidGameEingabeManger implements KeyListener {
    boolean tippenErlaubt =true;
    MinispielSpieler mainMinispielSpieler;
    MinispielManager minispielManager;

    public SquidGameEingabeManger(MinispielManager m, MinispielSpieler mainSpieler) {
       this.minispielManager=m;
       this.mainMinispielSpieler=mainSpieler;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if(mainMinispielSpieler.amSpielen) {
            if (this.mainMinispielSpieler == minispielManager.mainMinispielSpieler) {
                if (tippenErlaubt && mainMinispielSpieler.aktuellerZustand.equals("stehen")) {
                    if (e.getKeyChar() == 'd') {
                        if (mainMinispielSpieler.aktuellePalette == null) {
                            mainMinispielSpieler.aktuellePalette = minispielManager.squidGame.paletten[1];
                            if (!mainMinispielSpieler.aktuellePalette.hatFalle) {
                                mainMinispielSpieler.punktzahl++;
                            }
                        } else {
                            mainMinispielSpieler.aktuellePalette = mainMinispielSpieler.aktuellePalette.naechsteRechts;
                            if (!mainMinispielSpieler.aktuellePalette.hatFalle) {
                                mainMinispielSpieler.punktzahl++;
                            }
                            if (mainMinispielSpieler.aktuellePalette.paletteNummer >= 8) {
                                if (mainMinispielSpieler.aktuellePalette.paletteNummer >= 10) {
                                    mainMinispielSpieler.bildschirmY -= minispielManager.sp.vergroesserteFliesenGroesse;
                                    for (MinispielSpieler spieler : minispielManager.alleMinispielSpieler) {
                                        if (spieler != null) {
                                            spieler.bildschirmY += minispielManager.sp.vergroesserteFliesenGroesse;
                                        }
                                    }
                                }
                                mainMinispielSpieler.bildschirmY -= minispielManager.sp.vergroesserteFliesenGroesse;
                                for (MinispielSpieler spieler : minispielManager.alleMinispielSpieler) {
                                    if (spieler != null) {
                                        spieler.bildschirmY += minispielManager.sp.vergroesserteFliesenGroesse;
                                    }
                                }
                            }
                        }
                        for (MinispielSpieler spieler : minispielManager.alleMinispielSpieler) {
                            if (spieler != null) {
                                spieler.bildschirmY += 2 * minispielManager.sp.vergroesserteFliesenGroesse;
                            }
                        }
                        mainMinispielSpieler.minispielXPosition = mainMinispielSpieler.aktuellePalette.weltX;
                        mainMinispielSpieler.minispielYPosition = mainMinispielSpieler.aktuellePalette.weltY;
                    } else if (e.getKeyChar() == 'a') {
                        if (mainMinispielSpieler.aktuellePalette == null) {
                            mainMinispielSpieler.aktuellePalette = minispielManager.squidGame.paletten[0];
                            if (!mainMinispielSpieler.aktuellePalette.hatFalle) {
                                mainMinispielSpieler.punktzahl++;
                            }
                        } else {
                            mainMinispielSpieler.aktuellePalette = mainMinispielSpieler.aktuellePalette.naechsteLinks;
                            if (!mainMinispielSpieler.aktuellePalette.hatFalle) {
                                mainMinispielSpieler.punktzahl++;
                            }
                            if (mainMinispielSpieler.aktuellePalette.paletteNummer >= 8) {
                                if (mainMinispielSpieler.aktuellePalette.paletteNummer >= 10) {

                                    mainMinispielSpieler.bildschirmY -= minispielManager.sp.vergroesserteFliesenGroesse;
                                    for (MinispielSpieler spieler : minispielManager.alleMinispielSpieler) {
                                        if (spieler != null) {
                                            spieler.bildschirmY += minispielManager.sp.vergroesserteFliesenGroesse;
                                        }
                                    }
                                }
                                mainMinispielSpieler.bildschirmY -= minispielManager.sp.vergroesserteFliesenGroesse;
                                for (MinispielSpieler spieler : minispielManager.alleMinispielSpieler) {
                                    if (spieler != null) {
                                        spieler.bildschirmY += minispielManager.sp.vergroesserteFliesenGroesse;
                                    }
                                }
                            }
                        }
                        for (MinispielSpieler spieler : minispielManager.alleMinispielSpieler) {
                            if (spieler != null) {
                                spieler.bildschirmY += 2 * minispielManager.sp.vergroesserteFliesenGroesse;
                            }
                        }
                        mainMinispielSpieler.minispielXPosition = mainMinispielSpieler.aktuellePalette.weltX;
                        mainMinispielSpieler.minispielYPosition = mainMinispielSpieler.aktuellePalette.weltY;
                    }
                    tippenErlaubt = false;
                }
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (mainMinispielSpieler.amSpielen) {
            if (this.mainMinispielSpieler == minispielManager.mainMinispielSpieler) {
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    if (mainMinispielSpieler.aktuellePalette != null && mainMinispielSpieler.aktuellePalette.paletteNummer == 13 && !mainMinispielSpieler.aktuellePalette.hatFalle) {
                        minispielManager.squidGame.endeErreichen();
                    } else {
                        tippenErlaubt = true;
                    }

                } else if (e.getKeyCode() == KeyEvent.VK_A) {


                    if (mainMinispielSpieler.aktuellePalette != null && mainMinispielSpieler.aktuellePalette.paletteNummer == 12 && !mainMinispielSpieler.aktuellePalette.hatFalle) {
                        minispielManager.squidGame.endeErreichen();
                    } else {
                        tippenErlaubt = true;
                    }


                }
            }
        }
    }
}