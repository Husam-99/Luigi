package spieler;

import Networking.Pakete.Bescheid;
import Networking.Pakete.SpielerPosition;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Spielfigur {
    Spieler s;
    public BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3, profile;
    int spriteNum = 1, spriteZaehler = 0;
    Graphics2D g2;
    public String richtung = "unten";
    public Spielfigur(Spieler s){
        this.s = s;
    }
    public void getSpielFigurBilder() {}
    public void update(){
        if(!s.amSpiel) {
            Bescheid bescheid = new Bescheid();
            bescheid.fertig = true;
            s.sp.client.send(bescheid);
            s.amSpiel = true;
        }else if(s.sp.mapManager.mapEingabeManager.bewegungOben || s.sp.mapManager.mapEingabeManager.bewegungUnten ||
                s.sp.mapManager.mapEingabeManager.bewegungLinks || s.sp.mapManager.mapEingabeManager.bewegungRechts){

            if(s.sp.mapManager.mapEingabeManager.bewegungOben){
                if(s.aktuellesFeld == null){
                    s.aktuellesFeld = s.sp.mapManager.mapFliesen[19][11].feld;
                    s.weltY -= s.sp.vergroesserteFliesenGroesse / 2;
                    s.weltX += s.sp.vergroesserteFliesenGroesse / 2;
                    for(Spieler andererSpieler : s.sp.alleSpieler){
                        andererSpieler.bildschirmY += s.sp.vergroesserteFliesenGroesse / 2;
                        andererSpieler.bildschirmX -= s.sp.vergroesserteFliesenGroesse / 2;
                    }
                }
                if (s.naechstesFeld == null) {
                    s.naechstesFeld = s.aktuellesFeld.nordFeld;
                }

                if (s.naechstesFeld != null) {
                    if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[6][14].feld)) {
                        if (s.sp.mainSpieler.weltX > s.naechstesFeld.weltX) {
                            richtung = "links";
                            s.sp.mainSpieler.weltX -= s.geschwindigkeit;
                        }else if (s.sp.mainSpieler.weltY > (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.sp.mainSpieler.weltY -= s.geschwindigkeit;
                            richtung = "oben";
                        }else if (s.sp.mainSpieler.weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungOben = false;
                            if (s.schritteAnzahl == 0) {
                                s.aktuellFeld.effeckteAnwenden();
                            }
                        }
                    }else if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[6][26].feld)) {
                        if (s.sp.mainSpieler.weltX < s.naechstesFeld.weltX) {
                            richtung = "rechts";
                            s.sp.mainSpieler.weltX += s.geschwindigkeit;
                        }else if (s.sp.mainSpieler.weltY > (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            richtung = "oben";
                            s.sp.mainSpieler.weltY -= s.geschwindigkeit;
                        }else if (s.sp.mainSpieler.weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungOben = false;
                            if (s.schritteAnzahl == 0) {
                                s.aktuellFeld.effeckteAnwenden();
                            }
                        }
                    }else if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[6][9].feld) || s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[3][17].feld)) {
                        if (s.sp.mainSpieler.weltY > (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            richtung = "oben";
                            s.sp.mainSpieler.weltY -= s.geschwindigkeit;
                        }else if (s.sp.mainSpieler.weltX < (s.naechstesFeld.weltX)) {
                            richtung = "rechts";
                            s.sp.mainSpieler.weltX += s.geschwindigkeit;
                        }else if (s.sp.mainSpieler.weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungOben = false;
                            if (s.schritteAnzahl == 0) {
                                s.aktuellFeld.effeckteAnwenden();
                            }
                        }
                    }else if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[3][23].feld)) {
                        if (s.sp.mainSpieler.weltY > (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            richtung = "oben";
                            s.sp.mainSpieler.weltY -= s.geschwindigkeit;
                        }else if (s.sp.mainSpieler.weltX > s.naechstesFeld.weltX) {
                            richtung = "links";
                            s.sp.mainSpieler.weltX -= s.geschwindigkeit;
                        }else if (s.sp.mainSpieler.weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungOben = false;
                            if (s.schritteAnzahl == 0) {
                                s.aktuellFeld.effeckteAnwenden();
                            }
                        }
                    }else if (s.sp.mainSpieler.weltY > (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                        richtung = "oben";
                        s.sp.mainSpieler.weltY -= s.geschwindigkeit;
                        if(!s.sp.alleSpieler.isEmpty())
                            for(Spieler andererSpieler : s.sp.alleSpieler){
                                andererSpieler.bildschirmY += s.geschwindigkeit;
                            }
                    }else if (s.sp.mainSpieler.weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                        s.bewegung = false;
                        richtung = "stehen";
                        s.sp.mapManager.mapEingabeManager.bewegungOben = false;
                        if (s.schritteAnzahl == 0) {
                            s.aktuellFeld.effeckteAnwenden();
                        }
                    }
                    SpielerPosition spielerPosition = new SpielerPosition();
                    spielerPosition.weltX = s.weltX;
                    spielerPosition.weltY = s.weltY - s.sp.vergroesserteFliesenGroesse/2;


                    s.sp.client.send(spielerPosition);
                }
            }else if (s.sp.mapManager.mapEingabeManager.bewegungUnten){
                if (s.naechstesFeld == null) {
                    s.naechstesFeld = s.aktuellesFeld.suedFeld;
                }

                if (s.naechstesFeld != null) {
                    if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[7][15].feld)) {
                        if (s.sp.mainSpieler.weltY < (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.sp.mainSpieler.weltY += s.geschwindigkeit;
                            richtung = "unten";
                        }else if (s.sp.mainSpieler.weltX < (s.naechstesFeld.weltX)) {
                            richtung = "rechts";
                            s.sp.mainSpieler.weltX += s.geschwindigkeit;
                        }else if (s.sp.mainSpieler.weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungUnten = false;
                            if (s.schritteAnzahl == 0) {
                                s.aktuellFeld.effeckteAnwenden();
                            }
                        }
                    }else if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[4][24].feld)) {
                        if (s.sp.mainSpieler.weltX < (s.naechstesFeld.weltX)) {
                            richtung = "rechts";
                            s.sp.mainSpieler.weltX += s.geschwindigkeit;
                        }else if (s.sp.mainSpieler.weltY < (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.sp.mainSpieler.weltY += s.geschwindigkeit;
                            richtung = "unten";
                        }else if (s.sp.mainSpieler.weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungUnten = false;
                            if (s.schritteAnzahl == 0) {
                                s.aktuellFeld.effeckteAnwenden();
                            }
                        }
                    }else if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[4][16].feld) || s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[7][8].feld)) {
                        if (s.sp.mainSpieler.weltX > (s.naechstesFeld.weltX)) {
                            richtung = "links";
                            s.sp.mainSpieler.weltX -= s.geschwindigkeit;
                        }else if (s.sp.mainSpieler.weltY < (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.sp.mainSpieler.weltY += s.geschwindigkeit;
                            richtung = "unten";
                        }else if (s.sp.mainSpieler.weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungUnten = false;
                            if (s.schritteAnzahl == 0) {
                                s.aktuellFeld.effeckteAnwenden();
                            }
                        }
                    }else if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[7][25].feld)) {
                        if (s.sp.mainSpieler.weltY < (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.sp.mainSpieler.weltY += s.geschwindigkeit;
                            richtung = "unten";
                        }else if (s.sp.mainSpieler.weltX > (s.naechstesFeld.weltX)) {
                            richtung = "links";
                            s.sp.mainSpieler.weltX -= s.geschwindigkeit;
                        }else if (s.sp.mainSpieler.weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungUnten = false;
                            if (s.schritteAnzahl == 0) {
                                s.aktuellFeld.effeckteAnwenden();
                            }
                        }
                    }else if (s.sp.mainSpieler.weltY < (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                        s.sp.mainSpieler.weltY += s.geschwindigkeit;
                        richtung = "unten";
                        if(!s.sp.alleSpieler.isEmpty())
                            for(Spieler andererSpieler : s.sp.alleSpieler){
                               andererSpieler.bildschirmY -= s.geschwindigkeit;
                            }
                    }else if (s.sp.mainSpieler.weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                        s.bewegung = false;
                        richtung = "stehen";
                        s.sp.mapManager.mapEingabeManager.bewegungUnten = false;
                        if (s.schritteAnzahl == 0) {
                            s.aktuellFeld.effeckteAnwenden();
                        }
                    }
                    SpielerPosition spielerPosition = new SpielerPosition();
                    spielerPosition.weltX = s.weltX;
                    spielerPosition.weltY = s.weltY - s.sp.vergroesserteFliesenGroesse/2;

                    s.sp.client.send(spielerPosition);

                }
            }else if (s.sp.mapManager.mapEingabeManager.bewegungLinks){
                if (s.naechstesFeld == null) {
                    s.naechstesFeld = s.aktuellesFeld.westFeld;
                }

                if (s.naechstesFeld != null) {
                    if (s.sp.mainSpieler.weltX > (s.naechstesFeld.weltX)) {
                        s.sp.mainSpieler.weltX -= s.geschwindigkeit;
                        richtung = "links";
                        if(!s.sp.alleSpieler.isEmpty())
                            for(Spieler andererSpieler : s.sp.alleSpieler){
                                andererSpieler.bildschirmX += s.geschwindigkeit;

                            }
                    }else if (s.sp.mainSpieler.weltX == (s.naechstesFeld.weltX)) {
                        s.bewegung = false;
                        richtung = "stehen";
                        s.sp.mapManager.mapEingabeManager.bewegungLinks = false;
                        if (s.schritteAnzahl == 0) {
                            s.aktuellFeld.effeckteAnwenden();
                        }
                    }
                    SpielerPosition spielerPosition = new SpielerPosition();
                    spielerPosition.weltX = s.weltX;
                    spielerPosition.weltY = s.weltY - s.sp.vergroesserteFliesenGroesse/2;

                    s.sp.client.send(spielerPosition);
                }
            }else if(s.sp.mapManager.mapEingabeManager.bewegungRechts){
                if (s.naechstesFeld == null) {
                    s.naechstesFeld = s.aktuellesFeld.ostFeld;
                }

                if (s.naechstesFeld != null) {
                    if (s.sp.mainSpieler.weltX < (s.naechstesFeld.weltX)) {
                        s.sp.mainSpieler.weltX += s.geschwindigkeit;
                        richtung = "rechts";
                        if(!s.sp.alleSpieler.isEmpty())
                           for(Spieler andererSpieler:s.sp.alleSpieler){
                               andererSpieler.bildschirmX -= s.geschwindigkeit;
                           }
                    }else if (s.sp.mainSpieler.weltX == (s.naechstesFeld.weltX)) {
                        s.bewegung = false;
                        richtung = "stehen";
                        s.sp.mapManager.mapEingabeManager.bewegungRechts = false;
                        if (s.schritteAnzahl == 0) {
                            s.aktuellFeld.effeckteAnwenden();
                        }
                    }
                    SpielerPosition spielerPosition = new SpielerPosition();
                    spielerPosition.weltX = s.weltX;
                    spielerPosition.weltY = s.weltY - s.sp.vergroesserteFliesenGroesse/2;

                    s.sp.client.send(spielerPosition);
                }
            }
            spriteZaehler++;
            if(spriteZaehler > 6){
                if(spriteNum == 1){
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
                if(spriteNum == 2) {
                    image = up2;
                }
                if(spriteNum == 4) {
                    image = up3;
                }
                break;
            case "unten":
                if(spriteNum == 1 || spriteNum == 3) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                if(spriteNum == 4) {
                    image = down3;
                }
                break;
            case "links":
                if(spriteNum == 1 || spriteNum == 3) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                if(spriteNum == 4) {
                    image = left3;
                }
                break;
            case "rechts":
                if(spriteNum == 1 || spriteNum == 3) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                if(spriteNum == 4) {
                    image = right3;
                }
                break;
            case "stehen":
                image = down1;
                break;
        }
        g2.drawImage(image, this.s.bildschirmX, this.s.bildschirmY, s.sp.vergroesserteFliesenGroesse, s.sp.vergroesserteFliesenGroesse, null);
    }

}

