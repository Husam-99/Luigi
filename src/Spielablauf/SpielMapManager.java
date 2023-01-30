package Spielablauf;


import Main.SpielPanel;
import spieler.Spieler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SpielMapManager {
    public final Fliese[][] mapFliesen;
    SpielablaufManager spielablaufManager;
    Graphics2D g2;
    public MapEingabeManager mapEingabeManager;
    public Stern stern;
    public Muenze muenze;

    public SpielMapManager(SpielablaufManager spielablaufManager) {
        this.spielablaufManager = spielablaufManager;
        mapEingabeManager = new MapEingabeManager(this);
        this.mapFliesen = new Fliese[25][30];
        mapLaden();
        felderReihenfolgeFestlegen();
        this.stern = new Stern(this);
        this.muenze = new Muenze(spielablaufManager.sp);

    }
    public Feld feldEinrichten(int zeile, int spalte){
        switch(zeile){
            case 19:
                if(spalte == 11){
                    return new ViolettesFeld(this,zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,0);
                }
                else{
                    return null;
                }
            case 17:
                return switch (spalte) {
                    case 5 -> new RotesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,1);
                    case 8 -> new GruenesFeld(this,zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 2);
                    case 11 -> new GruenesFeld(this,zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 3);
                    case 14 -> new OrangesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 4);
                    case 17 -> new GelbesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 5);
                    default -> null;
                };
            case 15:
                return switch (spalte) {
                    case 5 -> new GruenesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,6);
                    case 8 -> new RotesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,7);
                    case 11  -> new BlauesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,8);
                    case 17 -> new BlauesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,9);
                    default -> null;
                };
            case 13:
                return switch (spalte) {
                    case 5 -> new ViolettesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,10);
                    case 8 -> new GruenesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,11);
                    case 14 -> new GruenesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,12);
                    case 11 -> new OrangesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,13);
                    case 17 -> new RotesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,14);
                    default -> null;
                };
            case 11:
                if(spalte == 8) {
                    return new OrangesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,15);
                }else{
                    return null;
                }
            case 10:
                if(spalte == 17){
                    return new BlauesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,16);
                }else{
                    return null;
                }
            case 9:
                if(spalte == 8) {
                    return new GelbesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,17);
                }else{
                    return null;
                }

            case 7:
                return switch (spalte) {
                    case 8 -> new GruenesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 18);
                    case 19 -> new GruenesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 19);
                    case 15 -> new OrangesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 20);
                    case 17 -> new RotesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 21);
                    case 23 -> new RotesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 22);
                    case 21 -> new BlauesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 23);
                    case 25 -> new GelbesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 24);
                    default -> null;
                };
            case 6:
                return switch (spalte) {
                    case 9 -> new RotesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,25);
                    case 26 -> new RotesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse,26);
                    case 11 -> new OrangesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 27);
                    case 14 -> new ViolettesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 28);
                    default -> null;
                };
            case 4:
                return switch (spalte) {
                    case 14 -> new GruenesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 29);
                    case 26 -> new GruenesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 30);
                    case 16 -> new RotesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 31);
                    case 24 -> new BlauesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 32);
                    default -> null;
                };
            case 3:
                return switch (spalte) {
                    case 17 -> new GruenesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 33);
                    case 21 -> new GruenesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 34);
                    case 19 -> new OrangesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 35);
                    case 23 -> new RotesFeld(this, zeile * spielablaufManager.sp.vergroesserteFliesenGroesse, spalte * spielablaufManager.sp.vergroesserteFliesenGroesse, 36);
                    default -> null;
                };
            default:
                return null;
        }
    }
    
    public void mapLaden(){
        String[][] mapFelder = new String[25][30];
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("src/source/mapBilder/mapStructure.txt"));
            String line;
            int zeilenIndex = 0;
            while ((line = reader.readLine()) != null) {
                String[] zeile = line.split("\t");

                System.arraycopy(zeile, 0, mapFelder[zeilenIndex], 0, zeile.length);
                zeilenIndex++;
            }
            for(int zeile = 0; zeile < 25; zeile++){
                for(int spalte = 0; spalte < 30; spalte++){
                    try{
                        Fliese fliese = new Fliese();
                        fliese.flieseImage = ImageIO.read(new File("src/source/mapBilder/tileset" + mapFelder[zeile][spalte] + ".png"));

                        fliese.feld = feldEinrichten(zeile, spalte);

                        mapFliesen[zeile][spalte] = fliese;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void felderReihenfolgeFestlegen(){
        mapFliesen[19][11].feld.nordFeld = mapFliesen[17][11].feld;

        mapFliesen[17][11].feld.ostFeld = mapFliesen[17][14].feld;
        mapFliesen[17][11].feld.westFeld = mapFliesen[17][8].feld;
        mapFliesen[17][11].feld.nordFeld = mapFliesen[15][11].feld;

        mapFliesen[17][14].feld.ostFeld = mapFliesen[17][17].feld;
        mapFliesen[17][14].feld.westFeld = mapFliesen[17][11].feld;

        mapFliesen[17][8].feld.westFeld = mapFliesen[17][5].feld;
        mapFliesen[17][8].feld.ostFeld = mapFliesen[17][11].feld;

        mapFliesen[15][11].feld.nordFeld = mapFliesen[13][11].feld;
        mapFliesen[15][11].feld.westFeld = mapFliesen[15][8].feld;
        mapFliesen[15][11].feld.suedFeld = mapFliesen[17][11].feld;

        mapFliesen[17][17].feld.nordFeld = mapFliesen[15][17].feld;
        mapFliesen[17][17].feld.westFeld = mapFliesen[17][14].feld;

        mapFliesen[17][5].feld.nordFeld = mapFliesen[15][5].feld;
        mapFliesen[17][5].feld.ostFeld = mapFliesen[17][8].feld;

        mapFliesen[15][5].feld.nordFeld = mapFliesen[13][5].feld;
        mapFliesen[15][5].feld.ostFeld = mapFliesen[15][8].feld;
        mapFliesen[15][5].feld.suedFeld = mapFliesen[17][5].feld;

        mapFliesen[15][17].feld.nordFeld = mapFliesen[13][17].feld;
        mapFliesen[15][17].feld.suedFeld = mapFliesen[17][17].feld;

        mapFliesen[15][8].feld.westFeld = mapFliesen[15][5].feld;
        mapFliesen[15][8].feld.ostFeld = mapFliesen[15][11].feld;

        mapFliesen[13][5].feld.ostFeld = mapFliesen[13][8].feld;
        mapFliesen[13][5].feld.suedFeld = mapFliesen[15][5].feld;

        mapFliesen[13][8].feld.westFeld = mapFliesen[13][5].feld;
        mapFliesen[13][8].feld.ostFeld = mapFliesen[13][11].feld;
        mapFliesen[13][8].feld.nordFeld = mapFliesen[11][8].feld;

        mapFliesen[13][11].feld.westFeld = mapFliesen[13][8].feld;
        mapFliesen[13][11].feld.ostFeld = mapFliesen[13][14].feld;
        mapFliesen[13][11].feld.suedFeld = mapFliesen[15][11].feld;

        mapFliesen[13][14].feld.ostFeld = mapFliesen[13][17].feld;
        mapFliesen[13][14].feld.westFeld = mapFliesen[13][11].feld;

        mapFliesen[13][17].feld.westFeld = mapFliesen[13][14].feld;
        mapFliesen[13][17].feld.nordFeld = mapFliesen[10][17].feld;
        mapFliesen[13][17].feld.suedFeld = mapFliesen[15][17].feld;

        mapFliesen[11][8].feld.nordFeld = mapFliesen[9][8].feld;
        mapFliesen[11][8].feld.suedFeld = mapFliesen[13][8].feld;

        mapFliesen[10][17].feld.nordFeld = mapFliesen[7][17].feld;
        mapFliesen[10][17].feld.suedFeld = mapFliesen[13][17].feld;

        mapFliesen[9][8].feld.nordFeld = mapFliesen[7][8].feld;
        mapFliesen[9][8].feld.suedFeld = mapFliesen[11][8].feld;

        mapFliesen[7][8].feld.nordFeld = mapFliesen[6][9].feld;
        mapFliesen[7][8].feld.suedFeld = mapFliesen[9][8].feld;

        mapFliesen[7][15].feld.ostFeld = mapFliesen[7][17].feld;
        mapFliesen[7][15].feld.nordFeld = mapFliesen[6][14].feld;

        mapFliesen[7][17].feld.suedFeld = mapFliesen[10][17].feld;
        mapFliesen[7][17].feld.westFeld = mapFliesen[7][15].feld;
        mapFliesen[7][17].feld.ostFeld = mapFliesen[7][19].feld;

        mapFliesen[7][19].feld.ostFeld = mapFliesen[7][21].feld;
        mapFliesen[7][19].feld.westFeld = mapFliesen[7][17].feld;

        mapFliesen[7][21].feld.westFeld = mapFliesen[7][19].feld;
        mapFliesen[7][21].feld.ostFeld = mapFliesen[7][23].feld;

        mapFliesen[7][23].feld.westFeld = mapFliesen[7][21].feld;
        mapFliesen[7][23].feld.ostFeld = mapFliesen[7][25].feld;

        mapFliesen[7][25].feld.nordFeld = mapFliesen[6][26].feld;
        mapFliesen[7][25].feld.westFeld = mapFliesen[7][23].feld;

        mapFliesen[6][26].feld.nordFeld = mapFliesen[4][26].feld;
        mapFliesen[6][26].feld.suedFeld = mapFliesen[7][25].feld;

        mapFliesen[4][26].feld.westFeld = mapFliesen[4][24].feld;
        mapFliesen[4][26].feld.suedFeld = mapFliesen[6][26].feld;

        mapFliesen[4][24].feld.ostFeld = mapFliesen[4][26].feld;
        mapFliesen[4][24].feld.nordFeld = mapFliesen[3][23].feld;

        mapFliesen[3][23].feld.suedFeld = mapFliesen[4][24].feld;
        mapFliesen[3][23].feld.westFeld = mapFliesen[3][21].feld;

        mapFliesen[3][21].feld.ostFeld = mapFliesen[3][23].feld;
        mapFliesen[3][21].feld.westFeld = mapFliesen[3][19].feld;

        mapFliesen[3][19].feld.ostFeld = mapFliesen[3][21].feld;
        mapFliesen[3][19].feld.westFeld = mapFliesen[3][17].feld;

        mapFliesen[3][17].feld.ostFeld = mapFliesen[3][19].feld;
        mapFliesen[3][17].feld.suedFeld = mapFliesen[4][16].feld;

        mapFliesen[4][16].feld.nordFeld = mapFliesen[3][17].feld;
        mapFliesen[4][16].feld.westFeld = mapFliesen[4][14].feld;

        mapFliesen[4][14].feld.ostFeld = mapFliesen[4][16].feld;
        mapFliesen[4][14].feld.suedFeld = mapFliesen[6][14].feld;

        mapFliesen[6][14].feld.nordFeld = mapFliesen[4][14].feld;
        mapFliesen[6][14].feld.westFeld = mapFliesen[6][11].feld;
        mapFliesen[6][14].feld.suedFeld = mapFliesen[7][15].feld;

        mapFliesen[6][11].feld.ostFeld = mapFliesen[6][14].feld;
        mapFliesen[6][11].feld.westFeld = mapFliesen[6][9].feld;

        mapFliesen[6][9].feld.ostFeld = mapFliesen[6][11].feld;
        mapFliesen[6][9].feld.suedFeld = mapFliesen[7][8].feld;


    }
    public void update(){
        stern.update();
    }
    public void malen(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(spielablaufManager.sp.marioPartyFont);
        mapMalen();
    }
    private void mapMalen(){
        int weltSpalte = 0;
        int weltZeile = 0;
        int xSternPosition = 0, ySternPosition = 0;
        BufferedImage image = null;

        while(weltSpalte < spielablaufManager.sp.maxWeltSpalte && weltZeile < spielablaufManager.sp.maxWeltZeile) {
            Fliese vorlauefigeFliese = mapFliesen[weltZeile][weltSpalte];

            int weltX = weltSpalte * spielablaufManager.sp.vergroesserteFliesenGroesse;
            int weltY = weltZeile * spielablaufManager.sp.vergroesserteFliesenGroesse;
            int bildschirmX = weltX - spielablaufManager.mainSpieler.weltX + spielablaufManager.mainSpieler.bildschirmX;
            int bildschirmY = weltY - spielablaufManager.mainSpieler.weltY + spielablaufManager.mainSpieler.bildschirmY;


            g2.drawImage(vorlauefigeFliese.getFlieseImage(), bildschirmX, bildschirmY, spielablaufManager.sp.vergroesserteFliesenGroesse, spielablaufManager.sp.vergroesserteFliesenGroesse, null);
            if(mapFliesen[weltZeile][weltSpalte].feld != null){
                g2.drawImage(mapFliesen[weltZeile][weltSpalte].feld.getFeldImage(), bildschirmX, bildschirmY, spielablaufManager.sp.vergroesserteFliesenGroesse, spielablaufManager.sp.vergroesserteFliesenGroesse, null);
                if(mapFliesen[weltZeile][weltSpalte].feld.hatStern){
                    xSternPosition = bildschirmX;
                    ySternPosition = bildschirmY;
                    if(stern.spriteNum == 0){
                        image = stern.stern1;
                    }else if(stern.spriteNum == 1 || stern.spriteNum == 7){
                        image = stern.stern2;
                    }else if(stern.spriteNum == 2 || stern.spriteNum == 6){
                        image = stern.stern3;
                    }else if(stern.spriteNum == 3 || stern.spriteNum == 5){
                        image = stern.stern4;
                    }else if(stern.spriteNum == 4){
                        image = stern.stern5;
                    }
                    switch (stern.sternPostition) {
                        case "unten" -> ySternPosition += spielablaufManager.sp.vergroesserteFliesenGroesse / 2.0;
                        case "oben" -> ySternPosition -= spielablaufManager.sp.vergroesserteFliesenGroesse;
                        case "rechts" -> xSternPosition += spielablaufManager.sp.vergroesserteFliesenGroesse;
                        case "links" -> xSternPosition -= spielablaufManager.sp.vergroesserteFliesenGroesse;
                    }
                }
            }
            weltSpalte++;
            if (weltSpalte == spielablaufManager.sp.maxWeltSpalte) {
                weltSpalte = 0;
                weltZeile++;
            }
        }
        g2.drawImage(image, xSternPosition, ySternPosition, spielablaufManager.sp.vergroesserteFliesenGroesse, spielablaufManager.sp.vergroesserteFliesenGroesse, null);
    }
}
