package Minispiele;
import Main.SpielPanel;
import Networking.Pakete.SquidGamePosition;
import Networking.Pakete.SquidGamePunkte;
import spieler.Abdo;
import spieler.Husam;
import spieler.Taha;
import spieler.Yousef;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;


public class SquidGame extends Minispiel {
    public Palette[] paletten;
    BufferedImage[][] minispielFliesen;
    BufferedImage falle1, falle2, falle3, falle4; //Fotos der Fallen, die gezeigt werden, wenn die Palette eine Falle hat.

    SquidGame(SpielPanel sp, MinispielSpieler mainMinispielSpieler, ArrayList<MinispielSpieler> alleMinispielSpieler) {
        super(sp, mainMinispielSpieler, alleMinispielSpieler);
        minispielFliesen = new BufferedImage[20][20];
        paletten = new Palette[14];
        mapLaden();
        setzePaletten();
        paletteVerbinden();
    }
      //Maptiles aus dem Textadatei einlesen.
    @Override
    public void mapLaden() {
        String[][] mapFelder = new String[20][20];
        try {
            InputStream is = getClass().getResourceAsStream("/miniSpiele/Squidgamemap/squidbilder.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int zeilenIndex = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] zeile = line.split(" ");
                System.arraycopy(zeile, 0, mapFelder[zeilenIndex], 0, zeile.length);
                zeilenIndex++;
            }
            for (int zeile = 0; zeile < 20; zeile++) {
                for (int spalte = 0; spalte < 20; spalte++) {
                    try {
                        minispielFliesen[zeile][spalte] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/Squidgamemap/SquidgameBilder/tileset" + mapFelder[zeile][spalte] + ".png")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            br.close();
            falle1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/Squidgamemap/SquidgameBilder/Falle1.png")));
            falle2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/Squidgamemap/SquidgameBilder/Falle2.png")));
            falle3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/Squidgamemap/SquidgameBilder/Falle3.png")));
            falle4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/Squidgamemap/SquidgameBilder/Falle4.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
     //Paletten postionen festlegen
    public void setzePaletten() {
        paletten[0] = new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5), sp.vergroesserteFliesenGroesse * 15 - sp.vergroesserteFliesenGroesse / 3, 0, true);
        paletten[1] = new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5), sp.vergroesserteFliesenGroesse * 15 - sp.vergroesserteFliesenGroesse / 3, 1, true);
        paletten[2] = new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5), sp.vergroesserteFliesenGroesse * 13 - sp.vergroesserteFliesenGroesse / 3, 2, true);
        paletten[3] = new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5), sp.vergroesserteFliesenGroesse * 13 - sp.vergroesserteFliesenGroesse / 3, 3, true);
        paletten[4] = new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5), sp.vergroesserteFliesenGroesse * 11 - sp.vergroesserteFliesenGroesse / 3, 4, true);
        paletten[5] = new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5), sp.vergroesserteFliesenGroesse * 11 - sp.vergroesserteFliesenGroesse / 3, 5, true);
        paletten[6] = new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5), sp.vergroesserteFliesenGroesse * 9 - sp.vergroesserteFliesenGroesse / 3, 6, true);
        paletten[7] = new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5), sp.vergroesserteFliesenGroesse * 9 - sp.vergroesserteFliesenGroesse / 3, 7, true);
        paletten[8] = new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5), sp.vergroesserteFliesenGroesse * 7 - sp.vergroesserteFliesenGroesse / 3, 8, true);
        paletten[9] = new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5), sp.vergroesserteFliesenGroesse * 7 - sp.vergroesserteFliesenGroesse / 3, 9, true);
        paletten[10] = new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5), sp.vergroesserteFliesenGroesse * 5 - sp.vergroesserteFliesenGroesse / 3, 10, true);
        paletten[11] = new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5), sp.vergroesserteFliesenGroesse * 5 - sp.vergroesserteFliesenGroesse / 3, 11, true);
        paletten[12] = new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5), sp.vergroesserteFliesenGroesse * 3 - sp.vergroesserteFliesenGroesse / 3, 12, true);
        paletten[13] = new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5), sp.vergroesserteFliesenGroesse * 3 - sp.vergroesserteFliesenGroesse / 3, 13, true);
    }
    //palettenReihenfolge festlegen
    public void paletteVerbinden() {
        for (int i = 0; i < 12; i++) {
            if (i % 2 == 0) {
                paletten[i].naechsteRechts = paletten[i + 3];
                paletten[i].naechsteLinks = paletten[i + 2];
            } else {
                paletten[i].naechsteRechts = paletten[i + 2];
                paletten[i].naechsteLinks = paletten[i + 1];
            }
        }
    }

    //Zufeallige Fallen zu irgendeiner Palette festlegen.
    public void falleFestlegen(ArrayList<Boolean> palletenFalle) {
        int j = 0;
        for (int i = 0; i < 13; i += 2) {
            paletten[i].hatFalle = palletenFalle.get(j);
            paletten[i + 1].hatFalle = !paletten[i].hatFalle;
            j++;
        }
    }

    //Siegerkueren methode mit verschiedenen Fallunterscheidungen, die die Sieger sortieren und festlegen,
    // dementsprechend werden die Sieger Meunzen erhalten.
    @Override
    public void siegerFestlegen() {
        int spielfigurIndex = -1;
        if (mainMinispielSpieler.minispielSpieler.spielfigur instanceof Abdo) {
            alleMinispielSpieler.set(0, mainMinispielSpieler);
            spielfigurIndex = 0;
        } else if (mainMinispielSpieler.minispielSpieler.spielfigur instanceof Husam) {
            alleMinispielSpieler.set(1, mainMinispielSpieler);
            spielfigurIndex = 1;
        } else if (mainMinispielSpieler.minispielSpieler.spielfigur instanceof Taha) {
            alleMinispielSpieler.set(2, mainMinispielSpieler);
            spielfigurIndex = 2;
        } else if (mainMinispielSpieler.minispielSpieler.spielfigur instanceof Yousef) {
            alleMinispielSpieler.set(3, mainMinispielSpieler);
            spielfigurIndex = 3;
        }

        alleMinispielSpieler.removeIf(Objects::isNull);
        alleMinispielSpieler.sort(new Comparator<MinispielSpieler>() {
            @Override
            public int compare(MinispielSpieler spieler1, MinispielSpieler spieler2) {
                if (spieler2.punktzahl > spieler1.punktzahl) {
                    return 1;
                } else if (spieler1.punktzahl == 7 && spieler1.punktzahl == spieler2.punktzahl) {
                    if (spieler2.endeErreichtSekunde > spieler1.endeErreichtSekunde) {
                        return 1;
                    } else if (spieler2.endeErreichtSekunde < spieler1.endeErreichtSekunde) {
                        return -1;
                    }
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        for (int i = 0; i < alleMinispielSpieler.size(); i++) {
            if (sp.spielablaufManager.mainSpieler.spielfigur instanceof Abdo && spielfigurIndex == 0) {
                if (i == 0 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Abdo) {
                    sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                } else if (i == 1 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Abdo) {
                    if (alleMinispielSpieler.get(i).punktzahl < 7) {
                        if (alleMinispielSpieler.size() - 1 == 2) {
                            if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl) {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                            } else {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                            }
                        } else {
                            if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl) {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                            } else {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                            }
                        }
                    } else {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                    }
                } else if (i == 2 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Abdo) {
                    if (alleMinispielSpieler.get(i).punktzahl < 7) {
                        if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl && alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                            sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                        } else if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                            sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                        } else {
                            sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(4);
                        }
                    } else {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(4);
                    }
                }
            } else if (sp.spielablaufManager.mainSpieler.spielfigur instanceof Husam && spielfigurIndex == 1) {
                if (i == 0 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Husam) {
                    sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                } else if (i == 1 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Husam) {
                    if (alleMinispielSpieler.get(i).punktzahl < 7) {
                        if (alleMinispielSpieler.size() - 1 == 2) {
                            if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl) {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                            } else {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                            }
                        } else {
                            if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl) {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                            } else {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                            }
                        }
                    } else {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                    }
                } else if (i == 2 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Husam) {
                    if (alleMinispielSpieler.get(i).punktzahl < 7) {
                        if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl && alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                            sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                        } else if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                            sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                        } else {
                            sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(4);
                        }
                    } else {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(4);
                    }
                }
            } else if (sp.spielablaufManager.mainSpieler.spielfigur instanceof Taha && spielfigurIndex == 2) {
                if (i == 0 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Taha) {
                    sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                } else if (i == 1 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Taha) {
                    if (alleMinispielSpieler.get(i).punktzahl < 7) {
                        if (alleMinispielSpieler.size() - 1 == 2) {
                            if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl) {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                            } else {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                            }
                        } else {
                            if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl) {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                            } else {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                            }
                        }
                    } else {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                    }

                } else if (i == 2 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Taha) {
                    if (alleMinispielSpieler.get(i).punktzahl < 7) {
                        if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl && alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                            sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                        } else if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                            sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                        } else {
                            sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(4);
                        }
                    } else {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(4);
                    }
                }
            } else if (sp.spielablaufManager.mainSpieler.spielfigur instanceof Yousef && spielfigurIndex == 3) {
                if (i == 0 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Yousef) {
                    sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                } else if (i == 1 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Yousef) {
                    if (alleMinispielSpieler.get(i).punktzahl < 7) {
                        if (alleMinispielSpieler.size() - 1 == 2) {
                            if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl) {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                            } else {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                            }
                        } else {
                            if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl) {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                            } else {
                                sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                            }
                        }
                    } else {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                    }
                } else if (i == 2 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Yousef) {
                    if (alleMinispielSpieler.get(i).punktzahl < 7) {
                        if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl && alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                            sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                        } else if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                            sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                        } else {
                            sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(4);
                        }
                    } else {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(4);
                    }
                }
            }
        }
    }

    //Hier werden die Spieler(Sieger) mit jeweils Anzahl die erhaltenden Punkte auf dem Bildschirm gemalt.
    @Override
    public void siegerKuerenMalen(Graphics2D g2) {
        BufferedImage coin;
        try {
            coin = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/source/bestandteile/muenze/Coin1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int muenzenAnzahl;
        int gewinnPlatz = 1;
        int platz = 1;
        int y = 4 * sp.vergroesserteFliesenGroesse;
        int x;
        if (alleMinispielSpieler.size() == 2) {
            x = 5 * sp.vergroesserteFliesenGroesse;
        } else if (alleMinispielSpieler.size() == 3) {
            x = (int) (3.5 * sp.vergroesserteFliesenGroesse);
        } else {
            x = 2 * sp.vergroesserteFliesenGroesse;
        }
        ergbnisBoxmalen(g2);
        for (int i = 0; i < alleMinispielSpieler.size(); i++) {
            if (gewinnPlatz == 1) {
                muenzenAnzahl = 10;
            } else if (gewinnPlatz == 2) {
                muenzenAnzahl = 7;
            } else if (gewinnPlatz == 3) {
                muenzenAnzahl = 4;
            } else {
                muenzenAnzahl = 0;
            }
            g2.drawImage(alleMinispielSpieler.get(i).minispielSpieler.spielfigur.down1, x, y - 96, sp.fliesenGroesse * 8, sp.fliesenGroesse * 8, null);

            g2.setColor(Color.yellow);
            if (i == alleMinispielSpieler.size() - 1) {
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
                g2.drawString("#", x + 50, y - 150);
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 120F));
                g2.drawString("" + gewinnPlatz, x + 120, y - 150);
            } else {
                if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(i + 1).punktzahl) {
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
                    g2.drawString("#", x + 50, y - 150);
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 120F));
                    g2.drawString("" + gewinnPlatz, x + 120, y - 150);
                    if (alleMinispielSpieler.get(i).punktzahl == 7) {
                        if (gewinnPlatz == 1 || gewinnPlatz >= 2) {
                            gewinnPlatz++;
                            platz++;
                        }
                    }
                    else{
                        platz++;
                    }
                }
                if (alleMinispielSpieler.get(i).punktzahl != alleMinispielSpieler.get(i + 1).punktzahl) {
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
                    g2.drawString("#", x + 50, y - 150);
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 120F));
                    g2.drawString("" + gewinnPlatz, x + 120, y - 150);
                    platz++;
                    gewinnPlatz = platz;
                }
            }

            g2.drawImage(coin, x + 22, y + 240, sp.vergroesserteFliesenGroesse + 30, sp.vergroesserteFliesenGroesse + 30, null);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));

            g2.drawString("+" + muenzenAnzahl, x + 100, y + 300);
            x += 3 * sp.vergroesserteFliesenGroesse;
        }

    }

    public void ergbnisBoxmalen(Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g2.setColor(Color.black);
        g2.fillRoundRect(sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, 1440 - 2 * sp.vergroesserteFliesenGroesse, 864 - 2 * sp.vergroesserteFliesenGroesse, 25, 25);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(sp.vergroesserteFliesenGroesse + 5, sp.vergroesserteFliesenGroesse + 5, 1430 - 2 * sp.vergroesserteFliesenGroesse, 854 - 2 * sp.vergroesserteFliesenGroesse, 15, 15);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    //Was passiert wenn die Spieler das Ende erreichen.
    public void endeErreichen() {
        mainMinispielSpieler.endeErreichtSekunde = mainMinispielSpieler.minispielManager.gesamtSekundenAnzahl;
        mainMinispielSpieler.bildschirmY -= sp.vergroesserteFliesenGroesse / 2;
        if (mainMinispielSpieler.minispielSpieler.spielfigur instanceof Abdo) {
            mainMinispielSpieler.minispielXPosition = (int) (5.5 * sp.vergroesserteFliesenGroesse) - 2;
        } else if (mainMinispielSpieler.minispielSpieler.spielfigur instanceof Husam) {
            mainMinispielSpieler.minispielXPosition = (int) (6.5 * sp.vergroesserteFliesenGroesse) - 5;
        } else if (mainMinispielSpieler.minispielSpieler.spielfigur instanceof Taha) {
            mainMinispielSpieler.minispielXPosition = (int) (7.5 * sp.vergroesserteFliesenGroesse) + 5;
        } else if (mainMinispielSpieler.minispielSpieler.spielfigur instanceof Yousef) {
            mainMinispielSpieler.minispielXPosition = (int) (8.5 * sp.vergroesserteFliesenGroesse) + 2;
        }
        mainMinispielSpieler.minispielYPosition = sp.vergroesserteFliesenGroesse;
        for (MinispielSpieler spieler : alleMinispielSpieler) {
            if (spieler != null) {
                spieler.bildschirmX = spieler.minispielXPosition - mainMinispielSpieler.minispielXPosition + mainMinispielSpieler.bildschirmX;
                spieler.bildschirmY = spieler.minispielYPosition - mainMinispielSpieler.minispielYPosition + mainMinispielSpieler.bildschirmY;
            }
        }
        mainMinispielSpieler.endeErreicht = true;
        SquidGamePosition squidGamePosition = new SquidGamePosition();
        squidGamePosition.minispielXPosition = mainMinispielSpieler.minispielXPosition;
        squidGamePosition.minispielYPosition = mainMinispielSpieler.minispielYPosition;
        sp.client.send(squidGamePosition);

        SquidGamePunkte squidGamePunkte = new SquidGamePunkte();
        squidGamePunkte.sekundenAnzahl = mainMinispielSpieler.endeErreichtSekunde;
        squidGamePunkte.endeErreicht = true;
        squidGamePunkte.punktZahl = 7;
        sp.client.send(squidGamePunkte);
        mainMinispielSpieler.amSpielen = false;
    }


    //Spieler postionen aktualisieren und dementsprechend malen
    public void update() {
        mainMinispielSpieler.update();
        for (MinispielSpieler spieler : alleMinispielSpieler) {
            if (spieler != null) {
                spieler.update();
            }
        }
    }

    //SquidGame Map und Fallen malen
    @Override
    public void malen(Graphics2D g2) {
        int spalte = 0;
        int zeile = 0;

        while (spalte < 20 && zeile < 20) {
            int worldX = spalte * sp.vergroesserteFliesenGroesse;
            int worldY = zeile * sp.vergroesserteFliesenGroesse;
            int bildschirmX = worldX - (int) (sp.vergroesserteFliesenGroesse * 2.5);
            int bildschirmY = worldY - mainMinispielSpieler.minispielYPosition + mainMinispielSpieler.bildschirmY;

            if (mainMinispielSpieler.aktuellePalette != null && mainMinispielSpieler.aktuellePalette.hatFalle) {
                if (mainMinispielSpieler.aktuellePalette.paletteNummer <= 1 || mainMinispielSpieler.aktuellePalette.paletteNummer == 12 || mainMinispielSpieler.aktuellePalette.paletteNummer == 13) {
                    g2.drawImage(falle1, mainMinispielSpieler.minispielXPosition, mainMinispielSpieler.bildschirmY + sp.vergroesserteFliesenGroesse / 3, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
                } else if (mainMinispielSpieler.aktuellePalette.paletteNummer == 2 || mainMinispielSpieler.aktuellePalette.paletteNummer == 3) {
                    g2.drawImage(falle2, mainMinispielSpieler.minispielXPosition, mainMinispielSpieler.bildschirmY + sp.vergroesserteFliesenGroesse / 3, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
                } else if (mainMinispielSpieler.aktuellePalette.paletteNummer <= 9) {
                    g2.drawImage(falle3, mainMinispielSpieler.minispielXPosition, mainMinispielSpieler.bildschirmY + sp.vergroesserteFliesenGroesse / 3, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
                } else if (mainMinispielSpieler.aktuellePalette.paletteNummer == 10 || mainMinispielSpieler.aktuellePalette.paletteNummer == 11) {
                    g2.drawImage(falle4, mainMinispielSpieler.minispielXPosition, mainMinispielSpieler.bildschirmY + sp.vergroesserteFliesenGroesse / 3, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
                }
            }
            g2.drawImage(minispielFliesen[zeile][spalte], bildschirmX, bildschirmY, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
            spalte++;
            if (spalte == 20) {
                spalte = 0;
                zeile++;
            }
        }
    }
}