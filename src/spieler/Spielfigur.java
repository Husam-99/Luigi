package spieler;

import Networking.Pakete.Bescheid;
import Networking.Pakete.Bewegung;
import Networking.Pakete.SpielerPosition;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Spielfigur {
    Spieler spieler;
    public BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3, profile, wolke;
    public int spriteNum = 1, spriteZaehler = 0;
    Graphics2D g2;
    public String richtung = "stehen";
    Bewegung bewegung = new Bewegung();
    public Spielfigur(){}
    public Spielfigur(Spieler spieler){
        this.spieler = spieler;
        //Wolke Bild
        try {
            wolke= ImageIO.read(getClass().getResourceAsStream("/bestandteile/Wolke.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void getSpielFigurBilder() {}
    public void update(){
        if(spieler == spieler.spielablaufManager.mainSpieler) {
            if (!spieler.amSpiel) {
                Bescheid bescheid = new Bescheid();
                bescheid.fertig = true;
                spieler.spielablaufManager.sp.client.send(bescheid);
                spieler.amSpiel = true;
            } else if (spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben || spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten ||
                    spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungLinks || spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungRechts) {

                if (spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben) {
                    if (spieler.aktuellesFeld == null) {
                        spieler.aktuellesFeld = spieler.spielablaufManager.mapManager.mapFliesen[19][11].feld;
                        spieler.weltY -= spieler.geschwindigkeit;
                        spieler.weltX += spieler.geschwindigkeit;
                        for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                            andererSpieler.bildschirmY += spieler.geschwindigkeit;
                            andererSpieler.bildschirmX -= spieler.geschwindigkeit;
                        }
                    }
                    if (spieler.naechstesFeld == null) {
                        spieler.naechstesFeld = spieler.aktuellesFeld.nordFeld;
                    }

                    if (spieler.naechstesFeld != null) {
                        if (spieler.naechstesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[19][11].feld)) {
                            if(spieler.spielfigur instanceof Abdo || spieler.spielfigur instanceof Husam) {
                                if (spieler.spielablaufManager.mainSpieler.weltX < spieler.naechstesFeld.weltX) {
                                    rechtsUpdate();
                                } else if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                    obenUpdate();
                                } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                    stehenUpdate();
                                    spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                                }
                            } else if (spieler.spielfigur instanceof Taha || spieler.spielfigur instanceof Yousef) {
                                if (spieler.spielablaufManager.mainSpieler.weltX > spieler.naechstesFeld.weltX) {
                                    linksUpdate();
                                } else if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                    obenUpdate();
                                } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                    stehenUpdate();
                                    spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                                }
                            }
                        }else if (spieler.naechstesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[6][14].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltX > spieler.naechstesFeld.weltX) {
                                linksUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                obenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                            }
                        } else if (spieler.naechstesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[6][26].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltX < spieler.naechstesFeld.weltX) {
                                rechtsUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                obenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                            }
                        } else if (spieler.naechstesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[6][9].feld) || spieler.naechstesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[3][17].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                obenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltX < (spieler.naechstesFeld.weltX)) {
                                rechtsUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                            }
                        } else if (spieler.naechstesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[3][23].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                obenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltX > spieler.naechstesFeld.weltX) {
                                linksUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                            }
                        } else if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                            obenUpdate();
                        } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                            stehenUpdate();
                            System.out.println("when equal");
                            System.out.println("aktuelles: " +spieler.aktuellesFeld);
                            System.out.println("naechstes: " +spieler.naechstesFeld);
                            System.out.println("aktuell: "+ spieler.aktuellFeld);
                            System.out.println("temp: " + spieler.tempFeld);
                            spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                        }
                        koordinatenSchicken();
                    }
                } else if (spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten) {
                    if (spieler.naechstesFeld == null) {
                        spieler.naechstesFeld = spieler.aktuellesFeld.suedFeld;
                    }

                    if (spieler.naechstesFeld != null) {
                        if (spieler.naechstesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[7][15].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltY < (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                untenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltX < (spieler.naechstesFeld.weltX)) {
                                rechtsUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten = false;
                            }
                        } else if (spieler.naechstesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[4][24].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltX < (spieler.naechstesFeld.weltX)) {
                                rechtsUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY < (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                untenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten = false;
                            }
                        } else if (spieler.naechstesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[4][16].feld) || spieler.naechstesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[7][8].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltX > (spieler.naechstesFeld.weltX)) {
                                linksUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY < (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                untenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten = false;
                            }
                        } else if (spieler.naechstesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[7][25].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltY < (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                untenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltX > (spieler.naechstesFeld.weltX)) {
                                linksUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten = false;
                            }
                        } else if (spieler.spielablaufManager.mainSpieler.weltY < (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                            untenUpdate();
                        } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.naechstesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                            stehenUpdate();
                            System.out.println("when equal");
                            System.out.println("aktuelles: " +spieler.aktuellesFeld);
                            System.out.println("naechstes: " +spieler.naechstesFeld);
                            System.out.println("aktuell: "+ spieler.aktuellFeld);
                            System.out.println("temp: " + spieler.tempFeld);

                            spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten = false;
                        }
                        koordinatenSchicken();

                    }
                } else if (spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungLinks) {
                    if (spieler.naechstesFeld == null) {
                        spieler.naechstesFeld = spieler.aktuellesFeld.westFeld;
                    }

                    if (spieler.naechstesFeld != null) {
                        if (spieler.spielablaufManager.mainSpieler.weltX > (spieler.naechstesFeld.weltX)) {
                            linksUpdate();
                        } else if (spieler.spielablaufManager.mainSpieler.weltX == (spieler.naechstesFeld.weltX)) {
                            stehenUpdate();
                            System.out.println("when equal");
                            System.out.println("aktuelles: " +spieler.aktuellesFeld);
                            System.out.println("naechstes: " +spieler.naechstesFeld);
                            System.out.println("aktuell: "+ spieler.aktuellFeld);
                            System.out.println("temp: " + spieler.tempFeld);

                            spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungLinks = false;
                        }
                        koordinatenSchicken();
                    }
                } else if (spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungRechts) {
                    if (spieler.naechstesFeld == null) {
                        spieler.naechstesFeld = spieler.aktuellesFeld.ostFeld;
                    }

                    if (spieler.naechstesFeld != null) {
                        if (spieler.spielablaufManager.mainSpieler.weltX < (spieler.naechstesFeld.weltX)) {
                            rechtsUpdate();
                        } else if (spieler.spielablaufManager.mainSpieler.weltX == (spieler.naechstesFeld.weltX)) {
                            stehenUpdate();
                            System.out.println("when equal");
                            System.out.println("aktuelles: " +spieler.aktuellesFeld);
                            System.out.println("naechstes: " +spieler.naechstesFeld);
                            System.out.println("aktuell: "+ spieler.aktuellFeld);
                            System.out.println("temp: " + spieler.tempFeld);

                            spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungRechts = false;
                        }
                       koordinatenSchicken();
                    }
                }
                bilderUpdate();
            } else if(spieler.zuStern){
                bewegung.zustern = true;
                spieler.spielablaufManager.sp.client.send(bewegung);
                if(spieler.spielablaufManager.mainSpieler.weltY < spieler.spielablaufManager.mapManager.mapFliesen[spieler.spielablaufManager.mapManager.stern.sternFeldZeile][spieler.spielablaufManager.mapManager.stern.sternFeldSpalte].feld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2){
                    spieler.spielablaufManager.mainSpieler.weltY += spieler.geschwindigkeit;
                    if (!spieler.spielablaufManager.sp.alleSpieler.isEmpty())
                        for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                            andererSpieler.bildschirmY -= spieler.geschwindigkeit;
                        }
                }else if(spieler.spielablaufManager.mainSpieler.weltY > spieler.spielablaufManager.mapManager.mapFliesen[spieler.spielablaufManager.mapManager.stern.sternFeldZeile][spieler.spielablaufManager.mapManager.stern.sternFeldSpalte].feld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2){
                    spieler.spielablaufManager.mainSpieler.weltY -= spieler.geschwindigkeit;
                    if (!spieler.spielablaufManager.sp.alleSpieler.isEmpty())
                        for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                            andererSpieler.bildschirmY += spieler.geschwindigkeit;
                        }
                } else if(spieler.spielablaufManager.mainSpieler.weltX > spieler.spielablaufManager.mapManager.mapFliesen[spieler.spielablaufManager.mapManager.stern.sternFeldZeile][spieler.spielablaufManager.mapManager.stern.sternFeldSpalte].feld.weltX){
                    spieler.spielablaufManager.mainSpieler.weltX -= spieler.geschwindigkeit;
                    if (!spieler.spielablaufManager.sp.alleSpieler.isEmpty())
                        for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                            andererSpieler.bildschirmX += spieler.geschwindigkeit;
                        }
                }else if(spieler.spielablaufManager.mainSpieler.weltX < spieler.spielablaufManager.mapManager.mapFliesen[spieler.spielablaufManager.mapManager.stern.sternFeldZeile][spieler.spielablaufManager.mapManager.stern.sternFeldSpalte].feld.weltX){
                    spieler.spielablaufManager.mainSpieler.weltX += spieler.geschwindigkeit;
                    if (!spieler.spielablaufManager.sp.alleSpieler.isEmpty())
                        for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                            andererSpieler.bildschirmX -= spieler.geschwindigkeit;
                        }
                }else if(spieler.spielablaufManager.mainSpieler.weltX == spieler.spielablaufManager.mapManager.mapFliesen[spieler.spielablaufManager.mapManager.stern.sternFeldZeile][spieler.spielablaufManager.mapManager.stern.sternFeldSpalte].feld.weltX){
                    spieler.zuStern = false;
                    spieler.bewegung = false;
                    bewegung.zustern = false;
                    spieler.spielablaufManager.sp.client.send(bewegung);
                    spieler.aktuellFeld.effeckteAnwenden();
                }
                koordinatenSchicken();
            }
        }else {
            bilderUpdate();
        }
    }
    private void obenUpdate(){
        spieler.spielablaufManager.mainSpieler.weltY -= spieler.geschwindigkeit;
        richtung = "oben";
        bewegung.richtung = "oben";
        spieler.spielablaufManager.sp.client.send(bewegung);
        if (!spieler.spielablaufManager.sp.alleSpieler.isEmpty())
            for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                andererSpieler.bildschirmY += spieler.geschwindigkeit;
            }
    }
    private void untenUpdate(){
        spieler.spielablaufManager.mainSpieler.weltY += spieler.geschwindigkeit;
        richtung = "unten";
        bewegung.richtung = "unten";
        spieler.spielablaufManager.sp.client.send(bewegung);
        if (!spieler.spielablaufManager.sp.alleSpieler.isEmpty())
            for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                andererSpieler.bildschirmY -= spieler.geschwindigkeit;
            }
    }
    private void rechtsUpdate(){
        spieler.spielablaufManager.mainSpieler.weltX += spieler.geschwindigkeit;
        richtung = "rechts";
        bewegung.richtung = "rechts";
        spieler.spielablaufManager.sp.client.send(bewegung);
        if (!spieler.spielablaufManager.sp.alleSpieler.isEmpty())
            for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                andererSpieler.bildschirmX -= spieler.geschwindigkeit;
            }
    }
    private void linksUpdate(){
        spieler.spielablaufManager.mainSpieler.weltX -= spieler.geschwindigkeit;
        richtung = "links";
        bewegung.richtung = "links";
        spieler.spielablaufManager.sp.client.send(bewegung);
        if (!spieler.spielablaufManager.sp.alleSpieler.isEmpty())
            for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                andererSpieler.bildschirmX += spieler.geschwindigkeit;
            }
    }
    private void stehenUpdate(){
        spieler.bewegung = false;
        richtung = "stehen";
        bewegung.richtung = "stehen";
        spieler.aktuellFeld = spieler.naechstesFeld;
        spieler.spielablaufManager.sp.client.send(bewegung);
        if (spieler.schritteAnzahl == 0) {
            spieler.naechstesFeld.effeckteAnwenden();
        }
    }
    private void bilderUpdate(){
        spriteZaehler++;
        if (spriteZaehler > 6) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 1;
            }
            spriteZaehler = 0;
        }
    }
    private void koordinatenSchicken(){
        SpielerPosition spielerPosition = new SpielerPosition();
        spielerPosition.weltX = spieler.weltX;
        spielerPosition.weltY = spieler.weltY;
        spieler.spielablaufManager.sp.client.send(spielerPosition);
    }

    public void malen(Graphics2D g2){
       this.g2 = g2;
       spielfigureMalen();
    }
    private void spielfigureMalen(){
        BufferedImage image = null;
        switch(richtung) {
            case "oben":
                if(spriteNum == 1 ||spriteNum ==3) {
                    image = up1;
                }
                else if(spriteNum == 2) {
                    image = up2;
                }
                else if(spriteNum == 4) {
                    image = up3;
                }
                break;
            case "unten":
                if(spriteNum == 1 || spriteNum == 3) {
                    image = down1;
                }
                else if(spriteNum == 2) {
                    image = down2;
                }
                else if(spriteNum == 4) {
                    image = down3;
                }
                break;
            case "links":
                if(spriteNum == 1 || spriteNum == 3) {
                    image = left1;
                }
                else if(spriteNum == 2) {
                    image = left2;
                }
                else if(spriteNum == 4) {
                    image = left3;
                }
                break;
            case "rechts":
                if(spriteNum == 1 || spriteNum == 3) {
                    image = right1;
                }
                else if(spriteNum == 2) {
                    image = right2;
                }
                else if(spriteNum == 4) {
                    image = right3;
                }
                break;
            case "stehen":
                image = down1;
                break;
        }
        if(spieler.zuStern){
            g2.drawImage(wolke, this.spieler.bildschirmX, this.spieler.bildschirmY+30, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse, null);
        }
        g2.drawImage(image, this.spieler.bildschirmX, this.spieler.bildschirmY, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse, null);
    }
}
