package Minispiele;

import Main.SpielPanel;
import Networking.Pakete.SammlerGegenstaende;
import Networking.Pakete.SammlerPunkte;
import Spielablauf.Fliese;
import spieler.Abdo;
import spieler.Husam;
import spieler.Taha;
import spieler.Yousef;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Sammler extends Minispiel {
    private SammlerElement muenze1;
    private SammlerElement muenze2;
    private SammlerElement diamond;
    private SammlerElement mushroom;
    SammlerElement spider1;
    SammlerElement spider2;
    SammlerElement spider3;

    Rectangle[] wandRechteck;


    public Sammler(SpielPanel sp, MinispielSpieler mainMinispielSpieler, ArrayList<MinispielSpieler> alleMinispielSpieler) {
        super(sp, mainMinispielSpieler, alleMinispielSpieler);
        this.minispielFliesen = new Fliese[9];
        this.minispielMap = new int[15][9];
        this.getFlieseImage();
        this.mapLaden();
    }

    public void setzeElement(int elementIndex, SammlerElement element) {
        if (elementIndex == 1) {
            this.muenze1 = element;
        } else if (elementIndex == 2) {
            this.muenze2 = element;
        } else if (elementIndex == 3) {
            this.spider1 = element;
        } else if (elementIndex == 4) {
            this.spider2 = element;
        } else if (elementIndex == 5) {
            this.spider3 = element;
        } else if (elementIndex == 6) {
            this.diamond = element;
        } else if (elementIndex == 7) {
            this.mushroom = element;
        }
    }


    public void update() {
        mainMinispielSpieler.update();
        for (MinispielSpieler spieler : alleMinispielSpieler) {
            if (spieler != null) {
                spieler.update();
            }
        }
        if (muenze1 != null) {
            muenze1.muenzeUpdate();
        }
        if (muenze2 != null) {
            muenze2.muenzeUpdate();
        }
        if (diamond != null) {
            diamond.daimondupdate();
        }

    }


    // Hier wird geprüft, ob eine Kollision aufgetreten ist
    public void kollisionChecken(MinispielSpieler spieler) {

        if (muenze1 != null) {
            if (spieler.minispielSpielerRechteck.intersects(muenze1.elementRechteck)) {
                if (spieler == mainMinispielSpieler) {
                    muenze1 = null;
                    spieler.punktzahl++;
                    SammlerPunkte punkte = new SammlerPunkte();
                    sp.client.send(punkte);
                    SammlerGegenstaende sammlerGegenstaende = new SammlerGegenstaende();
                    sammlerGegenstaende.elementIndex = 1;
                    sp.client.send(sammlerGegenstaende);
                }
            }
        }
        if (muenze2 != null) {
            if (spieler.minispielSpielerRechteck.intersects(muenze2.elementRechteck)) {
                if (spieler == mainMinispielSpieler) {
                    muenze2 = null;
                    spieler.punktzahl++;
                    SammlerPunkte punkte = new SammlerPunkte();
                    sp.client.send(punkte);
                    SammlerGegenstaende sammlerGegenstaende = new SammlerGegenstaende();
                    sammlerGegenstaende.elementIndex = 2;
                    sp.client.send(sammlerGegenstaende);
                }
            }
        }
        if (mushroom != null) {
            if (spieler.minispielSpielerRechteck.intersects(mushroom.elementRechteck)) {
                mushroom = null;
                spieler.hatMushroom = true;
                spieler.mushroomZeit = sp.minispielManager.gesamtSekundenAnzahl;

            }
        }


        if (diamond != null) {
            if (spieler.minispielSpielerRechteck.intersects(diamond.elementRechteck)) {
                diamond = null;
                if (spieler == mainMinispielSpieler) {
                    spieler.punktzahl += 3;
                    SammlerPunkte punkte = new SammlerPunkte();
                    punkte.diamond = true;
                    sp.client.send(punkte);
                }
            }
        }
        if (spieler.minispielSpielerRechteck.intersects(spider1.elementRechteck) ||
                spieler.minispielSpielerRechteck.intersects(spider2.elementRechteck) ||
                spieler.minispielSpielerRechteck.intersects(spider3.elementRechteck)) {

            spieler.unterSpider = true;
        } else {
            spieler.unterSpider = false;
        }


    }


    //Hier wird das map Fotos fesgelegt
    public void getFlieseImage() {
        try {
            minispielFliesen[0] = new Fliese();
            minispielFliesen[0].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice0.png")));

            minispielFliesen[1] = new Fliese();
            minispielFliesen[1].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice1.png")));

            minispielFliesen[2] = new Fliese();
            minispielFliesen[2].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice3.png")));

            minispielFliesen[3] = new Fliese();
            minispielFliesen[3].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice4.png")));

            minispielFliesen[4] = new Fliese();
            minispielFliesen[4].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice5.png")));

            minispielFliesen[5] = new Fliese();
            minispielFliesen[5].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice6.png")));

            minispielFliesen[6] = new Fliese();
            minispielFliesen[6].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice7.png")));

            minispielFliesen[7] = new Fliese();
            minispielFliesen[7].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice8.png")));

            minispielFliesen[8] = new Fliese();
            minispielFliesen[8].flieseImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/slice9.png")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mapLaden() {
        try {
            InputStream is = getClass().getResourceAsStream("/miniSpiele/sammlerMapBilder/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int spalte = 0;
            int zeile = 0;

            while (spalte < 15 && zeile < 9) {
                String line = br.readLine();
                while (spalte < 15) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[spalte]);

                    minispielMap[spalte][zeile] = num;
                    spalte++;
                }
                if (spalte == 15) {
                    spalte = 0;
                    zeile++;
                }
            }
            br.close();

        } catch (Exception e) {

        }
        wandRechteck = new Rectangle[4];
        wandRechteck[0] = new Rectangle(0, 0, 1440, 96);
        wandRechteck[1] = new Rectangle(0, 864 - 96, 1440, 96);
        wandRechteck[2] = new Rectangle(0, 0, 96, 800);
        wandRechteck[3] = new Rectangle(1440 - 96, 0, 96, 800);

    }

    // Hier wird SiegerKueren bestimmt
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
                } else if (spieler1.punktzahl == spieler2.punktzahl) {

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
                } else if (i == 2 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Abdo) {
                    if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl && alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                    } else if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                    } else {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(4);
                    }
                }
            } else if (sp.spielablaufManager.mainSpieler.spielfigur instanceof Husam && spielfigurIndex == 1) {
                if (i == 0 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Husam) {
                    sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                } else if (i == 1 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Husam) {
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
                } else if (i == 2 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Husam) {
                    if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl && alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                    } else if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                    } else {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(4);
                    }
                }
            } else if (sp.spielablaufManager.mainSpieler.spielfigur instanceof Taha && spielfigurIndex == 2) {
                if (i == 0 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Taha) {
                    sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                } else if (i == 1 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Taha) {
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
                } else if (i == 2 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Taha) {
                    if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl && alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                    } else if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                    } else {
                        sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(4);
                    }
                }
            } else if (sp.spielablaufManager.mainSpieler.spielfigur instanceof Yousef && spielfigurIndex == 3) {
                if (i == 0 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Yousef) {
                    sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                } else if (i == 1 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Yousef) {
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
                }
            } else if (i == 2 && alleMinispielSpieler.get(i).minispielSpieler.spielfigur instanceof Yousef) {
                if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(0).punktzahl && alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                    sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(10);
                } else if (alleMinispielSpieler.get(i).punktzahl == alleMinispielSpieler.get(1).punktzahl) {
                    sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(7);
                } else {
                    sp.spielablaufManager.mainSpieler.konto.muenzenErhalten(4);
                }
            }
        }
    }

    // Hier wird Siegerkueren gemalt
    @Override
    public void siegerKuerenMalen(Graphics2D g2) {
        BufferedImage coin = null;
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

                    platz++;

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


    //Hier wird das Ergbnisbox fetstlegen
    public void ergbnisBoxmalen(Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g2.setColor(Color.black);
        g2.fillRoundRect(sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, 1440 - 2 * sp.vergroesserteFliesenGroesse, 864 - 2 * sp.vergroesserteFliesenGroesse, 25, 25);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(sp.vergroesserteFliesenGroesse + 5, sp.vergroesserteFliesenGroesse + 5, 1430 - 2 * sp.vergroesserteFliesenGroesse, 854 - 2 * sp.vergroesserteFliesenGroesse, 15, 15);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));


    }

    //Hier wird das Map Fotos , Element und Siegerkuren gemalt
    @Override
    public void malen(Graphics2D g2) {
        int spalte = 0;
        int zeile = 0;
        int x = 0;
        int y = 0;
        while (spalte < 15 && zeile < 9) {

            int flieseIndex = minispielMap[spalte][zeile];
            g2.drawImage(minispielFliesen[flieseIndex].flieseImage, x, y, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);

            spalte++;
            x += sp.vergroesserteFliesenGroesse;

            if (spalte == 15) {
                spalte = 0;
                x = 0;
                zeile++;
                y += sp.vergroesserteFliesenGroesse;
            }
        }

        if (muenze1 != null) {
            muenze1.muenzeMalen(g2);
        }
        if (muenze2 != null) {
            muenze2.muenzeMalen(g2);
        }
        if (mushroom != null) {
            mushroom.mushroomMalen(g2);
        }
        if (diamond != null) {
            diamond.daimondmalen(g2);
        }

    }

}
