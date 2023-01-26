package Minispiele;

import Main.SpielPanel;
import Networking.Pakete.SammlerGegenstaende;
import Networking.Pakete.SammlerPunkte;
import Spielablauf.Fliese;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        } else if(elementIndex == 2) {
            this.muenze2 = element;
        } else if(elementIndex == 3) {
            this.spider1 = element;
        } else if(elementIndex == 4){
            this.spider2 = element;
        } else if(elementIndex == 5){
            this.spider3 = element;
        } else if(elementIndex == 6){
            this.diamond = element;
        } else if(elementIndex == 7){
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

    public void kollisionChecken(MinispielSpieler spieler) {

        if(muenze1!=null) {
            if (spieler.minispielSpielerRechteck.intersects(muenze1.elementRechteck)) {
                if(spieler == mainMinispielSpieler) {
                    muenze1 = null;
                    spieler.punktzahl++;
                    SammlerPunkte punkte = new SammlerPunkte();
                    punkte.clientIndex = sp.client.clientIndex;
                    sp.client.send(punkte);
                    SammlerGegenstaende sammlerGegenstaende = new SammlerGegenstaende();
                    sammlerGegenstaende.elementIndex = 1;
                    sp.client.send(sammlerGegenstaende);
                }
            }
        }
        if(muenze2!=null) {
            if (spieler.minispielSpielerRechteck.intersects(muenze2.elementRechteck)) {
                if(spieler == mainMinispielSpieler) {
                    muenze2 = null;
                    spieler.punktzahl++;
                    SammlerPunkte punkte = new SammlerPunkte();
                    punkte.clientIndex = sp.client.clientIndex;
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
                    punkte.clientIndex = sp.client.clientIndex;
                    sp.client.send(punkte);
                }
            }
        }
        if (spieler.minispielSpielerRechteck.intersects(spider1.elementRechteck)||
            spieler.minispielSpielerRechteck.intersects(spider2.elementRechteck)||
            spieler.minispielSpielerRechteck.intersects(spider3.elementRechteck)) {

            spieler.unterSpider = true;
        } else{
            spieler.unterSpider = false;
        }



    }


    @Override
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
