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
import java.util.Objects;

public class SquidGame extends Minispiel {
    public Palette[] paletten;
    BufferedImage[][] minispielFliesen;

    BufferedImage falle1, falle2, falle3, falle4;

    SquidGame(SpielPanel sp, MinispielSpieler mainMinispielSpieler, ArrayList<MinispielSpieler> alleMinispielSpieler) {
        super(sp, mainMinispielSpieler, alleMinispielSpieler);
        minispielFliesen = new BufferedImage[20][20];
        paletten = new Palette[14];
        mapLaden();
        setzePaletten();
        paletteVerbinden();
    }

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
            falle1=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/Squidgamemap/SquidgameBilder/Falle1.png")));
            falle2=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/Squidgamemap/SquidgameBilder/Falle2.png")));
            falle3=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/Squidgamemap/SquidgameBilder/Falle3.png")));
            falle4=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/miniSpiele/Squidgamemap/SquidgameBilder/Falle4.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setzePaletten() {
        paletten[0] = new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5) , sp.vergroesserteFliesenGroesse * 15 - sp.vergroesserteFliesenGroesse / 3, 0, true);
        paletten[1] = new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5 ) , sp.vergroesserteFliesenGroesse * 15 - sp.vergroesserteFliesenGroesse / 3, 1, true);
        paletten[2] = new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5) , sp.vergroesserteFliesenGroesse * 13 - sp.vergroesserteFliesenGroesse / 3, 2, true);
        paletten[3] = new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5 ) , sp.vergroesserteFliesenGroesse * 13 - sp.vergroesserteFliesenGroesse / 3, 3, true);
        paletten[4] = new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5) , sp.vergroesserteFliesenGroesse * 11 - sp.vergroesserteFliesenGroesse / 3, 4, true);
        paletten[5] = new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5 ) , sp.vergroesserteFliesenGroesse * 11 - sp.vergroesserteFliesenGroesse / 3, 5, true);
        paletten[6] = new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5) , sp.vergroesserteFliesenGroesse * 9 - sp.vergroesserteFliesenGroesse / 3, 6, true);
        paletten[7] = new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5 ) , sp.vergroesserteFliesenGroesse * 9 - sp.vergroesserteFliesenGroesse / 3, 7, true);
        paletten[8] = new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5) , sp.vergroesserteFliesenGroesse * 7 - sp.vergroesserteFliesenGroesse / 3, 8, true);
        paletten[9] = new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5 ) , sp.vergroesserteFliesenGroesse * 7 - sp.vergroesserteFliesenGroesse / 3, 9, true);
        paletten[10] =new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5) , sp.vergroesserteFliesenGroesse * 5 - sp.vergroesserteFliesenGroesse / 3, 10, true);
        paletten[11] =new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5 ) , sp.vergroesserteFliesenGroesse * 5 - sp.vergroesserteFliesenGroesse / 3, 11, true);
        paletten[12] =new Palette((int) (sp.vergroesserteFliesenGroesse * 5.5) , sp.vergroesserteFliesenGroesse * 3 - sp.vergroesserteFliesenGroesse / 3, 12, true);
        paletten[13] =new Palette((int) (sp.vergroesserteFliesenGroesse * 8.5 ) , sp.vergroesserteFliesenGroesse * 3 - sp.vergroesserteFliesenGroesse / 3, 13, true);
    }

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

    public void falleFestlegen(ArrayList<Boolean> palletenFalle) {
        int j = 0;
        for (int i = 0; i < 13; i+=2) {
            paletten[i].hatFalle = palletenFalle.get(j);
            paletten[i + 1].hatFalle = !paletten[i].hatFalle;

            j++;
        }
    }
    @Override
    public void siegerFestlegen() {

    }

    @Override
    public void siegerKuerenMalen(Graphics2D g2) {


    }

    public void endeErreichen() {
        mainMinispielSpieler.bildschirmY -= sp.vergroesserteFliesenGroesse/2;
        if(mainMinispielSpieler.minispielSpieler.spielfigur instanceof Abdo) {
            mainMinispielSpieler.minispielXPosition = (int) (5.5 * sp.vergroesserteFliesenGroesse) - 2;
        }
        else if(mainMinispielSpieler.minispielSpieler.spielfigur  instanceof Husam) {
            mainMinispielSpieler.minispielXPosition = (int) (6.5 * sp.vergroesserteFliesenGroesse) - 5;
        }
        else if(mainMinispielSpieler.minispielSpieler.spielfigur  instanceof Taha) {
            mainMinispielSpieler.minispielXPosition = (int) (7.5 * sp.vergroesserteFliesenGroesse) + 5;
        }
        else if(mainMinispielSpieler.minispielSpieler.spielfigur  instanceof Yousef) {
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
        squidGamePunkte.endeErreicht = true;
        squidGamePunkte.punktZahl = -1;
        sp.client.send(squidGamePunkte);
        mainMinispielSpieler.amSpielen = false;
    }
    public void update(){
        mainMinispielSpieler.update();
        for (MinispielSpieler spieler : alleMinispielSpieler) {
            if (spieler != null) {
                spieler.update();
            }
        }
    }

    @Override
    public void malen(Graphics2D g2) {
        int spalte = 0;
        int zeile = 0;

        while (spalte < 20 && zeile < 20) {
            int worldX = spalte * sp.vergroesserteFliesenGroesse;
            int worldY = zeile * sp.vergroesserteFliesenGroesse;
            int bildschirmX = worldX - (int) (sp.vergroesserteFliesenGroesse * 2.5);
            int bildschirmY = worldY - mainMinispielSpieler.minispielYPosition +mainMinispielSpieler.bildschirmY;

            if(mainMinispielSpieler.aktuellePalette!=null&&mainMinispielSpieler.aktuellePalette.hatFalle) {
                if (mainMinispielSpieler.aktuellePalette.paletteNummer <= 1 || mainMinispielSpieler.aktuellePalette.paletteNummer == 12 ||mainMinispielSpieler.aktuellePalette.paletteNummer == 13) {
                    g2.drawImage(falle1, mainMinispielSpieler.minispielXPosition, mainMinispielSpieler.bildschirmY +sp.vergroesserteFliesenGroesse / 3, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
                } else if (mainMinispielSpieler.aktuellePalette.paletteNummer == 2 || mainMinispielSpieler.aktuellePalette.paletteNummer == 3) {
                    g2.drawImage(falle2, mainMinispielSpieler.minispielXPosition, mainMinispielSpieler.bildschirmY +sp.vergroesserteFliesenGroesse / 3, sp.vergroesserteFliesenGroesse, sp.vergroesserteFliesenGroesse, null);
                } else if ( mainMinispielSpieler.aktuellePalette.paletteNummer <= 9) {
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