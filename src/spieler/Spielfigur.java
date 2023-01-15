package spieler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Spielfigur {
    Spieler s;
    public BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3, profile;
    int spriteNum = 1;
    int spriteZaehler = 0;
    public String richtung = "unten";
    public Spielfigur(Spieler s){
        this.s = s;
    }
    public void getSpielFigurBilder() {}
    public void update(){
        if(s.sp.mapManager.mapEingabeManager.bewegungOben || s.sp.mapManager.mapEingabeManager.bewegungUnten ||
                s.sp.mapManager.mapEingabeManager.bewegungLinks || s.sp.mapManager.mapEingabeManager.bewegungRechts){


            if(s.sp.mapManager.mapEingabeManager.bewegungOben){
                if(s.aktuellesFeld == null){
                    s.aktuellesFeld = s.sp.mapManager.mapFliesen[19][11].feld;
                }
                if (s.naechstesFeld == null) {
                    s.naechstesFeld = s.aktuellesFeld.nordFeld;
                }

                if (s.naechstesFeld != null) {
                    if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[6][14].feld)) {
                        if (s.sp.alleSpieler.get(0).weltX > s.naechstesFeld.weltX) {
                            richtung = "links";
                            s.sp.alleSpieler.get(0).weltX -= s.geschwindigkeit;
                        }else if (s.sp.alleSpieler.get(0).weltY > (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.sp.alleSpieler.get(0).weltY -= s.geschwindigkeit;
                            richtung = "oben";
                        }else if (s.sp.alleSpieler.get(0).weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungOben = false;
                        }
                    }else if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[6][26].feld)) {
                        if (s.sp.alleSpieler.get(0).weltX < s.naechstesFeld.weltX) {
                            richtung = "rechts";
                            s.sp.alleSpieler.get(0).weltX += s.geschwindigkeit;
                        }else if (s.sp.alleSpieler.get(0).weltY > (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            richtung = "oben";
                            s.sp.alleSpieler.get(0).weltY -= s.geschwindigkeit;
                        }else if (s.sp.alleSpieler.get(0).weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungOben = false;
                        }
                    }else if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[6][9].feld) || s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[3][17].feld)) {
                        if (s.sp.alleSpieler.get(0).weltY > (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            richtung = "oben";
                            s.sp.alleSpieler.get(0).weltY -= s.geschwindigkeit;
                        }else if (s.sp.alleSpieler.get(0).weltX < (s.naechstesFeld.weltX)) {
                            richtung = "rechts";
                            s.sp.alleSpieler.get(0).weltX += s.geschwindigkeit;
                        }else if (s.sp.alleSpieler.get(0).weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungOben = false;
                        }
                    }else if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[3][23].feld)) {
                        if (s.sp.alleSpieler.get(0).weltY > (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            richtung = "oben";
                            s.sp.alleSpieler.get(0).weltY -= s.geschwindigkeit;
                        }else if (s.sp.alleSpieler.get(0).weltX > s.naechstesFeld.weltX) {
                            richtung = "links";
                            s.sp.alleSpieler.get(0).weltX -= s.geschwindigkeit;
                        }else if (s.sp.alleSpieler.get(0).weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungOben = false;
                        }
                    }else if (s.sp.alleSpieler.get(0).weltY > (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                        richtung = "oben";
                        s.sp.alleSpieler.get(0).weltY -= s.geschwindigkeit;
                    }else if (s.sp.alleSpieler.get(0).weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                        s.bewegung = false;
                        richtung = "stehen";
                        s.sp.mapManager.mapEingabeManager.bewegungOben = false;
                    }

                }
            }else if (s.sp.mapManager.mapEingabeManager.bewegungUnten){
                if (s.naechstesFeld == null) {
                    s.naechstesFeld = s.aktuellesFeld.suedFeld;
                }

                if (s.naechstesFeld != null) {
                    if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[7][15].feld)) {
                        if (s.sp.alleSpieler.get(0).weltY < (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.sp.alleSpieler.get(0).weltY += s.geschwindigkeit;
                            richtung = "unten";
                        }else if (s.sp.alleSpieler.get(0).weltX < (s.naechstesFeld.weltX)) {
                            richtung = "rechts";
                            s.sp.alleSpieler.get(0).weltX += s.geschwindigkeit;
                        }else if (s.sp.alleSpieler.get(0).weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungUnten = false;
                        }
                    }else if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[4][24].feld)) {
                        if (s.sp.alleSpieler.get(0).weltX < (s.naechstesFeld.weltX)) {
                            richtung = "rechts";
                            s.sp.alleSpieler.get(0).weltX += s.geschwindigkeit;
                        }else if (s.sp.alleSpieler.get(0).weltY < (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.sp.alleSpieler.get(0).weltY += s.geschwindigkeit;
                            richtung = "unten";
                        }else if (s.sp.alleSpieler.get(0).weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungUnten = false;
                        }
                    }else if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[4][16].feld) || s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[7][8].feld)) {
                        if (s.sp.alleSpieler.get(0).weltX > (s.naechstesFeld.weltX)) {
                            richtung = "links";
                            s.sp.alleSpieler.get(0).weltX -= s.geschwindigkeit;
                        }else if (s.sp.alleSpieler.get(0).weltY < (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.sp.alleSpieler.get(0).weltY += s.geschwindigkeit;
                            richtung = "unten";
                        }else if (s.sp.alleSpieler.get(0).weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungUnten = false;
                        }
                    }else if (s.naechstesFeld.equals(s.sp.mapManager.mapFliesen[7][25].feld)) {
                        if (s.sp.alleSpieler.get(0).weltY < (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.sp.alleSpieler.get(0).weltY += s.geschwindigkeit;
                            richtung = "unten";
                        }else if (s.sp.alleSpieler.get(0).weltX > (s.naechstesFeld.weltX)) {
                            richtung = "links";
                            s.sp.alleSpieler.get(0).weltX -= s.geschwindigkeit;
                        }else if (s.sp.alleSpieler.get(0).weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                            s.bewegung = false;
                            richtung = "stehen";
                            s.sp.mapManager.mapEingabeManager.bewegungUnten = false;
                        }
                    }else if (s.sp.alleSpieler.get(0).weltY < (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                        s.sp.alleSpieler.get(0).weltY += s.geschwindigkeit;
                        richtung = "unten";
                    }else if (s.sp.alleSpieler.get(0).weltY == (s.naechstesFeld.weltY - s.sp.vergroesserteFliesenGroesse / 2)) {
                        s.bewegung = false;
                        richtung = "stehen";
                        s.sp.mapManager.mapEingabeManager.bewegungUnten = false;
                    }
                }
            }else if (s.sp.mapManager.mapEingabeManager.bewegungLinks){
                if (s.naechstesFeld == null) {
                    s.naechstesFeld = s.aktuellesFeld.westFeld;
                }

                if (s.naechstesFeld != null) {
                    if (s.sp.alleSpieler.get(0).weltX > (s.naechstesFeld.weltX)) {
                        s.sp.alleSpieler.get(0).weltX -= s.geschwindigkeit;
                        richtung = "links";
                    }else if (s.sp.alleSpieler.get(0).weltX == (s.naechstesFeld.weltX)) {
                        s.bewegung = false;
                        richtung = "stehen";
                        s.sp.mapManager.mapEingabeManager.bewegungLinks = false;
                    }
                }
            }else if(s.sp.mapManager.mapEingabeManager.bewegungRechts){
                if (s.naechstesFeld == null) {
                    s.naechstesFeld = s.aktuellesFeld.ostFeld;
                }

                if (s.naechstesFeld != null) {
                    if (s.sp.alleSpieler.get(0).weltX < (s.naechstesFeld.weltX)) {
                        s.sp.alleSpieler.get(0).weltX += s.geschwindigkeit;
                        richtung = "rechts";
                    }else if (s.sp.alleSpieler.get(0).weltX == (s.naechstesFeld.weltX)) {
                        s.bewegung = false;
                        richtung = "stehen";
                        s.sp.mapManager.mapEingabeManager.bewegungRechts = false;
                    }
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
        }if(this.s == s.sp.mainSpieler ){
            g2.drawImage(image, s.sp.mainSpieler.bildschirmX, s.sp.mainSpieler.bildschirmY, s.sp.vergroesserteFliesenGroesse, s.sp.vergroesserteFliesenGroesse, null);
        }else if(s.sp.alleSpieler.contains(this.s)){
            g2.drawImage(image, this.s.bildschirmX, this.s.bildschirmY, s.sp.vergroesserteFliesenGroesse, s.sp.vergroesserteFliesenGroesse, null);
        }

    }
}
