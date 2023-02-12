package spieler;

import Networking.Pakete.Bescheid;
import Networking.Pakete.Bewegung;
import Networking.Pakete.SpielerPosition;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Spielfigur {

    Spieler spieler;
    Graphics2D g2;

    public BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3, profile;

    //für das gelbe Feld und Sterntaxi
    public BufferedImage wolke;

    //für Squid Game Minispiel
    public BufferedImage fallen1, fallen2, fallen3, fallen4, fallen5;

    public int spriteNum, spriteZaehler;
    public String richtung;

    Bewegung bewegung;

    public Spielfigur(){
        spriteNum = 1;
        spriteZaehler = 0;
    }

    public Spielfigur(Spieler spieler){
        this.spieler = spieler;
        richtung = "stehen";
        spriteNum = 1;
        spriteZaehler = 0;
        bewegung = new Bewegung();

        //Wolke Bild
        try {
            wolke= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bestandteile/Wolke.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void getSpielFigurBilder() {}

    public void update(){

        //Main Spieler update
        if(spieler == spieler.spielablaufManager.mainSpieler) {

            //Spieler zug ist fertig an allen Clients senden
            if (!spieler.amSpiel) {
                Bescheid bescheid = new Bescheid();
                bescheid.fertig = true;
                spieler.spielablaufManager.sp.client.send(bescheid);
                spieler.amSpiel = true;
            }

            //Main Spieler bewegung zwischen den Feldern
            else if (spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben || spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten ||
                    spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungLinks || spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungRechts) {

                //Bewegung oben
                if (spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben) {

                    if (spieler.vorherigesFeld == null) {

                        //hier setze das vorherige Feld auf dem ersten Feld auf dem Map
                        spieler.vorherigesFeld = spieler.spielablaufManager.mapManager.mapFliesen[19][11].feld;

                        spieler.weltY -= spieler.geschwindigkeit;
                        spieler.weltX += spieler.geschwindigkeit;

                        //alle Spieler x und y anpassen
                        for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                            andererSpieler.bildschirmY += spieler.geschwindigkeit;
                            andererSpieler.bildschirmX -= spieler.geschwindigkeit;
                        }
                    }

                    if (spieler.aktuellesFeld == null) {

                        //neues aktuelles Feld an alle Clients setzen und senden
                        spieler.aktuellesFeld = spieler.vorherigesFeld.nordFeld;
                        bewegung.feldNum = spieler.aktuellesFeld.feldNum;
                        spieler.spielablaufManager.sp.client.send(bewegung);
                    }

                    if (spieler.aktuellesFeld != null) {

                        //Speziell Fall für erste Bewegung nach dem ersten Feld
                        if (spieler.aktuellesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[19][11].feld)) {
                            if(spieler.spielfigur instanceof Abdo || spieler.spielfigur instanceof Husam) {
                                if (spieler.spielablaufManager.mainSpieler.weltX < spieler.aktuellesFeld.weltX) {
                                    rechtsUpdate();
                                } else if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                    obenUpdate();
                                } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                    stehenUpdate();
                                    spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                                }
                            } else if (spieler.spielfigur instanceof Taha || spieler.spielfigur instanceof Yousef) {
                                if (spieler.spielablaufManager.mainSpieler.weltX > spieler.aktuellesFeld.weltX) {
                                    linksUpdate();
                                } else if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                    obenUpdate();
                                } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                    stehenUpdate();
                                    spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                                }
                            }

                        }

                        //Spezielle Fälle für den Ecken auf dem Spielbrett
                        else if (spieler.aktuellesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[6][14].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltX > spieler.aktuellesFeld.weltX) {
                                linksUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                obenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                            }
                        } else if (spieler.aktuellesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[6][26].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltX < spieler.aktuellesFeld.weltX) {
                                rechtsUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                obenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                            }
                        } else if (spieler.aktuellesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[6][9].feld) || spieler.aktuellesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[3][17].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                obenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltX < (spieler.aktuellesFeld.weltX)) {
                                rechtsUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                            }
                        } else if (spieler.aktuellesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[3][23].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                obenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltX > spieler.aktuellesFeld.weltX) {
                                linksUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                            }
                        }

                        //Bewegung nach oben
                        else if (spieler.spielablaufManager.mainSpieler.weltY > (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                            obenUpdate();
                        }

                        //stehen, wenn der main Spieler auf dem neuen Feld gelandet ist
                        else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                            stehenUpdate();
                            spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungOben = false;
                        }

                        koordinatenSchicken();
                    }

                }

                //Bewegung unten
                else if (spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten) {

                    if (spieler.aktuellesFeld == null) {

                        //neues aktuelles Feld an alle Clients setzen und senden
                        spieler.aktuellesFeld = spieler.vorherigesFeld.suedFeld;
                        bewegung.feldNum = spieler.aktuellesFeld.feldNum;
                        spieler.spielablaufManager.sp.client.send(bewegung);
                    }

                    if (spieler.aktuellesFeld != null) {

                        //Spezielle Fälle für den Ecken auf dem Spielbrett
                        if (spieler.aktuellesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[7][15].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltY < (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                untenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltX < (spieler.aktuellesFeld.weltX)) {
                                rechtsUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten = false;
                            }
                        } else if (spieler.aktuellesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[4][24].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltX < (spieler.aktuellesFeld.weltX)) {
                                rechtsUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY < (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                untenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten = false;
                            }
                        } else if (spieler.aktuellesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[4][16].feld) || spieler.aktuellesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[7][8].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltX > (spieler.aktuellesFeld.weltX)) {
                                linksUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY < (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                untenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten = false;
                            }
                        } else if (spieler.aktuellesFeld.equals(spieler.spielablaufManager.mapManager.mapFliesen[7][25].feld)) {
                            if (spieler.spielablaufManager.mainSpieler.weltY < (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                untenUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltX > (spieler.aktuellesFeld.weltX)) {
                                linksUpdate();
                            } else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                                stehenUpdate();
                                spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten = false;
                            }
                        }

                        //Bewegung nach unten
                        else if (spieler.spielablaufManager.mainSpieler.weltY < (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                            untenUpdate();
                        }

                        //stehen, wenn der main Spieler auf dem neuen Feld gelandet ist
                        else if (spieler.spielablaufManager.mainSpieler.weltY == (spieler.aktuellesFeld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2)) {
                            stehenUpdate();
                            spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungUnten = false;
                        }

                        koordinatenSchicken();

                    }
                }

                //Bewegung links
                else if (spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungLinks) {

                    if (spieler.aktuellesFeld == null) {

                        //neues aktuelles Feld an alle Clients setzen und senden
                        spieler.aktuellesFeld = spieler.vorherigesFeld.westFeld;
                        bewegung.feldNum = spieler.aktuellesFeld.feldNum;
                        spieler.spielablaufManager.sp.client.send(bewegung);
                    }

                    if (spieler.aktuellesFeld != null) {

                        //Bewegung nach links
                        if (spieler.spielablaufManager.mainSpieler.weltX > (spieler.aktuellesFeld.weltX)) {
                            linksUpdate();
                        }

                        //stehen, wenn der main Spieler auf dem neuen Feld gelandet ist
                        else if (spieler.spielablaufManager.mainSpieler.weltX == (spieler.aktuellesFeld.weltX)) {
                            stehenUpdate();
                            spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungLinks = false;
                        }

                        koordinatenSchicken();
                    }
                }

                //Bewegung rechts
                else {

                    if (spieler.aktuellesFeld == null) {

                        //neues aktuelles Feld an alle Clients setzen und senden
                        spieler.aktuellesFeld = spieler.vorherigesFeld.ostFeld;
                        bewegung.feldNum = spieler.aktuellesFeld.feldNum;
                        spieler.spielablaufManager.sp.client.send(bewegung);
                    }

                    if (spieler.aktuellesFeld != null) {

                        //Bewegung nach rechts
                        if (spieler.spielablaufManager.mainSpieler.weltX < (spieler.aktuellesFeld.weltX)) {
                            rechtsUpdate();
                        }

                        //stehen, wenn der main Spieler auf dem neuen Feld gelandet ist
                        else if (spieler.spielablaufManager.mainSpieler.weltX == (spieler.aktuellesFeld.weltX)) {
                            stehenUpdate();
                            spieler.spielablaufManager.mapManager.mapEingabeManager.bewegungRechts = false;
                        }

                       koordinatenSchicken();
                    }
                }

                bilderUpdate();

            }

            //für das gelbe Feld und Sterntaxi
            else if(spieler.zuStern){
                bewegung.zustern = true;
                spieler.spielablaufManager.sp.client.send(bewegung);

                if(spieler.spielablaufManager.mainSpieler.weltY < spieler.spielablaufManager.mapManager.mapFliesen[spieler.spielablaufManager.mapManager.stern.sternFeldZeile][spieler.spielablaufManager.mapManager.stern.sternFeldSpalte].feld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2){
                    spieler.spielablaufManager.mainSpieler.weltY += spieler.geschwindigkeit;

                    //alle Spieler x und y anpassen
                    if (!spieler.spielablaufManager.sp.alleSpieler.isEmpty())
                        for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                            andererSpieler.bildschirmY -= spieler.geschwindigkeit;
                        }
                }else if(spieler.spielablaufManager.mainSpieler.weltY > spieler.spielablaufManager.mapManager.mapFliesen[spieler.spielablaufManager.mapManager.stern.sternFeldZeile][spieler.spielablaufManager.mapManager.stern.sternFeldSpalte].feld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2){
                    spieler.spielablaufManager.mainSpieler.weltY -= spieler.geschwindigkeit;

                    //alle Spieler x und y anpassen
                    if (!spieler.spielablaufManager.sp.alleSpieler.isEmpty())
                        for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                            andererSpieler.bildschirmY += spieler.geschwindigkeit;
                        }
                }
                if(spieler.spielablaufManager.mainSpieler.weltX > spieler.spielablaufManager.mapManager.mapFliesen[spieler.spielablaufManager.mapManager.stern.sternFeldZeile][spieler.spielablaufManager.mapManager.stern.sternFeldSpalte].feld.weltX){
                    spieler.spielablaufManager.mainSpieler.weltX -= spieler.geschwindigkeit;

                    //alle Spieler x und y anpassen
                    if (!spieler.spielablaufManager.sp.alleSpieler.isEmpty())
                        for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                            andererSpieler.bildschirmX += spieler.geschwindigkeit;
                        }
                }else if(spieler.spielablaufManager.mainSpieler.weltX < spieler.spielablaufManager.mapManager.mapFliesen[spieler.spielablaufManager.mapManager.stern.sternFeldZeile][spieler.spielablaufManager.mapManager.stern.sternFeldSpalte].feld.weltX){
                    spieler.spielablaufManager.mainSpieler.weltX += spieler.geschwindigkeit;

                    //alle Spieler x und y anpassen
                    if (!spieler.spielablaufManager.sp.alleSpieler.isEmpty())
                        for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                            andererSpieler.bildschirmX -= spieler.geschwindigkeit;
                        }
                }

                //stehen, wenn der main Spieler auf dem Feld, der ein Stern hat, gelandet ist
                else if(spieler.spielablaufManager.mainSpieler.weltX == spieler.spielablaufManager.mapManager.mapFliesen[spieler.spielablaufManager.mapManager.stern.sternFeldZeile][spieler.spielablaufManager.mapManager.stern.sternFeldSpalte].feld.weltX
                 && spieler.spielablaufManager.mainSpieler.weltY == spieler.spielablaufManager.mapManager.mapFliesen[spieler.spielablaufManager.mapManager.stern.sternFeldZeile][spieler.spielablaufManager.mapManager.stern.sternFeldSpalte].feld.weltY - spieler.spielablaufManager.sp.vergroesserteFliesenGroesse / 2){
                    spieler.zuStern = false;
                    spieler.bewegung = false;
                    bewegung.zustern = false;
                    spieler.spielablaufManager.sp.client.send(bewegung);
                    spieler.aktuellesFeld.effeckteAnwenden();
                }

                koordinatenSchicken();
            }
        }

        //alle Spieler update
        else {
            bilderUpdate();
        }
    }

    private void obenUpdate(){
        spieler.spielablaufManager.mainSpieler.weltY -= spieler.geschwindigkeit;
        richtung = "oben";
        bewegung.richtung = "oben";
        spieler.spielablaufManager.sp.client.send(bewegung);

        //alle Spieler x und y anpassen
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

        //alle Spieler x und y anpassen
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

        //alle Spieler x und y anpassen
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

        //alle Spieler x und y anpassen
        if (!spieler.spielablaufManager.sp.alleSpieler.isEmpty())
            for (Spieler andererSpieler : spieler.spielablaufManager.sp.alleSpieler) {
                andererSpieler.bildschirmX += spieler.geschwindigkeit;
            }
    }

    private void stehenUpdate(){
        spieler.bewegung = false;
        richtung = "stehen";
        bewegung.richtung = "stehen";
        spieler.spielablaufManager.sp.client.send(bewegung);

        if (spieler.schritteAnzahl == 0) {
            spieler.aktuellesFeld.effeckteAnwenden();
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
        switch (richtung) {
            case "oben" -> {
                if (spriteNum == 1 || spriteNum == 3) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                } else if (spriteNum == 4) {
                    image = up3;
                }
            }
            case "unten" -> {
                if (spriteNum == 1 || spriteNum == 3) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                } else if (spriteNum == 4) {
                    image = down3;
                }
            }
            case "links" -> {
                if (spriteNum == 1 || spriteNum == 3) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                } else if (spriteNum == 4) {
                    image = left3;
                }
            }
            case "rechts" -> {
                if (spriteNum == 1 || spriteNum == 3) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                } else if (spriteNum == 4) {
                    image = right3;
                }
            }
            case "stehen" -> image = down1;
        }

        //für das gelbe Feld und Sterntaxi die Wolke malen
        if(spieler.zuStern){
            g2.drawImage(wolke, this.spieler.bildschirmX, this.spieler.bildschirmY+30, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse, null);
        }
        g2.drawImage(image,this.spieler.bildschirmX,  this.spieler.bildschirmY, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse, spieler.spielablaufManager.sp.vergroesserteFliesenGroesse, null);
    }

}
